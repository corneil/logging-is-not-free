package com.github.corneil.demos.logging;

import org.junit.Test;

import java.util.Date;

public class LoggingTest {
    @Test
    public void testDisable() {
        SimplePOJO pojo = new SimplePOJO("string", 1, 2.0, new Date());
        LoggingUtil.disable();

        ExampleLogging.logWithPlaceHolders(pojo);
        ExampleLogging.logWithStringCat(pojo);
        ExampleLogging.logWithStringCatToString(pojo);
        ExampleLogging.logFluentWithParam(pojo);
        ExampleLogging.logFluentWithLazy(pojo);
    }

    @Test
    public void testEnable() {
        SimplePOJO pojo = new SimplePOJO("string", 1, 2.0, new Date());
        LoggingUtil.enable();

        ExampleLogging.logWithPlaceHolders(pojo);
        ExampleLogging.logWithStringCat(pojo);
        ExampleLogging.logWithStringCatToString(pojo);
        ExampleLogging.logFluentWithParam(pojo);
        ExampleLogging.logFluentWithLazy(pojo);
    }
}
