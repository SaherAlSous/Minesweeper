class IceCreamOrder {
    
    val price: Int
    
    constructor(popsicles: Int) {
        price = popsicles * 7
    }
    
    constructor(scoops: Int, toppings: Int) {
        price = 5 * scoops + 2 * toppings
    }
}
