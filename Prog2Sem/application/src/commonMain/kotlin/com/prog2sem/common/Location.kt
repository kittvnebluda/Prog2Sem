package com.prog2sem.common

import kotlinx.serialization.Serializable

@Serializable
data class Location (val x: Float = 0f,
                     val y: Float = 0f,
                     val z: Int = 0,
                     val name: String? = null)