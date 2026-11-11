package lezione03_condizioni;

/*
 * =====================================================================
 *  LEZIONE 3 - LE CONDIZIONI: if, else, switch
 * =====================================================================
 *
 *  Finora i programmi eseguivano le istruzioni una dopo l'altra, sempre
 *  tutte. Con le CONDIZIONI il programma può PRENDERE DECISIONI:
 *  "SE succede questo, fai quello, ALTRIMENTI fai quest'altro".
 *
 *      if (condizione) {
 *          // eseguito solo se la condizione è true
 *      } else if (altraCondizione) {
 *          // eseguito se la prima è false e questa è true
 *      } else {
 *          // eseguito se tutte le precedenti sono false
 *      }
 *
 *  La condizione è SEMPRE un'espressione boolean (true/false).
 */
public class Condizioni {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. IF semplice
        // ---------------------------------------------------------------
        int temperatura = 32;

        if (temperatura > 30) {
            System.out.println("Fa caldo! Accendi il condizionatore.");
        }

        // ---------------------------------------------------------------
        // 2. IF / ELSE
        // ---------------------------------------------------------------
        int eta = 16;

        if (eta >= 18) {
            System.out.println("Sei maggiorenne.");
        } else {
            System.out.println("Sei minorenne: mancano " + (18 - eta) + " anni.");
        }

        // ---------------------------------------------------------------
        // 3. IF / ELSE IF / ELSE  (catena di condizioni)
        //    Java le controlla IN ORDINE e si ferma alla PRIMA vera.
        // ---------------------------------------------------------------
        int voto = 27;

        if (voto == 30) {
            System.out.println("Perfetto!");
        } else if (voto >= 27) {
            System.out.println("Ottimo"); // <- entra qui e salta il resto
        } else if (voto >= 22) {
            System.out.println("Buono");
        } else if (voto >= 18) {
            System.out.println("Sufficiente");
        } else {
            System.out.println("Non superato");
        }

        // ---------------------------------------------------------------
        // 4. CONDIZIONI COMPOSTE con && || !
        // ---------------------------------------------------------------
        int ora = 14;
        boolean festivo = false;

        if (ora >= 9 && ora < 18 && !festivo) {
            System.out.println("Il negozio è aperto.");
        } else {
            System.out.println("Il negozio è chiuso.");
        }

        // CORTOCIRCUITO: con && se la prima parte è false, la seconda NON
        // viene nemmeno valutata. Utile per evitare errori:
        String nome = null; // null = "nessun valore", la variabile è vuota
        if (nome != null && nome.length() > 3) { // senza il primo controllo -> crash!
            System.out.println("Nome lungo");
        } else {
            System.out.println("Nome assente o corto");
        }

        // ---------------------------------------------------------------
        // 5. IF ANNIDATI (un if dentro un altro)
        // ---------------------------------------------------------------
        boolean haBiglietto = true;
        int altezzaCm = 125;

        if (haBiglietto) {
            if (altezzaCm >= 130) {
                System.out.println("Puoi salire sulle montagne russe!");
            } else {
                System.out.println("Hai il biglietto ma sei troppo basso.");
            }
        } else {
            System.out.println("Prima compra il biglietto.");
        }

        // ---------------------------------------------------------------
        // 6. OPERATORE TERNARIO:  condizione ? valoreSeVero : valoreSeFalso
        //    È un if/else "compatto" che PRODUCE un valore.
        // ---------------------------------------------------------------
        int numero = 7;
        String tipo = (numero % 2 == 0) ? "pari" : "dispari";
        System.out.println(numero + " è " + tipo);

        int x = 10, y = 25;
        int massimo = (x > y) ? x : y;
        System.out.println("Il massimo tra " + x + " e " + y + " è " + massimo);

        // ---------------------------------------------------------------
        // 7. SWITCH CLASSICO
        //    Utile quando confrontiamo UNA variabile con tanti valori precisi.
        //    Funziona con int, char, String, enum.
        // ---------------------------------------------------------------
        int giorno = 3;
        String nomeGiorno;

        switch (giorno) {
            case 1:
                nomeGiorno = "Lunedì";
                break; // <- IMPORTANTE: senza break "cade" nel case successivo!
            case 2:
                nomeGiorno = "Martedì";
                break;
            case 3:
                nomeGiorno = "Mercoledì";
                break;
            case 4:
                nomeGiorno = "Giovedì";
                break;
            case 5:
                nomeGiorno = "Venerdì";
                break;
            case 6:
            case 7:     // più case possono condividere lo stesso codice
                nomeGiorno = "Weekend";
                break;
            default:    // come l'else: se nessun case corrisponde
                nomeGiorno = "Giorno non valido";
        }
        System.out.println("Giorno " + giorno + ": " + nomeGiorno);

        // Esempio di "fall-through" (caduta) dimenticando il break:
        System.out.println("\nFall-through senza break:");
        int livello = 2;
        switch (livello) {
            case 1:
                System.out.println("  - accesso livello 1");
            case 2:
                System.out.println("  - accesso livello 2");
            case 3:
                System.out.println("  - accesso livello 3"); // stampato anche questo!
        }

        // ---------------------------------------------------------------
        // 8. SWITCH MODERNO (Java 14+) con la freccia ->
        //    Niente break, niente fall-through, e può restituire un valore.
        // ---------------------------------------------------------------
        String mese = "febbraio";

        int giorniNelMese = switch (mese) {
            case "febbraio" -> 28;
            case "aprile", "giugno", "settembre", "novembre" -> 30;
            default -> 31;
        };
        System.out.println("\n" + mese + " ha " + giorniNelMese + " giorni");

        char operatore = '*';
        int risultato = switch (operatore) {
            case '+' -> 6 + 3;
            case '-' -> 6 - 3;
            case '*' -> 6 * 3;
            case '/' -> 6 / 3;
            default -> {
                // se servono più istruzioni si usano le graffe e "yield"
                System.out.println("Operatore sconosciuto");
                yield 0;
            }
        };
        System.out.println("6 " + operatore + " 3 = " + risultato);
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Dato un anno, stabilisci se è bisestile. Regola: è bisestile se è
 *     divisibile per 4 ma non per 100, oppure se è divisibile per 400.
 *     (2024 sì, 1900 no, 2000 sì)
 *  2. Dati tre numeri, stampa il più grande (prima con if, poi con il
 *     ternario).
 *  3. Calcolatrice: dati due numeri e un operatore char, usa lo switch
 *     moderno per calcolare il risultato. Gestisci la divisione per zero!
 *  4. Dato il prezzo di un biglietto (10€) e l'età: sotto i 12 anni sconto
 *     50%, sopra i 65 sconto 30%, altrimenti prezzo pieno. Stampa il prezzo.
 */
