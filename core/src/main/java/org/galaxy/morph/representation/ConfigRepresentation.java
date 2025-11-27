package org.galaxy.morph.representation;

import org.galaxy.morph.annotations.Config;
import org.galaxy.morph.annotations.SupportedExtensions;
import org.galaxy.morph.exceptions.InvalidConfigObjectException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ConfigRepresentation {

    private final String name;
    private final Set<String> supportedExtensions;

    private ConfigRepresentation(String name,
                                 Set<String> supportedExtensions) {
        this.name = name;
        this.supportedExtensions = supportedExtensions;
    }

    public @NotNull String getName() {
        return name;
    }

    public @Nullable Set<String> getSupportedExtensions() {
        return supportedExtensions;
    }

    public static @NotNull ConfigRepresentation of(@NotNull Class<?> type) {
        Config config = type.getDeclaredAnnotation(Config.class);
        SupportedExtensions supportedExtensions = type.getDeclaredAnnotation(SupportedExtensions.class);

        if (config == null)
            throw new InvalidConfigObjectException(String.format("Missing @Config on object %s", type.getName()));
        Set<String> extensions = supportedExtensions != null ?
                new HashSet<>(Arrays.asList(supportedExtensions.value())) : null;

        return new ConfigRepresentation(config.value(), extensions);
    }
}
