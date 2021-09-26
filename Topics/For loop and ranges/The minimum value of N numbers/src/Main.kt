fun main() {
    val n = readLine()!!.toInt()
    var nums = IntArray(n) { readLine()!!.toInt() }
    val min = nums.minOrNull()
    println(min)
}
