package org.galaxy.morph;

import org.galaxy.morph.source.Resource;
import org.galaxy.morph.source.Source;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface ConfigSourceResolver {

    boolean canCreate();

    void setup(@NotNull Path workingDirectory);

    @NotNull Stream<@NotNull Resource> resolve(@NotNull List<ConfigProvider> providers, @NotNull String name);

    @NotNull Resource createSource(@NotNull Source source, @NotNull ConfigProvider provider);

}
