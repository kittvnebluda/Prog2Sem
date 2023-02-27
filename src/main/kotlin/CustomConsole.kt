/**
 * Класс для упрощения работы с консолью
 */
class CustomConsole(private val invoker: Invoker) {
    companion object {
        const val red = "\u001b[31m"
        const val green = "\u001B[32m"
        const val reset = "\u001b[0m"
        /**
         * Выводит [offers], принимает количество строк в консоли, равное количеству [offers] и возвращает их.
         * @param offers запросы к пользователю
         * @return ввод пользователя
         */
        fun readlines(vararg offers: String?): Array<String?> {
            val userInput = arrayOf(*offers)
            var inputText: String?
            offers.forEachIndexed { index, offer ->
                println(offer)
                inputText = readlnOrNull()
                userInput[index] = inputText
            }
            return userInput
        }
        /** Возвращает случайный ANSI escape код (91 - 96, светлые цвета) */
        fun rBC(): String {
            return "\u001b[${kotlin.random.Random.nextInt(91, 97)}m"
        }

        /** Парочка приветственных слов */
        fun greetings() {
            val greeting = when(kotlin.random.Random.nextInt(3)) {
                0 -> "Здравствуйте, пользователь."
                1 -> "ПРИВЕТСТВУЮ!"
                2 -> "ВСЕ ${rBC()}Х ${rBC()}О ${rBC()}Р ${rBC()}О ${rBC()}Ш ${rBC()}О \u001b[0m?"
                else -> "Вы точно хотите знать зачем я нужен?"
            }
            println("#############################################")
            println("# $greeting")
            println("# Введите команду или 'help' для помощи.")
            println("#############################################")
        }

        /**
         * Генерирует текст для команды помощи.
         * @param classes классы, методы которых будут проверены
         */
        fun generateHelp(vararg classes: Any) {
            // Проходимся по всем классам
            for (c in classes) {
                // Проходимся по всем методам класса
                c.javaClass.methods.forEach {method ->

                    val inHelp = method.getAnnotation(InHelp::class.java)

                    if (inHelp != null) {
                        val params = LinkedHashMap<String, String>()
                        // Проходимся по всем параметрам метода
                        method.parameters.forEach {param ->

                            val inHelpParam = param.getAnnotation(InHelp::class.java)
                            if (inHelpParam != null)
                                params[param.name] = inHelpParam.desc
                        }
                        HELP += "${method.name} ${params.keys.joinToString(" ")}\t\t\t\t : ${inHelp.desc}\n"
                        // Добавляем описания параметров
                        params.forEach { (key, value) ->
                            HELP += "\t\t\t\t$key : $value\n"
                        }
                    }
                }
            }
        }
    }

    /**
     * Главная функция класса, реализующая постоянное "общение" с пользователем
     */
    fun loop() {
        greetings()
        while (!ISQUIT) {
            try {
                invoker.proceed(readln())
            }
            catch (e: InvalidUserInputException) {
                println("${red}Ошибка | ${e.message}$reset")
            }
        }
    }
}
