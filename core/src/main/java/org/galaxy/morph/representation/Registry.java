package org.galaxy.morph.representation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class Registry {

    private static final ClassValue<ConfigRepresentation> CACHE = new ClassValue<ConfigRepresentation>() {
        @Override
        protected ConfigRepresentation computeValue(@NotNull Class<?> type) {
            return ConfigRepresentation.of(type);
        }
    };
    private static final ClassValue<Map<Object, ObjectComments>> INSTANCE_CACHE = new ClassValue<Map<Object, ObjectComments>>() {
        @Override
        protected Map<Object, ObjectComments> computeValue(@NotNull Class<?> type) {
            return Collections.synchronizedMap(new WeakHashMap<>());
        }
    };

    public static @NotNull ConfigRepresentation get(@NotNull Class<?> type) {
        return CACHE.get(type);
    }

    public static void setInstance(@NotNull Object instance, @Nullable ObjectComments comments) {
        if (comments != null)
            INSTANCE_CACHE.get(instance.getClass()).put(instance, comments);
    }

    public static @Nullable ObjectComments getInstance(@NotNull Object instance) {
        return INSTANCE_CACHE.get(instance.getClass()).get(instance);
    }
}
