package com.prog2sem.shared

import kotlinx.serialization.Serializable

@Serializable
data class Location (val x: Float = 0f,
                     val y: Float = 0f,
                     val z: Int = 0,
                     val name: String? = null) {

    override fun toString(): String {
        return StringBuilder().append("Coordinate X: $x\n")
            .append("Coordinate Y: $y\n")
            .append("Coordinate Z: $z\n")
            .append("Location name: ${name ?: "noname"}")
            .toString()
    }
}