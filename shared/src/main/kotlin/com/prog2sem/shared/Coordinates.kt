package com.prog2sem.shared

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(val x: Float, val y: Double) {
    override fun toString(): String {
        return StringBuilder().append("Coordinate X: $x\n")
            .append("Coordinate Y: $y")
            .toString()
    }
}