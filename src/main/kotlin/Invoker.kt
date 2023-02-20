interface Invoker {
    val cmdMap: HashMap<String, Command>
    fun put(command: Command)
    fun proceed(cmd: String)
}