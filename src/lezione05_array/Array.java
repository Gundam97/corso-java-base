package lezione05_array;

import java.util.Arrays;

/*
 * =====================================================================
 *  LEZIONE 5 - GLI ARRAY
 * =====================================================================
 *
 *  Un ARRAY è una sequenza di valori DELLO STESSO TIPO, con una
 *  DIMENSIONE FISSA decisa al momento della creazione.
 *
 *  Pensalo come una fila di cassetti numerati:
 *
 *     indice:   [0]   [1]   [2]   [3]   [4]
 *     valore:    12    7     45    3     28
 *
 *  REGOLE IMPORTANTI
 *   - Gli indici partono da 0 (ZERO!), non da 1.
 *   - L'ultimo indice è  lunghezza - 1.
 *   - La lunghezza si legge con  nomeArray.length  (senza parentesi).
 *   - Accedere a un indice che non esiste causa un errore a runtime:
 *     ArrayIndexOutOfBoundsException.
 *   - Una volta creato, un array NON può cambiare dimensione
 *     (per questo nella lezione 13 vedremo ArrayList).
 */
public class Array {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1. CREARE UN ARRAY
        // ---------------------------------------------------------------
        // Modo A: specifico la dimensione; gli elementi hanno un valore
        // di default (0 per i numeri, false per boolean, null per oggetti)
        int[] voti = new int[5];
        voti[0] = 28;
        voti[1] = 24;
        voti[2] = 30;
        voti[3] = 19;
        voti[4] = 26;

        // Modo B: specifico direttamente i valori
        String[] giorni = {"Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom"};

        System.out.println("Primo voto: " + voti[0]);
        System.out.println("Ultimo giorno: " + giorni[giorni.length - 1]);
        System.out.println("Numero di giorni: " + giorni.length);

        // Modificare un elemento
        voti[3] = 21;

        // Stampare un array "al volo"
        System.out.println("Array voti: " + Arrays.toString(voti));
        // NB: System.out.println(voti) stamperebbe qualcosa come [I@1b6d3586
        //     cioè l'indirizzo in memoria, non il contenuto!

        // ---------------------------------------------------------------
        // 2. SCORRERE UN ARRAY CON IL FOR CLASSICO
        //    Utile quando ci serve l'INDICE.
        // ---------------------------------------------------------------
        System.out.println("\n--- for classico ---");
        for (int i = 0; i < giorni.length; i++) {
            System.out.println("Giorno " + (i + 1) + ": " + giorni[i]);
        }

        // ---------------------------------------------------------------
        // 3. SCORRERE CON IL FOR-EACH
        //    for (Tipo elemento : array) { ... }
        //    Si legge: "per ogni elemento dentro array".
        //    Più semplice e leggibile, ma non dà l'indice e non permette di
        //    modificare l'array.
        // ---------------------------------------------------------------
        System.out.println("\n--- for-each ---");
        int somma = 0;
        for (int voto : voti) {
            somma += voto;
        }
        double media = (double) somma / voti.length;
        System.out.printf("Somma: %d, Media: %.2f%n", somma, media);

        // ---------------------------------------------------------------
        // 4. ALGORITMI CLASSICI SUGLI ARRAY
        // ---------------------------------------------------------------
        int[] numeri = {42, 7, 19, 88, 3, 56, 21};

        // a) Trovare il massimo e il minimo
        int max = numeri[0];
        int min = numeri[0];
        for (int n : numeri) {
            if (n > max) max = n;
            if (n < min) min = n;
        }
        System.out.println("\nMassimo: " + max + ", Minimo: " + min);

        // b) Cercare un elemento (ricerca lineare)
        int daCercare = 56;
        int posizione = -1; // -1 = "non trovato" (convenzione comune)
        for (int i = 0; i < numeri.length; i++) {
            if (numeri[i] == daCercare) {
                posizione = i;
                break;
            }
        }
        System.out.println(daCercare + " trovato in posizione: " + posizione);

        // c) Contare gli elementi che rispettano una condizione
        int pari = 0;
        for (int n : numeri) {
            if (n % 2 == 0) pari++;
        }
        System.out.println("Numeri pari: " + pari);

        // d) Invertire un array (scambio di elementi)
        int[] daInvertire = {1, 2, 3, 4, 5};
        for (int i = 0; i < daInvertire.length / 2; i++) {
            int temp = daInvertire[i];                          // salvo
            daInvertire[i] = daInvertire[daInvertire.length - 1 - i];
            daInvertire[daInvertire.length - 1 - i] = temp;     // scambio
        }
        System.out.println("Invertito: " + Arrays.toString(daInvertire));

        // e) Ordinamento "a mano": BUBBLE SORT
        //    Confronta coppie vicine e le scambia se sono nell'ordine
        //    sbagliato. Ripete finché tutto è ordinato.
        int[] disordinato = {5, 2, 9, 1, 7};
        for (int i = 0; i < disordinato.length - 1; i++) {
            for (int j = 0; j < disordinato.length - 1 - i; j++) {
                if (disordinato[j] > disordinato[j + 1]) {
                    int temp = disordinato[j];
                    disordinato[j] = disordinato[j + 1];
                    disordinato[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubble sort: " + Arrays.toString(disordinato));

        // f) ...oppure usando la libreria standard (nella realtà si fa così)
        int[] copia = Arrays.copyOf(numeri, numeri.length); // copia l'array
        Arrays.sort(copia);
        System.out.println("Arrays.sort: " + Arrays.toString(copia));
        System.out.println("Originale invariato: " + Arrays.toString(numeri));

        // ---------------------------------------------------------------
        // 5. ATTENZIONE: gli array sono "RIFERIMENTI"
        //    Assegnare un array a un'altra variabile NON lo copia: entrambe
        //    le variabili puntano allo STESSO array in memoria.
        // ---------------------------------------------------------------
        int[] originale = {1, 2, 3};
        int[] alias = originale;   // NON è una copia!
        alias[0] = 999;
        System.out.println("\noriginale dopo aver modificato alias: "
                + Arrays.toString(originale)); // [999, 2, 3] !!

        // ---------------------------------------------------------------
        // 6. ARRAY BIDIMENSIONALI (matrici)
        //    Un array di array: pensalo come una tabella righe x colonne.
        // ---------------------------------------------------------------
        System.out.println("\n--- Matrice ---");
        int[][] matrice = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Elemento riga 1, colonna 2: " + matrice[1][2]); // 6

        for (int r = 0; r < matrice.length; r++) {          // righe
            for (int c = 0; c < matrice[r].length; c++) {   // colonne
                System.out.print(matrice[r][c] + " ");
            }
            System.out.println();
        }

        // Esempio: griglia del tris
        char[][] tris = new char[3][3];
        for (char[] riga : tris) {
            Arrays.fill(riga, '.');   // riempie tutta la riga con '.'
        }
        tris[0][0] = 'X';
        tris[1][1] = 'O';
        tris[2][2] = 'X';
        System.out.println("\nTris:");
        for (char[] riga : tris) {
            System.out.println(new String(riga));
        }

        // ---------------------------------------------------------------
        // 7. L'ERRORE PIÙ FAMOSO
        // ---------------------------------------------------------------
        // int[] piccolo = new int[3];
        // piccolo[3] = 10;  // ArrayIndexOutOfBoundsException: gli indici vanno da 0 a 2!
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Dato un array di temperature settimanali, stampa la media e i giorni
 *     in cui la temperatura ha superato la media.
 *  2. Conta quante volte compare la lettera 'a' in un array di char
 *     (suggerimento: "banana".toCharArray() crea un array di char).
 *  3. Verifica se un array è palindromo (es. {1,2,3,2,1}).
 *  4. Unisci due array in un terzo array più grande.
 *  5. Data una matrice 3x3, calcola la somma della diagonale principale.
 */
