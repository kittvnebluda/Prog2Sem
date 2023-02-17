import java.util.*

fun main(args: Array<String>) {
    val manager = LocalManager<Person>(PriorityQueue())

    val help = HelpCommand(manager)
    val info = InfoCommand(manager)

    val server = ServerTalker()

    server.put("help", help)
    server.put("info", info)

    server.execute("help")
}