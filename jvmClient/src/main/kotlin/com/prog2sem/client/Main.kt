package com.prog2sem.client

import com.prog2sem.client.cmdpattern.*
import com.prog2sem.client.net.*
import com.prog2sem.client.utils.CustomConsole
import com.prog2sem.shared.net.NioUdpClient
import java.net.InetAddress
import java.net.InetSocketAddress

const val MAX_HISTORY_SIZE = 12

val HISTORY = mutableListOf<String>()
var ISQUIT = false
var HELP = ""
var HOST = "127.0.0.1"
var PORT = 4221

fun main(args: Array<String>) {
    val client = NioUdpClient()

    val host: InetAddress
    val port: Int

    if (args.size == 2) {
        host = InetAddress.getByName(args[0])
        HOST = host.toString()
        port = args[1].toInt()
        PORT = port
    } else {
        host = InetAddress.getByName(HOST)
        port = PORT
    }

    client.sendToAddress = InetSocketAddress(host, port)

    val dbCommands = InetDataBaseCommands(client)
    val clientCommands = ConsoleClientCommands()
    val invoker = ConsoleInvoker()

    // Создаем экземпляры команд
    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, invoker)
    val serverAddr = ServerAddressCommand(clientCommands)

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
        removeByLocation, filterByColor, printHairColor, addTest, serverAddr
    )

    invoker.genHelp() // Генерируем строку помощи

    val cc = CustomConsole(invoker)

    cc.talkWithUserForever()
}