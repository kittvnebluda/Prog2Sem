package com.prog2sem.server

import com.prog2sem.server.DataBaseCommands.DataBaseSim
import com.prog2sem.server.DataBaseCommands.DataBaseSim.dataBaseSim
import com.prog2sem.server.DataBaseCommands.DataBaseSim.getPersonsFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.addPerson
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getAllFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getId
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getLoginId
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.loginKeys
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.personKeys
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useStatement
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useUpdateQueryPrepare
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useUpdateQueryStat
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useUpdateStat
import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.idGen
import com.prog2sem.server.Important.isSaved
import com.prog2sem.server.tasks.KnowledgeFactorySHA1.encryptThisString
import com.prog2sem.shared.Color
import com.prog2sem.shared.io.FileWorker
import com.prog2sem.shared.utils.JsonWorker.json
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.persona.Person
import com.prog2sem.shared.utils.Log
import kotlinx.serialization.encodeToString
import java.time.ZonedDateTime

/**
 * Class for managing DataBase
 */
object LocalManager : ServCom, DataBaseCommands {

    override fun info(): String {
        return DataBaseSim.toString()
    }

    override fun show(): Array<Person> {
        val persons = mutableListOf<Person>()
        dataBaseSim.forEach { persons.add(it) }
        return persons.toTypedArray()
    }

    override fun update(index: Int, p: Person, login: String, password: String): Boolean {
//        dataBaseSim.sortedDescending()

        //val personKeys = listOf("id", "createTime", "name", "weight", "height", "birthday", "hairColor", "coordinates", "location", "login", "password")

        val updateById = "update public.\"TestJDBC\" set ${personKeys[2]} = '${p.name}', ${personKeys[3]} = ${p.weight}, ${personKeys[4]} = ${p.height}, " +
                "${personKeys[5]} = '${p.birthday}', ${personKeys[6]} = '${p.hairColor}', ${personKeys[7]} = '${p.coordinates.toTable()}', " +
                "${personKeys[8]} = '${p.location.toTable()}' " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${encryptThisString(password)}' and ${personKeys[0]} = $index"

        Log.d(updateById)

        if (!useUpdateQueryStat(updateById)) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

    override fun removeId(index: Int, login: String, password: String): Boolean {

        Log.d("here")

        val com = "delete from public.\"TestJDBC\" " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${encryptThisString(password)}' and ${personKeys[0]} = $index"

        Log.d(com)

        if (!useUpdateQueryStat(
                com,
            )
        ) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

    override fun add(p: Person, login: String, password: String): Boolean {

        val hashMap = p.toHashMap(personKeys)
        hashMap[personKeys[0]] = getId()
        hashMap[personKeys[1]] = ZonedDateTime.now().toString()
        hashMap[personKeys[9]] = login
        hashMap[personKeys[10]] = encryptThisString(password)

       if (!useUpdateQueryPrepare(
            addPerson,
            personKeys,
            hashMap
        )) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

    override fun clear(): Boolean {
        var com = "delete from public.\"TestJDBC\""

        if (!useUpdateQueryStat(
                com,
            )) return false

        getPersonsFromTable(getAllFromTable(), personKeys)


        com = "select setval('id', 1)"

        useStatement(com)

        return true
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

    override fun removeGreater(p: Person, login: String, password: String): Boolean {

        val com = "delete from public.\"TestJDBC\"" +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${encryptThisString(password)}' and ${personKeys[2]} > ${p.name}"

        if (!useUpdateQueryStat(
                com,
            )) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

    override fun removeAllByLocation(location: Location, login: String, password: String): Boolean {
        val com = "delete from public.\"TestJDBC\"" +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${encryptThisString(password)}' and ${personKeys[8]} = ${location.toTable()}"

        if (!useUpdateQueryStat(
                com,
            )) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        val persons = ArrayList<Person>()
        dataBaseSim.forEach { if (color == null) persons.add(it) else if (it.hairColor > color)  persons.add(it)}
        return persons.toTypedArray()
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        Person.colors.sort()
        return Person.colors.toTypedArray()
    }

    override fun checkLogin(login: String, password: String): Boolean {
        val com = "select * from public.\"TestLogin\" where ${personKeys[1]} = '$login' and ${personKeys[2]} = '${encryptThisString(password)}'"

        return useUpdateStat(com)
    }

    override fun addLogin(login: String, password: String): Boolean {
        val com = "insert into public.\"TestLogin\" values(?, ?, ?)"

        val hashMap = HashMap<String, Any>()

        hashMap[loginKeys[0]] = getLoginId()
        hashMap[loginKeys[1]] = login
        hashMap[loginKeys[2]] = encryptThisString(password)

        return useUpdateQueryPrepare(com, loginKeys, hashMap)
    }

    override fun addIfMin(p: Person, login: String, password: String): Boolean {

        val hashMap = p.toHashMap(personKeys)
        hashMap[personKeys[0]] = getId()
        hashMap[personKeys[1]] = ZonedDateTime.now().toString()
        hashMap[personKeys[9]] = login
        hashMap[personKeys[10]] = encryptThisString(password)

        val minPerson = dataBaseSim.minBy { it.name }

        if (!useUpdateQueryPrepare(
                addPerson,
                personKeys,
                hashMap
            ) && minPerson.name < p.name) return false

        getPersonsFromTable(getAllFromTable(), personKeys)

        return true
    }

}