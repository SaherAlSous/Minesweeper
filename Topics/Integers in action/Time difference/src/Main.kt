fun main() {
    val (a, b, c) = Array(3) { readLine()!!.toInt() }
    val (d, e, f) = Array(3) { readLine()!!.toInt() }
    println(d * 60 * 60 + e * 60 + f - (a * 60 * 60 + b * 60 + c))
}
