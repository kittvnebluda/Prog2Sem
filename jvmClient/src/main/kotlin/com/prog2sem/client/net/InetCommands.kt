package com.prog2sem.client.net

/**
 * Интерфейс работающих сервера команд пользователя для получения информации о сервере
 */
interface InetCommands {
    /**
     * Обеспечивает вывод информации об адресе сервера
     */
    fun showServerAddr()
    /**
     * Обеспечивает изменение адреса сервера
     */
    fun setServerAddr(address: String, port: Int)
}