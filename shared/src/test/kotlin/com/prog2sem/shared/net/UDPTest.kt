package com.prog2sem.shared.net

import org.junit.jupiter.api.Test
import java.net.InetAddress
import java.net.InetSocketAddress
import kotlin.test.assertTrue

class UDPTest {

    @Test
    fun sendReceive() {
        // Инициализируемся
        val host = InetAddress.getLocalHost()
        val port = 4222

        val client = UDP()
        val server = UDPServer(host, port)

        // Проверяем возвращается ли null
        assertTrue(client.receive() == null,
            "Клиент что-то получил, чего не должно было произойти")

        // Проверяем способность отправлять клиента и получать сервера
        var text = "AYAYA.HI"
        client.send(text, InetSocketAddress(host, port))
        var msg = server.receive()
        assertTrue(msg == "AYAYA.HI",
            "Полученное сервером $msg и отправленное клиентом $text не совпадают")

        // Проверяем способность отправлять сервера и получать клиента
        text = "NEVER GONNA GIVE YOU UP"
        server.send(text)
        msg = client.receive()!!
        assertTrue(msg == "NEVER GONNA GIVE YOU UP",
            "Полученное клиентом $msg и отправленное сервером $text не совпадают")
    }
}