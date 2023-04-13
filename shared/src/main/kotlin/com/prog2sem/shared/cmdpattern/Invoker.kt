package com.prog2sem.shared.cmdpattern

/**
 * Интерфейс для классов, хранящих и вызывающих команды
 */
interface Invoker {
    val cmdMap: HashMap<String, Command>

    /**
     * Запомнить команду
     */
    fun put(command: Command)

    /**
     * Запомнить команду
     */
    fun putAll(vararg commands: Command)

    /**
     * Выполнить команду с ее аргументами
     */
    fun proceed(cmd: String)
}