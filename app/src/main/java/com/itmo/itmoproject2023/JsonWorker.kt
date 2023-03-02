package com.itmo.itmoproject2023

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer

object JsonWorker {

    val json = Json { prettyPrint = true
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

