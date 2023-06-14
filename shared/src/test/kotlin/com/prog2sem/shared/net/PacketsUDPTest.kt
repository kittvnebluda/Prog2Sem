package com.prog2sem.shared.net

import org.junit.jupiter.api.Test
import java.net.InetAddress
import java.net.InetSocketAddress
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PacketsUDPTest {
    @Test
    fun sendReceive() {
        // Инициализируемся
        val host = InetAddress.getLocalHost()
        val port = 4221

        val client = PacketsUDP()
        val server = PacketsUDPServer(host, port)

        // Проверяем возвращается ли null
        assertTrue(
            client.receive() == null,
            "Клиент что-то получил, чего не должно было произойти"
        )

        // Проверяем способность отправлять клиента и получать сервера
        var text = "AYAYA.HI"

        var msg = server.receive()
        assertEquals(msg, text, "Полученное сервером $msg и отправленное клиентом $text не совпадают")

        // Проверяем способность отправлять сервера и получать клиента
        text = "NEVER GONNA GIVE YOU UP NEVER GONNA GIVE YOU UP NEVER GONNA GIVE YOU UP NEVER GONNA GIVE YOU UP NEVER GONNA GIVE YOU UP NEVER GONNA GIVE YOU UP"

        msg = client.receive()!!

        assertEquals(msg, text, "Полученное клиентом $msg и отправленное сервером $text не совпадают")
    }
}