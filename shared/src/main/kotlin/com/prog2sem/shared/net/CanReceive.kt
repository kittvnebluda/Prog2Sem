package com.prog2sem.shared.net

interface CanReceive {
    /**
     * Метод обеспечивающий получение сообщения
     */
    fun receive(): String
}