package lezione14_enum_record;

import java.util.List;

/*
 * =====================================================================
 *  LEZIONE 14 - ENUM e RECORD (due strumenti molto comodi)
 * =====================================================================
 *
 *  ENUM
 *  ----
 *  Un "enum" è un tipo che può assumere solo un insieme FISSO di valori.
 *  Esempi: giorni della settimana, semi delle carte, stati di un ordine.
 *
 *  Senza enum si userebbero stringhe o numeri ("LUNEDI", 1...) con il
 *  rischio di errori di battitura ("lunedi", "LUNEDÌ", 8...). Con un enum
 *  il compilatore accetta SOLO i valori previsti.
 *
 *  Gli enum in Java sono vere CLASSI: possono avere attributi,
 *  costruttori e metodi.
 *
 *  RECORD (Java 16+)
 *  ------
 *  Un "record" è una classe compatta per contenere DATI IMMUTABILI.
 *  Scrivendo una riga:
 *        record Punto(int x, int y) {}
 *  Java genera AUTOMATICAMENTE: attributi private final, costruttore,
 *  i metodi di accesso x() e y(), equals(), hashCode() e toString().
 *  Tutto quello che nella lezione 7 scrivevamo a mano!
 */
public class EnumERecord {

    // -----------------------------------------------------------------
    // ENUM SEMPLICE
    // -----------------------------------------------------------------
    enum Seme {
        CUORI, QUADRI, FIORI, PICCHE
    }

    // -----------------------------------------------------------------
    // ENUM CON ATTRIBUTI, COSTRUTTORE E METODI
    // -----------------------------------------------------------------
    enum Pianeta {
        MERCURIO(3.303e+23, 2.4397e6),
        TERRA(5.976e+24, 6.37814e6),
        GIOVE(1.9e+27, 7.1492e7);

        private final double massa;   // kg
        private final double raggio;  // metri

        // il costruttore di un enum è sempre privato
        Pianeta(double massa, double raggio) {
            this.massa = massa;
            this.raggio = raggio;
        }

        double gravitaSuperficiale() {
            final double G = 6.67300E-11;
            return G * massa / (raggio * raggio);
        }
    }

    enum StatoOrdine {
        CREATO, PAGATO, SPEDITO, CONSEGNATO;

        // Metodo che usa ordinal(): la posizione del valore (0, 1, 2...)
        StatoOrdine successivo() {
            StatoOrdine[] tutti = values();
            return ordinal() < tutti.length - 1 ? tutti[ordinal() + 1] : this;
        }
    }

    // -----------------------------------------------------------------
    // RECORD
    // -----------------------------------------------------------------
    record Punto(int x, int y) {

        // Possiamo aggiungere metodi...
        double distanzaDallOrigine() {
            return Math.sqrt(x * x + y * y);
        }

        // ...e metodi statici "fabbrica"
        static Punto origine() {
            return new Punto(0, 0);
        }
    }

    record Carta(int valore, Seme seme) {
        // Costruttore "compatto": serve a VALIDARE i dati
        Carta {
            if (valore < 1 || valore > 13) {
                throw new IllegalArgumentException("Valore non valido: " + valore);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Enum semplice ===");
        Seme mioSeme = Seme.CUORI;
        System.out.println("Seme scelto: " + mioSeme);
        System.out.println("Tutti i semi:");
        for (Seme s : Seme.values()) {
            System.out.println("  " + s.ordinal() + " -> " + s);
        }
        Seme daTesto = Seme.valueOf("PICCHE"); // da String a enum
        System.out.println("Da testo: " + daTesto);

        // Gli enum si confrontano con == e funzionano benissimo nello switch
        String colore = switch (mioSeme) {
            case CUORI, QUADRI -> "rosso";
            case FIORI, PICCHE -> "nero";
        };
        System.out.println(mioSeme + " è di colore " + colore);

        System.out.println("\n=== Enum con attributi ===");
        for (Pianeta p : Pianeta.values()) {
            System.out.printf("  Gravità su %-8s: %5.2f m/s²%n", p, p.gravitaSuperficiale());
        }

        StatoOrdine stato = StatoOrdine.CREATO;
        System.out.print("  Ordine: " + stato);
        while (stato != StatoOrdine.CONSEGNATO) {
            stato = stato.successivo();
            System.out.print(" -> " + stato);
        }
        System.out.println();

        System.out.println("\n=== Record ===");
        Punto a = new Punto(3, 4);
        Punto b = new Punto(3, 4);
        System.out.println("a = " + a);                              // toString automatico
        System.out.println("a.x() = " + a.x() + ", a.y() = " + a.y()); // accessori automatici
        System.out.println("Distanza dall'origine: " + a.distanzaDallOrigine());
        System.out.println("a.equals(b)? " + a.equals(b));           // equals automatico
        System.out.println("Origine: " + Punto.origine());
        // a.x = 10; // ERRORE: i record sono IMMUTABILI

        List<Carta> mano = List.of(
                new Carta(1, Seme.CUORI),
                new Carta(12, Seme.PICCHE),
                new Carta(7, Seme.QUADRI));
        System.out.println("Mano: " + mano);

        try {
            new Carta(20, Seme.FIORI);
        } catch (IllegalArgumentException e) {
            System.out.println("Carta rifiutata: " + e.getMessage());
        }
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Crea un enum  GiornoSettimana  con un metodo  isWeekend().
 *  2. Crea un enum  Taglia  (S, M, L, XL) con un attributo  prezzoExtra.
 *  3. Crea un record  Persona(String nome, int eta)  che rifiuti età
 *     negative, e un metodo  isMaggiorenne().
 *  4. Costruisci un mazzo di 52 carte (List<Carta>) con due cicli annidati,
 *     mescolalo con Collections.shuffle() e pescane 5.
 */
