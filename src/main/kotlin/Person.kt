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