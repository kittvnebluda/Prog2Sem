package com.prog2sem.shared.net

interface TempSaveCommands {
    /**
     * Возвращает есть ли временное сохранение
     * @return true, если сохранение существует
     */
    fun isTempSaveExist(): Boolean

    /**
     * Обеспечивает загрузку временного сохранения
     * @return true, если сохранение было загружено
     */
    fun loadTempSave(): Boolean
}