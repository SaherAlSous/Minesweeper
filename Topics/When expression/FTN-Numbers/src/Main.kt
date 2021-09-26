fun main() {
    val f = listOf(0, 1, 2, 3, 5, 8, 13, 21, 34, 55)
    val t = listOf(0, 1, 3, 6, 10, 15, 21, 28, 36, 45)
    val p = listOf(1, 10, 100, 1000, 10000, 100000)
    val n = readLine()!!.toInt()
    println(
        when {
            f.contains(n) -> "F"
            t.contains(n) -> "T"
            p.contains(n) -> "P"
            else -> "N"
        }
    )
}
