package org.galaxy.morph.logger.impl;

import org.galaxy.morph.logger.InternalLogger;
import org.jetbrains.annotations.NotNull;

public interface LoggerProvider {

    @NotNull String getName();

    @NotNull InternalLogger getLogger(Class<?> type);

}
