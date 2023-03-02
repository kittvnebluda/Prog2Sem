package com.itmo.itmoproject2023

import android.annotation.SuppressLint
import client
import com.itmo.itmoproject2023.DataBaseSim.dataBaseSim
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import com.itmo.itmoproject2023.JsonWorker.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File
import java.util.LinkedList
import java.util.Queue

fun main(args:Array<String>) {
    //FileWorker.clearFile(ImportantVal().filePath)
    if (args.isNotEmpty())
        //FileWorker.clearFile(args[0])
        DataBaseSim.readDataFromFile(args[0])
    val jsonString = FileWorker.readFileFromEnterFilePath(ImportantVal().filePath)
    if (jsonString != "") {
        val impval: ImportantVal = json.decodeFromString(jsonString)
        DataBaseSim.removedIds = LinkedList(impval.removesIds)
        Person.maxId = impval.maxId
    }
    client(args)
}
