package com.prog2sem.shared

/**
 * Интерфейс для строителя класса персоны
 */
interface PersonBuilder {
    fun chooseName(): PersonBuilder
    fun chooseCoordinates(): PersonBuilder
    fun chooseHeight(): PersonBuilder
    fun chooseBirthday(): PersonBuilder
    fun chooseWeight(): PersonBuilder
    fun chooseHairColor(): PersonBuilder
    fun chooseLocation(): PersonBuilder

    fun birth(): Person
}