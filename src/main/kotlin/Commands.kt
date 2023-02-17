interface Command {
    fun execute()
}

class HelpCommand (private val manager: CollectionManager<*>) : Command {
    override fun execute() {
        println(manager.help())
    }
}

class InfoCommand (private val manager: CollectionManager<*>): Command {
    override fun execute() {
        println(manager.info())
    }
}
