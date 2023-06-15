package com.prog2sem.client

//import Login
import com.prog2sem.client.app.TableInfo
import com.prog2sem.client.cmdpattern.*
import com.prog2sem.client.net.ConsoleInetCommands
import com.prog2sem.client.net.ConsoleLocalCommands
import com.prog2sem.client.net.InetDataBaseCommands
import com.prog2sem.client.utils.Smt
import com.prog2sem.shared.net.PacketsUDP
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import java.net.InetAddress
import java.net.InetSocketAddress
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val MAX_HISTORY_SIZE = 12

val commandsHistoryList = mutableListOf<String>()
var commandsHelp = ""
var doQuit = false
var isLogged = false

var host = "127.0.0.1"
var port = 4221

var login = "admin"
var password = "admin"

val invoker = ConsoleInvoker()

val getTableExecutor: ExecutorService = Executors.newSingleThreadExecutor()

//val loginScreen = Login("Login")

var msg:String? = null

val client = PacketsUDP()

val dbCommands = InetDataBaseCommands(client)
val clientCommands = ConsoleLocalCommands()
val inetCommands = ConsoleInetCommands(client)

fun main(args: Array<String>) {

    val host: InetAddress
    val port: Int

    if (args.size >= 2) {
        host = InetAddress.getByName(args[0])
        port = args[1].toInt()
    } else {
        host = InetAddress.getByName(com.prog2sem.client.host)
        port = com.prog2sem.client.port
    }

    client.sendToAddress = InetSocketAddress(host, port)

    if (args.size > 2) {
        Configurator.setRootLevel(Level.getLevel(args[2].uppercase()))
    } else {
        Configurator.setRootLevel(Level.getLevel("INFO"))
    }

    // Создаем экземпляры команд

    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, invoker)

    val showServerAddr = ShowServerAddressCommand(inetCommands)
    val setServerAddr = SetServerAddressCommand(inetCommands)

    val info = InfoCommand(dbCommands)
    val show = ShowCommand(dbCommands)
    val add = AddCommand(dbCommands)
    val addRnd = AddRndCommand(dbCommands)
    val fill = FillCommand(dbCommands)
    val update = UpdateCommand(dbCommands)
    val remove = RemoveIdCommand(dbCommands)
    val clear = ClearCommand(dbCommands)
    val addIdMax = AddIfMinCommand(dbCommands)
    val removeGreater = RemoveGreaterCommand(dbCommands)
    val removeByLocation = RemoveAllByLocationCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)
    val logIn = CheckLogin(dbCommands)
    val signUp = AddLogin(dbCommands)
    val getTable = GetTable(dbCommands)

    // Добавляем команды в вызыватель
    invoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor, addRnd, showServerAddr, setServerAddr, fill,
        logIn, signUp, getTable
    )

    invoker.genHelp() // Генерируем строку помощи

    Smt.talkWithUserForever(invoker)
}

fun getNew(): String {
    while (msg == null) {}
    val toReturn: String = msg as String
    msg = null
    return toReturn
}