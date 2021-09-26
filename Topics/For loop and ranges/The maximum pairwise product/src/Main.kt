fun main() {
    val list = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    if (list.size == 1) println(list[0]) else checkList(list)
}

private fun checkList(list: List<Int>) {
   val highestPairs = list.sorted()
    println(highestPairs.last() * highestPairs[highestPairs.size - 2])
}
