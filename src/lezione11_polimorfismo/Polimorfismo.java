package lezione11_polimorfismo;

import java.util.Objects;

/*
 * =====================================================================
 *  LEZIONE 11 - POLIMORFISMO (mettiamo tutto insieme)
 * =====================================================================
 *
 *  "Polimorfismo" = "molte forme". È la capacità di trattare oggetti di
 *  classi diverse in modo UNIFORME, attraverso un tipo comune (una
 *  superclasse o un'interfaccia), lasciando che OGNI OGGETTO si comporti
 *  a modo suo.
 *
 *  L'abbiamo già intravisto nelle lezioni 8, 9 e 10. Qui lo rendiamo
 *  esplicito, con un esempio di GESTIONE DEL PERSONALE:
 *
 *               <<interface>> Pagabile
 *                       ^
 *                       | implements
 *              (abstract) Dipendente
 *              /         |          \
 *        Impiegato    Manager     Stagista
 *
 *  CONCETTI CHIAVE
 *  ---------------
 *  1) UPCASTING (automatico): un oggetto della sottoclasse può essere
 *     assegnato a una variabile della superclasse.
 *         Dipendente d = new Manager(...);    // ok, un Manager È UN Dipendente
 *
 *  2) BINDING DINAMICO: quando chiamo d.calcolaStipendio(), Java esegue la
 *     versione dell'OGGETTO REALE (Manager), non quella del tipo della
 *     variabile (Dipendente). Deciso a TEMPO DI ESECUZIONE.
 *
 *  3) Il TIPO DELLA VARIABILE decide quali metodi POSSO CHIAMARE.
 *     Il TIPO DELL'OGGETTO decide quale versione VIENE ESEGUITA.
 *
 *  4) DOWNCASTING (esplicito): per usare i metodi specifici della
 *     sottoclasse devo "riconvertire" la variabile, dopo aver controllato
 *     con instanceof.
 *
 *  NOTA: in questo file ci sono più classi. Solo una può essere public
 *  (quella col nome del file). In un progetto reale andrebbero in file
 *  separati: qui sono insieme per leggerle tutte in un colpo d'occhio.
 */

// ---------------------------------------------------------------------
// INTERFACCIA: tutto ciò che può essere pagato
// ---------------------------------------------------------------------
interface Pagabile {
    double calcolaImporto();
}

// ---------------------------------------------------------------------
// CLASSE ASTRATTA: la base comune di tutti i dipendenti
// ---------------------------------------------------------------------
abstract class Dipendente implements Pagabile {

    private final String nome;
    private final String codiceFiscale;
    protected final double stipendioBase;

    protected Dipendente(String nome, String codiceFiscale, double stipendioBase) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.stipendioBase = stipendioBase;
    }

    // Ogni tipo di dipendente calcola lo stipendio a modo suo
    public abstract double calcolaStipendio();

    public abstract String getRuolo();

    // Implementazione dell'interfaccia Pagabile, valida per tutti
    @Override
    public double calcolaImporto() {
        return calcolaStipendio();
    }

    public String getNome() {
        return nome;
    }

    // ---------------------------------------------------------------
    // OVERRIDING dei metodi di Object: equals() e hashCode()
    // Per default equals() si comporta come == (stesso oggetto).
    // Lo ridefiniamo: due dipendenti sono "uguali" se hanno lo stesso
    // codice fiscale. Regola: se ridefinisci equals, ridefinisci anche
    // hashCode (servirà con HashMap e HashSet, lezione 13).
    // ---------------------------------------------------------------
    @Override
    public boolean equals(Object altro) {
        if (this == altro) return true;
        if (!(altro instanceof Dipendente)) return false;
        Dipendente d = (Dipendente) altro;
        return codiceFiscale.equals(d.codiceFiscale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceFiscale);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-9s %9.2f €", getRuolo(), nome, calcolaStipendio());
    }
}

// ---------------------------------------------------------------------
// SOTTOCLASSI CONCRETE
// ---------------------------------------------------------------------
class Impiegato extends Dipendente {

    private final int oreStraordinario;

    Impiegato(String nome, String cf, double base, int oreStraordinario) {
        super(nome, cf, base);
        this.oreStraordinario = oreStraordinario;
    }

    @Override
    public double calcolaStipendio() {
        return stipendioBase + oreStraordinario * 20;
    }

    @Override
    public String getRuolo() {
        return "Impiegato";
    }
}

class Manager extends Dipendente {

    private final double bonus;
    private final int teamSize;

    Manager(String nome, String cf, double base, double bonus, int teamSize) {
        super(nome, cf, base);
        this.bonus = bonus;
        this.teamSize = teamSize;
    }

    @Override
    public double calcolaStipendio() {
        return stipendioBase + bonus + teamSize * 50;
    }

    @Override
    public String getRuolo() {
        return "Manager";
    }

    // Metodo SPECIFICO del Manager
    public void organizzaRiunione() {
        System.out.println("  " + getNome() + " convoca una riunione con "
                + teamSize + " persone.");
    }
}

class Stagista extends Dipendente {

    Stagista(String nome, String cf) {
        super(nome, cf, 600); // rimborso spese fisso
    }

    @Override
    public double calcolaStipendio() {
        return stipendioBase;
    }

    @Override
    public String getRuolo() {
        return "Stagista";
    }
}

// Una classe che NON è un dipendente ma è comunque Pagabile
class Fattura implements Pagabile {
    private final String fornitore;
    private final double importo;

    Fattura(String fornitore, double importo) {
        this.fornitore = fornitore;
        this.importo = importo;
    }

    @Override
    public double calcolaImporto() {
        return importo * 1.22; // IVA inclusa
    }

    @Override
    public String toString() {
        return String.format("Fattura    %-9s %9.2f €", fornitore, calcolaImporto());
    }
}

// =====================================================================
// IL MAIN
// =====================================================================
public class Polimorfismo {

    // Overloading: due metodi con lo stesso nome e parametri diversi
    static void descrivi(Dipendente d) {
        System.out.println("  -> descrivi(Dipendente) chiamato");
    }

    static void descrivi(Manager m) {
        System.out.println("  -> descrivi(Manager) chiamato");
    }

    public static void main(String[] args) {

        // -------------------------------------------------------------
        // 1. UPCASTING + BINDING DINAMICO
        //    Un array di Dipendente contiene oggetti di classi diverse.
        //    Lo stesso codice  d.calcolaStipendio()  produce risultati
        //    diversi a seconda dell'oggetto: QUESTO è il polimorfismo.
        // -------------------------------------------------------------
        Dipendente[] azienda = {
                new Impiegato("Anna", "ANNCF01", 1600, 10),
                new Manager("Bruno", "BRNCF02", 2800, 500, 6),
                new Stagista("Carla", "CRLCF03"),
                new Impiegato("Dario", "DRICF04", 1550, 0)
        };

        System.out.println("=== Busta paga ===");
        double totale = 0;
        for (Dipendente d : azienda) {
            System.out.println("  " + d);       // toString() polimorfico
            totale += d.calcolaStipendio();     // calcolaStipendio() polimorfico
        }
        System.out.printf("  TOTALE STIPENDI: %.2f €%n", totale);

        // Il vantaggio: se domani aggiungiamo la classe "Consulente",
        // questo ciclo NON va modificato. Basta che estenda Dipendente.

        // -------------------------------------------------------------
        // 2. POLIMORFISMO CON INTERFACCE
        //    Dipendenti e fatture insieme: hanno in comune solo Pagabile.
        // -------------------------------------------------------------
        System.out.println("\n=== Tutte le uscite (Pagabile) ===");
        Pagabile[] uscite = {azienda[0], azienda[1], new Fattura("Enel", 300)};
        double totaleUscite = 0;
        for (Pagabile p : uscite) {
            System.out.println("  " + p);
            totaleUscite += p.calcolaImporto();
        }
        System.out.printf("  TOTALE USCITE: %.2f €%n", totaleUscite);

        // -------------------------------------------------------------
        // 3. TIPO DELLA VARIABILE vs TIPO DELL'OGGETTO
        // -------------------------------------------------------------
        System.out.println("\n=== Tipo variabile vs tipo oggetto ===");
        Dipendente d = new Manager("Elena", "ELNCF05", 3000, 1000, 4);

        // d.organizzaRiunione();
        // ^ ERRORE: il compilatore guarda il tipo della VARIABILE (Dipendente)
        //   e Dipendente non ha organizzaRiunione().

        System.out.println("  Stipendio (versione Manager eseguita): " + d.calcolaStipendio());

        // -------------------------------------------------------------
        // 4. DOWNCASTING sicuro con instanceof
        // -------------------------------------------------------------
        System.out.println("\n=== Downcasting ===");
        for (Dipendente dip : azienda) {
            // forma classica
            if (dip instanceof Manager) {
                Manager m = (Manager) dip;  // cast esplicito
                m.organizzaRiunione();
            }
        }
        // forma moderna (Java 16+): "pattern matching" - test e cast in uno
        if (d instanceof Manager m) {
            m.organizzaRiunione();
        }

        // Un cast SBAGLIATO compila ma esplode a runtime:
        try {
            Dipendente s = azienda[2];       // è uno Stagista
            Manager sbagliato = (Manager) s; // ClassCastException!
            sbagliato.organizzaRiunione();
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException: uno Stagista non è un Manager!");
        }

        // -------------------------------------------------------------
        // 5. OVERLOADING (compilazione) vs OVERRIDING (esecuzione)
        //    Il punto più sottile di tutto il corso!
        // -------------------------------------------------------------
        System.out.println("\n=== Overloading vs Overriding ===");
        Dipendente comeDipendente = new Manager("Fabio", "FBICF06", 2500, 0, 2);
        Manager comeManager = (Manager) comeDipendente; // stesso oggetto!

        // OVERRIDING: conta l'OGGETTO -> sempre la versione di Manager
        System.out.println("  getRuolo() via Dipendente: " + comeDipendente.getRuolo());
        System.out.println("  getRuolo() via Manager:    " + comeManager.getRuolo());

        // OVERLOADING: conta il TIPO DELLA VARIABILE, deciso in compilazione
        System.out.print("  descrivi(comeDipendente)");
        descrivi(comeDipendente); // -> versione Dipendente! (anche se è un Manager)
        System.out.print("  descrivi(comeManager)   ");
        descrivi(comeManager);    // -> versione Manager

        // -------------------------------------------------------------
        // 6. equals() ridefinito
        // -------------------------------------------------------------
        System.out.println("\n=== equals ===");
        Dipendente x = new Stagista("Gino", "GNICF07");
        Dipendente y = new Stagista("Gino (duplicato)", "GNICF07");
        System.out.println("  x == y      ? " + (x == y));        // false: oggetti diversi
        System.out.println("  x.equals(y) ? " + x.equals(y));     // true: stesso codice fiscale
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Aggiungi la classe  Consulente extends Dipendente  pagato a ore
 *     (tariffa * ore). Verifica che il ciclo della busta paga funzioni
 *     SENZA modificarlo.
 *  2. Scrivi un metodo  static Dipendente piuPagato(Dipendente[] lista).
 *  3. Conta quanti Manager ci sono nell'azienda usando instanceof.
 *  4. Spiega a parole, con un esempio tuo, perché nel punto 5 la chiamata
 *     descrivi(comeDipendente) sceglie la versione "Dipendente".
 */
