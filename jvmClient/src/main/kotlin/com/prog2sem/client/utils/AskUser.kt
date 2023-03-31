package com.prog2sem.client.utils

object AskUser {
    /**
     * Спрашиваем пользователя не хочет ли он загрузить последнее сохранение
     * @return true, если пользователь хочет загрузить сохранение
     */
    fun isLoadTempSave(): Boolean {
        print("Загрузить несохраненную коллекцию? y/n\n> ")
        while (true) {
            var ans = readln()
            if (ans.isNotEmpty()) {
                ans = ans.trim().lowercase()
                when (ans) {
                    "y" -> return true
                    "n" -> return false
                    else -> println("Введите 'y' или 'n'\n> ")
                }
            } else print("Загрузить последнее сохранение коллекции? y/n\n> ")
        }
    }
}