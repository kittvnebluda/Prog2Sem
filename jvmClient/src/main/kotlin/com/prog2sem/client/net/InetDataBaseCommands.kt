package com.prog2sem.client.net

import com.prog2sem.shared.Color
import com.prog2sem.shared.Location
import com.prog2sem.shared.net.*
import com.prog2sem.shared.persona.Person
import kotlinx.serialization.encodeToString

class InetDataBaseCommands(val client: Talker) : DataBaseCommands {
    private inline fun <reified T> funTalk(funName: String, vararg params: String): T {
        client.send(MsgMarker.markFun(funName, *params))
        val response = client.receive()
        return MsgMarker.getGenOrException(response)
    }

    override fun info(): String {
        return funTalk("info")
    }

    override fun show(): String {
        return funTalk("show")
    }

    override fun add(p: Person): Boolean {
        return funTalk("add", MsgMarker.markGeneric(p))
    }

    override fun update(index: Int, p: Person): Boolean {
        return funTalk("update", index.toString(), MsgMarker.markGeneric(p))
    }

    override fun removeId(index: Int): Boolean {
        return funTalk("remove_by_id", index.toString())
    }

    override fun clear(): Boolean {
        return funTalk("clear")
    }

    override fun addIfMin(p: Person): Boolean {
        return funTalk("add_if_min", MsgMarker.markGeneric(p))
    }

    override fun removeGreater(p: Person): Boolean {
        return funTalk("remove_greater", MsgMarker.markGeneric(p))
    }

    override fun removeAllByLocation(location: Location): Boolean {
        return funTalk("remove_all_by_location", MsgMarker.markGeneric(location))
    }

    override fun filterGreaterThanHairColor(color: Color?): Array<Person> {
        return funTalk("filter_greater_than_hair_color", MsgMarker.markGeneric(color))
    }

    override fun printFieldAscendingHairColor(): Array<Color> {
        return funTalk("print_field_ascending_hair_color")
    }
}