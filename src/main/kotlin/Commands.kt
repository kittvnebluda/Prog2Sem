/** Интерфейс всех команд */
interface Command {
    val name: String
    /**
     * Метод, обеспечивающий выполнение команды
     * @throws InvalidUserInputException
     */
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
        println("Удачи!")
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

    override fun execute(args: List<String>) {
        if (args.isNotEmpty())
            commands.executeScript(args[0], invoker)
        else
            throw InvalidUserInputException("В команде пропущен путь файла")
    }
}

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand (private val manager: DataBaseCommands<*>, override val name: String = "info"): Command {
    override fun execute(args: List<String>) {
        println(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand (private val manager: DataBaseCommands<*>, override val name: String = "show"): Command {
    override fun execute(args: List<String>) {
        println(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: DataBaseCommands<*>, override val name: String = "add"): Command {
    override fun execute(args: List<String>) {
        manager.add(cc.createPerson())
    }
}

class UpdateCommand(private val manager: DataBaseCommands<*>, override val name: String = "update"): Command {
    override fun execute(args: List<String>) {
        val index = if(args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указаны индекс класса и его имя")
        manager.update(index, cc.createPerson())
    }
}

class RemoveIdCommand(private val manager: DataBaseCommands<*>, override val name: String = "remove"): Command {
    override fun execute(args: List<String>) {
        val index = if(args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указан индекс класса")
        manager.removeId(index)
    }
}

class ClearCommand(private val manager: DataBaseCommands<*>, override val name: String = "clear"): Command {
    override fun execute(args: List<String>) {
        manager.clear()
    }
}

class SaveCommand(private val manager: DataBaseCommands<*>, override val name: String = "save"): Command {
    override fun execute(args: List<String>) {
        manager.save()
    }
}

class AddIfMinCommand(private val manager: DataBaseCommands<*>, override val name: String = "add_if_min"): Command {
    override fun execute(args: List<String>) {
        manager.addIfMin(cc.createPerson())
    }
}

class RemoveGreaterCommand(private val manager: DataBaseCommands<*>,
                           override val name: String = "remove_greater"): Command {
    override fun execute(args: List<String>) {
        manager.removeGreater(cc.createPerson())
    }
}

class FilterGreaterThanHairColorCommand(private val manager: DataBaseCommands<*>,
                                        override val name: String = "filter_by_hair"): Command {
    override fun execute(args: List<String>) {
        val color = if(args.isEmpty()) null else Color.valueOf(args[0].uppercase())
        manager.filterGreaterThanHairColor(color)
    }
}

class PrintFieldAscendingHairColorCommand(private val manager: DataBaseCommands<*>,
                                          override val name: String = "print_hair"): Command {
    override fun execute(args: List<String>) {
        manager.printFieldAscendingHairColor()
    }
}