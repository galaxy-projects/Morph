package org.galaxy.morph;

import org.galaxy.morph.representation.ConfigRepresentation;
import org.galaxy.morph.source.InputSource;
import org.galaxy.morph.source.Source;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Optional;

public interface ConfigSourceResolver {

    boolean canCreate();

    void setup(@NotNull Path workingDirectory);

    Optional<InputSource> resolve(@NotNull String name);

    @NotNull InputSource createSource(@NotNull ConfigRepresentation representation);

    @NotNull OutputStream createOutput(@NotNull Source source);

}
