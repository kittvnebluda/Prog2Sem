package com.prog2sem.shared

import kotlinx.serialization.Serializable

/**
 * Класс для общения с клиента и сервера
 * @param T тип передаваемой информации в [msg]
 */
@Serializable
@Deprecated("Use MsgMarker instead")
data class Response<T>(val success: Boolean, val msg: T, val error: String = "")