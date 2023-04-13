package com.prog2sem.client.utils

import com.prog2sem.client.HISTORY
import com.prog2sem.client.ISQUIT
import com.prog2sem.client.io.ColorfulOut.printerr
import com.prog2sem.client.io.ColorfulOut.randLightANSI
import com.prog2sem.shared.cmdpattern.Invoker
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.exceptions.MsgException

/**
 * Класс для упрощения работы с консолью
 */
class CustomConsole(private val invoker: Invoker) {
    companion object {
        const val red = "\u001b[31m"
        const val green = "\u001B[32m"
        const val reset = "\u001b[0m"

        /** Парочка приветственных слов */
        fun greetings() {
            val greeting = when (kotlin.random.Random.nextInt(3)) {
                0 -> "Здравствуйте, пользователь."
                1 -> "ПРИВЕТСТВУЮ!"
                else -> "ВСЕ ${randLightANSI()}Х ${randLightANSI()}О ${randLightANSI()}Р ${randLightANSI()}О ${randLightANSI()}Ш ${randLightANSI()}О \u001b[0m!"
            }
            println("#############################################")
            println("# $greeting")
            println("# Если Вам нужна помощь, введите \"help\"")
            println("#############################################")
        }

        /** Удаляет лишние пробелы и разделяет строку по пробелам */
        fun splitSpaces(s: String): List<String> = s.trim().replace("\\s+".toRegex(), " ").split(" ")

        /** Вывод простого ответа */
        fun outBool(boolean: Boolean) {
            if (boolean)
                println("${green}Успех!$reset")
            else
                printerr("Неудача")
        }

        /** Вывод итерабельного ответа */
        fun <T> outIterable(array: Array<T>) {
            array.forEach { println(it) }
        }

        /** Добавление сложного аргумента в историю для последней команды */
        fun addArgToHistory(arg: String) {
            val lastCmd = HISTORY[HISTORY.lastIndex]
            HISTORY[HISTORY.lastIndex] = "$lastCmd\n$arg"
        }
    }

    /** Главная функция класса, реализующая постоянное "общение" с пользователем */
    fun talkWithUserForever() {
        greetings()
        while (!ISQUIT) {
            try {
                print("${randLightANSI()}> $reset") // Просто красивая штучка
                invoker.proceed(readln())
            } catch (e: Exception) {
                when (e) {
                    is InvalidUserInputException -> println(e.message)
                    is MsgException -> println(e.message)
                    else -> throw e
                }
            }
        }
    }
}
