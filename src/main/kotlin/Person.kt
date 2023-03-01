import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

/**
 * Class describing some person
 * @throws InvalidUserInputException
 */
@Serializable
data class Person(private val name: String,
                  private val coordinates: Coordinates,
                  private val height: Long,
                  @Serializable(KZonedDateTimeSerializer::class)
                  private val birthday: ZonedDateTime,
                  private val weight: Int,
                  private val hairColor: Color,
                  private val location: Location) {

    companion object {
        fun personFromStrings(args: Array<String>): Person {
            val strCoordinates = cc.splitSpaces(args[1])
            if (strCoordinates.size < 2) throw InvalidUserInputException("Неправильные координаты: Нужно ввести два числа через пробел")
            val coordinates = Coordinates(strCoordinates[0].toFloat(), strCoordinates[1].toDouble())

            val height = args[2].trim().toLong()

            val birthday = ZonedDateTime.parse(args[3].trim()) ?:
            throw InvalidUserInputException("Неправильный формат дня рождения, пример: \"2011-12-03T10:15:30+01:00[Europe/Paris]\"")

            val weight = args[4].trim().toInt()

            val hairColor = Color.valueOf(args[5].trim().uppercase())

            val strLocation = cc.splitSpaces(args[6])
            if (strLocation.size < 3) throw InvalidUserInputException("Неправильное местонахождение: Нужно ввести три числа через пробел")
            val locationName = if (strLocation.size > 3) strLocation[3] else null
            val location = Location(strLocation[0].toFloat(), strLocation[1].toFloat(), strLocation[2].toInt(), locationName)

            return Person(args[0], coordinates, height, birthday, weight, hairColor, location)
        }
    }
    init {
        if (height < 1) throw InvalidUserInputException("Высота человека должна быть больше 0")
        if (weight < 1) throw InvalidUserInputException("Вес человека должен быть больше 0")

        /**
         *      Поле не может быть null
         *      Значение поля должно быть больше 0
         *      Значение этого поля должно быть уникальным
         *      Значение этого поля должно генерироваться автоматически
         */
        val id: Int = kotlin.random.Random.nextInt()

    }

    @Serializable(KZonedDateTimeSerializer::class)
    private val creationDate = ZonedDateTime.now()
}