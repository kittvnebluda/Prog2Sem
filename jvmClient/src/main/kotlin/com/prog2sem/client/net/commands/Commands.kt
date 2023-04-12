package com.prog2sem.client.net.commands

import com.prog2sem.client.utils.CustomConsole
import com.prog2sem.client.MAX_HISTORY_SIZE
import com.prog2sem.client.exceptions.InvalidUserInputException
import com.prog2sem.client.Invoker
import com.prog2sem.client.net.InetCommands
import com.prog2sem.client.utils.CreateFromStd
import com.prog2sem.client.net.LocalCommands
import com.prog2sem.client.persona.FromConsolePersonBuilder
import com.prog2sem.client.persona.NoNamePersonBuilder
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.persona.PersonDirector

/** Вызов команды помощи */
class HelpCommand(private val commands: LocalCommands, override val name: String = "help") : Command {
    override val desc: String = "описание команд"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.help()
    }
}

/** Вызов команды завершения программы */
class ExitCommand(private val commands: LocalCommands, override val name: String = "exit") : Command {
    override val desc: String = "завершение программы"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println("Удачи!")
        commands.exit()
    }
}

/** Вызов истории выполненных команд */
class HistoryCommand(private val commands: LocalCommands, override val name: String = "history") : Command {
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

class ServerAddressCommand(
    private val commands: InetCommands,
    override val name: String = "get_server_addr"
) : Command {
    override val desc: String = "Выводит адрес сервера"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        commands.getServerAddr()
    }

}

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand(private val manager: DataBaseCommands, override val name: String = "info") : Command {
    override val desc: String = "вывести информацию о коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand(private val manager: DataBaseCommands, override val name: String = "show") : Command {
    override val desc: String = "вывести все элементы коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: DataBaseCommands, override val name: String = "add") : Command {
    override val desc: String = "добавить элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        CustomConsole.addArgToHistory(person.toString())
        CustomConsole.outBool(manager.add(person))
    }
}

/** Реализация вызова команды обновления элемента коллекции */
class UpdateCommand(private val manager: DataBaseCommands, override val name: String = "update") : Command {
    override val desc: String = "обновить элемент коллекции"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id обновляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if (args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указаны индекс класса и его имя")

        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        CustomConsole.addArgToHistory(person.toString())
        CustomConsole.outBool(manager.update(index, person))
    }
}

/** Реализация вызова команды удаления элемента коллекции */
class RemoveIdCommand(private val manager: DataBaseCommands, override val name: String = "remove_by_id") : Command {
    override val desc: String = "удалить элемент из коллекции по его id"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id удаляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if (args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указан индекс класса")
        CustomConsole.outBool(manager.removeId(index))
    }
}

/** Реализация вызова команды отчистки коллекции */
class ClearCommand(private val manager: DataBaseCommands, override val name: String = "clear") : Command {
    override val desc: String = "очистить коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        CustomConsole.outBool(manager.clear())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию с условием */
class AddIfMinCommand(private val manager: DataBaseCommands, override val name: String = "add_if_min") : Command {
    override val desc: String =
        "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        CustomConsole.addArgToHistory(person.toString())
        CustomConsole.outBool(manager.addIfMin(person))
    }
}

/** Реализация вызова команды удаления наибольшего элемента коллекции */
class RemoveGreaterCommand(
    private val manager: DataBaseCommands,
    override val name: String = "remove_greater"
) : Command {
    override val desc: String = "удалить из коллекции все элементы, превышающие заданный"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        CustomConsole.addArgToHistory(person.toString())
        CustomConsole.outBool(manager.removeGreater(person))
    }
}

/** Реализация вызова команды удаления элемента коллекции по его локации*/
class RemoveAllByLocationCommand(
    private val manager: DataBaseCommands,
    override val name: String = "remove_all_by_location"
) : Command {
    override val desc: String =
        "удалить из коллекции все элементы, значение поля location которого эквивалентно заданному"
    override val methodsDesc: Map<String, String> = mapOf(Pair("location", "x, y, z и опциональное название места"))
    override fun execute(args: List<String>) {
        val location = CreateFromStd.location(args.joinToString(" "))
        CustomConsole.addArgToHistory(location.toString())
        CustomConsole.outBool(manager.removeAllByLocation(location))
    }
}

/** Реализация вызова команды вывода элементов коллекции */
class FilterGreaterThanHairColorCommand(
    private val manager: DataBaseCommands,
    override val name: String = "filter_greater_than_hair_color"
) : Command {
    override val desc: String = "вывести элементы, значение поля hairColor которых больше заданного"
    override val methodsDesc: Map<String, String> = mapOf(Pair("color", "GREEN, RED, BLACK, YELLOW или BROWN"))
    override fun execute(args: List<String>) {
        val color = CreateFromStd.color(args.joinToString(" "))
        CustomConsole.addArgToHistory(color.toString())
        val res = manager.filterGreaterThanHairColor(color)
        CustomConsole.outIterable(res)
    }
}

/** Реализация вызова команды вывода полей элементов коллекции */
class PrintFieldAscendingHairColorCommand(
    private val manager: DataBaseCommands,
    override val name: String = "print_field_ascending_hair_color"
) : Command {
    override val desc: String = "вывести значения поля hairColor всех элементов в порядке возрастания"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val res = manager.printFieldAscendingHairColor()
        CustomConsole.outIterable(res)
    }
}