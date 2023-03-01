import java.util.*

typealias cc = CustomConsole

const val MAX_HISTORY_SIZE = 12

val HISTORY = LinkedList<String>()
var ISQUIT = false
var HELP = ""

fun main(args: Array<String>) {
    val dbCommands = object : DataBaseCommands<Person> {
        override fun info(): Callback<String> {
            TODO("Not yet implemented")
        }

        override fun show(): Callback<String> {
            TODO("Not yet implemented")
        }

        override fun add(p: Person): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun update(index: Int, p: Person): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun removeId(index: Int): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun clear(): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun save(): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun addIfMin(p: Person): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun removeGreater(p: Person): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun removeAllByLocation(location: Location): Callback<Boolean> {
            TODO("Not yet implemented")
        }

        override fun filterGreaterThanHairColor(color: Color?): Callback<Array<Person>> {
            TODO("Not yet implemented")
        }

        override fun printFieldAscendingHairColor(): Callback<Array<Color>> {
            TODO("Not yet implemented")
        }
    }
    val clientCommands = ConsoleClientCommands()
    val consoleInvoker = ConsoleInvoker()

    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, consoleInvoker)

    val info = InfoCommand(dbCommands)
    val show = ShowCommand(dbCommands)
    val add = AddCommand(dbCommands)
    val update = UpdateCommand(dbCommands)
    val remove = RemoveIdCommand(dbCommands)
    val clear = ClearCommand(dbCommands)
    val save = SaveCommand(dbCommands)
    val addIdMax = AddIfMinCommand(dbCommands)
    val removeGreater = RemoveGreaterCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)

    consoleInvoker.put(help)
    consoleInvoker.put(info)
    consoleInvoker.put(show)
    consoleInvoker.put(add)
    consoleInvoker.put(exit)
    consoleInvoker.put(history)
    consoleInvoker.put(execute)
    consoleInvoker.put(update)
    consoleInvoker.put(remove)
    consoleInvoker.put(clear)
    consoleInvoker.put(save)
    consoleInvoker.put(addIdMax)
    consoleInvoker.put(removeGreater)
    consoleInvoker.put(filterByColor)
    consoleInvoker.put(printHairColor)

    cc.generateHelp(clientCommands)

    args.forEach { println(it) }

    val cc = CustomConsole(consoleInvoker)

    cc.loop()
}