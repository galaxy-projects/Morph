package org.galaxy.morph.source;

import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;

public class ExtensionConfigProvider {

    private final @NotNull String extension;
    private final @NotNull ConfigProvider provider;

    public ExtensionConfigProvider(@NotNull String extension, @NotNull ConfigProvider provider) {
        this.extension = extension;
        this.provider = provider;
    }

    public @NotNull String extension() {
        return extension;
    }

    public @NotNull ConfigProvider provider() {
        return provider;
    }
}
