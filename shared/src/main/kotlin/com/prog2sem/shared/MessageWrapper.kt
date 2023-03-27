package com.prog2sem.shared

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.ByteBuffer

/**
 * Класс для обработки отправляемых и получаемых сообщений
 */
@Serializable
class MessageWrapper {

    companion object {
        /**
         * Метод преобразует байты буфера в строку
         */
        fun extractMsg(buffer: ByteBuffer): String {
            buffer.flip()
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)
            return String(bytes)
        }

        /**
         * Метод создания сообщения
         * Сообщение кодируется в json
         */
        fun <T> wrapGenericMsg(msg: Class<T>): MessageWrapper {
            val wrapper = MessageWrapper()
            wrapper.msg = "0${Json.encodeToString(msg)}"
            wrapper.msgType = "Generic"
            return wrapper
        }

        /**
         * Метод создания сообщения об ошибке
         */
        fun wrapErrorMsg(msg: String): MessageWrapper {
            val wrapper = MessageWrapper()
            wrapper.msg = "1$msg"
            return wrapper
        }
    }

    private lateinit var msg: String
    private lateinit var msgType: String

    /**
     * Метод возвращает декодированное сообщение
     */
    fun <T> getGenericMsg() = Json.decodeFromString<Class<T>>(msg.drop(1))

    /**
     * Метод возвращает true, если содержащееся сообщение является сообщением c дженериком
     */
    fun isGeneric(): Boolean = msg[0] == '0'

    /**
     * Метод возвращает true, если содержащееся сообщение является сообщением об ошибке
     */
    fun isError(): Boolean = msg[0] == '1'

    /**
     * Метод возвращает сообщение об ошибке
     */
    fun getError() = msg.drop(1)
}