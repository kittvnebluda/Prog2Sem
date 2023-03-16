package com.prog2sem.server

import application.src.commonMain.kotlin.com.prog2sem.common.Important
import application.src.commonMain.kotlin.com.prog2sem.common.Important.autoSaveFileName
import application.src.commonMain.kotlin.com.prog2sem.common.Important.idGen
import application.src.commonMain.kotlin.com.prog2sem.common.Important.isSaved
import application.src.commonMain.kotlin.com.prog2sem.common.Important.loadAuto
import com.prog2sem.common.Color
import com.prog2sem.server.DataBaseSim.dataBaseSim
import com.prog2sem.common.JsonWorker.json
import com.prog2sem.common.*
import kotlinx.serialization.encodeToString

/**
 * Class for managing DataBase
 */
class LocalManager(): CollectionManager {

    override fun info(): String {
        return json.encodeToString(ServerAnswer(answerMessage = DataBaseSim.toString()))
    }

    override fun show(): String {
        return json.encodeToString(ServerAnswer(answerMessage = dataBaseSim.toString().filter { it != '[' && it != ']'}.replace(", ", "")))
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
        idGen.newRemovedId(id)
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
        idGen.clear()
        return json.encodeToString(ServerAnswer())
    }

    override fun save(filePath: String): String {
        if (filePath != autoSaveFileName) isSaved = true
        val isSuccess = FileWorker.writeFileFromEnterFilePath(
            filePath, json.encodeToString(
                dataBaseSim
            )
        )
        Important.save()
        return json.encodeToString(
            ServerAnswer(isSuccess,
            if (isSuccess) "All Okay"
            else "File does not exist on this: \"$filePath\" file path\n Can not create new file with this file path")
        )
    }

    override fun removeGreater(e: Person): String {
        dataBaseSim.removeIf { val check = it > e; if (check) idGen.newRemovedId(it.id); return@removeIf check }
        return json.encodeToString(ServerAnswer())
    }

    override fun removeAllByLocation(location: Location): String {
        dataBaseSim.removeIf { val check = it.location == location; if (check) idGen.newRemovedId(it.id); return@removeIf check}
        return json.encodeToString(ServerAnswer())
    }

    override fun filterGreaterThanHairColor(color: Color?): String {
        if (color == null) return json.encodeToString(ServerAnswer(answerMessage = dataBaseSim.toString()))
        val sb = StringBuilder()
        dataBaseSim.forEach { if (it.hairColor > color)  sb.append(it.toString())}
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

    fun isAutoSaved(): Boolean {
        return !isSaved
    }

    fun loadAutoSave(){
        DataBaseSim.readDataFromFile(autoSaveFileName)
        loadAuto()
    }
}