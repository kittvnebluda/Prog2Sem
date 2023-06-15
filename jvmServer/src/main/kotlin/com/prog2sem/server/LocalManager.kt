package com.prog2sem.server

import com.prog2sem.server.DataBaseCommands.DataBaseConnector
import com.prog2sem.server.DataBaseCommands.DataBaseConnector.getAllInfoFromTable
import com.prog2sem.server.DataBaseCommands.DataBaseConnector.localDataBase
import com.prog2sem.server.DataBaseCommands.DataBaseConnector.getPersonsFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.addPerson
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.dataBaseScheme
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getAllFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getId
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getLoginId
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getResultSetStatement
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.loginDataBaseName
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.loginKeys
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.personDataBaseName
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.personKeys
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useStatement
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useUpdateQueryPrepare
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useUpdateQueryStat
import com.prog2sem.shared.Color
import com.prog2sem.shared.FromServer
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.persona.Person
import com.prog2sem.shared.utils.Log
import java.time.ZonedDateTime

/**
 * Class for managing DataBase
 */
object LocalManager : DataBaseCommands {

    override fun info(): String {
        return DataBaseConnector.toString()
    }

    override fun show(): Array<Person> {
        val persons = mutableListOf<Person>()
        localDataBase.forEach { persons.add(it) }
        return persons.toTypedArray()
    }

    override fun update(index: Int, p: Person, login: String, password: String): Boolean {
//        dataBaseSim.sortedDescending()

        //val personKeys = listOf("id", "createTime", "name", "weight", "height", "birthday", "hairColor", "coordinates", "location", "login", "password")

        val updateById = "update $dataBaseScheme.\"$personDataBaseName\" set ${personKeys[2]} = '${p.name}', ${personKeys[3]} = ${p.weight}, ${personKeys[4]} = ${p.height}, " +
                "${personKeys[5]} = '${p.birthday}', ${personKeys[6]} = '${p.hairColor}', ${personKeys[7]} = '${p.coordinates.toTable()}', " +
                "${personKeys[8]} = '${p.location.toTable()}' " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${password}' and ${personKeys[0]} = $index"

        Log.d(updateById)

        if (!useUpdateQueryStat(updateById)) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun removeId(index: Int, login: String, password: String): Boolean {

        Log.d("here")

        val com = "delete from $dataBaseScheme.\"$personDataBaseName\" " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${password}' and ${personKeys[0]} = $index"

        Log.d(com)

        if (!useUpdateQueryStat(
                com,
            )
        ) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun add(p: Person, login: String, password: String): Boolean {

        val hashMap = p.toHashMap(personKeys)
        hashMap[personKeys[0]] = getId()
        hashMap[personKeys[1]] = ZonedDateTime.now().toString()
        hashMap[personKeys[9]] = login
        hashMap[personKeys[10]] = password

       if (!useUpdateQueryPrepare(
            addPerson,
            personKeys,
            hashMap
        )) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun clear(): Boolean {
        var com = "delete from $dataBaseScheme.\"$personDataBaseName\""

        if (!useUpdateQueryStat(
                com,
            )) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)


        com = "select setval('id', 1)"

        useStatement(com)

        localDataBase.clear()

        return true
    }

    override fun removeGreater(p: Person, login: String, password: String): Boolean {

        val com = "delete from $dataBaseScheme.\"$personDataBaseName\" " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${password}' and ${personKeys[2]} > '${p.name}'"


        if (!useUpdateQueryStat(
                com,
            )) return false


        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun removeAllByLocation(location: Location, login: String, password: String): Boolean {
        val com = "delete from $dataBaseScheme.\"$personDataBaseName\" " +
                "where ${personKeys[9]} = '$login' and ${personKeys[10]} = '${password}' and ${personKeys[8]} = '${location.toTable()}'"

        if (!useUpdateQueryStat(
                com,
            )) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        val persons = ArrayList<Person>()
        localDataBase.forEach { if (color == null) persons.add(it) else if (it.hairColor > color)  persons.add(it)}
        return persons.toTypedArray()
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        Person.colors.sort()
        return Person.colors.toTypedArray()
    }

    override fun login(login: String, password: String): Boolean {
        val com = "select * from $dataBaseScheme.\"$loginDataBaseName\" where ${loginKeys[1]} = '$login' and ${loginKeys[2]} = '${password}'"

        return getResultSetStatement(com)?.next() ?: false
    }

    override fun signup(login: String, password: String): Boolean {
        if (!login(login, password)) {
            val com = "insert into $dataBaseScheme.\"$loginDataBaseName\" values(?, ?, ?)"
            val hashMap = HashMap<String, Any>()

            hashMap[loginKeys[0]] = getLoginId()
            hashMap[loginKeys[1]] = login
            hashMap[loginKeys[2]] = password

            return useUpdateQueryPrepare(com, loginKeys, hashMap)
        }
        return false
    }

    override fun addIfMin(p: Person, login: String, password: String): Boolean {

        val hashMap = p.toHashMap(personKeys)
        hashMap[personKeys[0]] = getId()
        hashMap[personKeys[1]] = ZonedDateTime.now().toString()
        hashMap[personKeys[9]] = login
        hashMap[personKeys[10]] = password

        val minPerson = localDataBase.minBy { it.name }

        if (!useUpdateQueryPrepare(
                addPerson,
                personKeys,
                hashMap
            ) && minPerson.name < p.name) return false

        getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)

        return true
    }

    override fun getAllTable(): List<FromServer> {
        return getAllInfoFromTable(getAllFromTable(personDataBaseName), personKeys)
    }

}