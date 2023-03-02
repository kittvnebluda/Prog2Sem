package  com.itmo.itmoproject2023

import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
class Person(var name: String = "Steve", var coordinates: Coordinates = Coordinates(),
             @Serializable(CustomsSerializators.DateSerialazer::class)
             var birthday: ZonedDateTime = ZonedDateTime.now(),
             var height: Long = 170, var weight: Int = 70,
             var hairColor: Color? = null, var location: Location? = null): Comparable<Person>{

    init {
        previous_id = maxId
        if (hairColor != null && colors.indexOf(hairColor) < 0) colors.add(hairColor!!)
    }

    companion object {
        private var previous_id: Int = 0
        fun clear(){
            previous_id = 0
        }
        var maxId = 1
        val colors = mutableListOf<Color>()
    }

    val id: Int = if (DataBaseSim.removedIds.size != 0) DataBaseSim.removedIds.remove()
        else {
            ++previous_id
            maxId++
    }

    @Serializable(CustomsSerializators.DateSerialazer::class)
    var creationDate: ZonedDateTime = ZonedDateTime.now()

    override fun compareTo(other: Person): Int {
        return this.id.compareTo(other.id)
    }

    override fun toString(): String {
        return "Person(name='$name', coordinates=$coordinates, birthday=$birthday, height=$height, weight=$weight, hairColor=$hairColor, location=$location, id=$id, creationDate=$creationDate)"
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
        if (id != other.id) return false
        if (creationDate != other.creationDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + coordinates.hashCode()
        result = 31 * result + birthday.hashCode()
        result = (31 * result + height).toInt()
        result = 31 * result + weight
        result = 31 * result + (hairColor?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + creationDate.hashCode()
        return result
    }

}