package org.galaxy.morph.source;

import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.ConfigSourceResolver;
import org.galaxy.morph.exceptions.ConfigException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.function.Supplier;

public class Resource {

    private final @NotNull Source source;
    private final @NotNull ConfigSourceResolver resolver;
    private final @NotNull ConfigProvider provider;

    private final @NotNull Supplier<InputStream> input;
    private final @NotNull Supplier<OutputStream> output;

    public Resource(@NotNull Source source,
                    @NotNull ConfigSourceResolver resolver,
                    @NotNull ConfigProvider provider,
                    @NotNull Supplier<InputStream> input,
                    @NotNull Supplier<OutputStream> output) {
        this.source = source;
        this.resolver = resolver;
        this.provider = provider;
        this.input = input;
        this.output = output;
    }

    public @NotNull Source source() {
        return source;
    }

    public @NotNull ConfigSourceResolver resolver() {
        return resolver;
    }

    public @NotNull ConfigProvider provider() {
        return provider;
    }

    public @NotNull Supplier<InputStream> input() {
        return input;
    }

    public @NotNull Supplier<OutputStream> output() {
        return output;
    }

    public static Resource fromFile(@NotNull File file,
                                    @NotNull ConfigSourceResolver resolver,
                                    @NotNull ConfigProvider provider) {
        Source source = new Source(file.getPath(), extensionOf(file));
        Supplier<InputStream> input = () -> {
            try {
                return Files.newInputStream(file.toPath());
            } catch (IOException e) {
                throw new ConfigException(e);
            }
        };
        Supplier<OutputStream> output = () -> {
            try {
                return Files.newOutputStream(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        return new Resource(source, resolver, provider, input, output);
    }

    private static String extensionOf(@NotNull File file) {
        String name = file.getName();
        int index = name.lastIndexOf('.');

        if (index == -1) return "";
        return name.substring(index + 1);
    }
}
