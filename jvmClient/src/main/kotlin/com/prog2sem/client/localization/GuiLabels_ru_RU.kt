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
        arrayOf<Any>("signup", "ЗАРЕГИСТРИРОВАТЬСЯ"),
        arrayOf<Any>("server address", "Адрес сервера"),
        arrayOf<Any>("server port", "Порт"),
        arrayOf<Any>("auth tab", "Аутентификация"),
        arrayOf<Any>("server settings tab", "Настройки сервера"),
        arrayOf<Any>("server_not_answering", "Сервер молчит."),
        arrayOf<Any>("incorrect_name_or_pass", "Неверное имя пользователя или пароль."),
        arrayOf<Any>("try_again", "Попробуйте снова."),
        arrayOf<Any>("user_exists", "Пользователь с таким именем уже существует."),
        arrayOf<Any>("sort", "СОРТИРОВАТЬ")
    )
    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}