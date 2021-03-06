package com.github.corneil.demos.logging

import mu.KotlinLogging

class KExampleLogging {
    companion object {
        private val logger = KotlinLogging.logger {}

        @JvmStatic
        fun logWithPlaceHoldersNoExtInterpolation(pojo: SimplePOJO) {
            logger.info { "logWithPlaceHoldersNoExtInterpolation:$pojo" }
        }

        @JvmStatic
        fun logWithPlaceHoldersNoExtInterpolationCheck(pojo: SimplePOJO) {
            if (logger.isInfoEnabled) {
                logger.info { "logWithPlaceHoldersNoExtInterpolationCheck:$pojo" }
            }
        }

        @JvmStatic
        fun logWithPlaceHoldersExtInterpolation(pojo: SimplePOJO) {
            logger.entry(pojo)
            logger.info { "logWithPlaceHoldersExtInterpolation:$pojo" }
            logger.exit()
        }
        @JvmStatic
        fun logBlank(pojo: SimplePOJO) {
            // do nothing
        }
    }
}
