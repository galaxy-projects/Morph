package org.galaxy.morph;

import org.galaxy.morph.annotations.CommentMergeStrategy;
import org.galaxy.morph.logger.InternalLogger;
import org.galaxy.morph.logger.InternalLoggerFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

public class MorphBuilder {

    private static final InternalLogger LOGGER = InternalLoggerFactory.getLogger(Morph.class);

    private @Nullable Path workingDirectory;
    private @Nullable CommentMergeStrategy defaultCommentStrategy;
    private int indentation;
    private @Nullable List<ConfigProvider> providers;
    private @Nullable List<ConfigSourceResolver> resolvers;

    MorphBuilder() {
        workingDirectory = Paths.get(".");
        defaultCommentStrategy = CommentMergeStrategy.MERGE;
        indentation = 2;
        providers = null;
        resolvers = null;
    }

    public @NotNull MorphBuilder workingDirectory(@NotNull Path workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }

    public @NotNull MorphBuilder defaultCommentStrategy(
            @NotNull CommentMergeStrategy defaultCommentStrategy) {
        this.defaultCommentStrategy = defaultCommentStrategy;
        return this;
    }

    public @NotNull MorphBuilder indentation(int indentation) {
        this.indentation = indentation;
        return this;
    }

    public @NotNull MorphBuilder providers(@NotNull List<@NotNull ConfigProvider> providers) {
        this.providers = providers;
        return this;
    }

    public @NotNull MorphBuilder resolvers(@NotNull List<@NotNull ConfigSourceResolver> resolvers) {
        this.resolvers = resolvers;
        return this;
    }

    public @NotNull Morph build() {
        Objects.requireNonNull(workingDirectory, "workingDirectory cannot be null");
        Objects.requireNonNull(defaultCommentStrategy, "defaultCommentStrategy cannot be null");
        if (indentation <= 0)
            throw new IllegalArgumentException("Indentation cannot be negative");

        if (providers == null) {
            providers = loadServices(ConfigProvider.class);
            LOGGER.debug("{} Config providers found", providers.size());
        } else
            providers.forEach(provider -> Objects.requireNonNull(provider, "provider cannot be null"));
        if (resolvers == null) {
            resolvers = loadServices(ConfigSourceResolver.class);
            LOGGER.debug("{} Config source resolvers found", resolvers.size());
        } else
            resolvers.forEach(resolver -> Objects.requireNonNull(resolver, "resolver cannot be null"));

        if (resolvers.isEmpty())
            throw new IllegalArgumentException("No Config source resolvers found");

        resolvers.forEach(resolver -> resolver.setup(workingDirectory));

        return new Morph(workingDirectory, defaultCommentStrategy, indentation, providers, resolvers);
    }

    private static <T> List<T> loadServices(Class<T> type) {
        List<T> services = new ArrayList<>();

        ServiceLoader.load(type).forEach(services::add);
        return services;
    }
}
