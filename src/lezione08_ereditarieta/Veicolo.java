package lezione08_ereditarieta;

/*
 * =====================================================================
 *  LEZIONE 8 - EREDITARIETÀ (extends) e OVERRIDING
 * =====================================================================
 *
 *  Ordine di lettura:
 *     1) Veicolo.java         <- la SUPERCLASSE (classe "genitore")
 *     2) Automobile.java      <- SOTTOCLASSE: extends Veicolo
 *     3) Moto.java            <- altra SOTTOCLASSE
 *     4) AutoSportiva.java    <- sottoclasse di una sottoclasse
 *     5) Ereditarieta.java    <- il main
 *
 *  COS'È L'EREDITARIETÀ?
 *  ---------------------
 *  Spesso classi diverse hanno molte cose IN COMUNE. Un'auto e una moto
 *  hanno entrambe marca, modello, velocità, possono accelerare e frenare...
 *  Invece di riscrivere tutto due volte, mettiamo le parti comuni in una
 *  classe "genitore" (SUPERCLASSE) e le classi "figlie" (SOTTOCLASSI) le
 *  EREDITANO, aggiungendo o modificando solo ciò che è specifico.
 *
 *                     Veicolo
 *                   /         \
 *            Automobile       Moto
 *                 |
 *            AutoSportiva
 *
 *  La relazione è di tipo "È UN" (IS-A):
 *     un'Automobile È UN Veicolo   -> ha senso: usa extends
 *     una Moto È UN Veicolo        -> ha senso
 *     un Motore È UN Veicolo?      -> NO! (l'auto HA UN motore: si usa un attributo)
 *
 *  REGOLE
 *   - In Java si può estendere UNA SOLA classe (ereditarietà singola).
 *   - La sottoclasse eredita attributi e metodi (non private-accessibili)
 *     e NON eredita i costruttori.
 *   - Tutte le classi, se non estendono nulla, estendono implicitamente
 *     java.lang.Object (da cui ereditano toString(), equals(), ecc.).
 */
public class Veicolo {

    // protected: visibile in questa classe, nel package e nelle SOTTOCLASSI.
    // Così le classi figlie possono usarli direttamente.
    protected String marca;
    protected String modello;
    protected int velocita;      // velocità attuale in km/h

    // private: nemmeno le sottoclassi possono accedervi direttamente
    private final int annoImmatricolazione;

    public Veicolo(String marca, String modello, int anno) {
        this.marca = marca;
        this.modello = modello;
        this.annoImmatricolazione = anno;
        this.velocita = 0;
        System.out.println("  (costruttore di Veicolo eseguito per " + marca + ")");
    }

    public void accelera(int incremento) {
        velocita += incremento;
        if (velocita > getVelocitaMassima()) {
            velocita = getVelocitaMassima();
        }
    }

    public void frena() {
        velocita = Math.max(0, velocita - 20);
    }

    // Le sottoclassi RIDEFINIRANNO questo metodo (overriding)
    public int getVelocitaMassima() {
        return 100;
    }

    public String suonaClacson() {
        return "Beep generico";
    }

    // Metodo "final": le sottoclassi NON possono ridefinirlo.
    public final int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public int getVelocita() {
        return velocita;
    }

    public String descrizione() {
        return marca + " " + modello + " (" + annoImmatricolazione + ")";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + descrizione()
                + " - va a " + velocita + " km/h";
    }
}
