package com.prog2sem.server

import com.prog2sem.shared.net.UDPServer
import com.prog2sem.shared.utils.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.net.InetAddress
import java.net.InetSocketAddress

val INVOKER = NetInvoker()
val server = UDPServer(InetAddress.getLocalHost(), 4221)
//val SELECTOR: Selector = Selector.open()
//val NOW_IP = InetAddress.getLocalHost()
val fileName = "DataBaseSaveFile"
const val AUTOSAVE_TIMEOUT = 30000L

suspend fun main(args: Array<String>) {

//    com.prog2sem.shared.io.FileWorker.clearFile(ImportantVal().filePath)

    try {
        server.channel.connect(InetSocketAddress(args[0], args[1].toInt()))
        Log.i("Connect to ${server.channel.localAddress}")
    }catch(e : Exception){
        Log.e("Can not connect to input address connect to local address ${server.channel.localAddress}")
    }

    load()

    initCommands()

    Runtime.getRuntime().addShutdownHook(Thread {
        saveAll()
    })

    checking()

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

    INVOKER.putAll(
        info, show, add, update, remove, clear, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor
    )
}
fun load(){
    Log.i("Server load info from file")
    DataBaseSim.readDataFromFile(fileName)
    Important.load()
}
fun saveAll(){
    Log.i("Server save database in file")
    Important.save()
    LocalManager.save(fileName)
}

suspend fun checking() = coroutineScope {

    async {
        while (true) {
            delay(AUTOSAVE_TIMEOUT)
            saveAll()
        }
    }

    async {
        ServerScheduler.scheduler()
    }
}
