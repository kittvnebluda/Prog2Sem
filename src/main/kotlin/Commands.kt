import java.util.LinkedList

const val MAX_HISTORY_SIZE = 12

val HISTORY = LinkedList<String>()
var ISQUIT = false

/** Интерфейс всех команд */
interface Command {
    val name: String
    fun execute(args: List<String>) {
        HISTORY.add(name)
        if (HISTORY.size > MAX_HISTORY_SIZE) HISTORY.remove()
    }
}

/** Реализация вызова команды помощи */
class HelpCommand (private val commands: ClientCommands, override val name: String = "help") : Command {
    override fun execute(args: List<String>) {
        super.execute(args)
        commands.help()
    }
}

/** Реализация вызова команды завершения программы */
class ExitCommand (private val commands: ClientCommands, override val name: String = "exit"): Command {
    override fun execute(args: List<String>) {
        super.execute(args)
        commands.exit()
    }
}

/** Реализация вызова истории выполненных команд*/
class HistoryCommand(private val commands: ClientCommands, override val name: String = "history") : Command {
    override fun execute(args: List<String>) {
        println(commands.history())
        super.execute(args)
    }
}

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand (private val manager: CollectionManager<*>, override val name: String = "info"): Command {
    override fun execute(args: List<String>) {
        super.execute(args)
        println(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand (private val manager: CollectionManager<*>, override val name: String = "show"): Command {
    override fun execute(args: List<String>) {
        super.execute(args)
        println(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: CollectionManager<*>, override val name: String = "add"): Command {
    override fun execute(args: List<String>) {
        super.execute(args)
        try {
            val name = if(args.isNotEmpty()) args[0] else throw InvalidUserInputException("Не указано имя класса")
            val userInput = CustomConsole.readlines("Введите рост: ", "Введите возраст: ")
            manager.add(arrayOf(name, *userInput).joinToString(" "))
        } catch (_: InvalidUserInputException) {}
    }
}
