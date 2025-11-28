package org.galaxy.morph.providers.tools;

import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;
import tools.jackson.core.JsonParser;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.MapperBuilder;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public abstract class ToolsConfigProvider implements ConfigProvider {

    private final MapperBuilder<?, ?> builder;
    private ObjectMapper mapper;

    public ToolsConfigProvider(@NotNull MapperBuilder<?, ?> builder) {
        this.builder = Objects.requireNonNull(builder, "builder is null");
        builder.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        builder.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        builder.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void setup(int indentation) {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        char[] indentChars = new char[indentation];
        Arrays.fill(indentChars, ' ');
        String indentationString = new String(indentChars);

        printer.indentArraysWith(new DefaultIndenter(indentationString, DefaultIndenter.SYS_LF));
        printer.indentObjectsWith(new DefaultIndenter(indentationString, DefaultIndenter.SYS_LF));

        builder.defaultPrettyPrinter(printer);
        mapper = Objects.requireNonNull(builder.build(), "builder returns is null");
    }

    @Override
    public @NotNull <T> T load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable {
        try (JsonParser parser = mapper.createParser(in)) {
            return mapper.readValue(parser, type);
        }
    }

    @Override
    public <T> void save(@NotNull OutputStream out, @NotNull T value) throws Throwable {
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, value);
    }
}
