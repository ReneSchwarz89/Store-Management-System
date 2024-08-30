<h1 align="center">Grundlagen der Programmierung</h1>
<h3 align="center">Abschlussaufgabe Store</h3>
<br>

<p align="center">
  <img src="img/img1.png" width="50%">
</p>

> **Hinweis**: Lies die Aufgabe aufmerksam. Versuche vor Beginn, die Aufgabe zu strukturieren. Notiere dir skizzenartig, was dein Programm braucht und setze deinen Plan Schritt für Schritt um.


### 1. Szenario

Du bist neuerdings App-Developer und bekommst deinen ersten Auftrag: Du sollst einen neuen Online Store „Golden Syntax“ mitentwickeln. Bei diesem Laden handelt es sich um einen Online Vertrieb von verschiedenen Produkten. In deinem Developer-Team wird dir die Aufgabe zugeteilt, die Funktionalität des Shops zu entwickeln. Um die Datenbank kümmert sich ein anderes Team. Danach sollst du ein Accountmanagementsystem erschaffen, sodass sich der Ladenbetreiber und die Kunden in ihr Profil einloggen können.

<span style="text-decoration:underline;">Welche Produkte in deinem Store zum Verkauf stehen, kannst du selbst entscheiden. </span>


### 2. Details

Die Vorlage ist ein leeres Projekt in IntelliJ. Erstelle alle nötigen Dateien: Es gibt eine Datei _main.kt_, in welcher der Onlinestore abläuft. Alle Klassen, die du erstellst, erhalten <span style="text-decoration:underline;">jeweils</span> eine eigene Datei. Funktionen kannst du <span style="text-decoration:underline;">zusammen</span> in eine Datei verlagern.


#### Informationen zu den Produkten:



* Es gibt mindestens _<span style="text-decoration:underline;">zwei</span>_ _<span style="text-decoration:underline;">Kategorien</span>_ _<span style="text-decoration:underline;">von</span>_ _<span style="text-decoration:underline;">Produkten</span>_, mit jeweils _<span style="text-decoration:underline;">mindestens</span>_ _<span style="text-decoration:underline;">zwei</span>_ _<span style="text-decoration:underline;">Unterkategorien</span>_. Jedes Produkt sollte _<span style="text-decoration:underline;">mindestens</span>_ einen _<span style="text-decoration:underline;">Namen</span>_, einen _<span style="text-decoration:underline;">Preis</span>_ und eine _<span style="text-decoration:underline;">Kundenrezension</span>_ besitzen.  \
  **Beispiele für Kategorien: ** \
  Modeartikel, Elektronikartikel, Filme, Bücher,  etc..
* Die Unterkategorien müssen sich sinnvoll von den anderen unterscheiden. D.h. explizit, dass sie <span style="text-decoration:underline;">mindestens eine einzigartige Eigenschaft (Attribut) oder Fähigkeit (Methode) </span> besitzen.   \
  **Beispiele für Unterkategorien: <span style="text-decoration:underline;"> \
  </span>**Mode: T-Shirts, Jacken, Schuhe \
  Elektronikartikel: Fernseher, Drucker, Konsole \
  Bücher: Roman, Lehrbuch, Atlas
* Überlege dir eine sinnvolle Struktur, um deine Produkte im Store speichern und bearbeiten zu können.


#### Informationen zu den Accounts:



* Es gibt _<span style="text-decoration:underline;">2 Arten von Accounts</span>_: Betreiber-Accounts und Kunden-Accounts.  \
  Beide Arten von Accounts sollten mithilfe eines Passworts geschützt werden, das beim Einloggen korrekt eingegeben werden muss.
* Die **Kunden-Accounts** sollten mit Hilfe der Konsole erstellt werden können.  \
  Jedes Kundenkonto sollte zumindest eine Zahlungsmethode abspeichern und einen Warenkorb speichern können. Diese Klasse sollte es ermöglichen, den aktuellen Gesamtpreis des Warenkorbs schnell einzusehen. Kunden-Accounts sollen außerdem die Möglichkeit besitzen, ein Produkt zu bewerten.
* Die **Betreiber-Accounts **sollten mit Hilfe der Konsole Produkte hinzufügen, löschen und nachbestellen können.


#### Funktionalitäten des Stores:


* Der Store sollte bereits mit einem guten Sortiment initialisiert werden, sodass man schnell auf die wichtigsten Objekte (**Accounts** und **Produkte**) zugreifen kann.
* Der Store hat eine Altersbegrenzung. Nur User mit einem Mindestalter von 12 Jahren haben Zugang zu den Funktionalitäten des Stores.
* Der Store besitzt einige print-Methoden, die nützliche Ansichten auf die Produkte auf der Konsole ausdrucken können.
* Implementiere mindestens zwei sortierte Ansichten auf die Produkte (z.B. nach Preis, alphabetisch, etc.).
* Implementiere mindestens zwei gefilterte Ansichten auf die Produkte (z.B. nach Unterkategorie, Maximalpreis).
* Der Store erlaubt es Kunden, Produkte zu ihrem Warenkorb hinzuzufügen und zeigt den Gesamtpreis an.


### 3. Optionale, freiwillige Ergänzungen und Anmerkungen

Selbstverständlich darfst du deinen Store mit deinen eigenen Ideen weiter ausschmücken.  \
Einige Ideen zur Erweiterung:

* Gutscheincodes
* Sonderangebote (z.B. “Kauf 3, zahl 2”)
* Empfehlung von ähnlichen Produkten


