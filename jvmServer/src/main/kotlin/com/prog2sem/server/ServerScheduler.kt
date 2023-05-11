package com.prog2sem.server

import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.Packets
import kotlinx.coroutines.*
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.util.LinkedList
import java.util.Queue


object ServerScheduler {


    private val addressToSchedule: Queue<Pair<SocketAddress, Job>> = LinkedList()




    suspend fun scheduler() = coroutineScope {


        async {
            while (true) {


            }
        }

        async {

        }

    }.await()

}