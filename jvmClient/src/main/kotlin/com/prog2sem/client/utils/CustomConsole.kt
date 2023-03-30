package com.prog2sem.client.utils

import com.prog2sem.client.HISTORY
import com.prog2sem.client.ISQUIT
import com.prog2sem.client.Invoker
import com.prog2sem.client.exceptions.InvalidUserInputException
import com.prog2sem.shared.Color
import com.prog2sem.shared.Location
import com.prog2sem.shared.exceptions.MsgErrorException

/**
 * Класс для упрощения работы с консолью
 */
class CustomConsole(private val invoker: Invoker) {
    companion object {
        const val red = "\u001b[31m"
        const val green = "\u001B[32m"
        const val reset = "\u001b[0m"

        /** Возвращает случайный ANSI escape код (91 - 96, светлые цвета) */
        fun rBC(): String {
            return "\u001b[${kotlin.random.Random.nextInt(91, 97)}m"
        }

        /** Парочка приветственных слов */
        fun greetings() {
            val greeting = when (kotlin.random.Random.nextInt(3)) {
                0 -> "Здравствуйте, пользователь."
                1 -> "ПРИВЕТСТВУЮ!"
                2 -> "ВСЕ ${rBC()}Х ${rBC()}О ${rBC()}Р ${rBC()}О ${rBC()}Ш ${rBC()}О \u001b[0m!"
                else -> "Вы точно хотите знать зачем я нужен?"
            }
            println("#############################################")
            println("# $greeting")
            println("# Если Вам нужна помощь, введите \"help\"")
            println("#############################################")
        }

        /** Удаляет лишние пробелы и разделяет строку по пробелам */
        fun splitSpaces(s: String): List<String> = s.trim().replace("\\s+".toRegex(), " ").split(" ")

        /** Создать Location из пользовательского ввода */
        fun locationFromConsoleInput(strField: String? = null): Location {
            var input = strField
            var location: Location
            while (true) {
                if (input == null || input == "")
                    println("Введите местоположение человека: x, y, z и название места, если хотите")
                else {
                    try {
                        val fineInput = splitSpaces(input)
                        if (fineInput.size < 3)
                            throw InvalidUserInputException("Нужно ввести минимум три числа через пробел. Попробуйте еще раз!")
                        val locName = if (fineInput.size > 3) fineInput[3] else null
                        location =
                            Location(fineInput[0].toFloat(), fineInput[1].toFloat(), fineInput[2].toInt(), locName)
                        break
                    } catch (e: NumberFormatException) {
                        printerr("Не получилось пропарсить строку в числа. Помните: x - Float, y - Float, z - Int. Попробуйте еще раз!")
                    } catch (e: InvalidUserInputException) {
                        printerr(e.message!!)
                    }
                }
                input = readlnOrNull()
            }
            return location
        }

        /** Создать Color из пользовательского ввода */
        fun colorFromConsoleInput(strField: String? = null): Color {
            var input = strField
            var color: Color
            while (true) {
                if (input == null || input == "")
                    println("Выберите цвет: ${Color.values().joinToString(", ")}")
                else {
                    try {
                        color = Color.valueOf(input.trim().uppercase())
                        break
                    } catch (e: IllegalArgumentException) {
                        printerr("Не найден цвет ${input.trim().uppercase()}. Попробуйте еще раз!")
                    }
                }
                input = readlnOrNull()
            }
            return color
        }

        /** Вывод простого ответа */
        fun boolResponse(boolean: Boolean) {
            if (boolean)
                println("${green}Успех!$reset")
            else
                printerr("Неудача")
        }

        /** Вывод итерабельного ответа */
        fun <T> iterableResponse(array: Array<T>) {
            array.forEach { println(it) }
        }

        /** Вывод текста красным в стандартный поток выхода */
        fun printerr(s: String) {
            println(red + s + reset)
        }

        /** Вывод текста зеленым в стандартный поток */
        fun printGreen(s: String) {
            println(green + s + reset)
        }

        /**
         * Спрашиваем пользователя не хочет ли он загрузить последнее сохранение
         * @return true, если пользователь хочет загрузить сохранение
         */
        fun isLoadTempSave(): Boolean {
            print("Загрузить несохраненную коллекцию? y/n\n> ")
            while (true) {
                var ans = readln()
                if (ans.isNotEmpty()) {
                    ans = ans.trim().lowercase()
                    when (ans) {
                        "y" -> return true
                        "n" -> return false
                        else -> println("Введите 'y' или 'n'\n> ")
                    }
                } else print("Загрузить последнее сохранение коллекции? y/n\n> ")
            }
        }

        /** Добавление сложного аргумента в историю для последней команды */
        fun addArgToHistory(arg: String) {
            val lastCmd = HISTORY[HISTORY.lastIndex]
            HISTORY[HISTORY.lastIndex] = "$lastCmd\n$arg"
        }
    }

    /**
     * Главная функция класса, реализующая постоянное "общение" с пользователем
     */
    fun talkWithUserForever() {
        greetings()
        while (!ISQUIT) {
            try {
                print("${rBC()}> $reset") // Просто красивая штучка
                invoker.proceed(readln())
            } catch (e: Exception) {
                when (e) {
                    is InvalidUserInputException -> println(e.message)
                    is MsgErrorException -> println(e.message)
                }
            }
        }
    }
}
