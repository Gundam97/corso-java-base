package lezione01_ciaomondo;

/*
 * =====================================================================
 *  LEZIONE 1 - IL PRIMO PROGRAMMA: "CIAO MONDO"
 * =====================================================================
 *
 *  COS'È JAVA?
 *  -----------
 *  Java è un linguaggio di programmazione "compilato" e "orientato agli
 *  oggetti". Funziona così:
 *
 *     1) Scriviamo il codice in file di testo con estensione .java
 *     2) Il COMPILATORE (comando `javac`) traduce il codice in "bytecode"
 *        (file .class), un linguaggio intermedio.
 *     3) La JVM (Java Virtual Machine, comando `java`) esegue il bytecode.
 *
 *     CiaoMondo.java  --javac-->  CiaoMondo.class  --java-->  output a video
 *
 *  Grazie alla JVM lo stesso programma gira su Windows, Mac e Linux senza
 *  modifiche: "Write once, run anywhere".
 *
 *  REGOLE FONDAMENTALI DA RICORDARE SUBITO
 *  ---------------------------------------
 *  - Java è CASE SENSITIVE: `Ciao`, `ciao` e `CIAO` sono tre cose diverse.
 *  - Ogni istruzione termina con il punto e virgola  ;
 *  - I blocchi di codice sono delimitati da parentesi graffe  { ... }
 *  - Tutto il codice vive DENTRO una classe.
 *  - Il nome della classe pubblica DEVE coincidere con il nome del file
 *    (classe CiaoMondo  ->  file CiaoMondo.java).
 */

// La riga "package" in cima indica in quale "cartella logica" si trova la
// classe. Serve a organizzare il codice (come le cartelle sul computer).

// Questa è la dichiarazione di una CLASSE. Per ora pensala come un
// "contenitore" del nostro codice. Capiremo bene cos'è nella lezione 7.
public class CiaoMondo {

    /*
     * Il metodo MAIN è il PUNTO DI INGRESSO del programma: quando lanciamo
     * il programma, la JVM cerca esattamente questo metodo e parte da qui.
     *
     *   public        -> visibile da chiunque (la JVM deve poterlo chiamare)
     *   static        -> si può chiamare senza creare un oggetto (lezione 7)
     *   void          -> non restituisce nessun valore
     *   main          -> il nome, obbligatorio
     *   String[] args -> eventuali parametri passati da riga di comando
     */
    public static void main(String[] args) {

        // System.out.println stampa un testo e poi VA A CAPO
        System.out.println("Ciao mondo!");

        // System.out.print stampa SENZA andare a capo
        System.out.print("Questo è ");
        System.out.print("sulla stessa riga. ");
        System.out.println(); // println vuoto = va solo a capo

        // Il testo tra virgolette doppie si chiama STRINGA.
        // Con il + possiamo "concatenare" (unire) stringhe.
        System.out.println("Ciao " + "a " + "tutti!");

        // Possiamo stampare anche numeri, e fare calcoli
        System.out.println(2 + 3);            // stampa 5
        System.out.println("2 + 3 = " + (2 + 3)); // stampa 2 + 3 = 5

        // ATTENZIONE: senza parentesi Java legge da sinistra a destra:
        // "2 + 3 = " + 2 diventa "2 + 3 = 2", poi + 3 diventa "2 + 3 = 23"
        System.out.println("Senza parentesi: " + 2 + 3); // stampa ...23 !!

        // Caratteri speciali dentro le stringhe (sequenze di "escape"):
        //   \n  = a capo        \t = tabulazione
        //   \"  = virgolette    \\ = backslash
        System.out.println("Riga 1\nRiga 2");
        System.out.println("Nome:\tMario");
        System.out.println("Lui disse: \"Ciao!\"");

        /*
         * TIPI DI COMMENTI
         *
         *   // commento su una riga
         *
         *   / *  commento
         *        su più righe  * /      (senza spazi tra / e *)
         *
         *   / **  commento Javadoc: serve a generare documentazione  * /
         *
         * I commenti vengono IGNORATI dal compilatore: servono a chi legge.
         */
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Modifica il programma perché stampi il tuo nome e la tua età.
 *  2. Stampa un piccolo "biglietto da visita" incorniciato, ad esempio:
 *        +-----------------+
 *        |  Mario Rossi    |
 *        |  Studente       |
 *        +-----------------+
 *  3. Togli un punto e virgola e prova a compilare: leggi con attenzione
 *     il messaggio di errore. Imparare a leggere gli errori è FONDAMENTALE.
 *  4. Cambia "main" in "Main" e prova a eseguire: cosa succede? Perché?
 */
