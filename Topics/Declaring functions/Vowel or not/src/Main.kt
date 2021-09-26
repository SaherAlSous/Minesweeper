// write your function here

fun main() {
    val letter = readLine()!!.first()

    println(isVowel(letter))
}

fun isVowel(letter:Char) : Boolean{
  val char = letter.lowercaseChar()
    return char == 'a' || char == 'e' || char == 'i' || char == 'o'  || char == 'u'
}