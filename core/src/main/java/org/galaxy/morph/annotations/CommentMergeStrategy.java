package org.galaxy.morph.annotations;

public enum CommentMergeStrategy {

    /**
     * Merge default comments with comments read from file
     */
    MERGE,
    /**
     * Keep comments from files
     */
    KEEP,
    /**
     * Write only default comments
     */
    REPLACE

}
