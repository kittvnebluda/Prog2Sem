package com.prog2sem.server

import com.prog2sem.shared.io.FileWorker
import com.prog2sem.shared.utils.JsonWorker.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object Important {
    private const val fileName = "Important"
    const val autoSaveFileName = "Tmp"
    var idGen = IdGenerator()
    var isSaved = false

    fun save() {
        idGen.save()
        if (isSaved) FileWorker.writeFileFromEnterFilePath(
            fileName,
            json.encodeToString(Ser(idGen, isSaved))
        )
        else FileWorker.writeFileFromEnterFilePath(
            fileName + autoSaveFileName,
            json.encodeToString(Ser(idGen, isSaved))
        )
    }

    fun load() {
        val jsonString = FileWorker.readFileFromEnterFilePath(fileName)
        if (jsonString.length > 2) {
            val imp: Ser = json.decodeFromString(jsonString)
            idGen = imp.idGen
            isSaved = imp.isSaved
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
