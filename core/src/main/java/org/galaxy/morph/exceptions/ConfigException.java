package org.galaxy.morph.exceptions;

public class ConfigException extends RuntimeException {

    private static final long serialVersionUID = 5131881107190869724L;

    public ConfigException() {
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }
}
