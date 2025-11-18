package org.galaxy.morph.exceptions;

public class ResolverNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7466619397198655568L;

    public ResolverNotFoundException() {
    }

    public ResolverNotFoundException(String message) {
        super(message);
    }

    public ResolverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResolverNotFoundException(Throwable cause) {
        super(cause);
    }
}
