package com.github.corneil.demos.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class LogbackLoggingManager implements LoggingManager {
    @Override
    public void enable() {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            lc.getLogger(ExampleLogging.class).setLevel(Level.INFO);
        } catch (Throwable x) {
            System.err.println("Cannot configure logback:" + x);
        }
    }

    @Override
    public void disable() {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            lc.getLogger(ExampleLogging.class).setLevel(Level.ERROR);
        } catch (Throwable x) {
            System.err.println("Cannot configure logback:" + x);
        }
    }
}
