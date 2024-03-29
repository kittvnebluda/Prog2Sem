package com.prog2sem.client.net

import com.prog2sem.shared.cmdpattern.Invoker

/**
 * Интерфейс работающих без сервера команд пользователя.
 */
interface LocalCommands {
    /**
     * Возвращает справку по доступным командам.
     * @return справка в виде текста
     */
    fun help()

    /**
     * Обеспечивает чтение и исполнение скрипта из указанного файла.
     * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
     * @param filename имя файла
     */
    fun executeScript(filename: String, invoker: Invoker)

    /**
     * Обеспечивает завершение программы без сохранения в файл.
     */
    fun exit()

    /**
     * Обеспечивает вывод последних 12 команд без их аргументов.
     */
    fun history()
}