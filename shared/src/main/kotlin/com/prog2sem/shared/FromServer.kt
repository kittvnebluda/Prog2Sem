package com.prog2sem.shared

import com.prog2sem.shared.persona.Person
import com.prog2sem.shared.utils.CustomSerializers
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class FromServer(val id: Int, @Serializable(CustomSerializers.KZonedDateTimeSerializer::class)
val creteTime: ZonedDateTime, val person: Person, val login:String, val password: String)