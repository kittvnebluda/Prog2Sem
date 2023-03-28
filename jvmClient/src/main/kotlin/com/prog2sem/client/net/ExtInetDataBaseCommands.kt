package com.prog2sem.client.net

import com.prog2sem.shared.Color
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.net.TempSaveCommands
import com.prog2sem.shared.persona.Person

class ExtInetDataBaseCommands : DataBaseCommands, TempSaveCommands {
    override fun info(): String {
        TODO("Not yet implemented")
    }

    override fun show(): String {
        TODO("Not yet implemented")
    }

    override fun add(p: Person): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(index: Int, p: Person): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeId(index: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear(): Boolean {
        TODO("Not yet implemented")
    }

    override fun addIfMin(p: Person): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeGreater(p: Person): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAllByLocation(location: Location): Boolean {
        TODO("Not yet implemented")
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        TODO("Not yet implemented")
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        TODO("Not yet implemented")
    }

    override fun isTempSaveExist(): Boolean {
        TODO("Not yet implemented")
    }

    override fun loadTempSave(): Boolean {
        TODO("Not yet implemented")
    }
}