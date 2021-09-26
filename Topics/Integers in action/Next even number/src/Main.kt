fun main() {
    val n = readLine()!!.toInt()
    val r = n % 2
    println(if (r == 0) n + 2 else n + 1)
}
