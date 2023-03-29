package com.prog2sem.shared.net

import org.junit.jupiter.api.Test
import java.net.InetAddress
import java.net.InetSocketAddress
import kotlin.test.assertTrue

class NioUdpTest {

    @Test
    fun sendReceive() {
        val client = NioUdpClient()
        val server = NioUdpServer(InetAddress.getLocalHost(), 4221)

        client.channel.configureBlocking(false)
        client.send("AYAYA.HI", InetSocketAddress(InetAddress.getLocalHost(), 4221))

        var msg = server.receive()
        assertTrue(msg == "AYAYA.HI", "Полученное и отправленное сообщения не совпадают")

        server.send("NEVER GONNA GIVE UP")

        msg = client.receive()
        assertTrue(msg == "NEVER GONNA GIVE UP", "Полученное и отправленное сообщения не совпадают")
    }
}