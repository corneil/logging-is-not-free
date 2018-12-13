package com.github.corneil.demos.logging;

import org.openjdk.jmh.infra.Blackhole;

import java.util.Date;

public class BaseExecutionPlan {

    final boolean enableLogging;

    public BaseExecutionPlan(boolean enableLogging) {
        this.enableLogging = enableLogging;
    }
    public SimplePOJO[] pojos = new SimplePOJO[10];
    public int index = 0;


    public void setUp() {
        for (int i = 0; i < pojos.length; i++) {
            pojos[i] = new SimplePOJO(String.format("string-%d", i), 1 + i, 2.0 * i, new Date(System.currentTimeMillis() - 1));
        }
        if (enableLogging) {
            LoggingUtil.enable();
        } else {
            LoggingUtil.disable();
        }
    }

    public SimplePOJO getPojo() {
        if (index >= pojos.length) {
            index = 0;
        }
        return pojos[index++];
    }

}
