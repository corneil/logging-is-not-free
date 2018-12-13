package com.github.corneil.demos.logging;

import java.io.IOException;

public interface LoggingManager {
    void enable() throws IOException;

    void disable();
}
