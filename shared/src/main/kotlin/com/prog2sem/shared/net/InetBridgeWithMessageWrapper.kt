package com.prog2sem.shared.net

import com.prog2sem.shared.net.wrappers.MessageWrapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.channels.DatagramChannel

open class InetBridgeWithMessageWrapper {

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

    lateinit var bridge: InetBridge

    protected lateinit var sendToAddress: SocketAddress
    protected lateinit var channel: DatagramChannel

    fun receive(): MessageWrapper {
        return Json.decodeFromString(bridge.receive())
    }

    fun send(msg: MessageWrapper, address: SocketAddress?) {
        bridge.send(Json.encodeToString(msg), address)
    }
}