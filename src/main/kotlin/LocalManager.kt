import java.util.PriorityQueue

/**
 * Class for managing PriorityQueue
 *
 * @param T the type of elements of PriorityQueue
 * @param DB PriorityQueue of T type elements.
 */
class LocalManager<T> (private val DB: PriorityQueue<T>): CollectionManager<T>{
    override fun help(): String {
        return  "    help : вывести справку по доступным командам\n" +
                "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "    add {element} : добавить новый элемент в коллекцию\n" +
                "    update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "    remove_by_id id : удалить элемент из коллекции по его id\n" +
                "    clear : очистить коллекцию\n" +
                "    save : сохранить коллекцию в файл\n" +
                "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "    exit : завершить программу (без сохранения в файл)\n" +
                "    remove_first : удалить первый элемент из коллекции\n" +
                "    add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "    history : вывести последние 9 команд (без их аргументов)\n" +
                "    sum_of_height : вывести сумму значений поля height для всех элементов коллекции\n" +
                "    count_by_hair_color hairColor : вывести количество элементов, значение поля hairColor которых равно заданному\n" +
                "    count_less_than_hair_color hairColor : вывести количество элементов, значение поля hairColor которых меньше заданного"
    }

    override fun info(): String {
        TODO("Not yet implemented")
    }

    override fun show(): String {
        var out = ""
        DB.forEach { out += "$it\n" }
        return out
    }

    override fun updateId(index: Int) {
        TODO("Not yet implemented")
    }

    override fun removeId(index: Int) {
        val e = DB.elementAtOrNull(index)
        if (e != null) DB.remove(e) else println("No such index in data base")
    }

    override fun add(e: T): Boolean = DB.add(e)

    override fun clear() = DB.clear()

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun executeScript(filename: String) {
        TODO("Not yet implemented")
    }

    override fun exit() {
        TODO("Not yet implemented")
    }

    override fun removeFirst() {
        TODO("Not yet implemented")
    }

    override fun history(): String {
        TODO("Not yet implemented")
    }

    override fun sumOfHeight(): Long {
        TODO("Not yet implemented")
    }

    override fun countByHairColor(color: Color?): Int {
        TODO("Not yet implemented")
    }

    override fun countLessThanHairColor(color: Color?): Int {
        TODO("Not yet implemented")
    }

    override fun addIfMax(e: T) {
        TODO("Not yet implemented")
    }
}