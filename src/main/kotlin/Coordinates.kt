import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(private val x: Float, private val y: Double) {
    init {
        if (x <= -948) throw InvalidUserInputException("x координата должна быть больше -948")
        if (y > 453) throw InvalidUserInputException("y координата должна быть меньше 453")
    }
}