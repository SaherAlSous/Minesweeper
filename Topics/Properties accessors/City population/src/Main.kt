class City(val name: String) {
    var population: Int = 50_000_000
    set(value) {
       field = if (value < 0) 0 else if (value > 50_000_000) field else value
    }
}
