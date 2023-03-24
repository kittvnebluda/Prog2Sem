package com.prog2sem.shared.communication

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.channels.DatagramChannel

open class InetBridgeWithMessageWrapper : InetBridgeWrapper {

    companion object {
        fun startServer(host: InetAddress, port: Int, bridge: InetBridge): InetBridgeWithMessageWrapper {
            val talker = InetBridgeWithMessageWrapper()
            talker.channel = DatagramChannel.open()

            val address = InetSocketAddress(host, port)

            talker.channel.bind(address) // The receiver is listening
            println("Receiver started at #$address")

            talker.bridge = bridge

            return talker
        }

        fun startClient(serverAddress: InetAddress, serverPort: Int, bridge: InetBridge): InetBridgeWithMessageWrapper {
            val talker = InetBridgeWithMessageWrapper()

            talker.sendToAddress = InetSocketAddress(serverAddress, serverPort)
            talker.channel = DatagramChannel.open()

            talker.bridge = bridge

            return talker
        }
    }

    override lateinit var bridge: InetBridge

    protected lateinit var sendToAddress: SocketAddress
    protected lateinit var channel: DatagramChannel

    override fun receive(): String {
        return bridge.receive()
    }

    override fun send(msg: String, address: SocketAddress?) {
        bridge.send(msg, address)
    }
}