class ServerTalker {
    private val cmdMap = HashMap<String, Command>()

    fun put(cmdName: String, command: Command) {
        cmdMap[cmdName] = command
    }

    fun proceed(cmd: String) {
        val postCmd = cmd.split(" ")
        postCmd.forEach { println(it) }
        val command = cmdMap[postCmd[0]] ?: throw IllegalStateException("No command registered for $cmd")
        command.execute(postCmd.slice(1 until postCmd.size))
    }
}