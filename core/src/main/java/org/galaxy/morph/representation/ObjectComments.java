package org.galaxy.morph.representation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectComments {

    private final Map<String, List<String>> commentsByField;

    public ObjectComments(Map<String, List<String>> commentsByField) {
        this.commentsByField = commentsByField;
    }

    public @Nullable List<String> get(@NotNull String field) {
        return commentsByField.get(field);
    }

    public @NotNull List<String> getOrDefault(@NotNull String key, @NotNull List<String> def) {
        return commentsByField.getOrDefault(key, def);
    }

    public static @NotNull ObjectComments from(@NotNull Comments comments) {
        Map<String, List<String>> commentsByField = new HashMap<>();

        comments.getAll().forEach((key, block) -> commentsByField.put(key, block.getLines()));
        return new ObjectComments(commentsByField);
    }
}
