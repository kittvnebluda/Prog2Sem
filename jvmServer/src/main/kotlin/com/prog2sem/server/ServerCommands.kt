package com.prog2sem.server

import com.prog2sem.shared.net.DataBaseCommands

/**
 * Интерфейс команд пользователя
 */
interface ServerCommands{
    fun save(filePath: String): Boolean

}