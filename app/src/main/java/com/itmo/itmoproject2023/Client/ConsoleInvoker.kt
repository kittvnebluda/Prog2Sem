/**
 * Класс хранящий и вызывающий команды
 */
class ConsoleInvoker: Invoker {
    override val cmdMap = HashMap<String, Command>()

    override fun put(command: Command) {
        cmdMap[command.name] = command
    }

    override fun putAll(vararg commands: Command) {
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

    fun genHelp() {
        cmdMap.values.forEach { command ->
            HELP += "${command.name} ${command.methodsDesc.keys.joinToString(" ")}\t\t\t\t\t\t\t\t\t\t\t\t : ${command.desc}\n"
            command.methodsDesc.forEach { (name, desc) ->
                HELP += "\t\t\t\t\t\t\t\t\t\t\t\t$name : $desc\n"
            }
        }
    }
}