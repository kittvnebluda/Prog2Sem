package com.prog2sem.client

import com.prog2sem.common.Color
import com.prog2sem.common.Coordinates
import com.prog2sem.common.Location
import com.prog2sem.common.Person
import com.prog2sem.common.Response
import com.prog2sem.common.SimpleResponse
import java.lang.NumberFormatException
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

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
            val greeting = when(kotlin.random.Random.nextInt(3)) {
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
        /** Создает com.prog2sem.common.Person из пользовательского ввода */
        fun createPerson(): Person {
            // Получаем имя человека
            var name: String? = null
            while (true) {
                if (name == null || name == "")
                    println("Введите имя человека ")
                else break
                name = readlnOrNull()
            }

            // Получаем координаты
            var input: String? = null
            var coordinates: Coordinates
            while (true) {
                if (input == null || input == "")
                    println("Введите координаты, x и y ")
                else {
                    try {
                        val fineInput = splitSpaces(input)
                        if (fineInput.size < 2)
                            throw InvalidUserInputException("Нужно ввести два числа через пробел. Попробуйте еще раз!")
                        coordinates = Coordinates(fineInput[0].toFloat(), fineInput[1].toDouble())
                        if (coordinates.x > -948 && coordinates.y <= 453)
                            break
                        else
                            printerr("x должен быть больше -948, а y должен быть меньше 453. Попробуйте еще раз!")
                    } catch (e: NumberFormatException) {
                        printerr("Не получилось пропарсить строку в числа. Помните: x - Float, y - Double. Попробуйте еще раз!")
                    } catch (e: InvalidUserInputException) {
                        printerr(e.message!!)
                    }
                }
                input = readlnOrNull()
            }

            // Получаем высоту
            input = null
            var height: Long
            while (true) {
                if (input == null || input == "")
                    println("Введите рост ")
                else {
                    try {
                        height = input.trim().toLong()
                        if (height > 0) break else printerr("Высота человека должна быть больше 0. Пропробуйте еще раз!")
                    } catch (e: NumberFormatException) {
                        printerr("Не получается пропарсить строку как Long. Попробуйте еще раз!")
                    }
                }
                input = readlnOrNull()
            }

            // Получаем день рождения
            input = null
            var birthday: ZonedDateTime
            while (true) {
                if (input == null || input == "")
                    println("Введите день рождения, пример формата ISO: \"2011-12-03T10:15:30+01:00[Europe/Paris]\"")
                else {
                    try {
                        birthday = ZonedDateTime.parse(input.trim())
                        break
                    } catch (e: DateTimeParseException) {
                        printerr("Не получается пропарсить дату. Попробуйте еще раз!")
                    }
                }
                input = readlnOrNull()
            }

            // Получаем вес
            input = null
            var weight: Int
            while (true) {
                if (input == null || input == "")
                    println("Введите вес человека")
                else {
                    try {
                        weight = input.trim().toInt()
                        if (weight > 0) break else printerr("Высота человека должна быть больше 0. Пропробуйте еще раз!")
                    } catch (e: NumberFormatException) {
                        printerr("Не получается пропарсить строку как Int. Попробуйте еще раз!")
                    }
                }
                input = readlnOrNull()
            }

            // Получаем цвет волос
            val hairColor = createColor()

            // Получаем местоположение
            val location = createLocation()

            return Person(name!!, coordinates, height, birthday, weight, hairColor, location)
        }
        /** Создать com.prog2sem.common.Location из пользовательского ввода */
        fun createLocation(strField: String? = null): Location {
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
        /** Создать com.prog2sem.common.Color из пользовательского ввода */
        fun createColor(strField: String? = null): Color {
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
        /** Обработка колбэка строки */
        fun callString(response: Response<String>) {
            if (response.success)
                println(response.msg)
            else
                println(red + response.error + reset)
        }
        /** Обработка колбэка булиана */
        fun callBool(response: SimpleResponse) {
            if (response.success)
                println("${green}Успех!$reset")
            else
                println(response.error)
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
         * Спрашиваем пользователя не хочет ли он загрузить последний сейв
         * @return true, если пользователь хочет загрузить сохранение
         */
        fun isLoadTempSave(): Boolean {
            println("Загрузить последнее сохранение коллекции? y/n")
            while (true) {
                var ans = readln()
                if (ans.isNotEmpty()) {
                    ans = ans.trim().lowercase()
                    when (ans) {
                        "y" -> return true
                        "n" -> return false
                        else -> println("Введите 'y' или 'n'")
                    }
                } else println("Загрузить последнее сохранение коллекции? y/n")
            }
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
            } catch (e: InvalidUserInputException) {
                e.message?.let { printerr(it) }
            }
        }
    }
}
