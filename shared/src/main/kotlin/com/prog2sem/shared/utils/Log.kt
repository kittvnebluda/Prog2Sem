package com.prog2sem.shared.utils

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import org.apache.logging.log4j.core.config.Configurator
import java.util.*


object Log {

//    init {
//        System.setProperty("log4j.configurationFile", "resources/Log4j.properties")
//    }

    private val Logger: Logger = LogManager.getLogger()

    init {
        Configurator.setLevel(Logger, Level.ALL)
    }

    fun e(msg: String){
        Logger.error(msg)
    }

    fun i(msg: String){
        Logger.info(msg)
    }

    fun d(msg: String){
        Logger.debug(msg)
    }

    fun w(msg: String){
        Logger.warn(msg)
    }

}