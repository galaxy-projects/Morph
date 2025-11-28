package org.galaxy.morph;

import org.galaxy.morph.exceptions.ConfigException;
import org.galaxy.morph.representation.ConfigRepresentation;
import org.galaxy.morph.representation.Registry;
import org.galaxy.morph.source.Resource;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Morph {


    private final @NotNull List<@NotNull ConfigProvider> providers;
    private final @NotNull List<@NotNull ConfigSourceResolver> resolvers;

    Morph(@NotNull List<@NotNull ConfigProvider> providers,
          @NotNull List<@NotNull ConfigSourceResolver> resolvers) {
        this.providers = providers;
        this.resolvers = resolvers;
    }

    public <T> @NotNull T load(@NotNull Class<T> type) {
        ConfigRepresentation representation = Registry.get(type);
        Resource resource = Util.getAvailableResource(resolvers, providers, representation).orElse(null);

        if (resource == null) {
            try {
                T value = type.getDeclaredConstructor().newInstance();

                save(value);
                return value;
            } catch (Throwable e) {
                throw new ConfigException("Failed to instantiate new config of type " + type.getName(), e);
            }
        }

        try (InputStream in = resource.input().get()) {
            return resource.provider().load(in, type);
        } catch (Throwable e) {
            throw new ConfigException("Failed to load config" + resource.source().path(), e);
        }
    }

    public <T> void save(@NotNull T value) {
        ConfigRepresentation representation = Registry.get(value.getClass());
        Resource resource = Util.getAvailableResource(resolvers, providers, representation)
                .orElseGet(() -> Util.createResource(resolvers, providers, representation));

        try (OutputStream out = resource.output().get()) {
            resource.provider().save(out, value);
        } catch (Throwable e) {
            throw new ConfigException("Failed to save config" + resource.source().path(), e);
        }
    }

    public static @NotNull MorphBuilder builder() {
        return new MorphBuilder();
    }
}
