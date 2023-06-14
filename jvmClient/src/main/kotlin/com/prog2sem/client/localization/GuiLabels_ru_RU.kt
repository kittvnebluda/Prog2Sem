@file:Suppress("unused")

package com.prog2sem.client.localization

import java.util.*

class GuiLabels_ru_RU : ListResourceBundle() {
    private val contents = arrayOf(
        arrayOf<Any>("add", "ДОБАВИТЬ НОВЫЙ ЭЛЕМЕНТ"),
        arrayOf<Any>("clear", "ОТЧИСТИТЬ ТАБЛИЦУ"),
        arrayOf<Any>("remove", "УДАЛИТЬ ЭЛЕМЕНТ"),
        arrayOf<Any>("update", "ОБНОВИТЬ ТАБЛИЦУ"),
        arrayOf<Any>("add if min", "ДОБАВИТЬ ЭЛЕМЕНТ, ЕСЛИ НАИМЕНЬШИЙ"),
        arrayOf<Any>("remove greater", "УДАЛИТЬ БОЛЬШИЕ ЭЛЕМЕНТЫ"),
        arrayOf<Any>("no db info", "Нет информации о базе данных"),
        arrayOf<Any>("empty history", "История пуста"),
        arrayOf<Any>("username", "Имя пользователя"),
        arrayOf<Any>("password", "Пароль"),
        arrayOf<Any>("login", "ВОЙТИ"),
        arrayOf<Any>("signup", "ЗАРЕГИСТРИРОВАТЬСЯ")
    )
    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}