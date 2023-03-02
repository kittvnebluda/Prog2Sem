/**
 * Класс хранящий и вызывающий команды
 */
class ConsoleInvoker: Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    fun putAll(commands: Array<Command>) {
        commands.forEach { put(it) }
    }

    override fun proceed(cmd: String) {
        val postCmd = CustomConsole.splitSpaces(cmd)
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$cmd: команда не найдена")

        command.execute(postCmd.slice(1 until postCmd.size))

        HISTORY.add(command.name)
        if (HISTORY.size > MAX_HISTORY_SIZE)
            HISTORY.remove()
    }
}