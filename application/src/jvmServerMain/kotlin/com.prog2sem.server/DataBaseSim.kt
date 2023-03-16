package com.prog2sem.server

import com.prog2sem.common.FileWorker
import java.time.ZonedDateTime
import com.prog2sem.common.JsonWorker.json
import com.prog2sem.common.Person
import kotlinx.serialization.decodeFromString

object DataBaseSim {
    private val creationDate = ZonedDateTime.now()
    var dataBaseSim: HashSet<Person> = hashSetOf()

    /**
     * @param filePath
     * @return
     */
    fun readDataFromFile(filePath: String){
        val jsonString = FileWorker.readFileFromEnterFilePath(filePath)
        if (jsonString.length > 2) return
        dataBaseSim = json.decodeFromString(jsonString)
    }

    /**
     * @param
     * @return
     */
    override fun toString(): String {
        return StringBuilder().append(
            "Type of DataBase: ", dataBaseSim.javaClass.typeName, "\n",
            "Date of creation: ", creationDate.dayOfMonth, " ", creationDate.month, " ", creationDate.year, "\n",
            "Time of creation: ", creationDate.hour, ":", creationDate.minute, ":", creationDate.second, "\n",
            "Count of elements: ", dataBaseSim.size).toString()
    }
}