package com.prog2sem.client.cmdpattern

import com.prog2sem.client.MAX_HISTORY_SIZE
import com.prog2sem.client.net.LocalCommands
import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.cmdpattern.Invoker
import com.prog2sem.shared.exceptions.InvalidUserInputException

/** Вызов команды помощи */
class HelpCommand(private val commands: LocalCommands, override val name: String = "help") :
    Command {
    override val desc: String = "описание команд"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.help()
    }
}

/** Вызов команды завершения программы */
class ExitCommand(private val commands: LocalCommands, override val name: String = "exit") :
    Command {
    override val desc: String = "завершение программы"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println("Удачи!")
        commands.exit()
    }
}

/** Вызов истории выполненных команд */
class HistoryCommand(private val commands: LocalCommands, override val name: String = "history") :
    Command {
    override val desc: String = "обеспечивает вывод последних $MAX_HISTORY_SIZE команд без их аргументов"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.history()
    }
}

/** Вызов выполнения скрипта */
class ExecuteScriptCommand(
    private val commands: LocalCommands,
    private val invoker: Invoker,
    override val name: String = "execute"
) : Command {
    override val desc: String = "исполнение скрипта из указанного файла"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        if (args.isNotEmpty())
            commands.executeScript(args[0], invoker)
        else
            throw InvalidUserInputException("В команде пропущен путь файла")
    }
}