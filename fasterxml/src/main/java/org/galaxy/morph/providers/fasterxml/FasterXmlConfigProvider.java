package org.galaxy.morph.providers.fasterxml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.representation.CommentedResult;
import org.galaxy.morph.representation.ObjectComments;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public abstract class FasterXmlConfigProvider implements ConfigProvider {

    private final ObjectMapper mapper;

    public FasterXmlConfigProvider(@NotNull ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "mapper is null");
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    @Override
    public void setup(int indentation) {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        char[] indentChars = new char[indentation];
        Arrays.fill(indentChars, ' ');
        String indentationString = new String(indentChars);

        printer.indentArraysWith(new DefaultIndenter(indentationString, DefaultIndenter.SYS_LF));
        printer.indentObjectsWith(new DefaultIndenter(indentationString, DefaultIndenter.SYS_LF));

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDefaultPrettyPrinter(printer);
    }

    @Override
    public @NotNull <T> CommentedResult<T> load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable {
        try (JsonParser parser = mapper.createParser(in)) {
            return new CommentedResult<>(mapper.readValue(parser, type), null);
        }
    }

    @Override
    public <T> void save(@NotNull OutputStream out, @NotNull T value, @NotNull ObjectComments comments)
            throws Throwable {
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, value);
    }
}
