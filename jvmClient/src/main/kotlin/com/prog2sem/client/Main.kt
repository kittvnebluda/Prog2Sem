package com.prog2sem.client

import com.prog2sem.client.CustomConsole.Companion.isLoadTempSave
import com.prog2sem.client.CustomConsole.Companion.printGreen
import com.prog2sem.client.CustomConsole.Companion.printerr

const val MAX_HISTORY_SIZE = 12

val HISTORY = mutableListOf<String>()
var ISQUIT = false
var HELP = ""

fun main(args: Array<String>) {
    val dbCommands = LocalDataBaseCommands(if (args.isNotEmpty()) args[0] else "DEFAULT_NAME")
    val clientCommands = ConsoleClientCommands()
    val invoker = ConsoleInvoker()

    // Создаем экземпляры команд
    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, invoker)

    val info = InfoCommand(dbCommands)
    val show = ShowCommand(dbCommands)
    val add = AddCommand(dbCommands)
    val addTest = AddTestCommand(dbCommands)
    val update = UpdateCommand(dbCommands)
    val remove = RemoveIdCommand(dbCommands)
    val clear = ClearCommand(dbCommands)
    val save = SaveCommand(dbCommands)
    val addIdMax = AddIfMinCommand(dbCommands)
    val removeGreater = RemoveGreaterCommand(dbCommands)
    val removeByLocation = RemoveAllByLocationCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)

    // Добавляем команды в вызыватель
    invoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, save, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor, addTest)

    invoker.genHelp() // Генерируем строку помощи

    // Временное сохранение прошлой сессии
    val existMsg = dbCommands.isTempSaveExist()
    if (existMsg.success && isLoadTempSave()) {
        val loadMsg = dbCommands.loadTempSave()
        if (loadMsg.success)
            printGreen("Сохранение загружено!")
        else
            printerr(loadMsg.error)
    }

    val cc = CustomConsole(invoker)

    cc.talkWithUserForever()
}