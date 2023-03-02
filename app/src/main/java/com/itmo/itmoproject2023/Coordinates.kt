package  com.itmo.itmoproject2023

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates (
    var x:Float = 0f, //Значение поля должно быть больше -948, Поле не может быть null
    var y:Double = 0.0 //Максимальное значение поля: 453
)