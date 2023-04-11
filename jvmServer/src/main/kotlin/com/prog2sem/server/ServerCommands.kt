package com.prog2sem.server

import com.prog2sem.shared.net.DataBaseCommands

/**
 * Интерфейс команд пользователя
 */
interface ServerCommands: DataBaseCommands{
    fun save(filePath: String): String

}