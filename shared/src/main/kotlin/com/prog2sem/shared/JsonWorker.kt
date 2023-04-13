package com.prog2sem.shared

import kotlinx.serialization.json.Json

object JsonWorker {

    val json = Json {
        prettyPrint = true
        encodeDefaults = true

    }

    /*fun packObject(obj: JsonElement): String {
        return json.encodeToString(obj)
    }

    fun packObjects(objects: JsonArray): String {
        return json.encodeToString(objects)
    }

    fun unpackObject(pack: String): JsonElement {
        TODO("Not yet implemented")
    }

    fun unpackObjects(pack: String): JsonArray {
        return Json.decodeFromString(pack)
    }*/

}

