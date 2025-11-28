package org.galaxy.morph.logger.impl;

import org.galaxy.morph.logger.InternalLogger;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JULLoggerProvider implements LoggerProvider {

    @Override
    public @NotNull String getName() {
        return "Java Logging";
    }

    @Override
    public @NotNull InternalLogger getLogger(Class<?> type) {
        return new JULLogger(Logger.getLogger(type.getName()));
    }

    static class JULLogger implements InternalLogger {

        private final Logger logger;

        public JULLogger(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void trace(String message, Object... args) {
            logger.log(Level.CONFIG, message, args);
        }

        @Override
        public void debug(String message, Object... args) {
            logger.log(Level.FINE, message, args);
        }

        @Override
        public void info(String message, Object... args) {
            logger.log(Level.INFO, message, args);
        }

        @Override
        public void warn(String message, Object... args) {
            logger.log(Level.WARNING, message, args);
        }

        @Override
        public void error(String message, Object... args) {
            logger.log(Level.SEVERE, message, args);
        }

        @Override
        public void fatal(String message, Object... args) {
            logger.log(Level.SEVERE, message, args);
        }
    }
}
