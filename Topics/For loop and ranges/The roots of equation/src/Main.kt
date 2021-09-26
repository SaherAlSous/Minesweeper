fun main() {
    val (a, b, c, d) = Array(4) { readLine()!!.toInt() }
    for (i in 1..1000){
        val equation = a * i * i * i + b * i * i + c * i + d
        if (equation == 0) println(i.toString())
    }
}
