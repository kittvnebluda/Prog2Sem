package com.prog2sem.shared.net

import com.prog2sem.shared.exceptions.NotMarkedMsgException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.jvm.Throws

object MsgMarker {
    enum class MsgMarks {
        GEN,
        ERR
    }

    fun <T> markGeneric(msg: Class<T>): String {
        return "/#?${MsgMarks.GEN}${Json.encodeToString(msg)}"
    }

    fun markError(msg: String): String {
        return "/#?${MsgMarks.ERR}$msg"
    }

    @Throws(NotMarkedMsgException::class)
    fun which(msg: String): MsgMarks {
        return when(msg.slice(0..5)) {
            "/#?${MsgMarks.GEN}" -> MsgMarks.GEN
            "/#?${MsgMarks.ERR}" -> MsgMarks.ERR
            else -> throw NotMarkedMsgException()
        }
    }
}