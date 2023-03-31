package com.prog2sem.client.io

import com.prog2sem.client.utils.CustomConsole

object ColorfulOut {
    /** Возвращает случайный ANSI escape код (91 - 96, светлые цвета) */
    fun randLightANSI(): String {
        return "\u001b[${kotlin.random.Random.nextInt(91, 97)}m"
    }

    /** Вывод текста красным в стандартный поток выхода */
    fun printerr(s: String) {
        println(CustomConsole.red + s + CustomConsole.reset)
    }

    /** Вывод текста зеленым в стандартный поток */
    fun printGreen(s: String) {
        println(CustomConsole.green + s + CustomConsole.reset)
    }
}