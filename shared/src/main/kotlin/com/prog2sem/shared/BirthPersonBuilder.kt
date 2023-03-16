package com.prog2sem.shared

import java.time.ZonedDateTime

/**
 * Абстрактный класс реализующий метод рождения персоны
 */
abstract class BirthPersonBuilder : PersonBuilder {

    var name: String? = null
    var coordinates: Coordinates? = null
    var height: Long? = null
    var birthday: ZonedDateTime? = null
    var weight: Int? = null
    var hairColor: Color? = null
    var location: Location? = null

    override fun birth(): Person {
        return Person(
            name!!,
            coordinates!!,
            height!!,
            birthday!!,
            weight!!,
            hairColor!!,
            location!!
        )
    }
}