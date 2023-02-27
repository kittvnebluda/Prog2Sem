/**
 * Класс хранящий и вызывающий команды
 */
class ConsoleInvoker: Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun proceed(cmd: String) {
        val postCmd = cmd.trim().replace("\\s+".toRegex(), " ").split(" ")
        val command = cmdMap[postCmd[0]] ?: throw InvalidUserInputException("$cmd: команда не найдена")

        command.execute(postCmd.slice(1 until postCmd.size))

        HISTORY.add(command.name)
        if (HISTORY.size > MAX_HISTORY_SIZE)
            HISTORY.remove()
    }
}