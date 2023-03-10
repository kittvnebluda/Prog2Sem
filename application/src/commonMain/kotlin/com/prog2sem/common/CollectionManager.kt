package com.prog2sem.common

/**
 * Интерфейс команд пользователя
 */
interface CollectionManager {
    /**
     * Возвращает информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     * @return информацию о коллекции в виде текста
     */
    fun info(): String
    /**
     * Возвращает все элементы коллекции в строковом представлении
     * @return все элементы коллекции в виде текста
     */
    fun show(): String
    /**
     * Обеспечивает добавление нового элемента в коллекцию
     * @param e элемент добавляемый в коллекцию
     * @return true если коллекция изменилась
     */
    fun add(e: Person): String
    /**
     * Обновляет значение элемента коллекции, id которого равен заданному
     * @param index индекс элемента коллекции
     * @param e замещающий элемент
     */
    fun update(index: Int, e: Person): String
    /**
     * Обеспечивает удаление элемента из коллекции по его id
     * @param index индекс элемента коллекции
     */
    fun removeId(id: Int): String
    /**
     * Обеспечивает очистку коллекции
     */
    fun clear(): String
    /**
     * Обеспечивает сохранение коллекции в файл
     */
    fun save(filePath:String): String
    /**
     * Обеспечивает чтение и исполнение скрипта из указанного файла.
     * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме
     * @param filename имя файла
     */
    fun removeGreater(e: Person): String
    /**
     * Обеспечивает добавление нового элемента в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
     * @param e добавляемый класс [T]
     */
    fun addIfMin(e: Person): String
    /**
     * Возвращает сумму значений поля height для всех элементов коллекции
     * @return сумма значений height
     */
    fun removeAllByLocation(location: Location): String
    /**
     * Возвращает количество элементов, значение поля hairColor которых равно заданному
     * @param color цвет волос, может быть null
     * @return количество подходящих элементов
     */
    fun filterGreaterThanHairColor(color: Color?): String
    /**
     * Возвращает количество элементов, значение поля hairColor которых меньше заданного
     * @param color цвет волос, может быть null
     * @return количество подходящих элементов
     */
    fun printFieldAscendingHairColor(): String
}