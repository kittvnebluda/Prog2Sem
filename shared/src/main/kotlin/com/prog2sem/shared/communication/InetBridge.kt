package com.prog2sem.shared.communication

import java.net.SocketAddress

interface InetBridge {
    /**
     * Метод обеспечивающий получение сообщения
     */
    fun receive(): String

    /**
     * Метод обеспечивающий отправку сообщения [msg] по указанному адресу [address]
     */
    fun send(msg: String, address: SocketAddress?)
}