package lezione10_interfacce;

/*
 * =====================================================================
 *  LEZIONE 10 - LE INTERFACCE (interface / implements)
 * =====================================================================
 *
 *  Ordine di lettura:
 *     Volante.java, Nuotatore.java   <- le interfacce
 *     Animale.java                   <- classe astratta di base
 *     Anatra.java, Pesce.java        <- animali che implementano interfacce
 *     Aereo.java                     <- NON è un animale, ma vola!
 *     Interfacce.java                <- il main
 *
 *  COS'È UN'INTERFACCIA?
 *  ---------------------
 *  Un'interfaccia è un CONTRATTO: un elenco di metodi che una classe
 *  PROMETTE di avere. Dice COSA si sa fare, non COME.
 *
 *  Esempio: "Volante" = "chiunque implementi questa interfaccia SA VOLARE".
 *  Un'anatra vola, un aereo vola, un drone vola... ma non hanno niente
 *  altro in comune! Non avrebbe senso farli discendere dalla stessa classe.
 *
 *  EREDITARIETÀ vs INTERFACCE
 *  --------------------------
 *    extends     -> relazione "È UN"      (un'Anatra È UN Animale)
 *    implements  -> relazione "SA FARE"   (un'Anatra SA volare e nuotare)
 *
 *    - Una classe può estendere UNA SOLA classe...
 *    - ...ma può implementare TANTE interfacce!
 *
 *       class Anatra extends Animale implements Volante, Nuotatore { ... }
 *
 *  COSA PUÒ CONTENERE UN'INTERFACCIA
 *   - Metodi astratti (impliciti: non serve scrivere "abstract" né "public")
 *   - Costanti (implicitamente public static final)
 *   - Metodi "default": con un'implementazione predefinita (Java 8+)
 *   - Metodi "static": di utilità, si chiamano con NomeInterfaccia.metodo()
 *   - NON può avere attributi di istanza né costruttori.
 *   - NON si può istanziare:  new Volante()  -> ERRORE
 *
 *  CLASSE ASTRATTA vs INTERFACCIA: quando usare cosa?
 *   - Classe astratta: quando le classi figlie SONO la stessa "famiglia" e
 *     condividono STATO (attributi) e codice.
 *   - Interfaccia: quando vuoi descrivere una CAPACITÀ che classi anche
 *     molto diverse possono avere.
 */
public interface Volante {

    // Costante: implicitamente public static final
    int ALTITUDINE_MAX_TERRESTRE = 12_000;

    // Metodi astratti: implicitamente public abstract
    void decolla();

    int getAltitudineMassima();

    // Metodo DEFAULT: ha già un corpo. Chi implementa l'interfaccia lo
    // eredita, ma può anche ridefinirlo (override).
    default void atterra() {
        String chi = getClass().getSimpleName();
        if (chi.isEmpty()) {
            // le classi anonime (vedi Interfacce.java, punto 5) non hanno un nome!
            chi = "Oggetto volante anonimo";
        }
        System.out.println("  " + chi + " atterra dolcemente.");
    }

    // Metodo STATICO: appartiene all'interfaccia, si chiama Volante.confronta(...)
    static Volante chiVolaPiuInAlto(Volante a, Volante b) {
        return a.getAltitudineMassima() >= b.getAltitudineMassima() ? a : b;
    }
}
