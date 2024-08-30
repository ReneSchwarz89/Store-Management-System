val red = "\u001B[31m"
val reset = "\u001B[0m"
val green = "\u001B[32m"
val yellow = "\u001B[33m"
val blue = "\u001B[34m"
val magenta = "\u001B[35m"
val cyan = "\u001B[36m"
val bold = "\u001B[1m"
val kursiv = "\u001B[3m"
open class Store {
    var loggedInAccount: Account? = null
    var accounts: MutableList<Account> = mutableListOf(
        OperatorAccount("Rudi", "1234", 19),
        OperatorAccount("Tom", "1234", 22),
        CustomerAccount("Sarah", "1234", 16, "Paypal"),
        CustomerAccount("Lisa", "1234", 11, "Überweisung")
    )
    var products: MutableList<Produkt> = mutableListOf(
        Drink("Wasser", 0.99, "Erfrischend gut.", 17, "12.12.2025", false),
        Drink("Sixer-Bier", 6.99, "Schön mild.", 13, "12.12.2024", true),
        Food("Käse", 2.99, "Cremig Nussig", 12, "12.12.22", false),
        Food("Schokolade", 1.99, "Feinster cacao", 45, "12.12.22", false),
        Kuechengeraet("Staubsauger", 499.99, "Er saugt so stark!", 5, "Dyson", "5 Jahre"),
        Kuechengeraet("Toaster", 39.99, "DoubleLoader", 4, "Miele", "2 Jahre"),
        Reinigungsmittel("Bio-Glas-Klar", 2.99, "Putzt umweltfreundlich so klar wie nie!", 6, "Clean-Bio", true),
        Reinigungsmittel("Meister Popper", 4.99, "Beste Scheuermilch", 17, "Popper", false),
    )

    fun continueShoppingRequest() {
        println("${blue}Möchtest du weitere Produkte deinem Warenkorb hinzufügen? ${reset}Ja/Nein?")
        val wantBuyMoreOrNot = readln().lowercase()
        if (wantBuyMoreOrNot == "ja") {
            (loggedInAccount as CustomerAccount).addProductToCart(this)
        } else if (wantBuyMoreOrNot == "nein") {
            showView()
        } else {
            println("${red}\"$wantBuyMoreOrNot\" ist keine passende Eingabe. ${yellow}Probier es nochmal...${reset}")
            continueShoppingRequest()
        }
    }

    private fun filteredBy() {
        println()
        println("${red}------------------${cyan} Filter! ${red}------------------${reset}")
        println("${cyan}Wähle einen passen Filter.${reset}")
        println("[${cyan}1${reset}] Sortiere Produkte nach Namen absteigend")
        println("[${cyan}2${reset}] Sortiere Produkte nach Preis absteigend")
        println("[${cyan}3${reset}] Zeige nur Lebensmittel")
        println("[${cyan}4${reset}] Zeige nur Haushaltsgeräte")
        println("[${cyan}5${reset}] Zurück")
        println("${yellow}Tippe deine auswahl als ganze Zahl.${reset}")
        val choice = readln().toIntOrNull()
        when (choice) {
            1 -> {
                val sortedByName = products.sortedByDescending { it.name }
                println("${red}-------${cyan}${bold} Produkte sortiert nach Namen absteigend ${reset}${red}-------${reset}")
                sortedByName.forEach { produkt -> println("${yellow}${produkt.name} - ${green}${produkt.price}€${reset} ${yellow}Auf Lager: ${green}${produkt.inventory}${reset}") }
                filteredBy()
            }

            2 -> {
                val sortedByPrice = products.sortedByDescending { it.price }
                println("${red}-------${cyan}${bold} Produkte sortiert nach Preis absteigend ${reset}${red}-------${reset}")
                sortedByPrice.forEach { produkt -> println("${yellow}${produkt.name} - ${green}${produkt.price}€${reset} ${yellow}Auf Lager: ${green}${produkt.inventory}${reset}") }
                filteredBy()
            }

            3 -> {
                val filteredLebensmittel = products.filter { it is Lebensmittel }
                println("${red}-------${cyan}${bold} Nur Lebensmittel ${reset}${red}-------${reset}")
                filteredLebensmittel.forEach { produkt -> println("${yellow}${produkt.name} - ${green}${produkt.price}€${reset} ${yellow}Auf Lager: ${green}${produkt.inventory}${reset}") }
                filteredBy()
            }

            4 -> {
                val filteredHaushaltsgeraete = products.filter { it is Haushaltsartikel }
                println("${red}-------${cyan}${bold} Nur Haushaltsgeräte ${reset}${red}-------${reset}")
                filteredHaushaltsgeraete.forEach { produkt -> println("${yellow}${produkt.name} - ${green}${produkt.price}€${reset} ${yellow}Auf Lager: ${green}${produkt.inventory}${reset}") }
                filteredBy()
            }

            5 -> showView()
            else -> {
                println("${red}Ungültige Auswahl. Bitte wähle eine Option zwischen 1 und 4.${reset}")
                filteredBy()
            }

        }
    }

    fun showView() {
        if (loggedInAccount is CustomerAccount) {
            println()
            println("${red}---------------${bold}${cyan}Kundenansicht${reset}${red}---------------${reset}")
            println("${cyan}Wähle eine Aktion aus:${reset}")
            println("[${cyan}1${reset}] Ansichts-Filteroptionsauswahl")
            println("[${cyan}2${reset}] Produkt dem Warenkorb hinzufügen")
            println("[${cyan}3${reset}] Produkt dem Warenkorb entfernen")
            println("[${cyan}4${reset}] Warenkorb anzeigen")
            println("[${cyan}5${reset}] Ausloggen")
            val choice = readln().toIntOrNull()
            when (choice) {
                1 -> {
                    (loggedInAccount as CustomerAccount)
                    filteredBy()
                    showView()
                }

                2 -> {
                    (loggedInAccount as CustomerAccount).addProductToCart(this)
                }

                3 -> {
                    (loggedInAccount as CustomerAccount).removeProductFromCart(this)
                    showView()
                }

                4 -> {
                    (loggedInAccount as CustomerAccount).showCart(this)
                    showView()
                }

                5 -> logout()
                else -> {
                    println("${red}Keine gültige Zahl eingegeben! Versuch's nochmal...${reset}")
                    showView()
                }
            }
        } else if (loggedInAccount is OperatorAccount) {
            println()
            println("${red}--------------${cyan+bold}Betreiberansicht${reset+red}--------------${reset}")
            println("${cyan}Wähle eine Aktion aus:${reset}")
            println("[${cyan}1${reset}] Produkte Anzeigen")
            println("[${cyan}2${reset}] Neues Produkt hinzufügen")
            println("[${cyan}3${reset}] Produkt löschen")
            println("[${cyan}4${reset}] Produkt Nachbestellen")
            println("[${cyan}5${reset}] Ausloggen")
            val choice = readln().toIntOrNull()
            when (choice) {
                1 -> {
                    println()
                    showProducts()
                    showView()
                }

                2 -> {
                    (loggedInAccount as OperatorAccount).addNewProductToProducts(this)
                    showView()
                }

                3 -> {
                    (loggedInAccount as OperatorAccount).deleteProductFromProducts(this)
                    showView()
                }

                4 -> {
                    (loggedInAccount as OperatorAccount).reorderProduct(this)
                    showView()
                }

                5 -> {
                    logout()
                    showView()
                }

                else -> {
                    println("Keine gültige Zahl eingegeben! Versuch's nochmal...")
                    showView()
                }
            }
        }
    }

    fun showProductsWithIndex() {
        println()
        println("${red}--------${bold+cyan}Verfügbare Produkte${reset+red}----------${reset}")
        products.forEachIndexed() { index, product ->
            println("[${cyan}${index + 1}${reset}]: ${yellow}${product.name} - ${green}${product.price}€  ${yellow}Auf Lager: ${green}${product.inventory}${reset}")
        }
    }

    fun showProducts() {
        println("${red}-----${bold}${cyan} Unser Sortiment ${reset}${red}-----${reset}")
        products.forEach { product -> println("${yellow}${product.name} - ${green}${product.price}€${yellow} Auf Lager: ${green}${product.inventory}${reset}") }
    }
}