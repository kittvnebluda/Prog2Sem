/** Интерфейс всех команд */
interface Command {
    val name: String
    fun execute(args: List<String>)
}

/** Вызов команды помощи */
class HelpCommand (private val commands: ClientCommands, override val name: String = "help") : Command {
    override fun execute(args: List<String>) {
        commands.help()
    }
}

/** Вызов команды завершения программы */
class ExitCommand (private val commands: ClientCommands, override val name: String = "exit"): Command {
    override fun execute(args: List<String>) {
        commands.exit()
    }
}

/** Вызов истории выполненных команд */
class HistoryCommand(private val commands: ClientCommands, override val name: String = "history") : Command {
    override fun execute(args: List<String>) {
        commands.history()
    }
}

/** Вызов выполнения скрипта */
class ExecuteScriptCommand(private val commands: ClientCommands,
                           private val invoker: Invoker,
                           override val name: String = "execute"): Command {
    /**
     * @throws InvalidUserInputException
     */
    override fun execute(args: List<String>) {
        if (args.isNotEmpty())
            commands.executeScript(args[0], invoker)
        else
            throw InvalidUserInputException("В команде пропущен путь файла")
    }
}

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand (private val manager: CollectionManager<*>, override val name: String = "info"): Command {
    override fun execute(args: List<String>) {
        println(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand (private val manager: CollectionManager<*>, override val name: String = "show"): Command {
    override fun execute(args: List<String>) {
        println(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: CollectionManager<*>, override val name: String = "add"): Command {
    /**
     * @throws InvalidUserInputException
     */
    override fun execute(args: List<String>) {
        val name = if(args.isNotEmpty()) args[0] else throw InvalidUserInputException("Не указано имя класса")
        val userInput = CustomConsole.readlines("Введите рост: ", "Введите возраст: ")
        manager.add(arrayOf(name, *userInput).joinToString(" "))
    }
}
