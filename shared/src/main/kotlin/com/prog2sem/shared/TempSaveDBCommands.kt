package com.prog2sem.shared

@Deprecated("Use TempSaveCommands from net package instead")
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