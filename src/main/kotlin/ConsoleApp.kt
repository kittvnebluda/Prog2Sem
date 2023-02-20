import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val manager = object : CollectionManager<Person> {
        override fun info(): String {
            TODO("Not yet implemented")
        }

        override fun show(): String {
            TODO("Not yet implemented")
        }

        override fun add(p: String): Boolean {
            TODO("Not yet implemented")
        }

        override fun update(index: Int, p: String) {
            TODO("Not yet implemented")
        }

        override fun removeId(index: Int) {
            TODO("Not yet implemented")
        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        override fun save() {
            TODO("Not yet implemented")
        }

        override fun addIfMin(p: String) {
            TODO("Not yet implemented")
        }

        override fun removeGreater() {
            TODO("Not yet implemented")
        }

        override fun removeAllByLocation(location: String): Boolean {
            TODO("Not yet implemented")
        }

        override fun filterGreaterThanHairColor(color: Color?): String {
            TODO("Not yet implemented")
        }

        override fun printFieldAscendingHairColor(color: Color?): String {
            TODO("Not yet implemented")
        }

    }
    val clientCommands = object : ClientCommands {
        override fun help(): String {
            TODO("Not yet implemented")
        }

        override fun executeScript(filename: String) {
            TODO("Not yet implemented")
        }

        override fun exit() {
            TODO("Not yet implemented")
        }

        override fun history() {
            TODO("Not yet implemented")
        }

    }

    val help = HelpCommand(clientCommands)
    val info = InfoCommand(manager)
    val show = ShowCommand(manager)
    val add = AddCommand(manager)

    val server = ServerTalker()

    server.put("help", help)
    server.put("info", info)
    server.put("show", show)
    server.put("add", add)

    server.proceed("add")
}