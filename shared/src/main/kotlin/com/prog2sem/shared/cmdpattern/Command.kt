package com.prog2sem.shared.cmdpattern

import com.prog2sem.shared.exceptions.InvalidUserInputException

/** Интерфейс всех команд */
interface Command {
    val name: String
    val desc: String
    val methodsDesc: Map<String, String>

    /**
     * Метод, обеспечивающий выполнение команды
     * @throws InvalidUserInputException
     */
    fun execute(args: List<String>)
}