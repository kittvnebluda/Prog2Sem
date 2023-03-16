package com.prog2sem.client

import kotlin.math.max

/**
 * Класс хранящий и вызывающий команды
 */
class ConsoleInvoker: Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun putAll(vararg commands: Command) {
        commands.forEach { put(it) }
    }

    override fun proceed(cmd: String) {
        val postCmd = CustomConsole.splitSpaces(cmd)
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$cmd: команда не найдена")

        command.execute(postCmd.slice(1 until postCmd.size))

        HISTORY.add(command.name)
        if (HISTORY.size > MAX_HISTORY_SIZE)
            HISTORY.remove()
    }

    fun genHelp() {
        var maxNameLen = 0
        cmdMap.values.forEach { command ->
            var nameLen = command.name.length + command.methodsDesc.size
            command.methodsDesc.forEach { (name, _) ->
                nameLen += name.length
            }
            maxNameLen = max(maxNameLen, nameLen)
        }
        cmdMap.values.forEach { command ->
            val methods = command.methodsDesc.keys.joinToString(" ")
            HELP += "${command.name} $methods ${" ".repeat(maxNameLen - command.name.length - methods.length)} : ${command.desc}\n"
            command.methodsDesc.forEach { (name, desc) ->
                HELP += "${" ".repeat(maxNameLen - name.length)}  $name : $desc\n"
            }
        }
    }
}