package com.prog2sem.client.cmdpattern

import com.prog2sem.client.io.ColorfulOut
import com.prog2sem.client.net.InetCommands
import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.exceptions.InvalidUserInputException

class ShowServerAddressCommand(
    private val commands: InetCommands,
    override val name: String = "show_server_addr"
) : Command {
    override val desc: String = "Выводит адрес сервера"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.showServerAddr()
    }
}

class SetServerAddressCommand(
    private val commands: InetCommands,
    override val name: String = "set_server_addr"
) : Command {
    override val desc: String = "Выводит адрес сервера"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        try {
            if (args.size > 1)
                commands.setServerAddr(args[0], args[1].toInt())
            else
                throw InvalidUserInputException("Не указаны адрес или порт (Пример: \"127.0.0.1 1872\")")
        } catch (e: Exception) {
            ColorfulOut.printlnRed("Неправильно введен адрес (Пример: \"127.0.0.1 1872\"). Попробуйте еще раз!")
        }
    }
}