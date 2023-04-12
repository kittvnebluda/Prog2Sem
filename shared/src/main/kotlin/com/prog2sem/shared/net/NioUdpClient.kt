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
    private val bufferCapacity: Int = 1024
) : Talker, AddressTalker {

    var channel: DatagramChannel = DatagramChannel.open()
    lateinit var sendToAddress: SocketAddress

    init {
        channel.configureBlocking(false)
    }

    override fun receive(): String {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)

        var counter = 0
        var address: SocketAddress?
        do {
            counter += 1
            address = channel.receive(buffer)
            when(counter % 10) {
                1 -> print("\rЖдем    ")
                3 -> print("\rЖдем..  ")
                6 -> print("\rЖдем... ")
                9 -> print("\rЖдем... ")
            }
        } while (address == null && counter < 2000000)

        val message = Buffer.toString(buffer)
        println("Received message from server: $address")
        return message
    }

    override fun send(msg: String, address: SocketAddress) {
        println("Sending: $msg")
        val buffer: ByteBuffer = ByteBuffer.wrap(msg.toByteArray())
        channel.send(buffer, address)
    }

    override fun send(msg: String) {
        send(msg, sendToAddress)
    }
}