fun main() {
    val (a, b, c) = Array(3) { readLine()!! }
    println(a != b && a != c && b != c)
}
