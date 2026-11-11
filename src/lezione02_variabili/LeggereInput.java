package lezione02_variabili;

// Per usare classi che stanno in altri package serve "import".
// Scanner si trova nel package java.util della libreria standard di Java.
import java.util.Scanner;

/*
 * =====================================================================
 *  LEZIONE 2 (bis) - LEGGERE DATI DALLA TASTIERA
 * =====================================================================
 *
 *  Finora i valori erano "scritti nel codice". Con la classe Scanner
 *  possiamo chiedere all'utente di inserirli mentre il programma gira.
 *
 *  NOTA: questo programma ASPETTA che tu scriva qualcosa e prema INVIO.
 */
public class LeggereInput {

    public static void main(String[] args) {

        // Creiamo uno Scanner che legge da System.in (la tastiera).
        // "new" crea un nuovo OGGETTO: lo vedremo bene nella lezione 7.
        Scanner tastiera = new Scanner(System.in);

        System.out.print("Come ti chiami? ");
        String nome = tastiera.nextLine();      // legge una riga intera di testo

        System.out.print("Quanti anni hai? ");
        int eta = tastiera.nextInt();           // legge un numero intero

        System.out.print("Quanto sei alto (in metri, es. 1,75)? ");
        double altezza = tastiera.nextDouble(); // legge un numero decimale
        // NB: a seconda della lingua del sistema il separatore decimale può
        // essere la virgola (1,75) oppure il punto (1.75).

        System.out.println();
        System.out.println("Ciao " + nome + "!");
        System.out.println("Fra 10 anni avrai " + (eta + 10) + " anni.");
        System.out.printf("Sei alto %.0f centimetri.%n", altezza * 100);

        // Lo Scanner va chiuso quando non serve più, per liberare le risorse.
        tastiera.close();
    }
}

/*
 *  TRAPPOLA CLASSICA: dopo nextInt() o nextDouble() nel buffer resta
 *  l'INVIO premuto dall'utente. Se subito dopo chiami nextLine(), questo
 *  legge quella riga vuota e sembra "saltato"! Soluzione: chiamare un
 *  nextLine() a vuoto dopo nextInt(), per consumare l'invio.
 *
 *  ESERCIZIO: chiedi all'utente due numeri e stampa somma, differenza,
 *  prodotto e quoziente (con i decimali!).
 */
