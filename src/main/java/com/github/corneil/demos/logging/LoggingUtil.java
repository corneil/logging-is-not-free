package com.github.corneil.demos.logging;

import java.util.Arrays;

public class LoggingUtil {
    public static void disable() {
        for (String cls : Arrays.asList("com.github.corneil.demos.logging.Log4JLoggingManager",
                "com.github.corneil.demos.logging.JULLoggingManager",
                "com.github.corneil.demos.logging.LogbackLoggingManager")) {
            try {
                LoggingManager manager = (LoggingManager) Class.forName(cls).newInstance();
                manager.disable();
            } catch (Throwable x) {
                // Ignore
            }
        }
    }

    public static void enable() {
        for (String cls : Arrays.asList("com.github.corneil.demos.logging.Log4JLoggingManager",
                "com.github.corneil.demos.logging.JULLoggingManager",
                "com.github.corneil.demos.logging.LogbackLoggingManager")) {
            try {
                LoggingManager manager = (LoggingManager) Class.forName(cls).newInstance();
                manager.enable();
            } catch (Throwable x) {
                // Ignore
            }
        }
    }
}
