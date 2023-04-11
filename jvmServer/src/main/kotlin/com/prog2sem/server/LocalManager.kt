package com.prog2sem.server

import com.prog2sem.shared.Color
import com.prog2sem.server.DataBaseSim.dataBaseSim
import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.idGen
import com.prog2sem.server.Important.isSaved
import com.prog2sem.shared.JsonWorker.json
import com.prog2sem.shared.*
import com.prog2sem.shared.persona.Person
import kotlinx.serialization.encodeToString

/**
 * Class for managing DataBase
 */
class LocalManager : ServerCommands {

    override fun info(): String {
        return json.encodeToString(ServerAnswer(answerMessage = DataBaseSim.toString()))
    }

    override fun show(): String {
        return json.encodeToString(ServerAnswer(answerMessage = dataBaseSim.toString().filter { it != '[' && it != ']' }
            .replace(", ", "")))
    }

    override fun update(index: Int, e: Person): Boolean {
//        dataBaseSim.sortedDescending()

        val newPerson = Person_Autogeneration(e)

        val el = dataBaseSim.elementAtOrNull(index - 1) ?: return TODO("Добавить возращение")
        with(el) {
            person.name = newPerson.person.name
            person.coordinates = newPerson.person.coordinates
            person.birthday = newPerson.person.birthday
            person.height = newPerson.person.height
            person.weight = newPerson.person.weight
            person.hairColor = newPerson.person.hairColor
            person.location = newPerson.person.location
            creationDate = newPerson.creationDate
        }
        removeId(newPerson.id)
        return TODO("Добавить возращение")
    }

    override fun removeId(id: Int): Boolean {
        val size = dataBaseSim.size
        dataBaseSim.removeIf { it.id == id }
        idGen.newRemovedId(id)
        return TODO("Добавить возращение")
    }

    override fun add(e: Person): Boolean {
        val person = Person_Autogeneration(e)
        val isAdd = dataBaseSim.add(person)
        return TODO("Добавить возращение")
    }

    override fun clear(): Boolean {
        dataBaseSim.clear()
        idGen.clear()
        return TODO("Добавить возращение")
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
            ServerAnswer(
                isSuccess,
                if (isSuccess) "All Okay"
                else "File does not exist on this: \"$filePath\" file path or permission denied"
            )
        )
    }

    override fun removeGreater(e: Person): Boolean {
        val person = Person_Autogeneration(e)
        dataBaseSim.removeIf { val check = it > person; if (check) idGen.newRemovedId(it.id); return@removeIf check }
        return TODO("Добавить возращение")
    }

    override fun removeAllByLocation(location: Location): Boolean {
        dataBaseSim.removeIf {
            val check = it.person.location == location; if (check) idGen.newRemovedId(it.id); return@removeIf check
        }
        return TODO("Добавить возращение")
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        if (color == null) return TODO("Добавить возращение")
        val sb = StringBuilder()
        dataBaseSim.forEach { if (it.person.hairColor > color) sb.append(it.toString()) }
        return TODO("Добавить возращение")
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        Person.colors.sort()
        return TODO("Добавить возращение")
    }

    override fun addIfMin(e: Person): Boolean {

        val person = Person_Autogeneration(e)

        val minPerson = dataBaseSim.minBy { it.id }

        if (minPerson.id < person.id) return TODO("Добавить возращение")
        dataBaseSim.add(person)

        return TODO("Добавить возращение")
    }

}