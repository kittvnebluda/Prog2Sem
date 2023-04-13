package com.prog2sem.shared.net

import com.prog2sem.shared.utils.Buffer
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

/**
 * Класс обеспечивающий обмен данными с помощью датаграмм из покета nio.
 * Настраивает сервер в неблокирующем режиме
 */
open class NioUdpServer(
    host: InetAddress,
    port: Int,
    val bufferCapacity: Int = 1024,
) : Talker, AddressTalker {

    var channel: DatagramChannel = DatagramChannel.open()
    private lateinit var sendToAddress: SocketAddress

    init {
        val address = InetSocketAddress(host, port)
        // The server is listening
        channel.bind(address)
        println("Receiver started at $address")
        channel.configureBlocking(false)
    }

    override fun receive(): String {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)
        var nulladress : SocketAddress? = null
        while (nulladress == null)
            nulladress = channel.receive(buffer)
        sendToAddress = nulladress
        val message = Buffer.toString(buffer)
        println("Received message from sender: $sendToAddress")
        return message
    }

    /**
     * Метод для отправки сообщений
     * @param address адрес отправки сообщения
     */
    override fun send(msg: String, address: SocketAddress) {
        println("Sending: $msg")
        val buffer: ByteBuffer = ByteBuffer.wrap(msg.toByteArray())
        channel.send(buffer, address)
    }

    /**
     * Метод для отправки сообщений по адресу отправителя, полученному после [receive] метода
     */
    override fun send(msg: String) {
        send(msg, sendToAddress)
    }
}