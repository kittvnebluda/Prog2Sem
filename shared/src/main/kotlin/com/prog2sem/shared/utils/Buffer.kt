package com.prog2sem.shared.utils

import java.nio.ByteBuffer

object Buffer {
    /**
     * Метод преобразует байты буфера в строку
     */
    fun toString(buffer: ByteBuffer): String {
        buffer.flip()
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return String(bytes)
    }
}