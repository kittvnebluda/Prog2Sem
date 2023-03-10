package com.prog2sem.client

import com.prog2sem.common.*
import java.time.ZonedDateTime

/**
 * Класс персоны без имени
 */
class NoNamePersonBuilder: BirthPersonBuilder() {

    override fun chooseName(): PersonBuilder {
        name = "Noname"
        return this
    }

    override fun chooseCoordinates(): PersonBuilder {
        coordinates = Coordinates(42f, 21.0)
        return this
    }

    override fun chooseHeight(): PersonBuilder {
        height = 255
        return this
    }

    override fun chooseBirthday(): PersonBuilder {
        birthday = ZonedDateTime.now()
        return this
    }

    override fun chooseWeight(): PersonBuilder {
        weight = 69
        return this
    }

    override fun chooseHairColor(): PersonBuilder {
        hairColor = Color.BLACK
        return this
    }

    override fun chooseLocation(): PersonBuilder {
        location = Location(55.1540200f, 61.4291500f, 219)
        return this
    }
}