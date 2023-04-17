package com.prog2sem.client.persona

import com.prog2sem.shared.Color
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.Location
import com.prog2sem.shared.persona.BirthPersonBuilder
import com.prog2sem.shared.persona.PersonBuilder
import java.time.ZonedDateTime
import kotlin.random.Random

/**
 * Класс персоны без имени
 */
class RndPersonBuilder : BirthPersonBuilder() {
    companion object {
        private val names = arrayOf("Века", "Вилка", "Сюзанна", "Андрей Григорьевич", "Года", "Кака")
        private val locations = arrayOf("Марианская впадина", "Луна", "Уран", "Орбита Солнца", "Африка", "Звезда смерти")
    }

    override fun chooseName(): PersonBuilder {
        name = names[Random.nextInt(names.size)]
        return this
    }

    override fun chooseCoordinates(): PersonBuilder {
        coordinates = Coordinates(Random.nextFloat() * -948, Random.nextDouble(453.0))
        return this
    }

    override fun chooseHeight(): PersonBuilder {
        height = Random.nextLong(1, 1000)
        return this
    }

    override fun chooseBirthday(): PersonBuilder {
        birthday = ZonedDateTime.now()
        return this
    }

    override fun chooseWeight(): PersonBuilder {
        weight = Random.nextInt(1, 1000)
        return this
    }

    override fun chooseHairColor(): PersonBuilder {
        hairColor = Color.values()[Random.nextInt(Color.values().size)]
        return this
    }

    override fun chooseLocation(): PersonBuilder {
        location = Location(
            Random.nextFloat() * 100,
            Random.nextFloat() * 100,
            Random.nextInt(),
            locations[Random.nextInt(locations.size)])
        return this
    }
}