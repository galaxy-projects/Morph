package org.galaxy.morph;

import org.galaxy.morph.annotations.CommentMergeStrategy;
import org.galaxy.morph.exceptions.ConfigException;
import org.galaxy.morph.exceptions.ProviderNotFoundException;
import org.galaxy.morph.representation.*;
import org.galaxy.morph.source.InputSource;
import org.galaxy.morph.source.OutputSource;
import org.galaxy.morph.source.Source;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Stream;

public class Morph {


    private final @NotNull CommentMergeStrategy defaultCommentStrategy;
    private final @NotNull List<@NotNull ConfigProvider> providers;
    private final @NotNull List<@NotNull ConfigSourceResolver> resolvers;

    Morph(@NotNull CommentMergeStrategy defaultCommentStrategy,
          @NotNull List<@NotNull ConfigProvider> providers,
          @NotNull List<@NotNull ConfigSourceResolver> resolvers) {
        this.defaultCommentStrategy = defaultCommentStrategy;
        this.providers = providers;
        this.resolvers = resolvers;
    }

    public <T> @NotNull T load(@NotNull Class<T> type) {
        ConfigRepresentation representation = Registry.get(type);
        Stream<InputSource> availableSources = Util.getAvailableSources(resolvers, representation);
        InputSource inputSource = availableSources.findFirst()
                .orElseGet(() -> Util.createSource(resolvers, representation));
        Source source = inputSource.source();

        ConfigProvider provider = providers.stream()
                .filter(p -> p.supportsExtension(source.extension()))
                .findFirst()
                .orElseThrow(
                        () -> new ProviderNotFoundException("No provider found for extension " + source.extension()));

        try (InputStream in = inputSource.input().get()) {
            CommentedResult<T> result = provider.load(in, type);

            Registry.setInstance(result.getValue(), result.getComments());
            return result.getValue();
        } catch (Throwable e) {
            throw new ConfigException("Failed to load config" + source.path(), e);
        }
    }

    public <T> void save(@NotNull T value) {
        ConfigRepresentation representation = Registry.get(value.getClass());
        ObjectComments fileComments = Registry.getInstance(value);
        Stream<InputSource> availableSources = Util.getAvailableSources(resolvers, representation);

        OutputSource source = availableSources.findFirst()
                .map(Util::createOutputFromInput)
                .orElseGet(() -> Util.createOutputSource(resolvers, providers, representation));
        CommentMergeStrategy mergeStrategy = representation.getClassMergeStrategy() != null ?
                representation.getClassMergeStrategy() : defaultCommentStrategy;


        ObjectComments mergedComments = Comments.merge(fileComments, representation.getComments(), mergeStrategy);

        try (OutputStream out = source.output().get()) {
            source.provider().save(out, value, mergedComments);
        } catch (Throwable e) {
            throw new ConfigException("Failed to save config" + source.source().path(), e);
        }
    }

    public static @NotNull MorphBuilder builder() {
        return new MorphBuilder();
    }
}
