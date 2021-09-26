fun main() {
    val (a, b) = Array(2) { readLine()!!.toInt() }
    var sum = 0
    for (i in a..b) {
        sum += i
    }
    println(sum)
}
