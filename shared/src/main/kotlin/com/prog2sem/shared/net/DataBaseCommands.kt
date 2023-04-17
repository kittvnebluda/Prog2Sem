package com.prog2sem.shared.net

import com.prog2sem.shared.Color
import com.prog2sem.shared.Location
import com.prog2sem.shared.persona.Person

/**
 * Интерфейс команд базы данных
 */
interface DataBaseCommands {
    /**
     * Возвращает информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     * @return информацию о коллекции в виде текста
     */
    fun info(): String

    /**
     * Возвращает все элементы коллекции
     * @return все элементы коллекции в виде текста
     */
    fun show(): Array<Person>

    /**
     * Обеспечивает добавление нового элемента в коллекцию
     * @param p
     * @return true, если коллекция изменилась
     */
    fun add(p: Person): Boolean

    /**
     * Обеспечивает обновление значения элемента коллекции, id которого равен заданному
     * @param index индекс элемента коллекции
     * @param p экземпляр класса коллекции
     * @return true, если коллекция изменилась
     */
    fun update(index: Int, p: Person): Boolean

    /**
     * Обеспечивает удаление элемента из коллекции по его id
     * @param index индекс элемента коллекции
     * @return true, если коллекция изменилась
     */
    fun removeId(index: Int): Boolean

    /**
     * Обеспечивает очистку коллекции
     * @return true, если коллекция изменилась
     */
    fun clear(): Boolean

    /**
     * Обеспечивает добавление нового элемента в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции
     * @param p экземпляр класса коллекции
     * @return true, если коллекция изменилась
     */
    fun addIfMin(p: Person): Boolean

    /**
     * Обеспечивает удаление из коллекции всех элементов, превышающие заданный
     * @param p экземпляр класса коллекции
     * @return true, если коллекция изменилась
     */
    fun removeGreater(p: Person): Boolean

    /**
     * Обеспечивает удаление из коллекции всех элементов, значение поля location которого эквивалентно заданному.
     * @param location
     * @return true, если коллекция изменилась
     */
    fun removeAllByLocation(location: Location): Boolean

    /**
     * Возвращает элементы, значение поля hairColor которых больше заданного.
     * @param color цвет волос, может быть null
     * @return элементы коллекции
     */
    fun filterGreaterThanHairColor(color: Color?): Array<Person>

    /**
     * Возвращает значения поля hairColor всех элементов в порядке возрастания
     * @return значения hairColor
     */
    fun printFieldAscendingHairColor(): Array<Color>
}