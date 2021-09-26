fun main() {
    // write your code here
    val cups = readLine()!!.toInt()
    val weekEnd = readLine()!!.toBoolean()
    println(
        if (cups in 15..25 && weekEnd) {
            "true"
        } else if (cups in 10..20 && !weekEnd) {
            "true"
        } else {
            "false"
        }
    )
}