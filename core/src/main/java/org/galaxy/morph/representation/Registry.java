package org.galaxy.morph.representation;

import org.jetbrains.annotations.NotNull;

public class Registry {

    private static final ClassValue<ConfigRepresentation> CACHE = new ClassValue<ConfigRepresentation>() {
        @Override
        protected ConfigRepresentation computeValue(@NotNull Class<?> type) {
            return ConfigRepresentation.of(type);
        }
    };

    public static @NotNull ConfigRepresentation get(@NotNull Class<?> type) {
        return CACHE.get(type);
    }

}
