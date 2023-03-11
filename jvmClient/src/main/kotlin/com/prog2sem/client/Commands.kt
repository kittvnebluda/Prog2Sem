package com.prog2sem.client

import com.prog2sem.common.*
import java.time.ZonedDateTime

/** Интерфейс всех команд */
interface Command {
    val name: String
    val desc: String
    val methodsDesc: Map<String, String>
    /**
     * Метод, обеспечивающий выполнение команды
     * @throws InvalidUserInputException
     */
    fun execute(args: List<String>)
}

/** Вызов команды помощи */
class HelpCommand (private val commands: ClientCommands, override val name: String = "help") : Command {
    override val desc: String = "описание команд"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.help()
    }
}

/** Вызов команды завершения программы */
class ExitCommand (private val commands: ClientCommands, override val name: String = "exit"): Command {
    override val desc: String = "завершение программы"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.exit()
    }
}

/** Вызов истории выполненных команд */
class HistoryCommand(private val commands: ClientCommands, override val name: String = "history") : Command {
    override val desc: String = "обеспечивает вывод последних $MAX_HISTORY_SIZE команд без их аргументов"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.history()
    }
}

/** Вызов выполнения скрипта */
class ExecuteScriptCommand(private val commands: ClientCommands,
                           private val invoker: Invoker,
                           override val name: String = "execute"): Command {
    override val desc: String = "исполнение скрипта из указанного файла"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        if (args.isNotEmpty())
            commands.executeScript(args[0], invoker)
        else
            throw InvalidUserInputException("В команде пропущен путь файла")
    }
}

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand (private val manager: DataBaseCommands, override val name: String = "info"): Command {
    override val desc: String = "вывести информацию о коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callString(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand (private val manager: DataBaseCommands, override val name: String = "show"): Command {
    override val desc: String = "вывести все элементы коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callString(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: DataBaseCommands, override val name: String = "add"): Command {
    override val desc: String = "добавить элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.add(CustomConsole.createPerson()))
    }
}

class UpdateCommand(private val manager: DataBaseCommands, override val name: String = "update"): Command {
    override val desc: String = "обновить элемент коллекции"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id обновляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if(args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указаны индекс класса и его имя")
        CustomConsole.callBool(manager.update(index, CustomConsole.createPerson()))
    }
}

class RemoveIdCommand(private val manager: DataBaseCommands, override val name: String = "remove"): Command {
    override val desc: String = "удалить элемент из коллекции по его id"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id удаляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if(args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указан индекс класса")
        CustomConsole.callBool(manager.removeId(index))
    }
}

class ClearCommand(private val manager: DataBaseCommands, override val name: String = "clear"): Command {
    override val desc: String = "очистить коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.clear())
    }
}

class SaveCommand(private val manager: DataBaseCommands, override val name: String = "save"): Command {
    override val desc: String = "сохранить коллекцию в файл"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.save())
    }
}

class AddIfMinCommand(private val manager: DataBaseCommands, override val name: String = "add_if_min"): Command {
    override val desc: String = "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.addIfMin(CustomConsole.createPerson()))
    }
}

class RemoveGreaterCommand(private val manager: DataBaseCommands,
                           override val name: String = "remove_greater"): Command {
    override val desc: String = "удалить из коллекции все элементы, превышающие заданный"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.removeGreater(CustomConsole.createPerson()))
    }
}

class RemoveAllByLocationCommand(private val manager: DataBaseCommands,
                                 override val name: String = "remove_by_loc"): Command {
    override val desc: String = "удалить из коллекции все элементы, значение поля location которого эквивалентно заданному"
    override val methodsDesc: Map<String, String> = mapOf(Pair("location", "x, y, z и опциональное название места"))
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.removeAllByLocation(CustomConsole.createLocation(args.joinToString(" "))))
    }
}

class FilterGreaterThanHairColorCommand(private val manager: DataBaseCommands,
                                        override val name: String = "filter_by_hair"): Command {
    override val desc: String = "вывести элементы, значение поля hairColor которых больше заданного"
    override val methodsDesc: Map<String, String> = mapOf(Pair("color", "GREEN, RED, BLACK, YELLOW или BROWN"))
    override fun execute(args: List<String>) {
        val res = manager.filterGreaterThanHairColor(CustomConsole.createColor(args.joinToString(" ")))
        if (res.success)
            res.msg.forEach { println(it) }
        else
            println(CustomConsole.red + res.errorMessage + CustomConsole.reset)
    }
}

class PrintFieldAscendingHairColorCommand(private val manager: DataBaseCommands,
                                          override val name: String = "print_hair"): Command {
    override val desc: String = "вывести значения поля hairColor всех элементов в порядке возрастания"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val res = manager.printFieldAscendingHairColor()
        if (res.success)
            res.msg.forEach { println(it) }
        else
            println(CustomConsole.red + res.errorMessage + CustomConsole.reset)
    }
}
/** Реализация вызова команды добавления готового элемента в коллекцию */
class AddTestCommand(private val manager: DataBaseCommands, override val name: String = "add_test"): Command {
    override val desc: String = "добавить готовый элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.callBool(manager.add(Person(
            "Noname",
            Coordinates(42f, 21.0),
            255,
            ZonedDateTime.now(),
            666,
            Color.BLACK,
            Location(11f, 22f, 44))))
    }
}