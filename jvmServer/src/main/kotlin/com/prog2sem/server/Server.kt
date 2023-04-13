package com.prog2sem.server

import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.isSaved
import com.prog2sem.server.Important.loadAuto
import com.prog2sem.shared.net.NioUdpServer
import java.net.InetAddress
import java.nio.channels.SelectionKey
import java.nio.channels.Selector

val SHEDULER = NioUdpServer(InetAddress.getLocalHost(), 4221)
val INVOKER = NetInvoker()
val SELECTOR: Selector = Selector.open()
val NOW_IP = InetAddress.getLocalHost()

fun main(args: Array<String>) {
//    com.prog2sem.shared.io.FileWorker.clearFile(ImportantVal().filePath)
    var fileName = "DataBaseSaveFile"
    if (args.isNotEmpty())
//        com.prog2sem.shared.io.FileWorker.clearFile(args[0])
        fileName = args[0]

    DataBaseSim.readDataFromFile(fileName)
    Important.load()

    if (!isSaved) {
        DataBaseSim.readDataFromFile(autoSaveFileName)
        loadAuto()
    }

    println(InetAddress.getLocalHost())


    initCommands()

    SHEDULER.channel.register(SELECTOR, SelectionKey.OP_READ)

    checking()

    Runtime.getRuntime().addShutdownHook(Thread {
        Important.save()
        if (!isSaved) LocalManager.save(autoSaveFileName)
    })
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

tailrec fun checking(){
    Scheduler.listener()
    //Scheduler.speaker()
    checking()
}
