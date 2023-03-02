package com.itmo.itmoproject2023

@kotlinx.serialization.Serializable
data class ServerAnswer (
    val isSuccess: Boolean = true,
    val answerMessage: String = "All Okay"
)