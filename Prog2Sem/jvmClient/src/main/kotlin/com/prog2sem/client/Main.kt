package com.prog2sem.client

import com.prog2sem.server.LocalDataBase
import java.util.*

typealias cc = CustomConsole

const val MAX_HISTORY_SIZE = 12

val HISTORY = LinkedList<String>()
var ISQUIT = false
var HELP = ""

fun main(args: Array<String>) {
    val dbCommands = LocalDataBase(if (args.isNotEmpty()) args[0] else "")
    val clientCommands = ConsoleClientCommands()
    val consoleInvoker = ConsoleInvoker()

    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, consoleInvoker)

    val info = InfoCommand(dbCommands)
    val show = ShowCommand(dbCommands)
    val add = AddCommand(dbCommands)
    val update = UpdateCommand(dbCommands)
    val remove = RemoveIdCommand(dbCommands)
    val clear = ClearCommand(dbCommands)
    val save = SaveCommand(dbCommands)
    val addIdMax = AddIfMinCommand(dbCommands)
    val removeGreater = RemoveGreaterCommand(dbCommands)
    val removeByLocation = RemoveAllByLocationCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)

    consoleInvoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, save, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor)

    consoleInvoker.genHelp()

    val cc = CustomConsole(consoleInvoker)

    cc.loop()
}