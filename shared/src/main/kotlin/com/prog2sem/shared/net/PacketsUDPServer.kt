package com.prog2sem.shared.net

import java.net.InetAddress
import java.net.InetSocketAddress

/**
 * Класс для общения по протоколу UDP с помощью собственных пакетов (без ограничения в размере сообщения)
 */
class PacketsUDPServer(
    host: InetAddress,
    port: Int,
    timeout: Int = 3000  // Время ожидания в миллисекундах
) : PacketsUDP(timeout) {
    init {
        val address = InetSocketAddress(host, port)
        channel.bind(address)  // The server is listening
        println("Receiver started at $address")
    }
}