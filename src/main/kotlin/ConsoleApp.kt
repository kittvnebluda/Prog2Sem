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
    val clientCommands = object : ClientCommands {
        override fun help() {
            println("help    : описание команд\n" +
                    "add {element name} :   добавление элемента в коллекцию\n" +
                    "exit   : завершение программы")
        }

        override fun executeScript(filename: String) {
            TODO("Not yet implemented")
        }

        override fun exit() {
            ISQUIT = true
        }

        override fun history() {
            HISTORY.forEach { println(it) }
        }
    }

    val help = HelpCommand(clientCommands)
    val exit = ExitCommand(clientCommands)
    val history = HistoryCommand(clientCommands)

    val info = InfoCommand(collManager)
    val show = ShowCommand(collManager)
    val add = AddCommand(collManager)

    val talker = Talker()

    talker.put(help)
    talker.put(info)
    talker.put(show)
    talker.put(add)
    talker.put(exit)
    talker.put(history)

    val cc = CustomConsole(talker)

    cc.loop()
}