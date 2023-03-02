package com.itmo.itmoproject2023

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZonedDateTime

object CustomsSerializators {
    object DateSerialazer : KSerializer<ZonedDateTime>{
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): ZonedDateTime {
            val serString = decoder.decodeString()
            return ZonedDateTime.parse(serString)
        }

        override fun serialize(encoder: Encoder, value: ZonedDateTime) {
            encoder.encodeString(value.toString())
        }

    }

   /* object PersonSerialazer : KSerializer<Person>{
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): ZonedDateTime {
            val serString = decoder.decodeString()
            return ZonedDateTime.parse(serString)
        }

        override fun serialize(encoder: Encoder, value: ZonedDateTime) {
            encoder.encodeString(value.toString())
        }

    }*/
}