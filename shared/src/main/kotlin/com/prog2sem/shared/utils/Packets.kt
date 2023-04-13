package com.prog2sem.shared.utils

import com.prog2sem.shared.net.MsgMarker

object Packets {
    /**
     * Делит строку на куски
     */
    private fun cut(string: String): List<String> {
        return string.chunked(100)
    }
    /**
     * Генерирует лист строк с метками пакета
     */
    fun generate(string: String): List<String> {
        var i = 0
        return cut(string).map { MsgMarker.markPacket(it, ++i) }.toList()
    }
    /**
     * Объединяет лист строк, помеченных как пакеты, в строку, соблюдая порядок покетов
     */
    fun merge(packets: List<String>): String {
        return packets
            .stream()
            .map { MsgMarker.getPacket(it) }
            .sorted { pair1, pair2 -> if(pair1.first > pair2.first) 1 else -1 }
            .map { it.second }
            .toList()
            .joinToString("")
    }
}