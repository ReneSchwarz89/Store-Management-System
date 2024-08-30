class Reinigungsmittel(name: String, price: Double, customerReview: String, inventory: Int, brand:String, var isBio: Boolean):Haushaltsartikel(name,price,customerReview,inventory,brand) {
    fun isBio(isBio: Boolean):Boolean{
        return isBio
    }
}