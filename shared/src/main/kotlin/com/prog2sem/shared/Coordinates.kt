package com.prog2sem.shared

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(val x: Float, val y: Double) {

    fun toTable(): String{
        return "X:${x} Y:${y}"
    }
    override fun toString(): String {
        return StringBuilder()
            .append("Coordinate X: $x\n")
            .append("Coordinate Y: $y")
            .toString()
    }
}