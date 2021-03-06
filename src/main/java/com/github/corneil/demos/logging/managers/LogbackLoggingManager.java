package com.github.corneil.demos.logging.managers;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import org.slf4j.LoggerFactory;

public class LogbackLoggingManager implements LoggingManager {
    @Override
    public void enable() {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(ClassLoader.getSystemResourceAsStream("logback.xml"));
            System.out.println("LogbackLoggingManager:enable");
        } catch (Throwable x) {
            System.err.println("Cannot configure logback:" + x);
        }
    }

    @Override
    public void disable() {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(ClassLoader.getSystemResourceAsStream("logback-disable.xml"));
            System.out.println("LogbackLoggingManager:disable");
        } catch (Throwable x) {
            System.err.println("Cannot configure logback:" + x);
        }
    }
}
