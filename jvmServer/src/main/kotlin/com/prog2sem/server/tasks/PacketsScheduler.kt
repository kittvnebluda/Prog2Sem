package com.prog2sem.server.tasks

import com.prog2sem.server.INVOKER
import com.prog2sem.server.commandsExecutor
import com.prog2sem.server.killAddressExecutor
import com.prog2sem.server.sendAddressExecutor
import com.prog2sem.server.server
import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.MakeAccessToken.checkVer
import com.prog2sem.shared.utils.MakeAccessToken.generateToken
import com.prog2sem.shared.utils.MakeAccessToken.getInfoFromToken
import com.prog2sem.shared.utils.MsgMarker
import com.prog2sem.shared.utils.Packets
import com.prog2sem.shared.utils.TokenPayload
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

object PacketsScheduler {

    private const val timeOut = 10L

    private val packetsTable = Collections.synchronizedMap(HashMap<SocketAddress, MutableList<String>>())
    private val packetsSizeTable = Collections.synchronizedMap(HashMap<SocketAddress, Int>())

    private val timeToKillAddress = Collections.synchronizedMap(HashMap<SocketAddress, ZonedDateTime>())

    @Synchronized fun addPacket(pack: String, address: SocketAddress) {
        if (!packetsTable.containsKey(address)) {

            val time = ZonedDateTime.now()

            timeToKillAddress[address] = time

            killAddressExecutor.submit(KillAddress(address, timeOut, time))

            packetsTable[address] = mutableListOf(pack)
            packetsSizeTable[address] =
                pack.substring(pack.indexOf('/') + 1, pack.indexOf('}')).toInt()
            check(address)
        } else {
            packetsTable[address]?.add(pack)
            check(address)
        }
    }

    @Synchronized fun clearAddress(address: SocketAddress, time: ZonedDateTime) {
        if (timeToKillAddress[address] == time) {
            Log.d("Kill $address")
            timeToKillAddress.remove(address)
            packetsTable.remove(address)
            packetsSizeTable.remove(address)
        }
    }

    private fun check(address: SocketAddress) {

            if (packetsTable[address]?.size == packetsSizeTable[address]) {
                val list = packetsTable[address] as List<String>
                val adr = address as InetSocketAddress
                commandsExecutor.submit { doTask(list, adr) }
            }
    }

    private fun doTask(packets: List<String>, address: InetSocketAddress) {

        timeToKillAddress.remove(address)
        packetsTable.remove(address)
        packetsSizeTable.remove(address)

        //killAddressExecutor.shutdown()

        Log.d("$address ready for schedule")

        var msg = Packets.merge(packets)

        if (checkVer(msg)) {
            val payload = getInfoFromToken(msg)

            msg = payload.info

            msg = address.hostString + ":" + address.port + ":" + msg

            INVOKER.proceed(msg, payload.login, payload.password)
        }

    }

    fun send(msg: String, address: SocketAddress, login: String, password: String){

        val msg = generateToken(TokenPayload(login, password, msg))

        Packets.generate(msg, login, password).forEach {
            val buffer: ByteBuffer = ByteBuffer.wrap(it.toByteArray())
            sendAddressExecutor.submit(Sender(buffer, address))
        }

    }

}