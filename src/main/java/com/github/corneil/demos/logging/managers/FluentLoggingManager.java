package com.github.corneil.demos.logging.managers;

import java.io.IOException;

public class FluentLoggingManager implements LoggingManager {
    private static final FluentLoggingManager manager = new FluentLoggingManager();

    @Override
    public void enable() throws IOException {
        manager.enable();
    }

    @Override
    public void disable() {
        manager.disable();
    }
}
