package com.prog2sem.server

import com.prog2sem.common.JsonWorker.json
import com.prog2sem.common.DataBaseSim
import com.prog2sem.common.FileWorker
import com.prog2sem.common.Person
import kotlinx.serialization.decodeFromString
import java.util.LinkedList

fun main(args:Array<String>) {
//    com.prog2sem.common.FileWorker.clearFile(ImportantVal().filePath)
    if (args.isNotEmpty())
//        com.prog2sem.common.FileWorker.clearFile(args[0])
        DataBaseSim.readDataFromFile(args[0])

    val jsonString = FileWorker.readFileFromEnterFilePath(ImportantVal().filePath)

    if (jsonString != "") {
        val impval: ImportantVal = json.decodeFromString(jsonString)
        DataBaseSim.removedIds = LinkedList(impval.removesIds)
        Person.maxId = impval.maxId
    }
}
