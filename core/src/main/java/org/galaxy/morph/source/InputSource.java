package org.galaxy.morph.source;

import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.ConfigSourceResolver;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.function.Supplier;

public class InputSource {

    private final @NotNull Source source;
    private final @NotNull ConfigSourceResolver resolver;
    private final @NotNull ConfigProvider provider;

    private final @NotNull Supplier<InputStream> input;

    public InputSource(@NotNull Source source,
                       @NotNull ConfigSourceResolver resolver,
                       @NotNull ConfigProvider provider,
                       @NotNull Supplier<InputStream> input) {
        this.source = source;
        this.resolver = resolver;
        this.provider = provider;
        this.input = input;
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

}
