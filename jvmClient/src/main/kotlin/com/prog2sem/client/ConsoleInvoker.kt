package com.prog2sem.client

import com.prog2sem.shared.net.Command
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.client.utils.CustomConsole
import com.prog2sem.shared.Invoker
import kotlin.math.max

/**
 * Класс хранящий и вызывающий команды
 */
class ConsoleInvoker : Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun putAll(vararg commands: Command) {
        commands.forEach { put(it) }
    }

    override fun proceed(cmd: String) {
        // Разделяем ввод и достаем команду
        val postCmd = CustomConsole.splitSpaces(cmd)
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$cmd: команда не найдена")

        val args = postCmd.slice(1 until postCmd.size)

        // Добавляем команду в историю
        HISTORY.add("${command.name} ${args.joinToString(" ")}")
        if (HISTORY.size > MAX_HISTORY_SIZE)
            HISTORY.removeFirst()

        command.execute(args) // Выполняем команду
    }

    fun genHelp() {
        // Считаем максимальную длину
        var maxNameLen = 0
        cmdMap.values.forEach { command ->
            var nameLen = command.name.length + command.methodsDesc.size
            command.methodsDesc.forEach { (name, _) ->
                nameLen += name.length
            }
            maxNameLen = max(maxNameLen, nameLen)
        }
        // Делаем красивый хэлп
        cmdMap.values.forEach { command ->
            val methods = command.methodsDesc.keys.joinToString(" ")
            HELP += "${command.name} $methods ${" ".repeat(maxNameLen - command.name.length - methods.length)} : ${command.desc}\n"
            command.methodsDesc.forEach { (name, desc) ->
                HELP += "${" ".repeat(command.name.length)} $name : $desc\n"
            }
        }
    }
}