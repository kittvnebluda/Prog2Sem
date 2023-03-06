package com.prog2sem.common

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse(val success: Boolean, val errorMessage: String = "")