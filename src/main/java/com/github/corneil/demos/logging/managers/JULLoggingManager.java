package com.github.corneil.demos.logging.managers;

import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JULLoggingManager implements LoggingManager {
    public void enable() {
        final LogManager logManager = LogManager.getLogManager();
        logManager.reset();
        try {
            setLevels(logManager, Level.ALL);
            logManager.readConfiguration(ClassLoader.getSystemResourceAsStream("logging.properties"));
            System.out.println("JULLoggingManager:enable");
        } catch (Throwable x) {
            System.err.println("Cannot read logging.properties:" + x);
        }
    }

    private void setLevels(LogManager logManager, Level level) {
        Enumeration<String> names = logManager.getLoggerNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Logger logger = logManager.getLogger(name);
            logger.setLevel(level);
            for (Handler handler : logger.getHandlers()) {
                handler.setLevel(level);
            }
        }
    }

    public void disable() {
        final LogManager logManager = LogManager.getLogManager();
        logManager.reset();
        try {
            logManager.readConfiguration(ClassLoader.getSystemResourceAsStream("logging-disable.properties"));
            setLevels(logManager, Level.SEVERE);
            setLevels(logManager, Level.OFF);
            System.out.println("JULLoggingManager:disable");
        } catch (Throwable x) {
            System.err.println("Cannot read logging.properties:" + x);
        }
    }
}
