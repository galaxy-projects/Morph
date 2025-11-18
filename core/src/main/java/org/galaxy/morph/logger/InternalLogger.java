package org.galaxy.morph.logger;

public interface InternalLogger {

    void trace(String message, Object... args);

    void debug(String message, Object... args);

    void info(String message, Object... args);

    void warn(String message, Object... args);

    void error(String message, Object... args);

    void fatal(String message, Object... args);

}
