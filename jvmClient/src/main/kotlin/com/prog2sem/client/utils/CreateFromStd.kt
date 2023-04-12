package com.prog2sem.client.utils

import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.client.io.ColorfulOut.printerr
import com.prog2sem.shared.Color
import com.prog2sem.shared.Location

object CreateFromStd {
    /** Создать Location из пользовательского ввода */
    fun location(strField: String? = null): Location {
        var input = strField
        var location: Location
        while (true) {
            if (input == null || input == "")
                println("Введите местоположение человека: x, y, z и название места, если хотите")
            else {
                try {
                    val fineInput = CustomConsole.splitSpaces(input)
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
    fun color(strField: String? = null): Color {
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
}