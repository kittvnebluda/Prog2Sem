package com.prog2sem.shared.io

import java.io.File
import java.io.FileOutputStream
import java.util.*

object FileWorker {
    private const val COUNT_OF_TRYING = 3

    /**
     * @param filePath
     * @return
     */
    fun readFileFromEnterFilePath(filePath: String): String {
        var file = File(filePath)
        val inputStr = StringBuilder()

        if (file.exists())
            if (file.canRead())
                Scanner(file).forEach { inputStr.append(it) }
            else
                println("Can't read $filePath file")
        else {
            var j = 0
            while (!file.exists() && j != COUNT_OF_TRYING) {
                j++
                file = createFileWithEnterPath(filePath)
            }
        }
        return inputStr.toString()
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

        if (file.canWrite()) {
            val outputStr = FileOutputStream(file)

            args.forEach { outputStr.write(it.toString().toByteArray()) }
            outputStr.close()
            return true
        }
        return false
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
    // TODO("Check how it works")
    fun clearFile(filePath: String) {
        val file = File(filePath)
        val outputStr = FileOutputStream(file)

        if (!file.exists()) {
            var j = 0
            while (!createFileWithEnterPath(filePath).exists() && j != COUNT_OF_TRYING) j++
            if (j == COUNT_OF_TRYING) {
                outputStr.close()
                return
            }
        }
        outputStr.write("".toByteArray())
        outputStr.close()
    }
}