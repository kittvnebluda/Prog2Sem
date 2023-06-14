package com.prog2sem.client.localization

import java.util.*

class GuiLabels_ru_RU : ListResourceBundle() {
    private val contents = arrayOf(
        arrayOf<Any>("add", "ДОБАВИТЬ НОВЫЙ ЭЛЕМЕНТ"),
        arrayOf<Any>("clear", "ОТЧИСТИТЬ ТАБЛИЦУ")
    )
    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}