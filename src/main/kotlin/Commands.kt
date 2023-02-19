interface Command {
    fun execute(args: List<String>)
}

class HelpCommand (private val manager: CollectionManager<*>) : Command {
    override fun execute(args: List<String>) {
        println(manager.help())
    }
}

class InfoCommand (private val manager: CollectionManager<*>): Command {
    override fun execute(args: List<String>) {
        println(manager.info())
    }
}

class ShowCommand (private val manager: CollectionManager<*>): Command {
    override fun execute(args: List<String>) {
        println(manager.show())
    }
}

class AddCommand<T> (private val manager: CollectionManager<T>): Command {
    override fun execute(args: List<String>) {
        try {
            val name = if(args.isNotEmpty()) args[0] else throw BadUserInputException("Не указано имя класса")
            val userInput = CustomConsole.readlines("Введите рост: ")
            manager.add(arrayOf(name, *userInput).joinToString(" "))
        } catch (_: BadUserInputException) {}
    }
}
