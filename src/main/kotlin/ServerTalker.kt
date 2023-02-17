class ServerTalker {
    private val cmdMap = HashMap<String, Command>()

    fun put(cmdName: String, command: Command) {
        cmdMap[cmdName] = command
    }

    fun execute(cmdName: String) {
        val command = cmdMap[cmdName] ?: throw IllegalStateException("No command registered for $cmdName")
        command.execute()
    }
}