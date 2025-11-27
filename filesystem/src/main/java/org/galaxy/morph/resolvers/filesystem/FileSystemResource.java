package org.galaxy.morph.resolvers.filesystem;

import org.galaxy.morph.ConfigProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.io.File;
import java.util.List;

public class FileSystemResource {

    private final @NotNull File file;
    private final @UnknownNullability ConfigProvider provider;

    public FileSystemResource(@NotNull File file, @UnknownNullability ConfigProvider provider) {
        this.file = file;
        this.provider = provider;
    }

    public @NotNull File file() {
        return file;
    }

    public @UnknownNullability ConfigProvider provider() {
        return provider;
    }

    public static @NotNull FileSystemResource fromFile(@NotNull List<ConfigProvider> providers, @NotNull File file) {
        String name = file.getName();
        int index = name.lastIndexOf('.');
        String extension = index == -1 ? "" : name.substring(index + 1);

        ConfigProvider provider = providers.stream()
                .filter(p -> p.supportsExtension(extension))
                .findFirst()
                .orElse(null);

        return new FileSystemResource(file, provider);
    }
}
