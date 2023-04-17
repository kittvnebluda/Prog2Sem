package com.prog2sem.client.net

import com.prog2sem.shared.net.UDP
import java.net.InetSocketAddress

class ConsoleInetCommands(val client: UDP) : InetCommands {
    override fun showServerAddr() {
        println("Адрес сервера: ${client.sendToAddress}")
    }

    override fun setServerAddr(address: String, port: Int) {
        client.sendToAddress = InetSocketAddress(address, port)
        println("Адрес сервера теперь: ${client.sendToAddress}")
    }
}