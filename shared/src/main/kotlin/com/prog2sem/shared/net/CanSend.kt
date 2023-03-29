package com.prog2sem.shared.net

interface CanSend {
    /**
     * Метод обеспечивающий отправку сообщения [msg]
     */
    fun send(msg: String)
}