package lezione02_variabili;

/*
 * =====================================================================
 *  LEZIONE 2 - VARIABILI, TIPI DI DATO E OPERATORI
 * =====================================================================
 *
 *  Una VARIABILE è una "scatola" con un NOME che contiene un VALORE.
 *  In Java ogni variabile ha un TIPO, che stabilisce che genere di valore
 *  può contenere. Java è un linguaggio "fortemente tipizzato": una volta
 *  deciso il tipo, non si può più cambiare.
 *
 *      tipo   nome   =  valore;
 *      int    eta    =  25;
 *
 *  I TIPI PRIMITIVI (gli 8 mattoncini base del linguaggio)
 *  -------------------------------------------------------
 *   Tipo     | Cosa contiene                 | Esempio
 *  ----------+-------------------------------+--------------------
 *   byte     | intero piccolo (-128..127)    | byte b = 100;
 *   short    | intero medio (-32768..32767)  | short s = 2000;
 *   int      | intero (circa ±2 miliardi)    | int n = 42;        <- il più usato
 *   long     | intero enorme                 | long l = 9000000000L;
 *   float    | decimale (precisione bassa)   | float f = 3.14f;
 *   double   | decimale (precisione alta)    | double d = 3.14;   <- il più usato
 *   char     | UN singolo carattere          | char c = 'A';      (apici singoli!)
 *   boolean  | vero o falso                  | boolean ok = true;
 *
 *  String NON è un tipo primitivo: è una CLASSE (lo capiremo meglio più
 *  avanti), ma si usa in modo così naturale che la vediamo subito.
 *  Le stringhe vanno tra virgolette DOPPIE: "ciao".
 *
 *  CONVENZIONI SUI NOMI (non obbligatorie ma TUTTI le rispettano)
 *  --------------------------------------------------------------
 *   - variabili e metodi:  camelCase        -> numeroStudenti, calcolaMedia
 *   - classi:              PascalCase       -> ContoCorrente, Studente
 *   - costanti:            MAIUSCOLO_SNAKE  -> PI_GRECO, MAX_TENTATIVI
 *   - i nomi non possono iniziare con un numero né contenere spazi.
 */
public class Variabili {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. DICHIARAZIONE E ASSEGNAMENTO
        // ---------------------------------------------------------------
        int eta;          // DICHIARAZIONE: creo la scatola "eta"
        eta = 18;         // ASSEGNAMENTO: ci metto dentro 18
        int anni = 30;    // dichiarazione + assegnamento insieme (inizializzazione)

        System.out.println("Età: " + eta);
        eta = 19;         // il valore di una variabile può CAMBIARE
        System.out.println("Età dopo il compleanno: " + eta);

        // ---------------------------------------------------------------
        // 2. I VARI TIPI IN AZIONE
        // ---------------------------------------------------------------
        double altezza = 1.78;
        char iniziale = 'M';
        boolean maggiorenne = true;
        String nome = "Mario";
        long popolazioneMondiale = 8_100_000_000L; // gli _ aiutano a leggere

        System.out.println(nome + " (" + iniziale + "), alto " + altezza
                + " m, maggiorenne: " + maggiorenne);
        System.out.println("Popolazione mondiale: " + popolazioneMondiale);

        // ---------------------------------------------------------------
        // 3. COSTANTI con "final": non possono più essere modificate
        // ---------------------------------------------------------------
        final double PI_GRECO = 3.14159;
        // PI_GRECO = 3; // <-- ERRORE di compilazione! Prova a decommentare.
        System.out.println("Pi greco: " + PI_GRECO);

        // ---------------------------------------------------------------
        // 4. OPERATORI ARITMETICI:  +  -  *  /  %
        // ---------------------------------------------------------------
        int a = 17;
        int b = 5;
        System.out.println("\n--- Operatori aritmetici ---");
        System.out.println("a + b = " + (a + b));   // 22
        System.out.println("a - b = " + (a - b));   // 12
        System.out.println("a * b = " + (a * b));   // 85
        System.out.println("a / b = " + (a / b));   // 3  <-- !!! DIVISIONE INTERA
        System.out.println("a % b = " + (a % b));   // 2  <-- RESTO della divisione

        // ATTENZIONE: tra due int la divisione è INTERA (tronca i decimali).
        // Per avere i decimali almeno uno dei due deve essere double.
        System.out.println("17.0 / 5 = " + (17.0 / 5));        // 3.4
        System.out.println("(double) a / b = " + ((double) a / b)); // 3.4

        // Il modulo % è utilissimo: n % 2 == 0 significa "n è pari"
        System.out.println("10 è pari? " + (10 % 2 == 0));

        // ---------------------------------------------------------------
        // 5. OPERATORI DI ASSEGNAMENTO COMPOSTO E INCREMENTO
        // ---------------------------------------------------------------
        System.out.println("\n--- Incrementi ---");
        int punti = 10;
        punti += 5;   // equivale a: punti = punti + 5;   -> 15
        punti -= 3;   // punti = punti - 3;               -> 12
        punti *= 2;   // punti = punti * 2;               -> 24
        punti /= 4;   // punti = punti / 4;               -> 6
        System.out.println("punti = " + punti);

        int contatore = 0;
        contatore++;  // aggiunge 1  -> 1
        contatore++;  //             -> 2
        contatore--;  // toglie 1    -> 1
        System.out.println("contatore = " + contatore);

        // Differenza tra x++ (post-incremento) e ++x (pre-incremento):
        int x = 5;
        int y = x++;  // PRIMA assegna x a y (y=5), POI incrementa x (x=6)
        int z = ++x;  // PRIMA incrementa x (x=7), POI assegna (z=7)
        System.out.println("x=" + x + " y=" + y + " z=" + z);

        // ---------------------------------------------------------------
        // 6. OPERATORI DI CONFRONTO (restituiscono un boolean)
        //     ==  uguale        !=  diverso
        //     <   minore        <=  minore o uguale
        //     >   maggiore      >=  maggiore o uguale
        // ---------------------------------------------------------------
        System.out.println("\n--- Confronti ---");
        System.out.println("5 == 5 ? " + (5 == 5));
        System.out.println("5 != 3 ? " + (5 != 3));
        System.out.println("4 >= 7 ? " + (4 >= 7));
        // ATTENZIONE: = assegna, == confronta. Errore classico dei principianti!

        // ---------------------------------------------------------------
        // 7. OPERATORI LOGICI
        //     &&  AND (vero se ENTRAMBI veri)
        //     ||  OR  (vero se ALMENO UNO vero)
        //     !   NOT (inverte)
        // ---------------------------------------------------------------
        System.out.println("\n--- Logica ---");
        boolean haPatente = true;
        boolean haAuto = false;
        System.out.println("Può guidare la sua auto? " + (haPatente && haAuto));
        System.out.println("Ha almeno una delle due? " + (haPatente || haAuto));
        System.out.println("Non ha l'auto? " + (!haAuto));

        // ---------------------------------------------------------------
        // 8. CONVERSIONI DI TIPO (CASTING)
        // ---------------------------------------------------------------
        System.out.println("\n--- Casting ---");
        // Da tipo "piccolo" a "grande": AUTOMATICO (widening), nessuna perdita
        int intero = 100;
        double decimale = intero;          // 100.0
        System.out.println("int -> double: " + decimale);

        // Da "grande" a "piccolo": va scritto ESPLICITAMENTE (narrowing),
        // perché si può perdere informazione.
        double prezzo = 9.99;
        int prezzoTroncato = (int) prezzo; // 9  (tronca, NON arrotonda!)
        System.out.println("double -> int: " + prezzoTroncato);
        System.out.println("Arrotondato:   " + Math.round(prezzo)); // 10

        // char e int sono "parenti": ogni carattere ha un codice numerico
        char lettera = 'A';
        int codice = lettera;              // 65
        char successiva = (char) (lettera + 1); // 'B'
        System.out.println("'A' vale " + codice + ", la successiva è " + successiva);

        // Da String a numero e viceversa
        String testo = "42";
        int numero = Integer.parseInt(testo);      // stringa -> int
        double d = Double.parseDouble("3.5");      // stringa -> double
        String diNuovoTesto = String.valueOf(numero); // int -> stringa
        System.out.println("parseInt: " + (numero + 1) + ", parseDouble: " + d
                + ", valueOf: " + diNuovoTesto + "!");

        // ---------------------------------------------------------------
        // 9. "var": il compilatore deduce il tipo da solo (Java 10+)
        // ---------------------------------------------------------------
        var citta = "Roma";   // il compilatore capisce che è una String
        var abitanti = 2_800_000; // ...e questo un int
        // citta = 5;  // ERRORE: il tipo resta comunque String!
        System.out.println("\n" + citta + " ha circa " + abitanti + " abitanti");

        // ---------------------------------------------------------------
        // 10. QUALCHE OPERAZIONE UTILE SULLE STRINGHE
        // ---------------------------------------------------------------
        System.out.println("\n--- Stringhe ---");
        String frase = "Java è divertente";
        System.out.println("Lunghezza:      " + frase.length());
        System.out.println("Maiuscolo:      " + frase.toUpperCase());
        System.out.println("Primo carattere:" + frase.charAt(0));
        System.out.println("Contiene 'è'?   " + frase.contains("è"));
        System.out.println("Sottostringa:   " + frase.substring(0, 4)); // "Java"
        System.out.println("Sostituisci:    " + frase.replace("divertente", "potente"));

        // IMPORTANTE: le stringhe si confrontano con .equals(), NON con ==
        // (== confronta se sono lo STESSO oggetto in memoria, non il contenuto)
        String s1 = "ciao";
        String s2 = new String("ciao");
        System.out.println("s1 == s2      -> " + (s1 == s2));      // false!
        System.out.println("s1.equals(s2) -> " + s1.equals(s2));   // true
        System.out.println("equalsIgnoreCase: " + "CIAO".equalsIgnoreCase(s1));

        // String.format / printf per formattare l'output
        //   %s = stringa   %d = intero   %f = decimale   %.2f = 2 decimali
        System.out.printf("%s ha %d anni e pesa %.1f kg%n", "Luca", 20, 70.456);
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Dichiara due variabili con base e altezza di un rettangolo e stampa
 *     perimetro e area.
 *  2. Dati dei secondi (es. 3725), calcola ore, minuti e secondi usando
 *     / e %.  (risultato atteso: 1h 2m 5s)
 *  3. Converti una temperatura da Celsius a Fahrenheit: F = C * 9 / 5 + 32.
 *     Prova sia con int che con double: che differenza vedi?
 *  4. Data la stringa "Programmazione", stampa: la lunghezza, la prima e
 *     l'ultima lettera, e la stringa tutta in minuscolo.
 */
