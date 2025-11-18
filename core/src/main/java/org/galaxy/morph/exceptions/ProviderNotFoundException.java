package org.galaxy.morph.exceptions;

public class ProviderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7031400352189655321L;

    public ProviderNotFoundException() {
    }

    public ProviderNotFoundException(String message) {
        super(message);
    }

    public ProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderNotFoundException(Throwable cause) {
        super(cause);
    }
}
