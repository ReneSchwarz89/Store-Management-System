open class Lebensmittel(name: String, price: Double, customerReview: String,inventory: Int, val mhd: String):Produkt(name,price,customerReview,inventory) {
    override fun toString(): String {
        return """
            ${super.toString()}
            MHD: $mhd
            """
    }
}