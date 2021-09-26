import kotlin.math.abs

fun main() {
    val (a, b) = readLine()!!.split(" ").map { it.toInt() }
    val (c, d) = readLine()!!.split(" ").map { it.toInt() }
    println(
        if (a == c || b == d || abs(a - c) == abs(b - d)) "YES" else "NO"
    )
}
