import java.util.*

fun main(args: Array<String>) {
    val DB = LocalManager<Person>(PriorityQueue())
    println(DB.help())
}