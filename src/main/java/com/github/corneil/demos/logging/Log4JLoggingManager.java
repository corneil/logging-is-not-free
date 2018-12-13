package com.github.corneil.demos.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4JLoggingManager implements LoggingManager {
    @Override
    public void enable() {
        try {
            final Logger rootLogger = Logger.getRootLogger();
            rootLogger.getLoggerRepository().resetConfiguration();
            PropertyConfigurator.configure(ClassLoader.getSystemResourceAsStream("log4j.properties"));
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
        } catch (Throwable x) {
            System.err.println("Cannot configure log4j:" + x);
        }

    }
}
