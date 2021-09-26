import kotlin.random.Random

class Calendar(var day: String, var month: String, var year: String) {

    init {
        selectCurrentDay()
    }
    fun selectCurrentDay() {
        day = "d123d"
        month = "m45m"
        year = "y6y"
    }
}
fun createCalendar() =
    Calendar("-", "-", "-")
fun main() {
val calendar = createCalendar()
    println("${calendar.day} ${calendar.month} ${calendar.year}")
}
