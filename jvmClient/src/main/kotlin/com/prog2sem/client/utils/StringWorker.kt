package com.prog2sem.client.utils

object StringWorker {
    /** Удаляет лишние пробелы и разделяет строку по пробелам */
    fun splitSpaces(s: String): List<String> = s.trim().replace("\\s+".toRegex(), " ").split(" ")
}