package com.prog2sem.shared.net.wrappers

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(val success: Boolean, val error: String = "")