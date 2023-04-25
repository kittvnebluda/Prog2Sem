package com.prog2sem.server

import com.prog2sem.shared.net.UDPServer
import com.prog2sem.shared.utils.Packets
import kotlinx.coroutines.*
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.LinkedList
import java.util.Queue

object ServerScheduler {

    private const val timeOut = 10000L

    private val packetsTable = HashMap<SocketAddress, MutableList<String>>()
    private val packetsSizeTable = HashMap<SocketAddress, Int>()
    private val addressToKill: Queue<Pair<SocketAddress, Job>> = LinkedList()

    suspend fun scheduler() = coroutineScope {

        async {
            while (true) {

                val pack: String? = server.receive()

                if (pack != null) {
                    val address = server.sendToAddress

                    if (!packetsTable.containsKey(address)) {
                        addressToKill.add(Pair(address, async {
                            delay(timeOut)
                        }))
                        packetsTable[address] = mutableListOf(pack)
                        packetsSizeTable[address] =
                            pack.substring(pack.indexOf('/') + 1, pack.indexOf('}')).toInt()
                        if (packetsTable[address]?.size == packetsSizeTable[address]){
                            val list = packetsTable[address] as List<String>
                            val adr = address as InetSocketAddress
                            doTask(
                                list,
                                adr
                            )
                        }
                    } else {
                        packetsTable[address]?.add(pack)
                        println(packetsTable[address])
                        if (packetsTable[address]?.size == packetsSizeTable[address]) {
                            val list = packetsTable[address] as List<String>
                            val adr = address as InetSocketAddress
                            doTask(
                                list,
                                adr
                            )
                        }
                    }
                }
            }
        }

        async {
            while (true) {
                if (addressToKill.isNotEmpty()){
                    val job = addressToKill.element().second
                    if (job.isCompleted) {
                        val address = addressToKill.remove().first
                        println("Remove address $address")
                        packetsTable.remove(address)
                        packetsSizeTable.remove(address)
                    }
                }
            }
        }
    }.await()

    private fun checkAddressToKill(address: InetSocketAddress): Boolean {
        var t = false
        for (el in addressToKill) {
            t = el.first == address
            if (t) break
        }
        return t
    }
    private fun doTask(packets: List<String>, address: InetSocketAddress) {

        addressToKill.remove()
        packetsTable.remove(address)
        packetsSizeTable.remove(address)

        var msg = Packets.merge(packets)
        msg = address.hostString + ":" + address.port + ":" + msg
        println(msg)
        INVOKER.proceed(msg)
    }
    private suspend fun killAddress(addressToKill: SocketAddress) = coroutineScope {

    }

    fun send(msg: String, address: InetSocketAddress) {
        var cnt = 0
        Packets.generate(msg).forEach {
            cnt++
            server.send(it, address)
        }
        println("Sent $cnt packet(s)")
    }

}