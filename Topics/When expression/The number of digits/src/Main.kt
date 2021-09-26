fun main() {
    val n = readLine()!!.toInt()
    println(
        when {
            n / 1000 in 1..9 -> "4"
            n / 100 in 1..9 -> "3"
            n / 10 in 1..9 -> "2"
            n / 10 < 1 -> "1"
            else -> "you number is out of range"
        }
    )
}