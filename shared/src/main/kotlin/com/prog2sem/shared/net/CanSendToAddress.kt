package com.prog2sem.shared.net

import java.net.SocketAddress

interface CanSendToAddress {
    /**
     * Метод обеспечивающий отправку сообщения [msg] по указанному адресу [address]
     * @param address адрес принимающего
     */
    fun send(msg: String, address: SocketAddress)
}