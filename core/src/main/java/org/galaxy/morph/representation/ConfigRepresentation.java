package org.galaxy.morph.representation;

import org.galaxy.morph.annotations.CommentMergeStrategy;
import org.galaxy.morph.annotations.CommentStrategy;
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
    private final Comments comments;
    private final Set<String> supportedExtensions;
    private final CommentMergeStrategy classMergeStrategy;

    private ConfigRepresentation(String name,
                                 Comments comments,
                                 Set<String> supportedExtensions,
                                 CommentMergeStrategy classMergeStrategy) {
        this.name = name;
        this.comments = comments;
        this.supportedExtensions = supportedExtensions;
        this.classMergeStrategy = classMergeStrategy;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Comments getComments() {
        return comments;
    }

    public @Nullable Set<String> getSupportedExtensions() {
        return supportedExtensions;
    }

    public @Nullable CommentMergeStrategy getClassMergeStrategy() {
        return classMergeStrategy;
    }

    public static @NotNull ConfigRepresentation of(@NotNull Class<?> type) {
        Config config = type.getDeclaredAnnotation(Config.class);
        SupportedExtensions supportedExtensions = type.getDeclaredAnnotation(SupportedExtensions.class);
        CommentStrategy commentStrategy = type.getDeclaredAnnotation(CommentStrategy.class);

        if (config == null)
            throw new InvalidConfigObjectException(String.format("Missing @Config on object %s", type.getName()));
        Comments comments = Comments.of(type);
        Set<String> extensions = supportedExtensions != null ?
                new HashSet<>(Arrays.asList(supportedExtensions.value())) : null;
        CommentMergeStrategy classMergeStrategy = commentStrategy != null ? commentStrategy.value() : null;

        return new ConfigRepresentation(config.value(), comments, extensions, classMergeStrategy);
    }
}
