package com.prog2sem.shared.utils

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Log {
    private val LOGGER: Logger = LogManager.getRootLogger()

    fun e(msg: String) {
        LOGGER.error(msg)
    }

    fun i(msg: String) {
        LOGGER.info(msg)
    }

    fun d(msg: String) {
        LOGGER.debug(msg)
    }

    fun w(msg: String) {
        LOGGER.warn(msg)
    }
}