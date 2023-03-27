package com.prog2sem.shared.net

import com.prog2sem.shared.MessageWrapper
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

/**
 * Класс обеспечивающий обмен данными с помощью датаграмм и покета nio
 */
open class NioUdpBridge (
    open var bufferCapacity: Int = 1024
) : InetBridge {

    companion object {
        /**
         * Настраивает сервер в неблокирующем режиме
         */
        fun startServer(host: InetAddress, port: Int): NioUdpBridge {
            val talker = NioUdpBridge()
            talker.channel = DatagramChannel.open()

            val address = InetSocketAddress(host, port)

            talker.channel.bind(address) // The receiver is listening
            println("Receiver started at #$address")
            talker.channel.configureBlocking(false)

            return talker
        }
        /**
         * Настраивает клиент в блокирующем режиме
         */
        fun startClient(serverAddress: InetAddress, serverPort: Int): NioUdpBridge {
            val talker = NioUdpBridge()

            talker.sendToAddress = InetSocketAddress(serverAddress, serverPort)
            talker.channel = DatagramChannel.open()

            return talker
        }
    }

    protected lateinit var sendToAddress: SocketAddress
    protected lateinit var channel: DatagramChannel

    override fun receive(): String {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)
        sendToAddress = channel.receive(buffer)
        val message = MessageWrapper.extractMsg(buffer)
        println("Received message from sender: $sendToAddress")
        return message
    }

    /**
     * Метод для отправки сообщений
     * @param address адрес принимающего, по умолчанию присваивается адресу отправителя после [receive] метода или при старте клиента
     */
    override fun send(msg: String, address: SocketAddress?) {
        val buffer: ByteBuffer = ByteBuffer.wrap(msg.toByteArray())
        channel.send(buffer, address?: sendToAddress)
    }
}