class Talker: Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun proceed(cmd: String) {
        val postCmd = cmd.split(" ")
        postCmd.forEach { println(it) }
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("Неизвестная команда: $cmd")
        command.execute(postCmd.slice(1 until postCmd.size))
    }
}