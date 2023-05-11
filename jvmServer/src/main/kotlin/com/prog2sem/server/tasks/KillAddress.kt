package com.prog2sem.server.tasks

import com.prog2sem.server.tasks.PacketsScheduler.clearAddress
import java.net.SocketAddress
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

class KillAddress(val address: SocketAddress, private val timeOut: Long, private val time: ZonedDateTime): Runnable {

    override fun run() {
        TimeUnit.SECONDS.sleep(timeOut)
        clearAddress(address, time)
    }

}