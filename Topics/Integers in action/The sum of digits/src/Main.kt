fun main() {
    val nums = readLine()!!
    var sum = 0
    for (i in 0..2) {
        sum += nums[i].toString().toInt()
    }
    print(sum)
}