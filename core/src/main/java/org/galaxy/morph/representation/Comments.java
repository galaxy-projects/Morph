package org.galaxy.morph.representation;

import org.galaxy.morph.annotations.CommentMergeStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.lang.reflect.Field;
import java.util.*;

public class Comments {

    private static final Comments EMPTY = new Comments(Collections.emptyMap());

    private final Map<String, CommentBlock> commentsByField;

    private Comments(@NotNull Map<String, CommentBlock> commentsByField) {
        this.commentsByField = commentsByField;
    }

    public static @NotNull ObjectComments merge(@UnknownNullability ObjectComments fileComments,
                                                @NotNull Comments defaultComments,
                                                @NotNull CommentMergeStrategy defaultCommentStrategy) {
        if (fileComments == null) return ObjectComments.from(defaultComments);
        Map<String, List<String>> mergedCommentsMap = new HashMap<>();

        defaultComments.commentsByField.forEach((key, defaultCommentBlock) -> {
            CommentMergeStrategy strategy = defaultCommentBlock.getStrategy() != null ?
                    defaultCommentBlock.getStrategy() : defaultCommentStrategy;
            List<String> lines;

            switch (strategy) {
                case REPLACE:
                    mergedCommentsMap.put(key, defaultCommentBlock.getLines());
                    break;
                case KEEP:
                    lines = fileComments.get(key);

                    if (lines != null) mergedCommentsMap.put(key, lines);
                    break;
                case MERGE:
                    lines = mergeLines(defaultCommentBlock.getLines(), fileComments.get(key));

                    if (lines != null) mergedCommentsMap.put(key, lines);
                    break;
            }
        });
        return new ObjectComments(mergedCommentsMap);
    }

    public @Nullable CommentBlock get(@NotNull String field) {
        return commentsByField.get(field);
    }

    public Map<String, CommentBlock> getAll() {
        return commentsByField;
    }

    private static List<String> mergeLines(@NotNull List<String> defaultLines, @Nullable List<String> fileLines) {
        if (fileLines == null) return defaultLines;
        List<String> mergedLines = new ArrayList<>(fileLines);

        for (String line : defaultLines) {
            if (mergedLines.stream().noneMatch(l -> l.equalsIgnoreCase(line)))
                mergedLines.add(line);
        }
        return mergedLines;
    }

    public static @NotNull Comments of(@NotNull Class<?> type) {
        Map<String, CommentBlock> commentsByField = new HashMap<>();
        CommentBlock block;

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            block = CommentBlock.of(field);

            if (block != null)
                commentsByField.put(field.getName(), block);
        }
        if (commentsByField.isEmpty())
            return EMPTY;
        return new Comments(commentsByField);
    }
}
