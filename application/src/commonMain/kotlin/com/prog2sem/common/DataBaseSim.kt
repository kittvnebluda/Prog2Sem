package com.prog2sem.common

import java.time.ZonedDateTime
import java.util.LinkedList
import java.util.Queue
import com.prog2sem.common.JsonWorker.json
import kotlinx.serialization.decodeFromString

object DataBaseSim {
    private val creationDate = ZonedDateTime.now()

    var removedIds:Queue<Int> = LinkedList()
    var dataBaseSim: HashSet<Person> = hashSetOf()

    /**
     * @param filePath путь к файлу
     */
    fun readDataFromFile(filePath: String){
        val jsonString = FileWorker.readFileFromEnterFilePath(filePath)
        if (jsonString == "") return
        dataBaseSim = json.decodeFromString(jsonString)
    }

    override fun toString(): String {
        return StringBuilder().append(
            "Type of DataBase: ", dataBaseSim.javaClass.typeName, "\n",
            "Date of creation: ", creationDate.dayOfMonth, " ", creationDate.month, " ", creationDate.year, "\n",
            "Time of creation: ", creationDate.hour, ":", creationDate.minute, ":", creationDate.second, "\n",
            "Count of elements: ", dataBaseSim.size).toString()
    }
}