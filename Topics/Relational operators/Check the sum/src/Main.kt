fun main() {
    val (a, b, c) = Array(3) { readLine()!!.toInt() }
    println(a + b == 20 || a + c == 20 || c + b == 20)
}
