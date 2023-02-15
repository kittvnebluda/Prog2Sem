import java.time.ZonedDateTime

/**
 * Class describing some person
 */
class Person {
    /**
     *      Поле не может быть null
     *      Значение поля должно быть больше 0
     *      Значение этого поля должно быть уникальным
     *      Значение этого поля должно генерироваться автоматически
     */
    private val id: Int? = null

    /**
     *      Поле не может быть null
     *      Строка не может быть пустой
     */
    private val name: String? = null

    /**
     *      Поле не может быть null
     */
    private val coordinates: Coordinates? = null

    /**
     *      Поле не может быть null
     *      Значение этого поля должно генерироваться автоматически
     */
    private val creationDate: ZonedDateTime? = null

    /**
     *      Значение поля должно быть больше 0
     */
    private val height: Long = 0

    /**
     *      Поле не может быть null
     */
    private val birthday: ZonedDateTime? = null

    /**
     *      Значение поля должно быть больше 0
     */
    private val weight = 0

    /**
     *      Поле может быть null
     */
    private val hairColor: Color? = null

    /**
     *      Поле может быть null
     */
    private val location: Location? = null
}