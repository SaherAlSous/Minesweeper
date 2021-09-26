fun main() {    
    val (a, b, h) = Array(3) { readLine()!!.toInt() }
    println(if (h in a..b) "Normal" else if (h < a) "Deficiency" else "Excess")
}