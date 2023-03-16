package com.prog2sem.common

import java.io.File
import java.io.FileOutputStream
import java.util.Scanner

object FileWorker {

    private val TAG = "com.prog2sem.common.FileWorker"
    private val COUNT_OF_TRYING = 3

    /**
     * @param filePath
     * @return
     */
    fun readFileFromEnterFilePath(filePath: String): String {
        var file = File(filePath)
        val intputStr = StringBuilder()

        if (file.exists())
            Scanner(file).forEach { intputStr.append(it) }
        else {
            var j = 0
            while (!file.exists() && j != COUNT_OF_TRYING) {
                j++
                file = createFileWithEnterPath(filePath)
            }
        }
        return intputStr.toString()
    }

    /**
     * @param filePath
     * @param args
     * @return
     */

    fun writeFileFromEnterFilePath(filePath: String, vararg args: Any): Boolean {

        var file = File(filePath)

        var j = 0
        while (!file.exists() && j != COUNT_OF_TRYING) {
            j++
            file = createFileWithEnterPath(filePath)
        }
        if (j == COUNT_OF_TRYING) {
            return false
        }

        val outputStr = FileOutputStream(file)

        args.forEach { outputStr.write(it.toString().toByteArray()) }
        outputStr.close()
        return true
    }

    /**
     * @param filePath
     * @return
     */

    private fun createFileWithEnterPath(filePath: String): File {
        val file = File(filePath)
        file.createNewFile()
        return file
    }

    /**
     * @param filePath
     * @return
     */
    fun clearFile(filePath: String){
        val file = File(filePath)
        val outputStr = FileOutputStream(file)

        if (!file.exists()) {
            var j = 0
            while (!createFileWithEnterPath(filePath).exists() || j != COUNT_OF_TRYING) j++
            if (j == COUNT_OF_TRYING) {
                outputStr.close()
                return
            }
        }
        outputStr.write("".toByteArray())
        outputStr.close()
    }
    /* TODO("Check how it work") */
}