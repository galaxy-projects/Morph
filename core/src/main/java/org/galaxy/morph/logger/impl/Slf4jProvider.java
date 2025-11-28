package org.galaxy.morph.logger.impl;

import org.galaxy.morph.logger.InternalLogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jProvider implements LoggerProvider {

    @Override
    public @NotNull String getName() {
        return "Slf4j Logger";
    }

    @Override
    public @NotNull InternalLogger getLogger(Class<?> type) {
        return new Slf4jLogger(LoggerFactory.getLogger(type));
    }

    static class Slf4jLogger implements InternalLogger {

        private final Logger logger;

        public Slf4jLogger(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void trace(String message, Object... args) {
            logger.trace(message, args);
        }

        @Override
        public void debug(String message, Object... args) {
            logger.debug(message, args);
        }

        @Override
        public void info(String message, Object... args) {
            logger.info(message, args);
        }

        @Override
        public void warn(String message, Object... args) {
            logger.warn(message, args);
        }

        @Override
        public void error(String message, Object... args) {
            logger.error(message, args);
        }

        @Override
        public void fatal(String message, Object... args) {
            logger.error(message, args);
        }
    }
}
