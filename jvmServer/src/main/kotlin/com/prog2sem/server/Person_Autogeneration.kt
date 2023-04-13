package com.prog2sem.server

import com.prog2sem.server.Important.idGen
import com.prog2sem.shared.utils.CustomSerializers
import com.prog2sem.shared.persona.Person
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
class Person_Autogeneration (val person: Person) {

    companion object {

        var maxId = 1
        var previousId: Int = 0
    }

    init {
        previousId = maxId
    }

    val id = idGen.getId()


    @Serializable(CustomSerializers.KZonedDateTimeSerializer::class)
    var creationDate: ZonedDateTime = ZonedDateTime.now()

    operator fun compareTo(other: Person_Autogeneration): Int {
        return this.id.compareTo(other.id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person_Autogeneration

        if (person == other.person) return false
        if (id != other.id) return false
        return creationDate == other.creationDate
    }

    override fun hashCode(): Int {
        var result = person.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + creationDate.hashCode()
        return result
    }

    override fun toString(): String {
        return StringBuilder().append("Person@$id\n").append(
            person.toString().replaceFirst("Person: ${person.name}\n", "") +
            "CreationDate: $creationDate\n"
        ).toString()
    }
}
