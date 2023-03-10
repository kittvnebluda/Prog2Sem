package com.prog2sem.client

import com.prog2sem.common.*
import com.prog2sem.server.LocalManager
import com.prog2sem.server.ServerAnswer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * @param filename путь к файлу сохранения
 */
class LocalDataBaseCommands(private val filename: String) : DataBaseCommands, TempSaveDBCommands {
    private val manager = LocalManager(filename)

    override fun info(): Response<String> {
        val jsonString = manager.info()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, sa.answerMessage, "")
    }

    override fun show(): Response<String> {
        val jsonString = manager.show()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, sa.answerMessage, "")
    }

    override fun add(p: Person): SimpleResponse {
        val jsonString = manager.add(p)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun update(index: Int, p: Person): SimpleResponse {
        val jsonString = manager.update(index, p)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun removeId(index: Int): SimpleResponse {
        val jsonString = manager.removeId(index)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun clear(): SimpleResponse {
        val jsonString = manager.clear()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun save(): SimpleResponse {
        val jsonString = manager.save(filename)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun addIfMin(p: Person): SimpleResponse {
        val jsonString = manager.addIfMin(p)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun removeGreater(p: Person): SimpleResponse {
        val jsonString = manager.removeGreater(p)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun removeAllByLocation(location: Location): SimpleResponse {
        val jsonString = manager.removeAllByLocation(location)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return SimpleResponse(sa.isSuccess)
    }

    override fun filterGreaterThanHairColor(color: Color?): Response<Array<Person>> {
        val jsonString = manager.filterGreaterThanHairColor(color)
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, Json.decodeFromString(sa.answerMessage))
    }

    override fun printFieldAscendingHairColor(): Response<Array<Color>> {
        val jsonString = manager.printFieldAscendingHairColor()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, Json.decodeFromString(sa.answerMessage))
    }

    override fun isTempSaveExist(): SimpleResponse {
        TODO("Not yet implemented")
    }

    override fun loadTempSave(): SimpleResponse {
        TODO("Not yet implemented")
    }
}