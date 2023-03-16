package com.prog2sem.server

import com.prog2sem.common.Important
import com.prog2sem.common.Important.autoSaveFileName
import com.prog2sem.common.Important.isSaved

fun main(args:Array<String>) {
//    com.prog2sem.common.FileWorker.clearFile(ImportantVal().filePath)
    if (!args.isNotEmpty())
//        com.prog2sem.common.FileWorker.clearFile(args[0])
        args[0] = "DEFAULT_NAME"

    DataBaseSim.readDataFromFile(args[0])
    Important.load()

    Runtime.getRuntime().addShutdownHook(Thread {
        Important.save()
        if (!isSaved) LocalManager().save(autoSaveFileName)
    })
}
