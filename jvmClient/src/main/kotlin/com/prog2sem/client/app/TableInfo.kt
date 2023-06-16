package com.prog2sem.client.app

import com.prog2sem.client.dbCommands
import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.client.invoker
import com.prog2sem.client.io.ColorfulOut
import com.prog2sem.client.login
import com.prog2sem.client.password
import com.prog2sem.shared.FromServer
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.exceptions.MsgException
import java.util.Collections

object TableInfo: Runnable {

    val keys = listOf(
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

    var tableNow: MutableList<FromServer> = Collections.synchronizedList(mutableListOf<FromServer>())

    //val info: MutableList<String> = Collections.synchronizedList(mutableListOf<String>())

    private var previousKey = keys[0]

    override fun run() {
        while (true) {
            try {
                dbCommands.getAllTable()
                HomePane.infoPane.text = dbCommands.info()
            } catch (e: Exception) {
                when (e) {
                    is InvalidUserInputException -> e.message?.let { ColorfulOut.printlnError(it) }
                    is MsgException -> e.message?.let { ColorfulOut.printlnError(it) }
                    is ServerNotAnsweringException -> e.message?.let { ColorfulOut.printlnError(it) }
                    else -> throw e
                }
            }
            Thread.sleep(5000)
        }
    }

    @Synchronized
    private fun tableNow(): List<FromServer>{
        return tableNow
    }

    fun getInfo(): List<FromServer>{
        return getInfo(previousKey)
    }

    fun getInfo(sortBy: String): List<FromServer>{
        val list = tableNow()
        previousKey = sortBy

           val sortedList = when(sortBy) {
                keys[0] -> list.sortedBy {it.id}
                keys[1] -> list.sortedBy {it.creteTime}
                keys[2] -> list.sortedBy {it.person.name}
                keys[3] -> list.sortedBy {it.person.weight}
                keys[4] -> list.sortedBy {it.person.height}
                keys[5] -> list.sortedBy {it.person.birthday}
                keys[6] -> list.sortedBy {it.person.hairColor}
                keys[7] -> list.sortedBy {it.person.coordinates.toString()}
                keys[8] -> list.sortedBy {it.person.location.toString()}
                else -> list
            }

        return sortedList
    }


}