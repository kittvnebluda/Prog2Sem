package com.prog2sem.shared.net

import com.prog2sem.shared.exceptions.GotErrorMsgException
import com.prog2sem.shared.exceptions.NotMarkedMsgException
import com.prog2sem.shared.exceptions.MsgException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.jvm.Throws

/**
 * Маркирует сообщения разного рода в строку.
 * Код маркировки должен состоять из 3 символов
 */
object MsgMarker {
    enum class MarkCodes {
        GEN,  // Generic message
        ERR,  // Error message
        FWP;  // Function call with parameters

        operator fun plus(s: String): String {
            return toString() + s
        }
    }

    fun extract(msg: String): String {
        return msg.substring(3)
    }
    /** Mark generic message, T must be serializable */
    inline fun <reified T> markGeneric(msg: T): String {
        return MarkCodes.GEN + Json.encodeToString(msg)
    }
    /** Get generic message */
    inline fun <reified T> getGeneric(msg: String): T {
        return Json.decodeFromString(extract(msg))
    }
    /** Mark error message */
    fun markError(error: String): String {
        return MarkCodes.ERR + error
    }
    /** Get error message */
    fun getError(msg: String): String {
        return extract(msg)
    }
    /** Mark function call message with corresponding parameters */
    fun markFun(funName: String, vararg params: String): String {
        return MarkCodes.FWP + Json.encodeToString(arrayOf(funName, *params))
    }
    /**
     * Get function name with its parameters.
     * @return An array whose first parameter is always the name of the function, the rest are its parameters
     */
    fun getFun(msg: String): Array<String> {
        return Json.decodeFromString(extract(msg))
    }

    @Throws(NotMarkedMsgException::class)
    fun which(msg: String): MarkCodes {
        return when(msg.slice(0..2)) {
            MarkCodes.GEN.toString() -> MarkCodes.GEN
            MarkCodes.ERR.toString() -> MarkCodes.ERR
            MarkCodes.FWP.toString() -> MarkCodes.FWP
            else -> throw NotMarkedMsgException()
        }
    }

    @Throws(MsgException::class)
    inline fun <reified T> getGenOrException(msg: String): T {
        return when(which(msg)) {
            MarkCodes.GEN -> getGeneric(msg)
            MarkCodes.ERR -> throw GotErrorMsgException(msg)
            else -> throw MsgException("Не найдены нужные теги в сообщении")
        }
    }
}