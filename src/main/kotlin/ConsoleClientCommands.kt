import java.io.File
import java.io.FileNotFoundException
import java.util.*

class ConsoleClientCommands: ClientCommands {
    override fun help() {
        println("help\t\t\t: описание команд\n" +
                "add {element name}\t\t\t: добавление элемента в коллекцию\n" +
                "exit\t\t\t: завершение программы\n" +
                "history\t\t\t: обеспечивает вывод последних 12 команд без их аргументов\n" +
                "execute {filename}\t\t\t: исполнение скрипта из указанного файла")
    }

    override fun executeScript(filename: String, consoleInvoker: ConsoleInvoker) {
        val file = File(filename)

        try {
            val sc = Scanner(file)
            while (sc.hasNextLine()) {
                val command = sc.nextLine()

                if (filename in command)
                    throw InvalidUserInputException("Не стоит вызывать в файле себя же!")
                else
                    consoleInvoker.proceed(command)
            }
            sc.close()
        } catch (e: FileNotFoundException) {
            throw InvalidUserInputException("$filename: файл не найден")
        }
    }

    override fun exit() {
        ISQUIT = true
    }

    override fun history() {
        HISTORY.forEach { println(it) }
    }
}