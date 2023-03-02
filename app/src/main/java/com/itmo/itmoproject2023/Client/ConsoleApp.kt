import com.itmo.itmoproject2023.*
import com.itmo.itmoproject2023.JsonWorker.json
import kotlinx.serialization.decodeFromString
import java.util.*

typealias cc = CustomConsole

const val MAX_HISTORY_SIZE = 12

val HISTORY = LinkedList<String>()
var ISQUIT = false
var HELP = ""

fun client(args: Array<String>) {
    val dbCommands = object : DataBaseCommands<Person> {
        override fun info(): Callback<String> {
            val jsonString = LocalManager.info()
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun show(): Callback<String> {
            val jsonString = LocalManager.show()
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun add(p: Person): Callback<String> {
            val jsonString = LocalManager.add(p)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun update(index: Int, p: Person): Callback<String> {
            val jsonString = LocalManager.update(index, p)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun removeId(index: Int): Callback<String> {
            val jsonString = LocalManager.removeId(index)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun clear(): Callback<String> {
            val jsonString = LocalManager.clear()
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun save(): Callback<String> {
            val jsonString = LocalManager.save(args[0])
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun addIfMin(p: Person): Callback<String> {
            val jsonString = LocalManager.addIfMin(p)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun removeGreater(p: Person): Callback<String> {
            val jsonString = LocalManager.removeGreater(p)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun removeAllByLocation(location: Location): Callback<String> {
            val jsonString = LocalManager.removeAllByLocation(location)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun filterGreaterThanHairColor(color: Color?): Callback<String> {
            val jsonString = LocalManager.filterGreaterThanHairColor(color)
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
        }

        override fun printFieldAscendingHairColor(): Callback<String> {
            val jsonString = LocalManager.printFieldAscendingHairColor()
            val sa: ServerAnswer = json.decodeFromString(jsonString)
            return Callback(sa.isSuccess, sa.answerMessage, "")
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
    val removeByLocation = RemoveAllByLocationCommand(dbCommands)
    val filterByColor = FilterGreaterThanHairColorCommand(dbCommands)
    val printHairColor = PrintFieldAscendingHairColorCommand(dbCommands)

    consoleInvoker.putAll(
        help, info, show, add, exit, history, execute, update, remove, clear, save, addIdMax, removeGreater,
        removeByLocation, filterByColor, printHairColor)

    consoleInvoker.genHelp()

    val cc = CustomConsole(consoleInvoker)

    cc.loop()
}