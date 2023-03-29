package com.prog2sem.shared.net

import com.prog2sem.shared.utils.Buffer
import java.net.SocketAddress
import java.net.SocketTimeoutException
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import kotlin.jvm.Throws

open class NioUdpClient(
    private val bufferCapacity: Int = 1024,
    timeout: Int = 10000
) : CanReceive, CanSendToAddress {

    var channel: DatagramChannel = DatagramChannel.open()

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
}