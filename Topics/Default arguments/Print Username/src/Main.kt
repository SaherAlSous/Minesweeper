fun main() {
    val name = readLine()!!
    println(
        if (name == "HIDDEN") "Hello, secret user!" else "Hello, $name!"
    )
}