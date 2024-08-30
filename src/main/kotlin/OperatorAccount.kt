class OperatorAccount(name: String, pw: String, age:Int):Account(name,pw,age) {
    override fun toString(): String {
        return """
            ${super.toString()}
        """
    }
    fun reorderProduct(store: Store) {
        var continueReordering = true
        while (continueReordering) {
            println()
            println("${red}--------${cyan+bold}Du möchtest also Produkte nachbestellen?${reset+red}---------${reset}")
            store.products.forEachIndexed { index, produkt ->
                println("[${cyan}${index + 1}${reset}]: ${yellow}${produkt.name}, ${green}Auf Lager: ${produkt.inventory}${reset}")
            }
            var productIndex: Int
            var quantity: Int

            try {
                println("${blue}Bitte gib eine gültige Zahl für den Index ein.${reset}")
                productIndex = readln().toInt()
                println("${blue}Wie viele Einheiten möchtest du nachbestellen?${reset}")
                quantity = readln().toInt()
                if (productIndex in 1..store.products.size) {
                    val productToReorder = store.products[productIndex - 1]
                    productToReorder.inventory += quantity
                    println("${yellow}Es wurden ${green}$quantity${yellow} Einheiten von ${green}${productToReorder.name}${yellow} nachbestellt. Neuer Lagerbestand: ${green}${productToReorder.inventory}${reset}")
                } else {
                    println("${red}Produktindex ist ungültig. Bitte einen gültigen Index eingeben.${reset}")
                }
            } catch (e: IllegalArgumentException) {
                println("${red}Ungültige Eingabe. Bitte versuche es erneut.${reset}")
            }

            println("${blue}Möchtest du weitere Produkte nachbestellen? (ja/nein)${reset}")
            val answer = readln().lowercase()
            when (answer) {
                "nein" -> continueReordering = false
                "ja" -> continueReordering = true
                else -> {
                    println("${red}Bitte antworte mit 'ja' oder 'nein'.${reset}")
                    println("${yellow}Zurück zum Operator Menü.${reset}")
                    break
                }
            }
        }
    }

    fun addNewProductToProducts(store: Store) {
        println("${red}----------${cyan+bold}Produkt Hinzufügen${reset+red}----------${reset}")
        println("${blue}Du willst also ein Produkt hinzufügen, wie heißt es?")
        val nameInput = readln()
        println("Wie viel kostet das Produkt?")
        var priceInput: Double? = null
        while (priceInput == null) {
            try {
                priceInput = readln().toDouble()
            } catch (e: NumberFormatException) {
                println("${red}Bitte gib einen gültigen Preis ein:${reset}")
            }
        }
        println("${blue}Gib eine Beispiel Rezension für dein Produkt an.")
        val reviewInput = readln()
        println("Gib den Lagerbestand als ganze Zahl an. (zb. 10)${reset}")
        var inventoryInput: Int? = null
        while (inventoryInput == null) {
            try {
                inventoryInput = readln().toInt()
            } catch (e: NumberFormatException) {
                println("${red}Bitte gib eine gültige Lagerbestandszahl ein:")
            }
        }
        println("${cyan}Welcher Kategorie gehört dein Produkt an?${reset}")
        println("[${cyan}1${reset}] Lebensmittel - Food")
        println("[${cyan}2${reset}] Lebensmittel - Drink")
        println("[${cyan}3${reset}] Haushaltsartikel - Kuechengeraet")
        println("[${cyan}4${reset}] Haushaltsartikel - Reinigungsmittel")
        val kategorieInput = readln()
        when (kategorieInput) {
            "1" -> {
                println("${blue}Gib das MHD deines Lebensmittels an.")
                val mhdInput = readln()
                println("Enthält das Lebensmittel Zucker? (ja/nein)${reset}")
                val withSugarInput = readln().equals("ja", ignoreCase = true)
                store.products.add(Food(nameInput, priceInput, reviewInput, inventoryInput, mhdInput, withSugarInput))
            }

            "2" -> {
                println("${blue}Gib das MHD deines Getränks an.")
                val mhdInput = readln()
                println("Enthält das Getränk Alkohol? (ja/nein)${reset}")
                val withAlcoholInput = readln().equals("ja", ignoreCase = true)
                store.products.add(Drink(nameInput, priceInput, reviewInput, inventoryInput, mhdInput, withAlcoholInput))
            }

            "3" -> {
                println("${blue}Gib die Marke des Kuechengeraets an.")
                val brandInput = readln()
                println("Gib die Garantieinformation des Kuechengeraets an.${reset}")
                val guaranteeInfoInput = readln()
                store.products.add(Kuechengeraet(nameInput, priceInput, reviewInput, inventoryInput, brandInput, guaranteeInfoInput)
                )
            }

            "4" -> {
                println("${blue}Gib die Marke des Reinigungsmittels an.")
                val brandInput = readln()
                println("Ist das Reinigungsmittel biologisch abbaubar? (ja/nein)${reset}")
                val isBioInput = readln().equals("ja", ignoreCase = true)
                store.products.add(Reinigungsmittel(nameInput, priceInput, reviewInput, inventoryInput, brandInput, isBioInput))
            }

            else -> {
                println("${red}Keine gültige Kategorie ausgewählt. Bitte versuche es erneut.${reset}")
                addNewProductToProducts(store)
            }
        }
        println("${yellow}Du hast ${green}$nameInput${yellow} deinem Store als Produkt hinzugefügt.${reset}")
    }

    fun deleteProductFromProducts(store: Store) {
        store.showProductsWithIndex()
        println("${cyan}Wähle ein Produkt anhand des Indexes.(Tippe eine Zahl)${reset}")
        try {
            val userInput = readln().toInt()
            if (userInput in 1..store.products.size) {
                val userChoice = store.products[userInput - 1]
                store.products.remove(userChoice)
                println("${green}Produkt erfolgreich entfernt.${reset}")
            } else {
                println("${red}Die Eingabe liegt außerhalb des gültigen Bereichs. Bitte wähle einen Index innerhalb der Liste.${reset}")
            }
        } catch (e: NumberFormatException) {
            println("${red}Eingabe muss eine Zahl sein. Bitte erneut versuchen.${reset}")
        } catch (e: IndexOutOfBoundsException) {
            println("${red}Produktindex nicht gefunden. Bitte einen gültigen Index eingeben.${reset}")
        }
    }

}
