package com.prog2sem.shared.communication

import java.net.SocketAddress

interface InetBridgeWrapper {
    var bridge: InetBridge
    fun receive(): String
    fun send(msg: String, address: SocketAddress?)
}