package com.prog2sem.client.io

object ColorfulOut {
    private const val red = "\u001b[31m"
    private const val green = "\u001B[32m"
    private const val reset = "\u001b[0m"

    /** Возвращает случайный ANSI escape код (91 - 96, светлые цвета) */
    fun randLightANSI(): String {
        return "\u001b[${kotlin.random.Random.nextInt(91, 97)}m"
    }

    /** Выводит текст случайного светлого цвета в стадратный поток */
    fun printRandColor(s: String) {
        print("${randLightANSI()}$s$reset")
    }

    /** Выводит красный текст в стандартный поток выхода */
    fun printlnRed(s: String) {
        println(red + s + reset)
    }

    /**  Выводит зеленый текст в стандартный поток */
    fun printlnGreen(s: String) {
        println(green + s + reset)
    }
}