/**
 * Класс для упрощения работы с консолью
 */
class CustomConsole(private val invoker: Invoker) {
    companion object {
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

        /**
         * Парочка приветственных слов
         */
        fun greetings() {
            val greeting = when(kotlin.random.Random.nextInt(3)) {
                0 -> "Здравствуйте, пользователь."
                1 -> "ПРИВЕТСТВУЮ!"
                2 -> "У меня все отлично. А у Вас?"
                else -> "Вы точно хотите знать зачем я нужен?"
            }
            println(greeting)
            println("Введите команду или 'help' для помощи.")
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
                println(e.message)
                println("Введите 'help' для помощи")
            }
        }
    }
}