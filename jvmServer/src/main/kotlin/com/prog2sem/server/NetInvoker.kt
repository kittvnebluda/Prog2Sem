package com.prog2sem.server

import com.prog2sem.shared.cmdpattern.Invoker
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.MsgMarker
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress

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

    override fun proceed(cmd: String, login: String, password: String) {
        // Разделяем ввод и достаем команду
        val address = InetAddress.getByName(cmd.substringBefore(':'))
        var newCmd = cmd.substring(cmd.indexOf(':') + 1)
        val port = (newCmd.substringBefore(':')).toInt()
        newCmd = newCmd.substring(newCmd.indexOf(':') + 1)
        val postCmd = MsgMarker.getFun(newCmd)
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$newCmd: команда не найдена")

        val args = mutableListOf(address.hostAddress, port) as MutableList<String>
        for (el in postCmd.slice(1 until postCmd.size)) args.add(el)

        command.execute(args, login, password) // Выполняем команду
    }

}