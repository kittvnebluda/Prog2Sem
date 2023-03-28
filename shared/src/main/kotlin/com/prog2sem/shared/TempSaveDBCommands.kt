package com.prog2sem.shared

import com.prog2sem.shared.net.wrappers.SimpleResponse

interface TempSaveDBCommands {
    /**
     * Возвращает есть ли временное сохранение
     * @return true, если сохранение существует
     */
    fun isTempSaveExist(): SimpleResponse

    /**
     * Обеспечивает загрузку временного сохранения
     * @return true, если сохранение было загружено
     */
    fun loadTempSave(): SimpleResponse
}