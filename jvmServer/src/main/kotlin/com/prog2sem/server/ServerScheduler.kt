package com.prog2sem.server

import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.Packets
import kotlinx.coroutines.*
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.LinkedList
import java.util.Queue


object ServerScheduler {

    private const val timeOut = 10000L

    private val packetsTable = HashMap<SocketAddress, MutableList<String>>()
    private val packetsSizeTable = HashMap<SocketAddress, Int>()
    private val addressToSchedule: Queue<Pair<SocketAddress, Job>> = LinkedList()

    private val addressToKill: Queue<Pair<SocketAddress, Job>> = LinkedList()


    suspend fun scheduler() = coroutineScope {

        async {
            while (true){
                val pack: String? = server.receive()

                if (pack != null) {
                    val address = server.sendToAddress

                    if (!packetsTable.containsKey(address)) {

                        addressToKill.add(Pair(address, launch {
                            delay(timeOut)
                        }))

                        packetsTable[address] = mutableListOf(pack)
                        packetsSizeTable[address] =
                            pack.substring(pack.indexOf('/') + 1, pack.indexOf('}')).toInt()

                    } else {
                        packetsTable[address]?.add(pack)
                    }
                }
            }
        }


        async {
            while (true) {
                if (packetsTable.isNotEmpty()) {
                    val address = packetsTable.keys.elementAt(0)

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

        async {
            while (true) {
                if (addressToKill.isNotEmpty()){
                    val job = addressToKill.element().second
                    if (job.isCompleted) {
                        val address = addressToKill.remove().first
                        Log.d("Kill $address")
                        packetsTable.remove(address)
                        packetsSizeTable.remove(address)
                    }
                }
            }
        }

    }.await()

    private fun doTask(packets: List<String>, address: InetSocketAddress) {

        addressToKill.remove()
        packetsTable.remove(address)
        packetsSizeTable.remove(address)

        Log.d("$address ready for schedule")

        var msg = Packets.merge(packets)
        msg = address.hostString + ":" + address.port + ":" + msg
        INVOKER.proceed(msg)
    }
    fun send(msg: String, address: InetSocketAddress) {
        var cnt = 0

        Packets.generate(msg).forEach {
            cnt++
            server.send(it, address)
        }

        Log.d("Send $cnt packets")
    }

}