package  com.itmo.itmoproject2023

@kotlinx.serialization.Serializable
data class Location (
    var x:Float = 0f, //Поле не может быть null
    var y:Float = 0f, //Поле не может быть null
    var z:Int = 0, //Поле не может быть null
    var name:String? = null //Строка не может быть пустой, Поле не может быть null
)