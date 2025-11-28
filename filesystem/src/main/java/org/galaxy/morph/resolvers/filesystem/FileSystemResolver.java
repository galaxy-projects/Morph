package org.galaxy.morph.resolvers.filesystem;

import com.google.auto.service.AutoService;
import org.galaxy.morph.ConfigProvider;
import org.galaxy.morph.ConfigSourceResolver;
import org.galaxy.morph.exceptions.ConfigException;
import org.galaxy.morph.source.Resource;
import org.galaxy.morph.source.Source;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@AutoService(ConfigSourceResolver.class)
public class FileSystemResolver implements ConfigSourceResolver {

    private Path workingDirectory;

    @Override
    public boolean canCreate() {
        return true;
    }

    @Override
    public void setup(@NotNull Path workingDirectory) {
        this.workingDirectory = Objects.requireNonNull(workingDirectory, "workingDirectory is null");
    }

    @Override
    public @NotNull Stream<@NotNull Resource> resolve(@NotNull List<ConfigProvider> providers, @NotNull String name) {
        File directory = workingDirectory.toFile();
        File[] files = directory.listFiles(file -> name.equalsIgnoreCase(nameOf(file)));

        if (files == null)
            return Stream.empty();
        return Arrays.stream(files)
                .map(file -> FileSystemResource.fromFile(providers, file))
                .filter(resource -> Objects.nonNull(resource.provider()))
                .map(resource -> Resource.fromFile(resource.file(), this, resource.provider()));
    }

    @Override
    public @NotNull Resource createSource(@NotNull Source source, @NotNull ConfigProvider provider) {
        File directory = workingDirectory.toFile();
        File target = new File(directory, source.path() + "." + source.extension());

        if (!target.exists()) {
            try {
                if (!target.getParentFile().exists())
                    Files.createDirectories(target.getParentFile().toPath());
                Files.createFile(target.toPath());
            } catch (IOException e) {
                throw new ConfigException("Failed to create file " + target.getAbsolutePath(), e);
            }
        }
        return Resource.fromFile(target, this, provider);
    }

    private String nameOf(File file) {
        String name = file.getName();
        int index = name.lastIndexOf('.');

        if (index == -1) return name;
        return name.substring(0, index);
    }
}
