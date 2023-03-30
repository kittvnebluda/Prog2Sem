package com.prog2sem.shared.net

import com.prog2sem.shared.utils.Buffer
import java.net.SocketAddress
import java.net.SocketTimeoutException
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import kotlin.jvm.Throws

/**
 * Класс обеспечивающий обмен данными с помощью датаграмм из покета nio.
 * Настраивает клиент
 */
open class NioUdpClient(
    private val bufferCapacity: Int = 1024,
    timeout: Int = 10000
) : CanReceive, CanSend, CanSendToAddress {

    var channel: DatagramChannel = DatagramChannel.open()

    lateinit var sendToAddress: SocketAddress

    init {
        channel.socket().soTimeout = timeout // set timeout
    }

    @Throws(SocketTimeoutException::class)
    override fun receive(): String {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)
        val address = channel.receive(buffer)
        val message = Buffer.toString(buffer)
        println("Received message from server: $address")
        return message
    }

    override fun send(msg: String, address: SocketAddress) {
        val buffer: ByteBuffer = ByteBuffer.wrap(msg.toByteArray())
        channel.send(buffer, address)
    }

    override fun send(msg: String) {
        send(msg, sendToAddress)
    }
}