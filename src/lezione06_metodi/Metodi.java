package lezione06_metodi;

import java.util.Arrays;

/*
 * =====================================================================
 *  LEZIONE 6 - I METODI (e l'OVERLOADING)
 * =====================================================================
 *
 *  Un METODO è un blocco di codice con un NOME, che svolge un compito
 *  preciso e che possiamo RICHIAMARE quante volte vogliamo.
 *  (In altri linguaggi si chiamano "funzioni".)
 *
 *  PERCHÉ USARLI?
 *   - Evitare di ripetere lo stesso codice (principio DRY: Don't Repeat Yourself)
 *   - Dividere un problema grande in pezzi piccoli e comprensibili
 *   - Dare un nome a un'operazione rende il codice più leggibile
 *
 *  ANATOMIA DI UN METODO
 *
 *     public static  int     somma  (int a, int b)   {
 *     ^^^^^^^^^^^^^  ^^^     ^^^^^   ^^^^^^^^^^^^
 *     modificatori   tipo    nome    parametri
 *                    di
 *                    ritorno
 *         return a + b;     <- "return" restituisce il risultato
 *     }
 *
 *   - TIPO DI RITORNO: il tipo del valore che il metodo restituisce.
 *     Se non restituisce niente si usa  void.
 *   - PARAMETRI: i dati in ingresso (possono essere zero o più).
 *   - FIRMA (signature) del metodo = nome + lista dei tipi dei parametri.
 *
 *  Per ora tutti i nostri metodi sono "static": significa che
 *  appartengono alla classe e si possono chiamare direttamente dal main.
 *  Nella lezione 7 vedremo i metodi NON static (di istanza).
 */
public class Metodi {

    // =================================================================
    // 1. METODO SENZA PARAMETRI E SENZA VALORE DI RITORNO
    // =================================================================
    static void stampaSeparatore() {
        System.out.println("----------------------------------------");
    }

    // =================================================================
    // 2. METODO CON PARAMETRI, SENZA VALORE DI RITORNO
    // =================================================================
    static void saluta(String nome) {
        System.out.println("Ciao, " + nome + "!");
    }

    static void stampaRiga(char simbolo, int quante) {
        for (int i = 0; i < quante; i++) {
            System.out.print(simbolo);
        }
        System.out.println();
    }

    // =================================================================
    // 3. METODI CHE RESTITUISCONO UN VALORE
    // =================================================================
    static int quadrato(int n) {
        return n * n;
    }

    static boolean isPari(int n) {
        return n % 2 == 0; // restituisce direttamente il risultato del confronto
    }

    static double media(int[] valori) {
        if (valori.length == 0) {
            return 0; // "return" ESCE SUBITO dal metodo
        }
        int somma = 0;
        for (int v : valori) {
            somma += v;
        }
        return (double) somma / valori.length;
    }

    static String valutazione(int voto) {
        // Più return: si esce al primo che viene raggiunto
        if (voto >= 27) return "Ottimo";
        if (voto >= 22) return "Buono";
        if (voto >= 18) return "Sufficiente";
        return "Insufficiente";
    }

    // =================================================================
    // 4. OVERLOADING (SOVRACCARICO)
    //
    //    Possiamo avere PIÙ METODI CON LO STESSO NOME, purché abbiano
    //    PARAMETRI DIVERSI (per numero, tipo o ordine).
    //    Il compilatore sceglie quale chiamare guardando gli argomenti.
    //
    //    NB: cambiare SOLO il tipo di ritorno NON basta! Questo è vietato:
    //        static int    calcola(int a) {...}
    //        static double calcola(int a) {...}   // ERRORE: stessa firma
    //
    //    L'overloading viene risolto a TEMPO DI COMPILAZIONE.
    //    (Da non confondere con l'OVERRIDING della lezione 8!)
    // =================================================================
    static int somma(int a, int b) {
        System.out.print("[somma(int,int)] ");
        return a + b;
    }

    static int somma(int a, int b, int c) {          // numero diverso di parametri
        System.out.print("[somma(int,int,int)] ");
        return a + b + c;
    }

    static double somma(double a, double b) {        // tipi diversi
        System.out.print("[somma(double,double)] ");
        return a + b;
    }

    static String somma(String a, String b) {        // tipi diversi
        System.out.print("[somma(String,String)] ");
        return a + b;
    }

    // Varargs: "int... numeri" accetta un numero QUALSIASI di argomenti int,
    // che dentro il metodo vengono visti come un array.
    static int sommaTutti(int... numeri) {
        int totale = 0;
        for (int n : numeri) totale += n;
        return totale;
    }

    // Un esempio di overloading che usa Java stesso: System.out.println
    // ha versioni per int, double, String, char, boolean, Object...

    // =================================================================
    // 5. PASSAGGIO DEI PARAMETRI: SEMPRE "PER VALORE"
    //
    //    Java passa al metodo una COPIA del valore.
    //    - Per i tipi primitivi: il metodo riceve una copia del numero,
    //      quindi modificarlo NON cambia la variabile originale.
    //    - Per array e oggetti: il metodo riceve una copia del RIFERIMENTO
    //      (l'"indirizzo"), quindi PUÒ modificare il contenuto originale.
    // =================================================================
    static void provaARaddoppiare(int numero) {
        numero = numero * 2;
        System.out.println("  dentro il metodo numero = " + numero);
    }

    static void raddoppiaTutti(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * 2;
        }
    }

    // =================================================================
    // 6. RICORSIONE: un metodo che chiama SE STESSO
    //    Serve sempre un CASO BASE che ferma la ricorsione, altrimenti
    //    si va avanti all'infinito (StackOverflowError).
    //
    //    fattoriale(4) = 4 * fattoriale(3)
    //                  = 4 * 3 * fattoriale(2)
    //                  = 4 * 3 * 2 * fattoriale(1)
    //                  = 4 * 3 * 2 * 1 = 24
    // =================================================================
    static long fattoriale(int n) {
        if (n <= 1) {
            return 1;                  // CASO BASE
        }
        return n * fattoriale(n - 1);  // PASSO RICORSIVO
    }

    // =================================================================
    // 7. SCOPE (visibilità) delle variabili
    //    Una variabile esiste SOLO nel blocco { } in cui è dichiarata.
    // =================================================================
    static int contatoreGlobale = 0; // variabile "di classe": visibile in tutti i metodi

    static void incrementa() {
        int locale = 10;           // esiste solo dentro questo metodo
        contatoreGlobale++;
        System.out.println("locale=" + locale + ", contatoreGlobale=" + contatoreGlobale);
    }

    // =================================================================
    // MAIN: qui USIAMO (chiamiamo) i metodi definiti sopra
    // =================================================================
    public static void main(String[] args) {

        stampaSeparatore();
        saluta("Giulia");
        saluta("Marco");
        stampaRiga('=', 30);
        stampaRiga('*', 10);

        stampaSeparatore();
        int q = quadrato(7);
        System.out.println("Il quadrato di 7 è " + q);
        System.out.println("Il quadrato di 12 è " + quadrato(12)); // anche direttamente
        System.out.println("8 è pari? " + isPari(8));
        System.out.println("Media: " + media(new int[]{18, 24, 30}));
        System.out.println("Voto 25: " + valutazione(25));

        // Composizione: il risultato di un metodo usato come argomento di un altro
        System.out.println("Il quadrato di 3 è pari? " + isPari(quadrato(3)));

        stampaSeparatore();
        System.out.println("OVERLOADING:");
        System.out.println(somma(2, 3));
        System.out.println(somma(2, 3, 4));
        System.out.println(somma(2.5, 1.2));
        System.out.println(somma("Ciao ", "mondo"));
        System.out.println("sommaTutti() = " + sommaTutti());
        System.out.println("sommaTutti(1,2,3,4,5) = " + sommaTutti(1, 2, 3, 4, 5));

        stampaSeparatore();
        System.out.println("PASSAGGIO PER VALORE:");
        int mioNumero = 5;
        provaARaddoppiare(mioNumero);
        System.out.println("  fuori dal metodo mioNumero = " + mioNumero + "  (invariato!)");

        int[] mioArray = {1, 2, 3};
        raddoppiaTutti(mioArray);
        System.out.println("  array dopo raddoppiaTutti = " + Arrays.toString(mioArray)
                + "  (modificato!)");

        stampaSeparatore();
        System.out.println("RICORSIONE:");
        for (int i = 0; i <= 10; i++) {
            System.out.println("  " + i + "! = " + fattoriale(i));
        }

        stampaSeparatore();
        System.out.println("SCOPE:");
        incrementa();
        incrementa();
        incrementa();
        // System.out.println(locale); // ERRORE: "locale" qui non esiste
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Scrivi  static boolean isPrimo(int n)  e usalo per stampare i
 *     numeri primi fino a 100.
 *  2. Scrivi  static int massimo(int[] numeri)  e  static int minimo(...)
 *  3. OVERLOADING: scrivi tre versioni di  area(...)
 *       - area(double lato)               -> quadrato
 *       - area(double base, double alt)   -> rettangolo
 *       - area(double raggio, boolean c)  -> cerchio (Math.PI * r * r)
 *     Ti sembra elegante la terza? Perché? (pensa a nomi più chiari)
 *  4. Scrivi  static String inverti(String s)  che restituisce la stringa
 *     al contrario, e  static boolean isPalindroma(String s)  che lo usa.
 *  5. Scrivi la successione di Fibonacci in versione ricorsiva.
 *     Prova fib(45): perché è così lenta?
 */
