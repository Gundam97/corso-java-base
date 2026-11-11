package lezione08_ereditarieta;

/*
 *  Automobile EXTENDS Veicolo
 *  --------------------------
 *  Automobile eredita da Veicolo: marca, modello, velocita, accelera(),
 *  frena(), descrizione(), ecc. senza doverli riscrivere.
 *
 *  In più può:
 *    - AGGIUNGERE nuovi attributi e metodi  (numeroPorte, apriBagagliaio)
 *    - RIDEFINIRE (OVERRIDE) metodi ereditati per cambiarne il comportamento
 *
 *  =================================================================
 *  OVERRIDING (RIDEFINIZIONE)
 *  =================================================================
 *  Una sottoclasse riscrive un metodo della superclasse con la STESSA
 *  FIRMA (stesso nome, stessi parametri, stesso tipo di ritorno o un
 *  sottotipo). Regole:
 *    - la visibilità non può essere ridotta (public resta public)
 *    - non si possono ridefinire metodi final, private o static
 *    - l'annotazione @Override non è obbligatoria ma VA SEMPRE MESSA:
 *      se sbagli la firma, il compilatore ti avvisa.
 *
 *  OVERLOADING vs OVERRIDING  (domanda classica!)
 *  ------------------------------------------------------------------
 *                   | OVERLOADING               | OVERRIDING
 *  -----------------+---------------------------+---------------------------
 *  Dove             | stessa classe (o figlie)  | sottoclasse
 *  Nome             | uguale                    | uguale
 *  Parametri        | DIVERSI                   | UGUALI
 *  Scopo            | stessa operazione su      | cambiare il comportamento
 *                   | input diversi             | ereditato
 *  Scelto quando    | compilazione (statico)    | esecuzione (dinamico)
 *  ------------------------------------------------------------------
 */
public class Automobile extends Veicolo {

    private final int numeroPorte;
    private boolean bagagliaioAperto;

    public Automobile(String marca, String modello, int anno, int numeroPorte) {
        // super(...) chiama il COSTRUTTORE DELLA SUPERCLASSE.
        // Deve essere la PRIMA istruzione. Se non lo scriviamo, Java prova
        // a chiamare super() senza argomenti (che qui non esiste -> errore).
        super(marca, modello, anno);
        this.numeroPorte = numeroPorte;
        System.out.println("  (costruttore di Automobile eseguito)");
    }

    // ----- OVERRIDING: ridefiniamo il comportamento ereditato -----
    @Override
    public int getVelocitaMassima() {
        return 180;
    }

    @Override
    public String suonaClacson() {
        return "Peee peee!";
    }

    // Override che RIUSA il metodo del padre con super.metodo()
    @Override
    public String descrizione() {
        return super.descrizione() + ", " + numeroPorte + " porte";
    }

    // ----- NUOVI metodi, specifici dell'automobile -----
    public void apriBagagliaio() {
        if (velocita > 0) { // possiamo usare "velocita" perché è protected
            System.out.println("  Fermati prima di aprire il bagagliaio!");
            return;
        }
        bagagliaioAperto = true;
        System.out.println("  Bagagliaio aperto.");
    }

    public boolean isBagagliaioAperto() {
        return bagagliaioAperto;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    // ----- OVERLOADING all'interno della sottoclasse -----
    // Stesso nome di accelera(int) ereditato, ma parametri DIVERSI.
    // Questo NON è overriding: è un nuovo metodo che si aggiunge.
    public void accelera(int incremento, boolean turbo) {
        accelera(turbo ? incremento * 2 : incremento);
    }

    // @Override
    // public int getAnnoImmatricolazione() { return 0; }
    // ^ ERRORE: il metodo è final nella superclasse, non si può ridefinire.
}
