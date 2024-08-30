//Audio Import ChatGPT gefragt wie es geht. Mustercode übernommen: "Geschichte frei erfunden von chatGPT"

import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import java.io.File
import java.lang.Thread
fun playMusic(filePath: String) {
    try {
        val audioInputStream = AudioSystem.getAudioInputStream(File(filePath))
        val clip: Clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun showCredits() {
    val storyParts = listOf(
        "${kursiv+yellow}In einem Land, nicht weit von der Welt der Bits und Bytes,",
        "begab sich ein mutiger Abenteurer auf eine Reise durch das Tal der Kotlin-Grundlagen.",
        "Acht Wochen lang, sechs Tage die Woche, folgte er dem Ruf der Vorlesungen,",
        "die ihn durch die Geheimnisse der Variablen, Schleifen und Funktionen führten.",
        "Checkpoint 1 und 2 waren wie alte Wächter, die am Wegesrand standen,",
        "um sein Wissen zu prüfen. Sie stellten Rätsel und Herausforderungen,",
        "die er mit Scharfsinn und Beharrlichkeit meisterte.",
        "Mit jedem gelösten Problem wuchs sein Verständnis und seine Macht über den Code.",
        "Als die ersten sechs Wochen vergangen waren, stand er vor der ultimativen Prüfung:",
        "Acht Tage lang sollte er einen Prüfungscode schreiben,",
        "ein Meisterwerk, das all sein neu erworbenes Wissen zusammenbringen würde.",
        "Tag und Nacht tippte er, umgeben von Kaffeetassen und Notizen,",
        "während die Tastatur unter seinen Fingern tanzte.",
        "Am Ende seiner Odyssee blickte er auf einen Bildschirm voller fehlerfreier Befehle",
        "und eleganten Methoden. Er hatte nicht nur die Grundlagen der Programmierung gemeistert,",
        "sondern auch eine tiefere Verbindung zur digitalen Welt gefunden, die nun vor ihm lag.",
        "So endet die Legende des Kotlin-Kriegers, der auszog, um die Grundlagen zu lernen",
        "und als Padawan der Codekunst zurückkehrte. Es bleibt nun eine letzt Prüfung des verstandes${reset}",
        "Nun aber ab in den codeBLOCK! GOGOGO"
    )
    val musicThread = Thread {
        playMusic("/Users/reneschwarz/Documents/SyntaxGrundlagen/Musik/John Williams - Finale (From Star Wars_ The Rise of Skywalker_Audio Only).wav")
    }
    musicThread.start()

    // Zeige die Credits.
    storyParts.forEach { part ->
        println(part)
        Thread.sleep(2780 , storyParts.size)
    }
    println("Ende der Credits.")

    // Warte, bis die Musik fertig ist, falls nötig.
    musicThread.join()

}


fun Store.startShop() {
    println("${red}-----${cyan} Willkommen im Store! ${red}-----${reset}")
    showProducts()
    println()
    println("${cyan}Wähle eine Aktion aus:${reset}")
    println("[${cyan}1${reset}] Account erstellen & login")
    println("[${cyan}2${reset}] login")
    println("[${cyan}3${reset}] turn off")
    val choice = readln()
    when (choice) {
        "1" -> {
            createAccount(accounts)
            loggedInAccount = login()
        }

        "2" -> loggedInAccount = login()
        "3" -> turnOff()
        else -> {
            println("${red}Keine Zahl zwischen 1-2 eingegeben! ${green}Versuch's nochmal...${reset}")
            startShop()
        }

    }
    showView()
}

private fun turnOff() {
    println("${magenta}Das Programm wird jetzt beendet. Auf Wiedersehen!${reset}")
    showCredits()
    System.exit(0)
}

private fun Store.createAccount(accounts: MutableList<Account>) {
    val operatorPw = "oapw1234"
    println()
    println("${blue}Gib deinen Namen ein:")
    val inputName = readln()
    println("Erstelle ein Passwort:")
    val inputPw = readln()
    println("Gib dein Alter ein:")
    var inputAge: Int? = null
    while (inputAge == null) {
        try {
            inputAge = readln().toInt()
        } catch (e: NumberFormatException) {
            println("Das ist keine gültige Zahl. Bitte gib dein Alter als Zahl ein:")
        }
    }
    println("${cyan}Welche Art von Account möchtest du erstellen?${reset}")
    println("[${cyan}1${reset}] CustomerAccount")
    println("[${cyan}2${reset}] OperatorAccount (Nur mit Passwort möglich!)")
    val accountType = readln()
    when (accountType) {
        "1" -> {
            println("${blue}Gib deine bevorzugte Zahlungsmethode ein (z.B. Paypal, Überweisung):")
            val paymentMethod = readln()
            accounts.add(CustomerAccount(inputName, inputPw, inputAge, paymentMethod))
            println("${yellow}CustomerAccount erfolgreich erstellt!")
        }

        "2" -> {
            var attempts = 0
            while (attempts < 3) {
                println("${blue}Gib bitte das Operator-Passwort ein:")
                val userInput = readln()
                if (userInput == operatorPw) {
                    accounts.add(OperatorAccount(inputName, inputPw, inputAge))
                    println("${yellow}OperatorAccount erfolgreich erstellt!")
                    break
                } else {
                    attempts++
                    if (attempts < 3) {
                        println("${red}Falsches Passwort.${yellow} Du hast noch ${3 - attempts} Versuch(e). ${blue}Bitte erneut eingeben:${reset}")
                    } else {
                        println("${red}Falsches Passwort. ${yellow}Die maximale Anzahl an Versuchen wurde erreicht. ${red}Accounterstellung abgebrochen.${reset}")
                        println()
                        Thread.sleep(800)
                        startShop()
                    }
                }
            }
        }

        else -> println("Ungültige Account-Art.")
    }
    println("${cyan}Sie werden zum Login weitergeleitet.")
    Thread.sleep(800)
}

private fun Store.login(): Account? {
    var account: Account? = null
    var attempts = 0
    while (account == null) {
        println()
        println("${red}--------------------${bold}${cyan}Login${reset}${red}-------------------")
        println("${blue}Bitte Name eingeben oder ${red}'exit'${blue} zum Beenden:${reset}")
        val nameInput = readln()
        if (nameInput.lowercase() == "exit") {
            startShop()
            return null
        }
        println("${blue}Bitte geben Sie Ihr Passwort ein:${reset}")
        val pwInput = readln()

        account = accounts.find { it.name == nameInput && it.checkPassword(pwInput) }
        if (account != null) {
            if (account.age >= 12) {
                when (account) {
                    is CustomerAccount -> {
                        println()
                        println("${cyan}Zugang als Kunde gewährt. Willkommen im Store, ${green}$nameInput!${reset}")
                        this.loggedInAccount = account
                        return account
                    }

                    is OperatorAccount -> {
                        println("${cyan}Zugang als Betreiber gewährt. Willkommen im Store, ${green}$nameInput!")
                        this.loggedInAccount = account
                        return account
                    }

                    else -> println("${red}Unbekannter Account-Typ.${reset}")
                }
            } else {
                println("${red}Zugang verweigert. ${cyan}Du musst mindestens 12 Jahre alt sein, um den Store zu betreten.")
                println("${magenta}Du wirst zum Start zurückgeleitet. Du bekommst eine Mail, wenn du alt genug bist.${reset}")
                println()
                startShop()
                return null
            }
        } else {
            attempts++
            if (attempts < 3) {
                println("${red}Account nicht gefunden oder falsches Passwort. Versuch $attempts von 3.${reset}")
            } else {
                println("${red}Maximale Anzahl an Login-Versuchen erreicht. ${magenta}Du wirst zum Start zurückgeleitet.")
                println()
                startShop()
                return null
            }
        }
    }
    return account
}

fun Store.logout() {

    loggedInAccount = null
    println("${cyan}Du wurdest erfolgreich ausgeloggt.${reset}")
    println()
    startShop()
}