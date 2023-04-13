package com.prog2sem.client.net

import com.prog2sem.shared.net.NioUdpClient
import java.net.InetSocketAddress

class ConsoleInetCommands(val client: NioUdpClient) : InetCommands {
    override fun showServerAddr() {
        println("Адрес сервера: ${client.sendToAddress}")
    }

    override fun setServerAddr(address: String, port: Int) {
        client.sendToAddress = InetSocketAddress(address, port)
        println("Адрес сервера теперь: ${client.sendToAddress}")
    }
}