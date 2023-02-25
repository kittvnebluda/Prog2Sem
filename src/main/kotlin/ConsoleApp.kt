fun main(args: Array<String>) {
    val collManager = object : CollectionManager<Person> {
        override fun info(): String {
            TODO("Not yet implemented")
        }

        override fun show(): String {
            TODO("Not yet implemented")
        }

        override fun add(p: String): Boolean {
            println(p)
            return true
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
    val clientCommands = ConsoleClientCommands()
    val talker = Talker()

    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)
    val execute = ExecuteScriptCommand(clientCommands, talker)

    val info = InfoCommand(collManager)
    val show = ShowCommand(collManager)
    val add = AddCommand(collManager)

    talker.put(help)
    talker.put(info)
    talker.put(show)
    talker.put(add)
    talker.put(exit)
    talker.put(history)
    talker.put(execute)

    val cc = CustomConsole(talker)

    cc.loop()
}