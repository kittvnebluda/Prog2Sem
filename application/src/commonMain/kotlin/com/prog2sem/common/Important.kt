package com.prog2sem.common

import com.prog2sem.common.JsonWorker.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object Important {
    val fileName = "Important"
    val autoSaveFileName = "Tmp"
    var idGen = IdGenerator()
    var isSaved = false
    var loadAutoSave: Boolean? = null

    fun save() {
        if (isSaved) FileWorker.writeFileFromEnterFilePath(fileName, json.encodeToString(Ser(idGen, isSaved)))
        else FileWorker.writeFileFromEnterFilePath(fileName + autoSaveFileName, json.encodeToString(Ser(idGen, isSaved)))
    }

    fun load() {
        val jsonString = FileWorker.readFileFromEnterFilePath(fileName)
        if (jsonString.length > 2) {
            val imp: Ser = json.decodeFromString(jsonString)
            idGen = imp.idGen
        }
    }

    fun loadAuto() {
        val jsonString = FileWorker.readFileFromEnterFilePath(fileName + autoSaveFileName)
        if (jsonString.length > 2) {
            val imp: Ser = json.decodeFromString(jsonString)
            idGen = imp.idGen
        }

    }
}
@Serializable
private data class Ser(var idGen: IdGenerator, var isSaved: Boolean)
