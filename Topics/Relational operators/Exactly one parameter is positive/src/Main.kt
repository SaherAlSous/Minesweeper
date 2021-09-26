fun main() {
    val (a, b, c) = Array(3) { readLine()!!.toInt() }
    val first = a > 0 && b <= 0 && c <= 0
    val second = a <= 0 && b > 0 && c <= 0
    val third = a <= 0 && b <= 0 && c > 0
    print(first || second || third)
}
