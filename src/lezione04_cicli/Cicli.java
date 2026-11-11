package lezione04_cicli;

/*
 * =====================================================================
 *  LEZIONE 4 - I CICLI: for, while, do-while
 * =====================================================================
 *
 *  Un CICLO (o "loop") ripete un blocco di codice più volte.
 *  Immagina di dover stampare i numeri da 1 a 1000: senza cicli
 *  servirebbero 1000 righe di codice!
 *
 *  Java ha 4 tipi di ciclo:
 *    - for       -> quando SAI QUANTE VOLTE ripetere
 *    - while     -> quando ripeti FINCHÉ una condizione è vera
 *    - do-while  -> come while, ma esegue ALMENO UNA VOLTA
 *    - for-each  -> per scorrere gli elementi di un array/collezione
 *                   (lo vediamo nella lezione 5)
 */
public class Cicli {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. CICLO FOR
        //
        //    for (inizializzazione; condizione; aggiornamento) { ... }
        //
        //    - inizializzazione: eseguita UNA volta all'inizio
        //    - condizione: controllata PRIMA di ogni giro; se false, esce
        //    - aggiornamento: eseguito ALLA FINE di ogni giro
        // ---------------------------------------------------------------
        System.out.println("--- for da 1 a 5 ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Giro numero " + i);
        }
        // Qui fuori "i" non esiste più: vive solo dentro il for (SCOPE).

        System.out.println("\n--- conto alla rovescia ---");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println("VIA!");

        System.out.println("\n--- solo i pari fino a 20 ---");
        for (int i = 0; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Esempio pratico: somma dei numeri da 1 a 100
        int somma = 0;
        for (int i = 1; i <= 100; i++) {
            somma += i;
        }
        System.out.println("\nSomma da 1 a 100 = " + somma); // 5050

        // ---------------------------------------------------------------
        // 2. CICLO WHILE
        //
        //    while (condizione) { ... }
        //
        //    Controlla la condizione PRIMA di ogni giro. Se è subito false,
        //    il corpo non viene mai eseguito.
        // ---------------------------------------------------------------
        System.out.println("\n--- while: quante volte posso dimezzare 1000? ---");
        int valore = 1000;
        int dimezzamenti = 0;
        while (valore > 1) {
            valore = valore / 2;
            dimezzamenti++;
        }
        System.out.println("Dimezzato " + dimezzamenti + " volte");

        // Simulazione: risparmio fino a raggiungere un obiettivo
        double salvadanaio = 0;
        int settimane = 0;
        while (salvadanaio < 100) {
            salvadanaio += 15.50;
            settimane++;
        }
        System.out.println("Servono " + settimane + " settimane per arrivare a 100€");

        // ATTENZIONE AL CICLO INFINITO: se la condizione non diventa mai
        // false, il programma non si ferma più!
        //   int k = 0;
        //   while (k < 10) { System.out.println(k); }  // manca k++ !!!

        // ---------------------------------------------------------------
        // 3. CICLO DO-WHILE
        //
        //    do { ... } while (condizione);
        //
        //    Controlla la condizione DOPO ogni giro: il corpo viene eseguito
        //    SEMPRE almeno una volta. Tipico per i menu ("ripeti finché
        //    l'utente non sceglie Esci").
        // ---------------------------------------------------------------
        System.out.println("\n--- do-while ---");
        int n = 100;
        do {
            System.out.println("Eseguito almeno una volta, n = " + n);
        } while (n < 10); // falsa subito, ma il corpo è già stato eseguito

        // ---------------------------------------------------------------
        // 4. BREAK e CONTINUE
        //    break    -> ESCE subito dal ciclo
        //    continue -> SALTA al giro successivo
        // ---------------------------------------------------------------
        System.out.println("\n--- break: cerco il primo multiplo di 7 dopo 50 ---");
        for (int i = 51; i < 100; i++) {
            if (i % 7 == 0) {
                System.out.println("Trovato: " + i);
                break; // non serve continuare
            }
        }

        System.out.println("\n--- continue: numeri da 1 a 10 tranne i multipli di 3 ---");
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                continue; // salta il resto e passa al prossimo i
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 5. CICLI ANNIDATI (un ciclo dentro un altro)
        //    Per ogni giro del ciclo esterno, quello interno fa TUTTI i suoi.
        // ---------------------------------------------------------------
        System.out.println("\n--- Tavola pitagorica 5x5 ---");
        for (int riga = 1; riga <= 5; riga++) {
            for (int colonna = 1; colonna <= 5; colonna++) {
                System.out.printf("%4d", riga * colonna);
            }
            System.out.println(); // a capo alla fine di ogni riga
        }

        System.out.println("\n--- Triangolo di asterischi ---");
        for (int riga = 1; riga <= 5; riga++) {
            for (int s = 0; s < riga; s++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // ---------------------------------------------------------------
        // 6. QUALE CICLO SCEGLIERE?
        //    - So quante volte?              -> for
        //    - Dipende da una condizione?    -> while
        //    - Almeno una volta di sicuro?   -> do-while
        //    - Devo scorrere un array/lista? -> for-each (lezione 5)
        // ---------------------------------------------------------------
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Stampa la tabellina del 7 (7 x 1 = 7 ... 7 x 10 = 70).
 *  2. Calcola il fattoriale di 10 (10! = 10*9*8*...*1). Che tipo serve per
 *     calcolare 20! ? (suggerimento: int non basta)
 *  3. Stampa i primi 15 numeri della successione di Fibonacci
 *     (0, 1, 1, 2, 3, 5, 8, ...).
 *  4. Dato un numero intero, conta le sue cifre usando while e /10.
 *  5. Stampa una piramide centrata di altezza 5:
 *            *
 *           ***
 *          *****
 *         *******
 *        *********
 *  6. Verifica se un numero è primo (divisibile solo per 1 e se stesso).
 */
