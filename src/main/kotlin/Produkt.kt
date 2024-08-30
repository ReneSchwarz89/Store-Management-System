open class Produkt(val name: String, var price: Double, var customerReview: String, var inventory: Int ) {

    var originalPrice: Double = price
    var hasDiscount: Boolean = false

    override fun toString():String{
        return """
            Produktname: $name
                        Produktpreis: $price
                        Rezension: $customerReview
                        Inventory: $inventory
        """.trimIndent()
    }
}