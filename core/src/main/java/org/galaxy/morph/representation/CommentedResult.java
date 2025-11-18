package org.galaxy.morph.representation;

import org.jetbrains.annotations.Nullable;

public class CommentedResult<T> {

    private final T value;
    private final ObjectComments comments;

    public CommentedResult(@Nullable T value, @Nullable ObjectComments comments) {
        this.value = value;
        this.comments = comments;
    }

    public T getValue() {
        return value;
    }

    public @Nullable ObjectComments getComments() {
        return comments;
    }
}
