package com.prog2sem.shared.net

import com.prog2sem.shared.utils.Buffer
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

/**
 * Класс обеспечивающий обмен данными с помощью датаграмм из покета nio в неблокирущем режиме
 */
open class  UDP : Talker, AddressTalker {

    protected val bufferCapacity: Int = 1024

    var channel: DatagramChannel = DatagramChannel.open()
    lateinit var sendToAddress: SocketAddress

    init {
        channel.configureBlocking(false)
    }

    override fun receive(): String? {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)

        return channel.receive(buffer)?.let {
            sendToAddress = it
            Buffer.toString(buffer)
        }
    }

    override fun send(msg: String, address: SocketAddress, login: String, password: String) {
        val buffer: ByteBuffer = ByteBuffer.wrap(msg.toByteArray())
        channel.send(buffer, address)
    }

    /**
     * Метод для отправки сообщений по адресу отправителя,
     * полученному после [receive] метода
     */
    override fun send(msg: String, login: String, password: String) {
        send(msg, sendToAddress, login, password)
    }
}