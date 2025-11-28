package org.galaxy.morph.exceptions;

public class InvalidConfigObjectException extends RuntimeException {

    private static final long serialVersionUID = -3029204206054640903L;

    public InvalidConfigObjectException() {}

    public InvalidConfigObjectException(String message) {
        super(message);
    }

    public InvalidConfigObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConfigObjectException(Throwable cause) {
        super(cause);
    }
}
