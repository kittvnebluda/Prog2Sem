package com.prog2sem.client.utils

import com.prog2sem.client.HISTORY
import com.prog2sem.client.ISQUIT
import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.client.io.ColorfulOut.printRandColor
import com.prog2sem.client.io.ColorfulOut.printlnGreen
import com.prog2sem.client.io.ColorfulOut.printlnRed
import com.prog2sem.client.io.ColorfulOut.randLightANSI
import com.prog2sem.shared.cmdpattern.Invoker
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.exceptions.MsgException

/**
 * Класс для упрощения работы с консолью
 */
object Smt {
    /** Парочка приветственных слов */
    private fun greetings() {
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

    /** Вывод простого ответа */
    fun outBool(boolean: Boolean) {
        if (boolean)
            printlnGreen("Успех!")
        else
            printlnRed("Неудача")
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

    /** Главная функция класса, реализующая постоянное "общение" с пользователем */
    fun talkWithUserForever(invoker: Invoker) {
        greetings()
        while (!ISQUIT) {
            try {
                printRandColor(">>> ") // Просто красивая штучка
                invoker.proceed(readln())
            } catch (e: Exception) {
                when (e) {
                    is InvalidUserInputException -> println(e.message)
                    is MsgException -> println(e.message)
                    is ServerNotAnsweringException -> println(e.message)
                    else -> throw e
                }
            }
        }
    }
}
