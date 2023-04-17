package com.prog2sem.shared.utils

import kotlin.math.absoluteValue

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
        val packets = cut(string).map { MsgMarker.markPacket(it, ++i) }.toMutableList()
        // Маркеруем последний пакет
        packets[packets.lastIndex] = packets[packets.lastIndex].replaceFirst("{", "{-")
        return packets
    }
    /**
     * Объединяет лист строк, помеченных как пакеты, в строку, соблюдая порядок покетов
     */
    fun merge(packets: List<String>): String {
        return packets
            .stream()
            .map { MsgMarker.getPacket(it) }
            .sorted { pair1, pair2 -> if(pair1.second.absoluteValue > pair2.second.absoluteValue) 1 else -1 }
            .map { it.first }
            .toList()
            .joinToString("")
    }
}