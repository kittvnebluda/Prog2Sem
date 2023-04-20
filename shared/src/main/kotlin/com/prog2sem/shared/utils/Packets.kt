package com.prog2sem.shared.utils

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
        val list = cut(string)
        var i = 0
        return list.map { MsgMarker.markPacket(it, ++i, list.size) }.toMutableList()
    }
    /**
     * Объединяет лист строк, помеченных как пакеты, в строку, соблюдая порядковый номер пакетов
     */
    fun merge(packets: List<String>): String {
        return packets
            .stream()
            .map { MsgMarker.getPacket(it) }
            .sorted { pair1, pair2 -> if(pair1.second > pair2.second) 1 else -1 }
            .map { it.first }
            .toList()
            .joinToString("")
    }
}