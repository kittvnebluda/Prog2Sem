package com.prog2sem.server

import com.prog2sem.shared.net.Command
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.Invoker
import com.prog2sem.shared.net.MsgMarker

/**
 * Класс хранящий и вызывающий команды
 */
class NetInvoker : Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun putAll(vararg commands: Command) {
        commands.forEach { put(it) }
    }

    override fun proceed(cmd: String) {
        // Разделяем ввод и достаем команду
        val postCmd = MsgMarker.getFun(cmd)
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$cmd: команда не найдена")

        val args = postCmd.slice(1 until postCmd.size)


        // Добавляем команду в историю

        command.execute(args) // Выполняем команду
    }

}