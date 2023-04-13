package com.prog2sem.server

import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.isSaved
import com.prog2sem.server.Important.loadAuto
import com.prog2sem.shared.net.NioUdpServer
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.net.InetAddress
import java.nio.channels.SelectionKey
import java.nio.channels.Selector

val SHEDULER = NioUdpServer(InetAddress.getLocalHost(), 4221)
val INVOKER = NetInvoker()
//val SELECTOR: Selector = Selector.open()
//val NOW_IP = InetAddress.getLocalHost()
var fileName = "DataBaseSaveFile"
const val AUTOSAVE_TIMEOUT = 15000L

suspend fun main(args: Array<String>) {
//    com.prog2sem.shared.io.FileWorker.clearFile(ImportantVal().filePath)

    if (args.isNotEmpty())
//        com.prog2sem.shared.io.FileWorker.clearFile(args[0])
        fileName = args[0]

    load()

    println(InetAddress.getLocalHost())

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
    DataBaseSim.readDataFromFile(fileName)
    Important.load()
}
fun saveAll(){
    Important.save()
    LocalManager.save(fileName)
}

suspend fun checking() {
    coroutineScope {
        async {
            while (true) {
                delay(AUTOSAVE_TIMEOUT)
                println("AutoSave")
                saveAll()
            }
        }
        async {
            while (true){
                Scheduler.listener()
            }
        }
    }
}
