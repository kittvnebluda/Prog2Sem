import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(val x: Float, val y: Double) {
    init {
        if (x <= -948) throw InvalidUserInputException("x координата должна быть больше -948")
        if (y > 453) throw InvalidUserInputException("y координата должна быть меньше либо равна 453")
    }
}