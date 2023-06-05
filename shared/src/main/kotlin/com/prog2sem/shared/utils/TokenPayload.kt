package com.prog2sem.shared.utils

import kotlinx.serialization.Serializable

@Serializable
data class TokenPayload (
    val login: String, var password: String, val info: String
)