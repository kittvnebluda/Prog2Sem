package com.prog2sem.shared.net

import com.prog2sem.shared.utils.JsonWorker.json
import com.prog2sem.shared.exceptions.GotErrorMsgException
import com.prog2sem.shared.exceptions.MsgException
import com.prog2sem.shared.exceptions.NotMarkedMsgException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

/**
 * Маркирует сообщения разного рода в строку.
 * Код маркировки должен состоять из 3 символов
 */
object MsgMarker {
    enum class Tags {
        GEN,  // Generic tag
        ERR,  // Error tag
        FWP,  // Function call with parameters tag
        PAC;  // Packet tag

        operator fun plus(s: String): String {
            return toString() + s
        }
    }

    fun extract(msg: String): String {
        return msg.substring(3)
    }
    /** Mark generic message, T must be serializable */
    inline fun <reified T> markGeneric(msg: T): String {
        return Tags.GEN + json.encodeToString(msg)
    }
    /** Get generic message */
    inline fun <reified T> getGeneric(msg: String): T {
        return json.decodeFromString(extract(msg))
    }
    /** Mark error message */
    fun markError(error: String): String {
        return Tags.ERR + error
    }
    /** Get error message */
    fun getError(msg: String): String {
        return extract(msg)
    }
    /** Mark function call message with corresponding parameters */
    fun markFun(funName: String, vararg params: String): String {
        return Tags.FWP + json.encodeToString(arrayOf(funName, *params))
    }
    /**
     * Get function name with its parameters.
     * @return An array whose first parameter is always the name of the function, the rest are its parameters
     */
    fun getFun(msg: String): Array<String> {
        return json.decodeFromString(extract(msg))
    }
    /** Mark packet with its order */
    fun markPacket(msg: String, order: Int): String {
        return Tags.PAC + "{$order}" + msg
    }
    /** Get packet with its order */
    fun getPacket(msg: String): Pair<Int, String> {
        val msg1 = extract(msg)
        val orderStart = msg1.indexOf('{')
        val orderEnd = msg1.indexOf('}')
        val order = msg1.slice(orderStart + 1 until orderEnd).toInt()
        val msg2 = msg1.substring(orderEnd - orderStart + 1)
        return Pair(order, msg2)
    }

    /**
     * Возвращает метку строки или выбрасывает ошибку,
     * если сообщение не маркеровано
     * @throws NotMarkedMsgException
     */
    fun which(msg: String): Tags {
        return when("$msg   ".slice(0..2)) {
            Tags.GEN.toString() -> Tags.GEN
            Tags.ERR.toString() -> Tags.ERR
            Tags.FWP.toString() -> Tags.FWP
            else -> throw NotMarkedMsgException()
        }
    }

    /**
     * Возвращает Генерик сообщение или выбрасывает ошибку,
     * если присутствует марка ошибки или не найдены тэги
     * @throws MsgException
     * @throws GotErrorMsgException
     * @throws NotMarkedMsgException
     */
    inline fun <reified T> getGenOrException(msg: String): T  {
        return when(which(msg)) {
            Tags.GEN -> getGeneric(msg)
            Tags.ERR -> throw GotErrorMsgException(msg)
            else -> throw MsgException("Не найдены нужные теги в сообщении")
        }
    }
}