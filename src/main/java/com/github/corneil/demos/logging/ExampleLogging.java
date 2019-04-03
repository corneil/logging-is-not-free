package com.github.corneil.demos.logging;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class ExampleLogging {
    public static final XLogger LOGGER = XLoggerFactory.getXLogger(ExampleLogging.class);

    public static void logWithPlaceHolders(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
        LOGGER.exit();
    }

    public static void logWithPlaceHoldersNoExt(SimplePOJO pojo) {
        LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
    }

    public static void logWithPlaceHoldersNoExtCheck(SimplePOJO pojo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("logWithPlaceHoldersNoExt:{}", pojo);
        }
    }

    public static void logWithStringCat(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCat:" + pojo);
        LOGGER.exit();
    }

    public static void logWithStringCatToString(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCatToString:" + pojo.toString());
        LOGGER.exit();
    }

    public static void logWithPlaceHolderToString(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHolderToString:{}", pojo.toString());
        LOGGER.exit();
    }
}
