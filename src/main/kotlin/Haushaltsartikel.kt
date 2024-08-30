open class Haushaltsartikel(name: String,price: Double, customerReview: String,inventory: Int, var brand: String):Produkt(name,price,customerReview,inventory) {
    override fun toString(): String {
        return """
            ${super.toString()}
            Marke: $brand
            """
    }
}