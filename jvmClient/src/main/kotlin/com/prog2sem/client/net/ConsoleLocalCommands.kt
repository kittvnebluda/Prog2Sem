package com.prog2sem.client.net

import com.prog2sem.client.*
import com.prog2sem.client.io.ColorfulOut.printlnGreen
import com.prog2sem.shared.cmdpattern.Invoker
import com.prog2sem.shared.exceptions.InvalidUserInputException
import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * Класс консольных работающих без сервера команд пользователя.
 */
class ConsoleLocalCommands : LocalCommands {
    companion object {
        private val openedScripts = LinkedList<String>()
    }

    override fun help() {
        println(HELP)
    }

    override fun executeScript(
        filename: String,
        invoker: Invoker,
        login: String,
        password: String
    ) {
        val file = File(filename)

        try {
            val absPath = file.absolutePath
            openedScripts.add(absPath)

            val sc = Scanner(file)
            while (sc.hasNextLine()) {
                val command = sc.nextLine()

                if (absPath in openedScripts)
                    throw InvalidUserInputException("Обнаружено зацикливание!")
                else
                    invoker.proceed(command, login, password)
            }
            printlnGreen("Выполнение команд завершено")
            sc.close()
            openedScripts.clear()

        } catch (e: FileNotFoundException) {
            throw InvalidUserInputException("$filename: файл не найден")
        }
    }

    override fun exit() {
        QUIT = true
    }

    override fun history() {
        HISTORY.forEachIndexed { index, s ->
            println("${index + 1}. $s")
        }
    }
}