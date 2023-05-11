package com.prog2sem.server.tasks

import com.prog2sem.server.server
import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.Packets
import java.net.SocketAddress
import java.nio.ByteBuffer

class Sender(private val msg: ByteBuffer, val address: SocketAddress): Runnable {

    override fun run() {
        server.send(msg, address)
    }

}