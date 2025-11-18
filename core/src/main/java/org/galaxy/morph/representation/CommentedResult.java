package org.galaxy.morph.representation;

public class CommentedResult<T> {

    private final T value;
    private final ObjectComments comments;

    public CommentedResult(T value, ObjectComments comments) {
        this.value = value;
        this.comments = comments;
    }

    public T getValue() {
        return value;
    }

    public ObjectComments getComments() {
        return comments;
    }
}
