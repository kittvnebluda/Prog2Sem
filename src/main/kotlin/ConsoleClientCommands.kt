import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * Класс консольных работающих без сервера команд пользователя.
 */
class ConsoleClientCommands: ClientCommands {
    companion object {
        private val openedScripts = LinkedList<String>()
    }
    @InHelp(desc = "описание команд")
    override fun help() {
        println(HELP)
    }
    @InHelp(desc = "исполнение скрипта из указанного файла")
    override fun executeScript(@InHelp(desc = "путь к файлу") filename: String, invoker: Invoker) {
        val file = File(filename)

        try {
            val absPath = file.absolutePath
            openedScripts.add(absPath)

            val sc = Scanner(file)
            while (sc.hasNextLine()) {
                val command = sc.nextLine()

                if (absPath in openedScripts)
                    throw InvalidUserInputException("Обнаружено зацикливание!")
                else
                    invoker.proceed(command)
            }
            println("${CustomConsole.green}Выполнение команд завершено${CustomConsole.reset}")
            sc.close()
            openedScripts.clear()

        } catch (e: FileNotFoundException) {
            throw InvalidUserInputException("$filename: файл не найден")
        }
    }
    @InHelp(desc = "завершение программы")
    override fun exit() {
        ISQUIT = true
    }
    @InHelp(desc = "обеспечивает вывод последних 12 команд без их аргументов")
    override fun history() {
        println(HISTORY.slice(0 until HISTORY.size).joinToString("\n"))
    }
}