package com.github.corneil.demos.logging;

import com.github.corneil.demos.logging.managers.LoggingManager;

import java.util.Arrays;

public class LoggingUtil {
    public static void disable() {
        for (String cls : Arrays.asList("com.github.corneil.demos.logging.managers.Log4JLoggingManager",
                "com.github.corneil.demos.logging.managers.JULLoggingManager",
                "com.github.corneil.demos.logging.managers.LogbackLoggingManager")) {
            try {
                LoggingManager manager = (LoggingManager) Class.forName(cls).newInstance();
                manager.disable();
            } catch (Throwable x) {
                // Ignore
            }
        }
    }

    public static void enable() {
        for (String cls : Arrays.asList("com.github.corneil.demos.logging.managers.Log4JLoggingManager",
                "com.github.corneil.demos.logging.managers.JULLoggingManager",
                "com.github.corneil.demos.logging.managers.LogbackLoggingManager")) {
            try {
                LoggingManager manager = (LoggingManager) Class.forName(cls).newInstance();
                manager.enable();
            } catch (Throwable x) {
                // Ignore
            }
        }
    }
}
