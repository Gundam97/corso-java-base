package lezione12_eccezioni;

import java.util.Scanner;

/*
 * =====================================================================
 *  LEZIONE 12 - LE ECCEZIONI (gestire gli errori)
 * =====================================================================
 *
 *  Un'ECCEZIONE è un evento anomalo che interrompe il normale flusso del
 *  programma: dividere per zero, leggere un indice inesistente di un
 *  array, usare una variabile null, aprire un file che non c'è...
 *
 *  Se nessuno la gestisce, il programma si FERMA e stampa lo "stack
 *  trace" (l'elenco dei metodi in cui si trovava). Con try/catch
 *  possiamo invece INTERCETTARLA e reagire.
 *
 *      try {
 *          // codice che potrebbe fallire
 *      } catch (TipoEccezione e) {
 *          // cosa fare se fallisce
 *      } finally {
 *          // eseguito SEMPRE, sia in caso di successo che di errore
 *      }
 *
 *  GERARCHIA (semplificata)
 *
 *                       Throwable
 *                      /         \
 *                  Error        Exception
 *         (gravi, non si        /        \
 *          gestiscono:     RuntimeException   IOException, ...
 *          es. memoria     (UNCHECKED)        (CHECKED)
 *          esaurita)       - ArithmeticException
 *                          - NullPointerException
 *                          - ArrayIndexOutOfBoundsException
 *                          - IllegalArgumentException
 *                          - NumberFormatException
 *
 *  CHECKED vs UNCHECKED
 *   - UNCHECKED (RuntimeException e figlie): di solito sono BUG del
 *     programmatore. Il compilatore non obbliga a gestirle.
 *   - CHECKED (le altre Exception): problemi "esterni" prevedibili (file
 *     mancante, rete giù...). Il compilatore OBBLIGA a gestirle con
 *     try/catch oppure a dichiararle con "throws" nella firma del metodo.
 */
public class Eccezioni {

    // =================================================================
    // Metodo che LANCIA un'eccezione (unchecked) se i dati non vanno bene
    // =================================================================
    static double calcolaMedia(int[] voti) {
        if (voti == null || voti.length == 0) {
            // "throw" lancia l'eccezione: il metodo si interrompe qui
            throw new IllegalArgumentException("L'array dei voti è vuoto");
        }
        int somma = 0;
        for (int v : voti) somma += v;
        return (double) somma / voti.length;
    }

    // =================================================================
    // Metodo che lancia un'eccezione CHECKED: deve dichiararlo con "throws".
    // Chi lo chiama è OBBLIGATO a gestirla.
    // =================================================================
    static double saldo = 100;

    static void preleva(double importo) throws SaldoInsufficienteException {
        if (importo > saldo) {
            throw new SaldoInsufficienteException(importo, saldo);
        }
        saldo -= importo;
        System.out.println("  Prelevati " + importo + " €, saldo residuo " + saldo + " €");
    }

    // Un metodo può anche NON gestire l'eccezione e "passarla" al chiamante
    static void prelevaDueVolte(double importo) throws SaldoInsufficienteException {
        preleva(importo);
        preleva(importo); // se fallisce, l'eccezione risale al chiamante
    }

    public static void main(String[] args) {

        // -------------------------------------------------------------
        // 1. ECCEZIONI COMUNI, intercettate con try/catch
        // -------------------------------------------------------------
        System.out.println("=== 1. Eccezioni comuni ===");

        try {
            int risultato = 10 / 0;
            System.out.println("Questa riga non verrà mai stampata " + risultato);
        } catch (ArithmeticException e) {
            System.out.println("  ArithmeticException: " + e.getMessage());
        }

        try {
            int[] numeri = {1, 2, 3};
            System.out.println(numeri[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  ArrayIndexOutOfBounds: " + e.getMessage());
        }

        try {
            String testo = null;
            System.out.println(testo.length());
        } catch (NullPointerException e) {
            System.out.println("  NullPointerException: la variabile era null!");
        }

        try {
            int n = Integer.parseInt("dodici");
            System.out.println(n);
        } catch (NumberFormatException e) {
            System.out.println("  NumberFormatException: " + e.getMessage());
        }

        // -------------------------------------------------------------
        // 2. PIÙ CATCH e FINALLY
        //    I catch vengono provati in ordine: si mettono prima le
        //    eccezioni più SPECIFICHE, poi quelle più GENERICHE.
        // -------------------------------------------------------------
        System.out.println("\n=== 2. Più catch e finally ===");
        String[] input = {"10", "0", "abc", "5"};
        for (String s : input) {
            try {
                int divisore = Integer.parseInt(s);
                System.out.println("  100 / " + divisore + " = " + (100 / divisore));
            } catch (NumberFormatException e) {
                System.out.println("  '" + s + "' non è un numero");
            } catch (ArithmeticException e) {
                System.out.println("  Non si divide per zero!");
            } catch (Exception e) {
                // Exception cattura (quasi) tutto: va messo per ULTIMO
                System.out.println("  Errore imprevisto: " + e);
            } finally {
                System.out.println("    (finally: eseguito comunque per '" + s + "')");
            }
        }

        // Multi-catch: stessa gestione per più tipi di eccezione
        try {
            Object o = "testo";
            Integer i = (Integer) o; // ClassCastException
            System.out.println(i);
        } catch (ClassCastException | IllegalStateException e) {
            System.out.println("  Multi-catch: " + e.getClass().getSimpleName());
        }

        // -------------------------------------------------------------
        // 3. LANCIARE eccezioni con throw
        // -------------------------------------------------------------
        System.out.println("\n=== 3. throw ===");
        try {
            System.out.println("  Media: " + calcolaMedia(new int[]{24, 28, 30}));
            System.out.println("  Media: " + calcolaMedia(new int[]{}));
        } catch (IllegalArgumentException e) {
            System.out.println("  Errore: " + e.getMessage());
        }

        // -------------------------------------------------------------
        // 4. ECCEZIONI CHECKED e PERSONALIZZATE
        //    Provate a togliere il try/catch: il codice NON compila!
        // -------------------------------------------------------------
        System.out.println("\n=== 4. Eccezione checked personalizzata ===");
        try {
            preleva(30);
            prelevaDueVolte(40); // la seconda volta fallisce
        } catch (SaldoInsufficienteException e) {
            System.out.println("  " + e.getMessage());
            System.out.printf("  Mancano %.2f €%n", e.getMancante());
        }

        // -------------------------------------------------------------
        // 5. TRY-WITH-RESOURCES
        //    Le risorse dichiarate tra parentesi vengono chiuse
        //    AUTOMATICAMENTE alla fine, anche in caso di errore.
        //    (Qui lo Scanner legge da una stringa invece che da tastiera.)
        // -------------------------------------------------------------
        System.out.println("\n=== 5. try-with-resources ===");
        try (Scanner sc = new Scanner("12 7 abc 3")) {
            int somma = 0;
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    somma += sc.nextInt();
                } else {
                    System.out.println("  Scarto il valore non numerico: " + sc.next());
                }
            }
            System.out.println("  Somma dei numeri letti: " + somma);
        } // <- qui sc.close() viene chiamato automaticamente

        // -------------------------------------------------------------
        // 6. STACK TRACE: cosa succede se NON gestiamo un'eccezione
        // -------------------------------------------------------------
        System.out.println("\n=== 6. Stack trace (stampato apposta) ===");
        try {
            calcolaMedia(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out); // mostra DOVE è nato l'errore
        }

        System.out.println("\nIl programma è arrivato fino in fondo senza crash!");
    }
}

/*
 *  BUONE PRATICHE
 *   - Non "ingoiare" le eccezioni con un catch vuoto: catch (Exception e) {}
 *     nasconde i problemi e rende il debug un incubo.
 *   - Cattura l'eccezione più SPECIFICA possibile.
 *   - Le eccezioni sono per i casi ECCEZIONALI: non usarle al posto di un if.
 *   - Valida gli input all'inizio dei metodi e lancia
 *     IllegalArgumentException se sono sbagliati ("fail fast").
 *
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Scrivi un metodo  int leggiEta(String testo)  che converte il testo
 *     in numero e lancia IllegalArgumentException se l'età è < 0 o > 120.
 *  2. Crea un'eccezione checked  EtaNonValidaException  e usala al posto
 *     della precedente.
 *  3. Riprendi ContoCorrente della lezione 7 e fai in modo che preleva()
 *     lanci SaldoInsufficienteException invece di restituire false.
 *  4. Con uno Scanner da tastiera, chiedi un numero finché l'utente non ne
 *     inserisce uno valido (ciclo + try/catch).
 */
