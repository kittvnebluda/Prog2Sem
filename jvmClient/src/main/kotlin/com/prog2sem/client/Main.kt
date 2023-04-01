package com.prog2sem.client

import com.prog2sem.client.utils.AskUser
import com.prog2sem.client.net.InetDataBaseCommands
import com.prog2sem.client.io.ColorfulOut.printGreen
import com.prog2sem.client.io.ColorfulOut.printerr
import com.prog2sem.client.net.ConsoleClientCommands
import com.prog2sem.client.net.commands.*
import com.prog2sem.client.utils.CustomConsole
import com.prog2sem.shared.net.NioUdpClient
import java.net.InetAddress
import java.net.InetSocketAddress

const val MAX_HISTORY_SIZE = 12

val HISTORY = mutableListOf<String>()
var ISQUIT = false
var HELP = ""

fun main() {
    val client = NioUdpClient()
    val host = InetAddress.getLocalHost()
    val port = 4221

    client.sendToAddress = InetSocketAddress(host, port)

    val dbCommands = InetDataBaseCommands(client)
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
    val addIdMax = AddIfMinCommand(dbCommands)
    val removeGreater = RemoveGreaterCommand(dbCommands)
    val removeByLocation = RemoveAllByLocationCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)

    // Добавляем команды в вызыватель
    invoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor, addTest
    )

    invoker.genHelp() // Генерируем строку помощи

    // Временное сохранение прошлой сессии
    val isExist = dbCommands.isTempSaveExist()
    if (isExist && AskUser.isLoadTempSave()) {
        val response = dbCommands.loadTempSave()
        if (response)
            printGreen("Сохранение загружено!")
        else
            printerr("Не получилось загрузить сохранение")
    }

    val cc = CustomConsole(invoker)

    cc.talkWithUserForever()
}