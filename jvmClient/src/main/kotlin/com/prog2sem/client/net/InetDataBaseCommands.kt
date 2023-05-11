package com.prog2sem.client.net

import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.client.login
import com.prog2sem.client.password
import com.prog2sem.shared.Color
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.utils.MsgMarker
import com.prog2sem.shared.net.Talker
import com.prog2sem.shared.persona.Person

class InetDataBaseCommands(val client: Talker) : DataBaseCommands {
    /**
     * @throws ServerNotAnsweringException
     */
    private inline fun <reified T> funTalk(funName: String, vararg params: String): T {
        client.send(MsgMarker.markFun(funName, *params), login, password)
        client.receive()?.let {
            return MsgMarker.getGenOrException(it)
        }
        throw ServerNotAnsweringException()
    }

    override fun info(): String {
        return funTalk("info")
    }

    override fun show(): Array<Person> {
        return funTalk("show")
    }

    override fun add(p: Person, login: String, password: String): Boolean {
        return funTalk("add", MsgMarker.markGeneric(p))
    }

    override fun update(index: Int, p: Person, login: String, password: String): Boolean {
        return funTalk("update", index.toString(), MsgMarker.markGeneric(p))
    }

    override fun removeId(index: Int, login: String, password: String): Boolean {
        return funTalk("remove_by_id", index.toString())
    }

    override fun clear(): Boolean {
        return funTalk("clear")
    }

    override fun addIfMin(p: Person, login: String, password: String): Boolean {
        return funTalk("add_if_min", MsgMarker.markGeneric(p))
    }

    override fun removeGreater(p: Person, login: String, password: String): Boolean {
        return funTalk("remove_greater", MsgMarker.markGeneric(p))
    }

    override fun removeAllByLocation(location: Location, login: String, password: String): Boolean {
        return funTalk("remove_all_by_location", MsgMarker.markGeneric(location))
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        return funTalk("filter_greater_than_hair_color", MsgMarker.markGeneric(color))
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        return funTalk("print_field_ascending_hair_color")
    }

    override fun checkLogin(login: String, password: String): Boolean {
        return funTalk("check_login")
    }

    override fun addLogin(login: String, password: String): Boolean {
        return funTalk("add_login")
    }
}