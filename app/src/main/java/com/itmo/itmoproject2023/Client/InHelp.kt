/**
 * Аннотация, помечающая методы и параметры, отображаемые в помощи
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class InHelp(val desc: String)