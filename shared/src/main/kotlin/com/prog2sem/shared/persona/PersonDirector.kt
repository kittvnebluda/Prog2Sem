package com.prog2sem.shared.persona

/**
 * Класс использующий строитель персоны для создания такового
 */
class PersonDirector(private val builder: PersonBuilder) {
    fun createPerson(): Person {
        return builder
            .chooseName()
            .chooseBirthday()
            .chooseHeight()
            .chooseWeight()
            .chooseHairColor()
            .chooseCoordinates()
            .chooseLocation()
            .birth()
    }
}