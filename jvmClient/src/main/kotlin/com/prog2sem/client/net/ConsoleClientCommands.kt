package com.prog2sem.client.net

import com.prog2sem.client.*
import com.prog2sem.client.exceptions.InvalidUserInputException
import com.prog2sem.client.Invoker
import com.prog2sem.client.utils.CustomConsole
import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * Класс консольных работающих без сервера команд пользователя.
 */
class ConsoleClientCommands : ClientCommands {
    companion object {
        private val openedScripts = LinkedList<String>()
    }

    override fun help() {
        println(HELP)
    }

    override fun executeScript(filename: String, invoker: Invoker) {
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
                    invoker.proceed(command)
            }
            println("${CustomConsole.green}Выполнение команд завершено${CustomConsole.reset}")
            sc.close()
            openedScripts.clear()

        } catch (e: FileNotFoundException) {
            throw InvalidUserInputException("$filename: файл не найден")
        }
    }

    override fun exit() {
        ISQUIT = true
    }

    override fun history() {
        HISTORY.forEachIndexed { index, s ->
            println("${index + 1}. $s")
        }
    }
}