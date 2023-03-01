import kotlinx.serialization.Serializable

@Serializable
data class Location (private val x: Float = 0f,
                     private val y: Float = 0f,
                     private val z: Int = 0,
                     private val name: String? = null)