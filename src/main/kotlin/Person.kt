import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

/**
 * Class describing some person
 * @throws InvalidUserInputException
 */
@Serializable
data class Person(val name: String,
                  val coordinates: Coordinates,
                  val height: Long,
                  @Serializable(KZonedDateTimeSerializer::class)
                  val birthday: ZonedDateTime,
                  val weight: Int,
                  val hairColor: Color,
                  val location: Location) {
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