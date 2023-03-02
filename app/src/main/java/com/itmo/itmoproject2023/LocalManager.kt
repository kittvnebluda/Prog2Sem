package  com.itmo.itmoproject2023

import android.util.Log
import com.itmo.itmoproject2023.DataBaseSim.dataBaseSim
import com.itmo.itmoproject2023.DataBaseSim.removedIds
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import com.itmo.itmoproject2023.JsonWorker.json
import kotlinx.serialization.encodeToString
import kotlin.math.min

/**
 * Class for managing DataBase
 *
 * @param Type_of_dataBase the type of elements of DataBase
 * @param dataBaseSim DataBase of Type_of_dataBase type elements.
 */

object LocalManager: CollectionManager{

    private val TAG = "LocalManager"

    override fun info(): String {
        return json.encodeToString(ServerAnswer(answerMessage = DataBaseSim.toString()))
    }

    override fun show(): String {
        return json.encodeToString(ServerAnswer(answerMessage =
        dataBaseSim.toString()))
    }

    override fun update(index: Int, e: Person): String {
        dataBaseSim.sortedDescending()
        val el = dataBaseSim.elementAtOrNull(index - 1)
            ?: return json.encodeToString(ServerAnswer(false, "Person with this id does not exist"))
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
        return json.encodeToString(ServerAnswer(size != dataBaseSim.size,
            if (size != dataBaseSim.size) "All Okay" else "Can not find this $id id"))
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
        val isSuccess = FileWorker.writeFileFromEnterFilePath(filePath, json.encodeToString(
            dataBaseSim))
        FileWorker.writeFileFromEnterFilePath(ImportantVal().filePath, json.encodeToString(ImportantVal(maxId = Person.maxId,
            removesIds = removedIds.toMutableList())))
        return json.encodeToString(ServerAnswer(isSuccess,
            if (isSuccess) "All Okay" else "File does not exist on this: \"$filePath\" file path\n" +
                "Can not create new file with this file path"))
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
        if (color == null) return json.encodeToString(ServerAnswer(answerMessage = dataBaseSim.toString()))
        val sb = StringBuilder()
        dataBaseSim.forEach { if (it.hairColor != null && it.hairColor!! > color)  sb.append(it.toString())}
        return json.encodeToString(ServerAnswer(answerMessage = sb.toString()))
    }

    override fun printFieldAscendingHairColor(): String {
        Person.colors.sort()
        return json.encodeToString(ServerAnswer(answerMessage = Person.colors.toString()))
    }

    override fun addIfMin(e: Person): String {
        val minPerson = dataBaseSim.minBy { it.id }
        if (minPerson.id < e.id) return json.encodeToString(ServerAnswer(false, "This person is not minimal"))
        dataBaseSim.add(e)
        return json.encodeToString(ServerAnswer())
    }
}