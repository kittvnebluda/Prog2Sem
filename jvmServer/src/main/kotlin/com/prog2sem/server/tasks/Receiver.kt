package com.prog2sem.server.tasks

import com.prog2sem.server.bufferCapacity
import com.prog2sem.server.server
import com.prog2sem.server.tasks.PacketsScheduler.addPacket
import com.prog2sem.shared.utils.Buffer
import com.prog2sem.shared.utils.Log
import java.net.SocketAddress
import java.nio.ByteBuffer

class Receiver: Runnable{

    private fun receive(): Pair<String?, SocketAddress?> {
        val buffer: ByteBuffer = ByteBuffer.allocate(bufferCapacity)
        var address: SocketAddress? = null

        return Pair(server.receive(buffer)?.let {
            address = it
            Buffer.toString(buffer)
        }, address)
    }

    override fun run() {

        while (true) {

            val packWithAddress = receive()

            val pack = packWithAddress.first

            if (pack != null) {
                val address = packWithAddress.second
                addPacket(pack, address as SocketAddress)
            }
        }
    }

}