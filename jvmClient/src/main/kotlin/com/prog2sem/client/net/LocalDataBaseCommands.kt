package com.prog2sem.client.net

import com.prog2sem.shared.*
import com.prog2sem.server.LocalManager
import com.prog2sem.server.ServerAnswer
import com.prog2sem.server.main
import com.prog2sem.shared.DataBaseCommands
import com.prog2sem.shared.TempSaveDBCommands
import com.prog2sem.shared.Response
import com.prog2sem.shared.SimpleResponse
import com.prog2sem.shared.persona.Person
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

/**
 * @param filename путь к файлу сохранения
 */
@Deprecated("Сделано на коленки, лучше не использовать")
class LocalDataBaseCommands(private val filename: String) : DataBaseCommands, TempSaveDBCommands {
    private val manager = LocalManager()

    init {
        main(arrayOf(filename))
    }

    override fun info(): Response<String> {
        val jsonString = manager.info()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, sa.answerMessage, sa.answerMessage)
    }

    override fun show(): Response<String> {
        val jsonString = manager.show()
        val sa: ServerAnswer = JsonWorker.json.decodeFromString(jsonString)
        return Response(sa.isSuccess, sa.answerMessage, sa.answerMessage)
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
        return SimpleResponse(sa.isSuccess, sa.answerMessage)
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
        return SimpleResponse(success = manager.isAutoSaved(), error = "Нет информации об ошибке")
    }

    override fun loadTempSave(): SimpleResponse {
        manager.loadAutoSave()
        return SimpleResponse(success = true, error = "Нет информации об ошибке")
    }
}