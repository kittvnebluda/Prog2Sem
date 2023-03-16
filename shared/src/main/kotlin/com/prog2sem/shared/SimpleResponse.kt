package com.prog2sem.shared

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(val success: Boolean, val error: String = "")