package com.prog2sem.shared.persona

import com.prog2sem.shared.Color
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.CustomSerializers.KZonedDateTimeSerializer
import com.prog2sem.shared.Location
import java.time.ZonedDateTime
import kotlinx.serialization.Serializable

/**
 * Класс описывающий личность
 */
@Serializable
data class Person(
    var name: String,
    var coordinates: Coordinates,
    var height: Long,
    @Serializable(KZonedDateTimeSerializer::class)
    var birthday: ZonedDateTime,
    var weight: Int,
    var hairColor: Color,
    var location: Location
) {

    companion object {
        val colors = mutableListOf<Color>()
    }

    init {
        if (colors.indexOf(hairColor) < 0)
            colors.add(hairColor)
    }

    operator fun compareTo(other: Person): Int {
        return TODO("Переопределить для обычного Person")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (coordinates != other.coordinates) return false
        if (birthday != other.birthday) return false
        if (height != other.height) return false
        if (weight != other.weight) return false
        if (hairColor != other.hairColor) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + coordinates.hashCode()
        result = 31 * result + birthday.hashCode()
        result = (31 * result + height).toInt()
        result = 31 * result + weight
        result = 31 * result + hairColor.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }

    override fun toString(): String {
        return StringBuilder().append("Person: $name\n").append(
                    "Height: $height\n" +
                    "Weight: $weight\n" +
                    "Birthday: $birthday\n" +
                    "HairColor: $hairColor\n" +
                    "Location:\n\t${location.toString().replace("\n", "\n\t")}\n" +
                    "Coordinates:\n\t${coordinates.toString().replace("\n", "\n\t")}\n"
        ).toString()
    }
}