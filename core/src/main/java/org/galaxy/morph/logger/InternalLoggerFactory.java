package org.galaxy.morph.logger;

import org.galaxy.morph.logger.impl.JULLoggerProvider;
import org.galaxy.morph.logger.impl.LoggerProvider;
import org.galaxy.morph.logger.impl.Slf4jProvider;
import org.jetbrains.annotations.NotNull;

public class InternalLoggerFactory {

    private static final LoggerProvider LOGGER_PROVIDER;

    public static @NotNull InternalLogger getLogger(Class<?> type) {
        return LOGGER_PROVIDER.getLogger(type);
    }

    static {
        LoggerProvider provider;
        try {
            Class.forName("org.slf4j.Logger");
            provider = new Slf4jProvider();
        } catch (ClassNotFoundException e) {
            provider = new JULLoggerProvider();
        }
        LOGGER_PROVIDER = provider;
        getLogger(InternalLoggerFactory.class).trace("Use logging system {}", provider.getName());
    }
}
