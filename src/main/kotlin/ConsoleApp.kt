import java.util.*

fun main(args: Array<String>) {
    val manager = LocalManager<Person>(PriorityQueue())

    val help = HelpCommand(manager)
    val info = InfoCommand(manager)
    val show = ShowCommand(manager)
    val add = AddCommand(manager)

    val server = ServerTalker()

    server.put("help", help)
    server.put("info", info)
    server.put("show", show)
    server.put("add", add)

    server.proceed("help")
    server.proceed("add")
}