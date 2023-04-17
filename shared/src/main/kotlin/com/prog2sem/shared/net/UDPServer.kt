package com.prog2sem.shared.net

import java.net.InetAddress
import java.net.InetSocketAddress

/**
 * Класс обеспечивающий обмен данными с помощью датаграмм из покета nio в неблокирующем режиме.
 * Настраивает сервер
 */
open class UDPServer(
    host: InetAddress,
    port: Int,
) : UDP() {
    init {
        val address = InetSocketAddress(host, port)
        channel.bind(address)  // The server is listening
        println("Receiver started at $address")
    }
}