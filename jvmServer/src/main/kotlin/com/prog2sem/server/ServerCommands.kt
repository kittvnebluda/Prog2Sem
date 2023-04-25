package com.prog2sem.server


import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.utils.MsgMarker.getGeneric
import com.prog2sem.shared.utils.MsgMarker.markGeneric
import java.net.InetAddress
import com.prog2sem.server.ServerScheduler.send
import java.net.InetSocketAddress


/** Реализация вызова команды получения информации о коллекции */
class InfoCommand(override val name: String = "info") : Command {
    override val desc: String = "вывести информацию о коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        println(args[1])
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.info()), address)
    }
}

/** Реализация вызова команды show */
class ShowCommand(override val name: String = "show") : Command {
    override val desc: String = "вывести все элементы коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.show()), address)
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(override val name: String = "add") : Command {
    override val desc: String = "добавить элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) {
            send(markGeneric(LocalManager.add(getGeneric(args[2]))), address)
        }
    }
}

/** Реализация вызова команды обновления элемента коллекции */
class UpdateCommand(override val name: String = "update") : Command {
    override val desc: String = "обновить элемент коллекции"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id обновляемого элемента"))
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.update(getGeneric(args[2]), getGeneric(args[3]))), address)
    }
}

/** Реализация вызова команды удаления элемента коллекции */
class RemoveIdCommand(override val name: String = "remove_by_id") : Command {
    override val desc: String = "удалить элемент из коллекции по его id"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id удаляемого элемента"))
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeId(getGeneric(args[2]))), address)
    }
}

/** Реализация вызова команды отчистки коллекции */
class ClearCommand(override val name: String = "clear") : Command {
    override val desc: String = "очистить коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.clear()), address)
    }
}

/** Реализация вызова команды добавления элемента в коллекцию с условием */
class AddIfMinCommand(override val name: String = "add_if_min") : Command {
    override val desc: String =
        "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.addIfMin(getGeneric(args[2]))), address)
    }
}

/** Реализация вызова команды удаления наибольшего элемента коллекции */
class RemoveGreaterCommand(
    override val name: String = "remove_greater"
) : Command {
    override val desc: String = "удалить из коллекции все элементы, превышающие заданный"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeGreater(getGeneric(args[2]))), address)
    }
}

/** Реализация вызова команды удаления элемента коллекции по его локации*/
class RemoveAllByLocationCommand(
    override val name: String = "remove_all_by_location"
) : Command {
    override val desc: String =
        "удалить из коллекции все элементы, значение поля location которого эквивалентно заданному"
    override val methodsDesc: Map<String, String> = mapOf(Pair("location", "x, y, z и опциональное название места"))
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeAllByLocation(getGeneric(args[2]))), address)
    }
}

/** Реализация вызова команды вывода элементов коллекции */
class FilterGreaterThanHairColorCommand(
    override val name: String = "filter_greater_than_hair_color"
) : Command {
    override val desc: String = "вывести элементы, значение поля hairColor которых больше заданного"
    override val methodsDesc: Map<String, String> = mapOf(Pair("color", "GREEN, RED, BLACK, YELLOW или BROWN"))
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.filterGreaterThanHairColor(getGeneric(args[2]))), address)
    }
}

/** Реализация вызова команды вывода полей элементов коллекции */
class PrintFieldAscendingHairColorCommand(
    override val name: String = "print_field_ascending_hair_color"
) : Command {
    override val desc: String = "вывести значения поля hairColor всех элементов в порядке возрастания"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.printFieldAscendingHairColor()), address)
    }
}