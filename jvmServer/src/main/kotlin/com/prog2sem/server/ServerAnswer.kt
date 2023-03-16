package com.prog2sem.server

@kotlinx.serialization.Serializable
data class ServerAnswer (
    val isSuccess: Boolean = true,
    val answerMessage: String = "All Okay"
)