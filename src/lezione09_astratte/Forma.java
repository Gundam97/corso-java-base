package lezione09_astratte;

/*
 * =====================================================================
 *  LEZIONE 9 - CLASSI ASTRATTE (abstract)
 * =====================================================================
 *
 *  Ordine di lettura: Forma.java -> Cerchio.java -> Rettangolo.java
 *                     -> Triangolo.java -> ClassiAstratte.java
 *
 *  IL PROBLEMA
 *  -----------
 *  Vogliamo rappresentare forme geometriche. Ogni forma ha un'area e un
 *  perimetro... ma come si calcola l'area di una "forma" generica?
 *  Non si può! Ha senso solo per un Cerchio, un Rettangolo, ecc.
 *  E non ha nemmeno senso creare un oggetto "Forma" generico.
 *
 *  LA SOLUZIONE: CLASSE ASTRATTA
 *  -----------------------------
 *  - Una classe "abstract" NON PUÒ ESSERE ISTANZIATA:  new Forma(...) -> ERRORE
 *  - Serve SOLO come base da estendere.
 *  - Può contenere METODI ASTRATTI: dichiarati ma SENZA CORPO.
 *       public abstract double getArea();     <- niente { }, solo ;
 *  - Ogni sottoclasse CONCRETA (non astratta) è OBBLIGATA a implementare
 *    tutti i metodi astratti. È un "contratto".
 *  - Può contenere anche attributi, costruttori e metodi normali
 *    (concreti), che le sottoclassi ereditano come al solito.
 *
 *  Pensa a una classe astratta come a un modulo "precompilato a metà":
 *  alcune parti sono già scritte, altre DEVONO essere completate da chi
 *  la estende.
 */
public abstract class Forma {

    // Attributi comuni a tutte le forme
    protected final String nome;
    protected final String colore;

    // Una classe astratta PUÒ avere un costruttore: verrà chiamato dalle
    // sottoclassi tramite super(...)
    protected Forma(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    // ---- METODI ASTRATTI: le sottoclassi DEVONO implementarli ----
    public abstract double getArea();

    public abstract double getPerimetro();

    // ---- METODI CONCRETI: già pronti, ereditati da tutte ----
    // Nota come questo metodo USA getArea() e getPerimetro() anche se qui
    // non sono implementati: al momento dell'esecuzione verrà chiamata la
    // versione della sottoclasse reale. (Questo schema si chiama
    // "Template Method".)
    public void stampaInfo() {
        System.out.printf("%-12s %-7s area = %8.2f   perimetro = %7.2f%n",
                nome, colore, getArea(), getPerimetro());
    }

    public boolean isPiuGrandeDi(Forma altra) {
        return this.getArea() > altra.getArea();
    }

    @Override
    public String toString() {
        return nome + " " + colore;
    }
}
