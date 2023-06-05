package com.prog2sem.server.DataBaseCommands

import com.prog2sem.shared.Color
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.Location
import com.prog2sem.shared.io.FileWorker
import com.prog2sem.shared.persona.Person
import com.prog2sem.shared.utils.JsonWorker.json
import kotlinx.serialization.decodeFromString
import java.sql.ResultSet
import java.time.ZonedDateTime
import java.util.Collections

object DataBaseConnector {
    private val creationDate = ZonedDateTime.now()
    var localDataBase: MutableSet<Person> = Collections.synchronizedSet(hashSetOf<Person>())

    /**
     * @param filePath путь к файлу
     */
    @Deprecated("После добавления PostgreSQL стало не нужно")
    fun readDataFromFile(filePath: String) {
        val jsonString = FileWorker.readFileFromEnterFilePath(filePath)
        if (jsonString.length < 2) return
        localDataBase = json.decodeFromString(jsonString)
    }

    fun getPersonsFromTable(req: ResultSet?, keys: List<String>) {

        while (req != null && req.next()){

            val person = Person(req.getString(keys[2]), getCoordinatesFromTable(req.getString(keys[7])),
                req.getLong(keys[4]), ZonedDateTime.parse(req.getString(keys[5])), req.getInt(keys[3]),
                Color.valueOf(req.getString(keys[6])), getLocationFromTable(req.getString(keys[8]))
            )

            localDataBase.add(person)
        }
    }

    private fun getCoordinatesFromTable(str: String): Coordinates{
        val x: Float
        val y: Double
        val ls = str.split(' ')
        x = ls[0].substring(ls[0].indexOf('X') + 2).toFloat()
        y = ls[1].substring(ls[1].indexOf('Y') + 2).toDouble()

        return Coordinates(x, y)
    }

    private fun getLocationFromTable(str: String): Location{
        val x: Float
        val y: Float
        val z: Int
        val name: String?
        val ls = str.split(' ')
        x = ls[0].substring(ls[0].indexOf('X') + 2).toFloat()
        y = ls[1].substring(ls[1].indexOf('Y') + 2).toFloat()
        z = ls[2].substring(ls[2].indexOf('Z') + 2).toInt()
        name = ls[3].substring(ls[3].indexOf("Name") + 5)

        return Location(x, y, z, name)
    }

    override fun toString(): String {
        return StringBuilder().append(
            "Type of DataBase: ", localDataBase.javaClass.typeName, "\n",
            "Date of creation: ", creationDate.dayOfMonth, " ", creationDate.month, " ", creationDate.year, "\n",
            "Time of creation: ", creationDate.hour, ":", creationDate.minute, ":", creationDate.second, "\n",
            "Count of elements: ", localDataBase.size
        ).toString()
    }
}