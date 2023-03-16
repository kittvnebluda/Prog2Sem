package com.prog2sem.server

import application.src.commonMain.kotlin.com.prog2sem.common.Important
import application.src.commonMain.kotlin.com.prog2sem.common.Important.autoSaveFileName
import application.src.commonMain.kotlin.com.prog2sem.common.Important.isSaved
import application.src.commonMain.kotlin.com.prog2sem.common.Important.loadAutoSave

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
