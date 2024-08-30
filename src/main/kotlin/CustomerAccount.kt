class CustomerAccount(name: String, pw: String, age:Int, var paymentMethod:String):Account(name,pw,age) {

    var warenkorb = mutableListOf<Produkt>()
    override fun toString(): String {
        return """
            ${super.toString()}
        """
    }
    fun showCart(store: Store) {
        if (warenkorb.isEmpty()) {
            println("${red}Dein Warenkorb ist leer.${reset}")
        } else {
            println()
            println("${red}----------${cyan+bold}Dein Warenkorb${reset+red}----------${reset}")
            warenkorb.forEach { produkt ->
                println("${yellow}${produkt.name} - ${green}${produkt.price}€${reset}")
            }
            println("${cyan}Dein aktueller Gesamtpreis ist: ${green}${shoppingCartCalculator()}€${reset}")
        }
    }


    fun shoppingCartCalculator(): Double {
        return warenkorb.sumOf { it.price }
    }


    fun addProductToCart(store: Store) {
        store.showProductsWithIndex()
        println("${cyan}Wählen Sie ein Produkt aus, welches sie dem Warenkorb hinzufügen möchten.${reset}")
        println("${yellow}Bitte teilen Sie uns die entsprechende Nummer mit.${reset}")

        var productNumberInput: Int? = null
        while (productNumberInput == null) {
            try {
                val input = readln()
                val num = input.toInt()
                if (num > 0) {
                    productNumberInput = num
                } else {
                    println("${red}Die Nummer muss positiv sein. Bitte geben Sie eine gültige Nummer ein.${reset}")
                }
            } catch (e: NumberFormatException) {
                println("${red}Ungültige Eingabe. Bitte geben Sie eine Zahl ein.${reset}")
            }
        }

        if (productNumberInput in 1..store.products.size) {
            val selectedProduct = store.products[productNumberInput - 1]
            addSelectedProductToCart(selectedProduct)
        } else {
            println("${red}Bitte wählen Sie eine Nummer zwischen 1 und ${store.products.size}.${reset}")
        }
        store.continueShoppingRequest()
    }

    fun addSelectedProductToCart(product: Produkt) {
        if (product is Drink && product.withAlcohol) {
            if (this.age >= 18) {
                warenkorb.add(product)
                product.inventory--
                println("${yellow}${product.name}${reset} wurde zum Warenkorb hinzugefügt.")
                if (product.inventory <= 0) {
                    println("${yellow}Achtung: Die Lieferzeit für ${product.name} kann sich um eine Woche verlängern, da das Produktlager leer ist.${reset}")
                }
            } else {
                println("${red}Du musst mindestens 18 Jahre alt sein, um alkoholische Getränke zu kaufen.${reset}")
            }
        } else {
            warenkorb.add(product)
            product.inventory--
            println("${green}${product.name}${reset} wurde zum Warenkorb hinzugefügt.")
            if (product.inventory <= 0) {
                println("${red}Achtung: ${reset}Die Lieferzeit für ${green}${product.name}${reset} kann sich um eine Woche verlängern, da das Produktlager leer ist.")
            }
            println()
        }
    }

    fun removeProductFromCart(store: Store) {
        println()
        println("${cyan}Du möchtest also ein Produkt aus deinem Warenkorb entfernen.${reset}")
        warenkorb.forEachIndexed { index, produkt ->
            println("[${cyan}${index + 1}${reset}] ${yellow}${produkt.name} - ${green}${produkt.price}€${reset}")
        }
        println("${yellow}Wähle die Nummer des zu entfernenden Produkts:${reset}")
        val userChoice = readln().toIntOrNull()
        if (userChoice != null && userChoice in 1..warenkorb.size) {
            val selectedProduct = warenkorb[userChoice - 1]
            val productInStore = store.products.find { it.name == selectedProduct.name }
            productInStore?.let {
                it.inventory++
                println("${yellow}${selectedProduct.name}${reset} wurde aus dem Warenkorb entfernt und dem Inventar hinzugefügt.")
            }
            warenkorb.removeAt(userChoice - 1)
        } else {
            println("${red}Ungültige Eingabe. Bitte versuche es erneut.${reset}")
            removeProductFromCart(store)
        }
    }
}