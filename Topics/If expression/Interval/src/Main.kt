fun main() {
    val num = readLine()!!.toInt()
    val result = num in -14..13 || num in 15..16 || num >= 19
    println(result.toString().capitalize())
}