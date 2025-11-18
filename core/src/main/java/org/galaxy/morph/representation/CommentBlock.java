package org.galaxy.morph.representation;

import org.galaxy.morph.annotations.Comment;
import org.galaxy.morph.annotations.CommentMergeStrategy;
import org.galaxy.morph.annotations.CommentStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class CommentBlock {

    private final CommentMergeStrategy strategy;
    private final List<String> lines;

    public CommentBlock(CommentMergeStrategy strategy, List<String> lines) {
        this.strategy = strategy;
        this.lines = lines;
    }

    public @Nullable CommentMergeStrategy getStrategy() {
        return strategy;
    }

    public @NotNull List<String> getLines() {
        return lines;
    }

    public static CommentBlock of(Field field) {
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))
            return null;
        Comment comment = field.getDeclaredAnnotation(Comment.class);
        CommentStrategy commentStrategy = field.getDeclaredAnnotation(CommentStrategy.class);

        if (comment == null) return null;
        CommentMergeStrategy strategy = commentStrategy != null ? commentStrategy.value() : null;

        return new CommentBlock(strategy, Arrays.asList(comment.value()));
    }
}
