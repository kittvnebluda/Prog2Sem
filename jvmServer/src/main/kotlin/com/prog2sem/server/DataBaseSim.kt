package com.prog2sem.server

import com.prog2sem.shared.FileWorker
import com.prog2sem.shared.JsonWorker.json
import kotlinx.serialization.decodeFromString
import java.time.ZonedDateTime

object DataBaseSim {
    private val creationDate = ZonedDateTime.now()
    var dataBaseSim: HashSet<Person_Autogeneration> = hashSetOf()

    /**
     * @param filePath путь к файлу
     */
    fun readDataFromFile(filePath: String) {
        val jsonString = FileWorker.readFileFromEnterFilePath(filePath)
        if (jsonString.length < 2) return
        dataBaseSim = json.decodeFromString(jsonString)
    }

    override fun toString(): String {
        return StringBuilder().append(
            "Type of DataBase: ", dataBaseSim.javaClass.typeName, "\n",
            "Date of creation: ", creationDate.dayOfMonth, " ", creationDate.month, " ", creationDate.year, "\n",
            "Time of creation: ", creationDate.hour, ":", creationDate.minute, ":", creationDate.second, "\n",
            "Count of elements: ", dataBaseSim.size
        ).toString()
    }
}