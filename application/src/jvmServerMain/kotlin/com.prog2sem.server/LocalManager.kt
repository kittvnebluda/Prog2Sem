package com.prog2sem.server

import com.prog2sem.common.Color
import com.prog2sem.common.DataBaseSim.dataBaseSim
import com.prog2sem.common.DataBaseSim.removedIds
import com.prog2sem.common.JsonWorker.json
import com.prog2sem.common.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.util.*

/**
 * Class for managing DataBase
 */
class LocalManager(filename: String): CollectionManager {
    init {
        if (filename.isNotEmpty())
            DataBaseSim.readDataFromFile(filename)

        val jsonString = FileWorker.readFileFromEnterFilePath(ImportantVal().filePath)

        if (jsonString != "") {
            val impval: ImportantVal = json.decodeFromString(jsonString)
            removedIds = LinkedList(impval.removesIds)
            Person.maxId = impval.maxId
        }
    }

    override fun info(): String {
        return json.encodeToString(ServerAnswer(answerMessage = DataBaseSim.toString()))
    }

    override fun show(): String {
        return json.encodeToString(ServerAnswer(answerMessage = dataBaseSim.toString()))
    }

    override fun update(index: Int, e: Person): String {
//        dataBaseSim.sortedDescending()
        val el = dataBaseSim.elementAtOrNull(index - 1) ?:
            return json.encodeToString(ServerAnswer(false, "com.prog2sem.common.Person with this id does not exist"))
        with(el){
            name = e.name
            coordinates = e.coordinates
            birthday = e.birthday
            height = e.height
            weight = e.weight
            hairColor = e.hairColor
            location = e.location
            creationDate = e.creationDate
        }
        return json.encodeToString(ServerAnswer())
    }

    override fun removeId(id: Int): String {
        val size = dataBaseSim.size
        dataBaseSim.removeIf { it.id == id }
        removedIds.add(id)
        return json.encodeToString(
            ServerAnswer(size != dataBaseSim.size,
            if (size != dataBaseSim.size) "All Okay" else "Can not find this $id id")
        )
    }

    override fun add(e: Person): String {
        val isAdd = dataBaseSim.add(e)
        return json.encodeToString(ServerAnswer(isAdd, if (!isAdd) "Can not add new el in DataBase" else "All Okay"))
    }

    override fun clear(): String {
        dataBaseSim.clear()
        Person.clear()
        return json.encodeToString(ServerAnswer())
    }

    override fun save(filePath: String): String {
        val isSuccess = FileWorker.writeFileFromEnterFilePath(
            filePath, json.encodeToString(
                dataBaseSim
            )
        )
        FileWorker.writeFileFromEnterFilePath(
            ImportantVal().filePath, json.encodeToString(
                ImportantVal(
                    maxId = Person.maxId,
                    removesIds = removedIds.toMutableList()
                )
            )
        )
        return json.encodeToString(
            ServerAnswer(isSuccess,
            if (isSuccess) "All Okay"
            else "File does not exist on this: \"$filePath\" file path\n Can not create new file with this file path")
        )
    }

    override fun removeGreater(e: Person): String {
        dataBaseSim.removeIf { val check = it > e; if (check) removedIds.add(it.id); return@removeIf check }
        return json.encodeToString(ServerAnswer())
    }

    override fun removeAllByLocation(location: Location): String {
        dataBaseSim.removeIf { val check = it.location == location; if (check) removedIds.add(it.id); return@removeIf check}
        return json.encodeToString(ServerAnswer())
    }

    override fun filterGreaterThanHairColor(color: Color?): String {
        if (color == null)
            return json.encodeToString(ServerAnswer(answerMessage = json.encodeToString(dataBaseSim.toArray())))

        val valid = mutableListOf<Person>()
        dataBaseSim.forEach {
            if (it.hairColor > color)
                valid.add(it)
        }
        return json.encodeToString(ServerAnswer(answerMessage = json.encodeToString(valid)))
    }

    override fun printFieldAscendingHairColor(): String {
        Person.colors.sort()
        return json.encodeToString(ServerAnswer(answerMessage = json.encodeToString(Person.colors)))
    }

    override fun addIfMin(e: Person): String {
        val minPerson = dataBaseSim.minBy { it.id }
        if (minPerson.id < e.id) return json.encodeToString(ServerAnswer(false, "This person is not minimal"))
        dataBaseSim.add(e)
        return json.encodeToString(ServerAnswer())
    }
}