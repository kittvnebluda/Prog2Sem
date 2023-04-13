package com.prog2sem.server

import com.prog2sem.server.DataBaseSim.dataBaseSim
import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.idGen
import com.prog2sem.server.Important.isSaved
import com.prog2sem.shared.Color
import com.prog2sem.shared.io.FileWorker
import com.prog2sem.shared.utils.JsonWorker.json
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.persona.Person
import kotlinx.serialization.encodeToString

/**
 * Class for managing DataBase
 */
object LocalManager : ServCom, DataBaseCommands {

    override fun info(): String {
        return DataBaseSim.toString()
    }

    override fun show(): String {
        return dataBaseSim.toString().filter { it != '[' && it != ']' }
            .replace(", ", "")
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
        return size != dataBaseSim.size
    }

    override fun add(e: Person): Boolean {
        val person = Person_Autogeneration(e)
        return dataBaseSim.add(person)
    }

    override fun clear(): Boolean {
        dataBaseSim.clear()
        idGen.clear()
        return dataBaseSim.size == 0
    }

    override fun save(filePath: String): Boolean {
        if (filePath != autoSaveFileName) isSaved = true
        val isSuccess = FileWorker.writeFileFromEnterFilePath(
            filePath, json.encodeToString(
                dataBaseSim
            )
        )
        Important.save()
        return isSuccess
    }

    override fun removeGreater(e: Person): Boolean {
        val size = dataBaseSim.size
        dataBaseSim.removeIf { val check = it.person > e; if (check) idGen.newRemovedId(it.id); return@removeIf check }
        return dataBaseSim.size != size
    }

    override fun removeAllByLocation(location: Location): Boolean {
        val size = dataBaseSim.size
        dataBaseSim.removeIf {
            val check = it.person.location == location; if (check) idGen.newRemovedId(it.id); return@removeIf check
        }
        return dataBaseSim.size != size
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        val persons = ArrayList<Person>()
        dataBaseSim.forEach { if (color == null) persons.add(it.person) else if (it.person.hairColor > color)  persons.add(it.person)}
        return persons.toTypedArray()
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        Person.colors.sort()
        return Person.colors.toTypedArray()
    }

    override fun addIfMin(e: Person): Boolean {

        val person = Person_Autogeneration(e)

        val minPerson = dataBaseSim.minBy { it.id }

        if (minPerson.id < person.id) return false
        dataBaseSim.add(person)

        return true
    }

}