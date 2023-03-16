package com.prog2sem.common

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(val success: Boolean, val msg: T, val errorMessage: String = "")