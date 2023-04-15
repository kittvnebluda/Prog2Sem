package com.prog2sem.client.cmdpattern

import com.prog2sem.client.persona.FromConsolePersonBuilder
import com.prog2sem.client.persona.NoNamePersonBuilder
import com.prog2sem.client.utils.CreateFromStd
import com.prog2sem.client.utils.Smt
import com.prog2sem.shared.exceptions.InvalidUserInputException
import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.net.DataBaseCommands
import com.prog2sem.shared.persona.PersonDirector

/** Реализация вызова команды получения информации о коллекции */
class InfoCommand(private val manager: DataBaseCommands, override val name: String = "info") :
    Command {
    override val desc: String = "вывести информацию о коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println(manager.info())
    }
}

/** Реализация вызова команды show */
class ShowCommand(private val manager: DataBaseCommands, override val name: String = "show") :
    Command {
    override val desc: String = "вывести все элементы коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        Smt.outIterable(manager.show())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(private val manager: DataBaseCommands, override val name: String = "add") :
    Command {
    override val desc: String = "добавить элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        Smt.addArgToHistory(person.toString())
        Smt.outBool(manager.add(person))
    }
}

/** Реализация вызова команды добавления готового элемента в коллекцию */
class AddTestCommand(private val manager: DataBaseCommands, override val name: String = "add_noname") :
    Command {
    override val desc: String = "добавить готовый элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(NoNamePersonBuilder()).createPerson()
        Smt.addArgToHistory(person.toString())
        Smt.outBool(manager.add(person))
    }
}

/** Реализация вызова команды обновления элемента коллекции */
class UpdateCommand(private val manager: DataBaseCommands, override val name: String = "update") :
    Command {
    override val desc: String = "обновить элемент коллекции"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id обновляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if (args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указаны индекс класса и его имя")

        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        Smt.addArgToHistory(person.toString())
        Smt.outBool(manager.update(index, person))
    }
}

/** Реализация вызова команды удаления элемента коллекции */
class RemoveIdCommand(private val manager: DataBaseCommands, override val name: String = "remove_by_id") :
    Command {
    override val desc: String = "удалить элемент из коллекции по его id"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id удаляемого элемента"))
    override fun execute(args: List<String>) {
        val index = if (args.isNotEmpty()) args[0].toInt()
        else throw InvalidUserInputException("Не указан индекс класса")
        Smt.outBool(manager.removeId(index))
    }
}

/** Реализация вызова команды отчистки коллекции */
class ClearCommand(private val manager: DataBaseCommands, override val name: String = "clear") :
    Command {
    override val desc: String = "очистить коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        Smt.outBool(manager.clear())
    }
}

/** Реализация вызова команды добавления элемента в коллекцию с условием */
class AddIfMinCommand(private val manager: DataBaseCommands, override val name: String = "add_if_min") :
    Command {
    override val desc: String =
        "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val person = PersonDirector(FromConsolePersonBuilder()).createPerson()
        Smt.addArgToHistory(person.toString())
        Smt.outBool(manager.addIfMin(person))
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
        Smt.addArgToHistory(person.toString())
        Smt.outBool(manager.removeGreater(person))
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
        Smt.addArgToHistory(location.toString())
        Smt.outBool(manager.removeAllByLocation(location))
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
        Smt.addArgToHistory(color.toString())
        val res = manager.filterGreaterThanHairColor(color)
        Smt.outIterable(res)
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
        Smt.outIterable(res)
    }
}