package com.prog2sem.client.persona

import com.prog2sem.client.io.ColorfulOut.printerr
import com.prog2sem.client.utils.CreateFromStd
import com.prog2sem.client.utils.CustomConsole
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.persona.BirthPersonBuilder
import com.prog2sem.shared.persona.PersonBuilder
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

/**
 * Класс создания персоны из стандартного входного потока
 */
class FromConsolePersonBuilder : BirthPersonBuilder() {

    override fun chooseName(): PersonBuilder {
        while (true) {
            if (name == null || name == "")
                println("Введите имя человека ")
            else break
            name = readlnOrNull()
        }
        return this
    }

    override fun chooseCoordinates(): PersonBuilder {
        var input: String? = null
        while (true) {
            if (input == null || input == "")
                println("Введите координаты, x и y ")
            else {
                try {
                    val fineInput = CustomConsole.splitSpaces(input)
                    if (fineInput.size < 2)
                        throw InvalidUserInputException("Нужно ввести два числа через пробел. Попробуйте еще раз!")
                    coordinates = Coordinates(fineInput[0].toFloat(), fineInput[1].toDouble())
                    if (coordinates!!.x > -948 && coordinates!!.y <= 453)
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
        return this
    }

    override fun chooseHeight(): PersonBuilder {
        var input: String? = null
        while (true) {
            if (input == null || input == "")
                println("Введите рост ")
            else {
                try {
                    height = input.trim().toLong()
                    if (height!! > 0) break else printerr("Высота человека должна быть больше 0. Пропробуйте еще раз!")
                } catch (e: NumberFormatException) {
                    printerr("Не получается пропарсить строку как Long. Попробуйте еще раз!")
                }
            }
            input = readlnOrNull()
        }
        return this
    }

    override fun chooseBirthday(): PersonBuilder {
        var input: String? = null
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
        return this
    }

    override fun chooseWeight(): PersonBuilder {
        var input: String? = null
        while (true) {
            if (input == null || input == "")
                println("Введите вес человека")
            else {
                try {
                    weight = input.trim().toInt()
                    if (weight!! > 0) break else printerr("Высота человека должна быть больше 0. Пропробуйте еще раз!")
                } catch (e: NumberFormatException) {
                    printerr("Не получается пропарсить строку как Int. Попробуйте еще раз!")
                }
            }
            input = readlnOrNull()
        }
        return this
    }

    override fun chooseHairColor(): PersonBuilder {
        hairColor = CreateFromStd.color()
        return this
    }

    override fun chooseLocation(): PersonBuilder {
        location = CreateFromStd.location()
        return this
    }
}