package com.prog2sem.shared

import kotlinx.serialization.Serializable

@Serializable
@Deprecated("Use MsgMarker instead")
data class SimpleResponse(val success: Boolean, val error: String = "")