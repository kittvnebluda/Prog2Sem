package com.prog2sem.server

import com.prog2sem.shared.net.NioUdpServer
import com.prog2sem.shared.utils.Buffer
import java.net.InetAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.nio.channels.SelectionKey

object Scheduler {

    fun listener() {
        /*println(SELECTOR.select())
        val keys = SELECTOR.selectedKeys()
        while (keys.iterator().hasNext()){
            val it : SelectionKey = keys.iterator().next()
            if (it.isValid) {
                println("New challenger: $it")
                if (it.isReadable) {
                    val dc = it.channel() as DatagramChannel
                    val buffer: ByteBuffer = ByteBuffer.allocate(1024)
                    dc.register(it.selector(), SelectionKey.OP_WRITE)
                    SHEDULER.sendToAddress = dc.receive(buffer)
                    INVOKER.proceed(Buffer.toString(buffer))
                }

            }
        }
        SELECTOR.selectedKeys().clear()*/
        val rec = SHEDULER.receive()
        if (rec.isNotEmpty()) INVOKER.proceed(rec)
    }

/*    fun speaker() {
        println("Speak")
        SELECTOR.select()
        val keys = SELECTOR.selectedKeys()
        keys.forEach {
            if (it.isValid) {
                val dc = it.channel() as DatagramChannel
                val data = it.attachment() as ByteBuffer
                if (it.isWritable && it.isValid) {
                    dc.write(data)
                }
            }
        }
        INVOKER.proceed(SHEDULER.receive())
    }
 */
}