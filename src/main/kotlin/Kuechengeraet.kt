class Kuechengeraet(name: String, price: Double, customerReview: String, inventory: Int, brand:String, var infoGuarantee: String):Haushaltsartikel(name,price,customerReview,inventory,brand) {

    fun infoGuarantee(info: String):String{
        return info
    }
}