package org.galaxy.morph;

import org.galaxy.morph.representation.CommentedResult;
import org.galaxy.morph.representation.ObjectComments;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

public interface ConfigProvider {

    @NotNull String getDefaultExtension();

    boolean supportsExtension(@NotNull String extension);

    void setup(int indentation);

    <T> @NotNull CommentedResult<T> load(@NotNull InputStream in, @NotNull Class<T> type) throws Throwable;

    <T> void save(@NotNull OutputStream out, @NotNull T value, @NotNull ObjectComments comments)
            throws Throwable;

}
