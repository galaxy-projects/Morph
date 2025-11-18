package org.galaxy.morph.source;

import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.util.function.Supplier;

public class OutputSource {

    private final @NotNull Source source;
    private final @NotNull ConfigProvider provider;
    private final @NotNull Supplier<OutputStream> output;

    public OutputSource(@NotNull Source source, @NotNull ConfigProvider provider,
                        @NotNull Supplier<OutputStream> output) {
        this.source = source;
        this.provider = provider;
        this.output = output;
    }

    public @NotNull Source source() {
        return source;
    }

    public @NotNull ConfigProvider provider() {
        return provider;
    }

    public @NotNull Supplier<OutputStream> output() {
        return output;
    }
}
