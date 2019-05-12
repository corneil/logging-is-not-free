package com.github.corneil.demos.logging;

import com.google.common.flogger.FluentLogger;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import static com.google.common.flogger.LazyArgs.lazy;


public class ExampleLogging {
    public static final XLogger LOGGER = XLoggerFactory.getXLogger(ExampleLogging.class);
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public static void logWithPlaceHolders(final SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
        LOGGER.exit();
    }

    public static void logWithPlaceHoldersNoExt(final SimplePOJO pojo) {
        LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
    }

    public static void logFluentWithParam(final SimplePOJO pojo) {
        logger.atInfo().log("logFluentWithParam:%s", pojo);
    }

    public static void logFluentWithLazy(final SimplePOJO pojo) {
        logger.atInfo().log("logFluentWithLazy:%s", lazy(() -> pojo.toString()));
    }

    public static void logWithPlaceHoldersNoExtCheck(SimplePOJO pojo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
        }
    }

    public static void logWithStringCat(final SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCat:" + pojo);
        LOGGER.exit();
    }

    public static void logWithStringCatToString(final SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCatToString:" + pojo.toString());
        LOGGER.exit();
    }

    public static void logWithPlaceHolderToString(final SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHolderToString:{}", pojo.toString());
        LOGGER.exit();
    }

}
