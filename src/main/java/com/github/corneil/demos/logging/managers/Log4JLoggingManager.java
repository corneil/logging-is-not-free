package com.github.corneil.demos.logging.managers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4JLoggingManager implements LoggingManager {
    @Override
    public void enable() {
        try {
            final Logger rootLogger = Logger.getRootLogger();
            rootLogger.getLoggerRepository().resetConfiguration();
            rootLogger.setLevel(Level.ALL);
            PropertyConfigurator.configure(ClassLoader.getSystemResourceAsStream("log4j.properties"));
            System.out.println("Log4JLoggingManager:enable");
        } catch (Throwable x) {
            System.err.println("Cannot configure log4j:" + x);
        }
    }

    @Override
    public void disable() {
        try {
            final Logger rootLogger = Logger.getRootLogger();
            rootLogger.getLoggerRepository().resetConfiguration();
            PropertyConfigurator.configure(ClassLoader.getSystemResourceAsStream("log4j-disable.properties"));
            rootLogger.setLevel(Level.OFF);
            System.out.println("Log4JLoggingManager:disable");
        } catch (Throwable x) {
            System.err.println("Cannot configure log4j:" + x);
        }

    }
}
