class Player(val id: Int, val name: String, val hp: Int) {
    companion object {
        var palyerId = 0
        fun create(name: String): Player {
            palyerId++
            return Player(palyerId, name, 100)
        }
    }
}
