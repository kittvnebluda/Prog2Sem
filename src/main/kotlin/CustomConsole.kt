class CustomConsole {
    companion object {
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
    }
}