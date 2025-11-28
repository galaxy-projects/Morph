package org.galaxy.morph.source;

import org.jetbrains.annotations.NotNull;

public class Source {

    private final @NotNull String path;
    private final @NotNull String extension;

    public Source(@NotNull String path, @NotNull String extension) {
        this.path = path;
        this.extension = extension;
    }

    public @NotNull String path() {
        return path;
    }

    public @NotNull String extension() {
        return extension;
    }
}
