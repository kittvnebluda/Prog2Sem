import com.itmo.itmoproject2023.Color
import com.itmo.itmoproject2023.Location
import com.itmo.itmoproject2023.Person

/**
 * Интерфейс команд пользователя
 */
interface DataBaseCommands<T> {
    /**
     * Возвращает информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     * @return информацию о коллекции в виде текста
     */
    fun info(): Callback<String>
    /**
     * Возвращает все элементы коллекции в строковом представлении
     * @return все элементы коллекции в виде текста
     */
    fun show(): Callback<String>
    /**
     * Обеспечивает добавление нового элемента в коллекцию
     * @param p
     * @return true если коллекция изменилась
     */
    fun add(p: Person): Callback<String>
    /**
     * Обеспечивает обновление значения элемента коллекции, id которого равен заданному
     * @param index индекс элемента коллекции
     * @param p экземпляр класса коллекции
     */
    fun update(index: Int, p: Person): Callback<String>
    /**
     * Обеспечивает удаление элемента из коллекции по его id
     * @param index индекс элемента коллекции
     */
    fun removeId(index: Int): Callback<String>
    /**
     * Обеспечивает очистку коллекции
     */
    fun clear(): Callback<String>
    /**
     * Обеспечивает сохранение коллекции в файл
     */
    fun save(): Callback<String>
    /**
     * Обеспечивает добавление нового элемента в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции
     * @param p экземпляр класса коллекции
     */
    fun addIfMin(p: Person): Callback<String>
    /**
     * Обеспечивает удаление из коллекции всех элементов, превышающие заданный
     * @param p экземпляр класса коллекции
     */
    fun removeGreater(p: Person): Callback<String>
    /**
     * Обеспечивает удаление из коллекции всех элементов, значение поля location которого эквивалентно заданному.
     * @param location
     * @return true если коллекция изменилась
     */
    fun removeAllByLocation(location: Location): Callback<String>
    /**
     * Возвращает элементы, значение поля hairColor которых больше заданного.
     * @param color цвет волос, может быть null
     * @return элементы коллекции
     */
    fun filterGreaterThanHairColor(color: Color?): Callback<String>
    /**
     * Возвращает значения поля hairColor всех элементов в порядке возрастания
     * @return значения hairColor
     */
    fun printFieldAscendingHairColor(): Callback<String>
}