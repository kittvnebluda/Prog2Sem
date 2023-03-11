package com.prog2sem.client

import java.util.*

const val MAX_HISTORY_SIZE = 12

val HISTORY = LinkedList<String>()
var ISQUIT = false
var HELP = ""

fun main(args: Array<String>) {
    val dbCommands = LocalDataBaseCommands(if (args.isNotEmpty()) args[0] else "")
    val clientCommands = ConsoleClientCommands()
    val invoker = ConsoleInvoker()

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

    invoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, save, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor, addTest)

    invoker.genHelp()

    val cc = CustomConsole(invoker)

    cc.talkWithUserForever()
}