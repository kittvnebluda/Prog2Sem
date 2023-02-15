interface CollectionManager<T> {
    fun help(): String
    fun info(): String
    fun show(): String
    fun add(e: T): Boolean
    fun updateId(index: Int)
    fun removeId(index: Int)
    fun clear()
    fun save()
    fun executeScript(filename: String)
    fun exit()
    fun removeFirst()
    fun addIfMax(e: T)
    fun history(): String
    fun sumOfHeight(): Long
    fun countByHairColor(color: Color?): Int
    fun countLessThanHairColor(color: Color?): Int
}