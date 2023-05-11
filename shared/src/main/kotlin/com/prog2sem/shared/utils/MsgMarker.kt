package com.prog2sem.shared.utils

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
        FUN,  // Function call with parameters tag
        LOG,
        PAS,
        PAC;  // Packet tag

        operator fun plus(s: String) = toString() + s
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

    fun markLogAndPas(msg: String, login: String, password: String): String{
        return Tags.LOG + login + Tags.PAS + password + msg
    }

    fun getLogAndPas(msg: String): Pair<String, String>{
        val login = msg.substring(3, msg.indexOf(Tags.PAS.toString()))
        Log.d(login)
        val password = msg.substring(msg.indexOf(Tags.PAS.toString()) + 3, msg.indexOf(Tags.PAC.toString()))
        Log.d(password)
        return Pair(login, password)
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
        return Tags.FUN + json.encodeToString(arrayOf(funName, *params))
    }
    /**
     * Get function name with its parameters.
     * @return An array whose first parameter is always the name of the function, the rest are its parameters
     */
    fun getFun(msg: String): Array<String> {
        return json.decodeFromString(extract(msg))
    }
    /** Mark packet with its index which starts with 1*/
    fun markPacket(msg: String, index: Int, count: Int, login: String, password: String): String {
        return markLogAndPas(Tags.PAC + "{$index/$count}" + msg, login, password)
    }
    /** Get the text in a packet with its index and total number of packets */
    fun getPacket(msg: String): Triple<String, Int, Int> {
        val indexStart = msg.indexOf('{')
        val delimiter = msg.indexOf('/')
        val countEnd = msg.indexOf('}')

        val index = msg.slice(indexStart + 1 until delimiter).toInt()
        val count = msg.slice(delimiter + 1 until countEnd).toInt()

        val innerMsg = msg.substring(countEnd + 1)
        return Triple(innerMsg, index, count)
    }

    /**
     * Возвращает метку строки или выбрасывает ошибку,
     * если сообщение не маркеровано
     * @throws NotMarkedMsgException
     */
    fun which(msg: String): Tags {
        val slice = "$msg   ".slice(0..2)
        for (tag in Tags.values())
            if (slice == tag.toString()) return tag
        throw NotMarkedMsgException()
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