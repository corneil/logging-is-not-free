package com.github.corneil.demos.logging;

import java.util.logging.LogManager;

public class JULLoggingManager implements LoggingManager {
    public void enable() {
        final LogManager logManager = LogManager.getLogManager();
        logManager.reset();
        try {
            logManager.readConfiguration(ClassLoader.getSystemResourceAsStream("logging.properties"));
        } catch (Throwable x) {
            System.err.println("Cannot read logging.properties:" + x);
        }
    }

    public void disable() {
        final LogManager logManager = LogManager.getLogManager();
        logManager.reset();
        try {
            logManager.readConfiguration(ClassLoader.getSystemResourceAsStream("logging-disable.properties"));
        } catch (Throwable x) {
            System.err.println("Cannot read logging.properties:" + x);
        }
    }
}
