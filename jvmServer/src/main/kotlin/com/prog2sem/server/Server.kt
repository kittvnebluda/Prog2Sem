package com.prog2sem.server

import com.prog2sem.server.Important.autoSaveFileName
import com.prog2sem.server.Important.isSaved
import com.prog2sem.server.Important.loadAuto


fun main(args: Array<String>) {
//    com.prog2sem.shared.FileWorker.clearFile(ImportantVal().filePath)
    var fileName = "DEFAULT_NAME"
    if (args.isNotEmpty())
//        com.prog2sem.shared.FileWorker.clearFile(args[0])
        fileName = args[0]

    DataBaseSim.readDataFromFile(fileName)
    Important.load()

    if (!isSaved) {
        DataBaseSim.readDataFromFile(autoSaveFileName)
        loadAuto()
    }

    //checking()

    Runtime.getRuntime().addShutdownHook(Thread {
        Important.save()
        if (!isSaved) LocalManager().save(autoSaveFileName)
    })
}

tailrec fun checking(){
    checking()
}
