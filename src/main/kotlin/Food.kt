class Food(name: String,price: Double,customerReview: String, inventory: Int, mhd:String, var withSugar: Boolean):Lebensmittel(name,price,customerReview,inventory,mhd) {
    fun isWithSugar(info:Boolean):Boolean{
        return info
    }
}
