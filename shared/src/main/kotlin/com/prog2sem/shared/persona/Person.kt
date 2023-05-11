package com.prog2sem.shared.persona

import com.prog2sem.shared.Color
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.utils.CustomSerializers.KZonedDateTimeSerializer
import com.prog2sem.shared.Location
import kotlinx.serialization.Serializable
import java.sql.ResultSet
import java.time.ZonedDateTime

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
        return name.compareTo(other.name)
    }

    fun toHashMap(keys: List<String>): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()

        hashMap[keys[2]] = name
        hashMap[keys[7]] = coordinates.toTable()
        hashMap[keys[6]] = hairColor.toString()
        hashMap[keys[5]] = birthday.toString()
        hashMap[keys[4]] = height
        hashMap[keys[3]] = weight
        hashMap[keys[8]] = location.toTable()

        return hashMap
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
        return location == other.location
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