fun main() {
    val (a, b) = Array(2) { readLine()!!.toInt() }
    for (i in a..b) {
        println(
            if (i % 3 == 0 && i % 5 == 0) "FizzBuzz" else if (i % 3 == 0) "Fizz" else if (i % 5 == 0) "Buzz" else i
        )
    }
}
