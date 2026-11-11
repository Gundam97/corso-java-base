package lezione09_astratte;

/*
 * =====================================================================
 *  LEZIONE 9 - CLASSI ASTRATTE: il main
 * =====================================================================
 */
public class ClassiAstratte {

    public static void main(String[] args) {

        // Forma f = new Forma("boh", "rosso");
        // ^ ERRORE: Forma is abstract; cannot be instantiated

        // Però possiamo usare Forma come TIPO di una variabile o di un array!
        Forma[] forme = {
                new Cerchio("rosso", 2),
                new Rettangolo("blu", 3, 5),
                new Quadrato("verde", 4),
                new Triangolo("giallo", 3, 4, 5)
        };

        System.out.println("=== Elenco forme ===");
        for (Forma f : forme) {
            f.stampaInfo(); // metodo concreto di Forma che chiama i metodi astratti
        }

        // Calcolo dell'area totale senza sapere che forme ci sono
        double areaTotale = 0;
        for (Forma f : forme) {
            areaTotale += f.getArea();
        }
        System.out.printf("%nArea totale: %.2f%n", areaTotale);

        // Trovare la forma più grande
        Forma piuGrande = forme[0];
        for (Forma f : forme) {
            if (f.isPiuGrandeDi(piuGrande)) {
                piuGrande = f;
            }
        }
        System.out.println("La forma più grande è: " + piuGrande);

        // Un triangolo impossibile: il costruttore lancia un'eccezione
        try {
            new Triangolo("nero", 1, 2, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErrore previsto: " + e.getMessage());
        }
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Aggiungi la forma  Rombo  (diagonali d1, d2 e lato).
 *  2. Aggiungi a Forma un metodo astratto  String disegnaAscii()  e
 *     implementalo almeno per Quadrato (stampa un quadrato di '#').
 *  3. Crea una classe astratta  Animale  con metodo astratto  verso()  e
 *     metodo concreto  presentati()  che stampa "Sono X e faccio: " + verso().
 *     Crea Cane, Gatto, Mucca.
 *  4. Domanda: una classe astratta può non avere NESSUN metodo astratto?
 *     (Prova!) E una classe NON astratta può avere un metodo astratto?
 */
