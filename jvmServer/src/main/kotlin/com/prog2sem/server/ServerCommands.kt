package com.prog2sem.server


import com.prog2sem.server.tasks.PacketsScheduler.send
import com.prog2sem.shared.cmdpattern.Command
import com.prog2sem.shared.utils.MsgMarker.getGeneric
import com.prog2sem.shared.utils.MsgMarker.markGeneric
import java.net.InetAddress
import com.prog2sem.shared.utils.Log
import com.prog2sem.shared.utils.MsgMarker.markError
import java.net.InetSocketAddress


/** Реализация вызова команды получения информации о коллекции */
class InfoCommand(override val name: String = "info") : Command {
    override val desc: String = "вывести информацию о коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.info()), address, login, password)
    }
}

/** Реализация вызова команды show */
class ShowCommand(override val name: String = "show") : Command {
    override val desc: String = "вывести все элементы коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.show()), address, login, password)
    }
}

/** Реализация вызова команды добавления элемента в коллекцию */
class AddCommand(override val name: String = "add") : Command {
    override val desc: String = "добавить элемент в коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) {
            val f = LocalManager.add(getGeneric(args[2]), login, password)
            Log.d(f.toString())
            send(markGeneric(f), address, login, password)
        }
    }
}

/** Реализация вызова команды обновления элемента коллекции */
class UpdateCommand(override val name: String = "update") : Command {
    override val desc: String = "обновить элемент коллекции"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id обновляемого элемента"))
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.update(args[2].toInt(), getGeneric(args[3]), login, password)), address, login, password)
    }
}

/** Реализация вызова команды удаления элемента коллекции */
class RemoveIdCommand(override val name: String = "remove_by_id") : Command {
    override val desc: String = "удалить элемент из коллекции по его id"
    override val methodsDesc: Map<String, String> = mapOf(Pair("index", "id удаляемого элемента"))
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeId(args[2].toInt(), login, password)), address, login, password)
    }
}

/** Реализация вызова команды отчистки коллекции */
class ClearCommand(override val name: String = "clear") : Command {
    override val desc: String = "очистить коллекцию"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.clear()), address, login, password)
    }
}

/** Реализация вызова команды добавления элемента в коллекцию с условием */
class AddIfMinCommand(override val name: String = "add_if_min") : Command {
    override val desc: String =
        "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.addIfMin(getGeneric(args[2]), login, password)), address, login, password)
    }
}

/** Реализация вызова команды удаления наибольшего элемента коллекции */
class RemoveGreaterCommand(
    override val name: String = "remove_greater"
) : Command {
    override val desc: String = "удалить из коллекции все элементы, превышающие заданный"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeGreater(getGeneric(args[2]), login, password)), address, login, password)
    }
}

/** Реализация вызова команды удаления элемента коллекции по его локации*/
class RemoveAllByLocationCommand(
    override val name: String = "remove_all_by_location"
) : Command {
    override val desc: String =
        "удалить из коллекции все элементы, значение поля location которого эквивалентно заданному"
    override val methodsDesc: Map<String, String> = mapOf(Pair("location", "x, y, z и опциональное название места"))
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.removeAllByLocation(getGeneric(args[2]), login, password)), address, login, password)
    }
}

/** Реализация вызова команды вывода элементов коллекции */
class FilterGreaterThanHairColorCommand(
    override val name: String = "filter_greater_than_hair_color"
) : Command {
    override val desc: String = "вывести элементы, значение поля hairColor которых больше заданного"
    override val methodsDesc: Map<String, String> = mapOf(Pair("color", "GREEN, RED, BLACK, YELLOW или BROWN"))
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        if (args.size > 2) send(markGeneric(LocalManager.filterGreaterThanHairColor(getGeneric(args[2]))), address, login, password)
    }
}

/** Реализация вызова команды вывода полей элементов коллекции */
class PrintFieldAscendingHairColorCommand(
    override val name: String = "print_field_ascending_hair_color"
) : Command {
    override val desc: String = "вывести значения поля hairColor всех элементов в порядке возрастания"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        send(markGeneric(LocalManager.printFieldAscendingHairColor()), address, login, password)
    }
}

class AddLogin(
    override val name: String = "add_login"
) : Command {
    override val desc: String = ""
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)
        val res = LocalManager.signup(login, password)
        if (res) {
            send(markGeneric(true), address, login, password)
        }
        else {
            send(markError("Пользователь с таким именем уже существует"), address, login, password)
        }
    }
}

class CheckLogin(
    override val name: String = "check_login"
) : Command {
    override val desc: String = ""
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)

        if (LocalManager.login(login, password))
            send(markGeneric(true), address, login, password)
        else
            send(markError("Пользователь с таким именем не существует"), address, login, password)
    }
}

class GetTable(
    override val name: String = "getTable"
) : Command {
    override val desc: String = "вывести значения поля hairColor всех элементов в порядке возрастания"
    override val methodsDesc: Map<String, String> = emptyMap()
    override fun execute(args: List<String>, login: String, password: String) {
        val port = args[1] as Int
        val address = InetSocketAddress(InetAddress.getByName(args[0]), port)

        val list = LocalManager.getAllTable()

        send(markGeneric(list), address, login, password)
    }


}