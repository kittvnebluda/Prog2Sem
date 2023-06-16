package com.prog2sem.client.app

import com.prog2sem.client.dbCommands
import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.client.io.ColorfulOut
import com.prog2sem.shared.FromServer
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.exceptions.MsgException
import java.util.Collections

object TableInfo: Runnable {

    val keys = arrayOf(
        "id",
        "createTime",
        "name",
        "weight",
        "height",
        "birthday",
        "hairColor",
        "coordinates",
        "location",
        "login",
        "pass")

    val keysWithNoLogin = arrayOf(
        "id",
        "createTime",
        "name",
        "weight",
        "height",
        "birthday",
        "hairColor",
        "coordinates",
        "location")

    var tableNow: MutableList<FromServer> = Collections.synchronizedList(mutableListOf<FromServer>())

    //val info: MutableList<String> = Collections.synchronizedList(mutableListOf<String>())

    var previousKey = keys[0]

    override fun run() {
        while (true) {
            try {
                tableNow = Collections.synchronizedList(getInfo(dbCommands.getAllTable(), previousKey))
                HomePane.infoPane.text = dbCommands.info()
                HomePane.updateTable()
            } catch (e: Exception) {
                when (e) {
                    is InvalidUserInputException -> e.message?.let { ColorfulOut.printlnError(it) }
                    is MsgException -> e.message?.let { ColorfulOut.printlnError(it) }
                    is ServerNotAnsweringException -> e.message?.let { ColorfulOut.printlnError(it) }
                    else -> throw e
                }
            }
            Thread.sleep(4000)
        }
    }

    @Synchronized
    private fun tableNow(): List<FromServer>{
        return tableNow
    }

    fun getInfo(mutableList: MutableList<FromServer>, sortBy: String): List<FromServer>{
        previousKey = sortBy

        val sortedList = when (sortBy) {
            keys[0] -> mutableList.sortedBy { it.id }
            keys[1] -> mutableList.sortedBy { it.creteTime }
            keys[2] -> mutableList.sortedBy { it.person.name }
            keys[3] -> mutableList.sortedBy { it.person.weight }
            keys[4] -> mutableList.sortedBy { it.person.height }
            keys[5] -> mutableList.sortedBy { it.person.birthday }
            keys[6] -> mutableList.sortedBy { it.person.hairColor }
            keys[7] -> mutableList.sortedBy { it.person.coordinates.toString() }
            keys[8] -> mutableList.sortedBy { it.person.location.toString() }
            else -> mutableList
        }

        return sortedList
    }


}