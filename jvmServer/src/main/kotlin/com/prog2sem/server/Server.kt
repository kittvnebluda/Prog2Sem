package com.prog2sem.server

import com.prog2sem.server.DataBaseCommands.DataBaseConnector.getPersonsFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.createSequenceLogins
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.createSequencePersons
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.createTableOfLogins
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.createTableOfPersons
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.getAllFromTable
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.personDataBaseName
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.personKeys
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.startConnection
import com.prog2sem.server.DataBaseCommands.PostgreSQLCommands.useStatement
import com.prog2sem.server.tasks.Receiver
import com.prog2sem.shared.utils.Log
import org.postgresql.util.PSQLException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory


val INVOKER = NetInvoker()
val server: DatagramChannel = startServer(InetAddress.getLocalHost(), 4221)

//val SELECTOR: Selector = Selector.open()
//val NOW_IP = InetAddress.getLocalHost()
val fileName = "DataBaseSaveFile"
const val AUTOSAVE_TIMEOUT = 30000L

private const val receiverCount = 5
//private const val senderCount = 10
//private const val schedulerCount = 10

const val bufferCapacity = 1024

val killAddressExecutor: ExecutorService = Executors.newCachedThreadPool()
val sendAddressExecutor: ExecutorService = Executors.newCachedThreadPool()
val receiverExecutor: ExecutorService = Executors.newFixedThreadPool(receiverCount)
val commandsExecutor: ExecutorService = Executors.newCachedThreadPool()

fun main(args: Array<String>) {

//    com.prog2sem.shared.io.FileWorker.clearFile(ImportantVal().filePath)

    try {
        server.connect(InetSocketAddress(args[0], args[1].toInt()))
        Log.i("Connect to ${server.localAddress}")
    }catch(e : Exception){
        Log.e("Can not connect to input address connect to local address ${server.localAddress}")
    }

    startConnection()

    useStatement(createTableOfPersons)
    useStatement(createTableOfLogins)

    try {
        useStatement(createSequencePersons)
    }catch (e: PSQLException){
        Log.e(e.message!!)
    }

    try {
        useStatement(createSequenceLogins)
    }catch (e: PSQLException){
        Log.e(e.message!!)
    }

    load()

  //  usePrepareStatement(addPerson, personKeys, dataBaseSim.elementAt(0).toHashMap(personKeys))

    initCommands()

    try {
        start()
    }catch (e: Exception){
        when(e){
            else -> Log.e("Произошла ошибка")
        }
    }

}

fun initCommands(){

    val info = InfoCommand()
    val show = ShowCommand()
    val add = AddCommand()
    val update = UpdateCommand()
    val remove = RemoveIdCommand()
    val clear = ClearCommand()
    val addIdMax = AddIfMinCommand()
    val removeGreater = RemoveGreaterCommand()
    val removeByLocation = RemoveAllByLocationCommand()
    val filterByColor = FilterGreaterThanHairColorCommand()
    val printHairColor = PrintFieldAscendingHairColorCommand()
    val addLogin = AddLogin()
    val checkLogin = CheckLogin()
    val getTable = GetTable()

    INVOKER.putAll(
        info, show, add, update, remove, clear, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor, addLogin, checkLogin, getTable
    )
}
fun load(){
    Log.i("Server load info from file")
    getPersonsFromTable(getAllFromTable(personDataBaseName), personKeys)
}

private fun start(){
    for (i in 0..receiverCount)
        receiverExecutor.submit(Receiver())
}
private fun startServer(host: InetAddress, port: Int): DatagramChannel{
    val address = InetSocketAddress(host, port)
    val channel: DatagramChannel = DatagramChannel.open()

    channel.configureBlocking(false)

    channel.bind(address)  // The server is listening
    println("Receiver started at $address")

    return channel
}
