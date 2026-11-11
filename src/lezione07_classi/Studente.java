package lezione07_classi;

/*
 * =====================================================================
 *  LEZIONE 7 - CLASSI E OGGETTI  (file 1 di 3: la classe Studente)
 * =====================================================================
 *
 *  Leggi i file in quest'ordine:
 *     1) Studente.java        <- sei qui: com'è fatta una classe
 *     2) ContoCorrente.java   <- incapsulamento in un caso concreto
 *     3) ClassiEOggetti.java  <- il main che crea e usa gli oggetti
 *
 *  CLASSE vs OGGETTO
 *  -----------------
 *  - Una CLASSE è un PROGETTO, uno stampo. Descrive COME sarà fatto
 *    qualcosa: quali dati contiene e cosa sa fare.
 *  - Un OGGETTO (o ISTANZA) è una cosa CONCRETA costruita a partire dal
 *    progetto. Da una classe si possono creare infiniti oggetti.
 *
 *    Esempio: "Studente" è la classe (il modulo di iscrizione vuoto).
 *             Mario, 20 anni, matricola 1234 è un oggetto (il modulo compilato).
 *
 *  Una classe contiene:
 *   - ATTRIBUTI (o campi): i DATI, lo "stato" dell'oggetto  -> nome, età...
 *   - COSTRUTTORI: codice speciale che PREPARA un nuovo oggetto
 *   - METODI: i COMPORTAMENTI, cosa l'oggetto sa fare     -> presentati()...
 *
 *  INCAPSULAMENTO
 *  --------------
 *  Gli attributi si dichiarano quasi sempre "private": nessuno dall'esterno
 *  può toccarli direttamente. Per leggerli/modificarli si passa da metodi
 *  pubblici (GETTER e SETTER) che possono CONTROLLARE i valori.
 *  È come un bancomat: non metti le mani nella cassaforte, usi i pulsanti.
 *
 *  MODIFICATORI DI ACCESSO
 *    private   -> visibile solo dentro questa classe
 *    (nessuno) -> visibile nello stesso package ("package-private")
 *    protected -> come sopra + sottoclassi (lezione 8)
 *    public    -> visibile da tutti
 */
public class Studente {

    // ---------------------------------------------------------------
    // ATTRIBUTI DI ISTANZA: ogni oggetto Studente ha i PROPRI valori
    // ---------------------------------------------------------------
    private String nome;
    private String cognome;
    private int eta;
    private final int matricola; // final: assegnata una volta, poi non cambia più
    private double[] voti = new double[0];

    // ---------------------------------------------------------------
    // ATTRIBUTO STATICO: appartiene alla CLASSE, è CONDIVISO da tutti
    // gli oggetti. Ne esiste una sola copia.
    // ---------------------------------------------------------------
    private static int prossimaMatricola = 1000;
    public static final String UNIVERSITA = "Università degli Studi"; // costante

    // ---------------------------------------------------------------
    // COSTRUTTORI
    //  - Hanno lo STESSO NOME della classe
    //  - NON hanno tipo di ritorno (nemmeno void)
    //  - Vengono chiamati con "new"
    //  - Si possono avere più costruttori: è OVERLOADING dei costruttori!
    //
    //  Se non scriviamo nessun costruttore, Java ne crea uno vuoto
    //  "di default". Appena ne scriviamo uno, quello di default sparisce.
    // ---------------------------------------------------------------
    public Studente(String nome, String cognome, int eta) {
        // "this" indica L'OGGETTO CORRENTE. Serve a distinguere
        // l'attributo (this.nome) dal parametro (nome) che ha lo stesso nome.
        this.nome = nome;
        this.cognome = cognome;
        setEta(eta); // riusiamo il setter per avere il controllo di validità
        this.matricola = prossimaMatricola++;
    }

    // Costruttore sovraccarico: età non nota.
    // this(...) richiama UN ALTRO costruttore della stessa classe
    // (deve essere la prima istruzione).
    public Studente(String nome, String cognome) {
        this(nome, cognome, 18);
    }

    // ---------------------------------------------------------------
    // GETTER: permettono di LEGGERE un attributo privato
    // Convenzione: getNomeAttributo() (per i boolean: isQualcosa())
    // ---------------------------------------------------------------
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public int getMatricola() {
        return matricola;
    }

    // ---------------------------------------------------------------
    // SETTER: permettono di MODIFICARE un attributo, con dei CONTROLLI
    // Convenzione: setNomeAttributo(valore)
    // Nota: matricola NON ha setter -> è di sola lettura dall'esterno.
    // ---------------------------------------------------------------
    public void setEta(int eta) {
        if (eta < 0 || eta > 120) {
            System.out.println("  [!] Età non valida (" + eta + "), ignorata.");
            return;
        }
        this.eta = eta;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            System.out.println("  [!] Il nome non può essere vuoto.");
            return;
        }
        this.nome = nome;
    }

    // ---------------------------------------------------------------
    // METODI DI ISTANZA: lavorano sui dati di QUESTO oggetto
    // ---------------------------------------------------------------
    public void aggiungiVoto(double voto) {
        if (voto < 18 || voto > 30) {
            System.out.println("  [!] Voto non valido: " + voto);
            return;
        }
        // gli array hanno dimensione fissa: ne creiamo uno più grande
        double[] nuovi = new double[voti.length + 1];
        for (int i = 0; i < voti.length; i++) {
            nuovi[i] = voti[i];
        }
        nuovi[voti.length] = voto;
        voti = nuovi;
    }

    public double getMedia() {
        if (voti.length == 0) return 0;
        double somma = 0;
        for (double v : voti) somma += v;
        return somma / voti.length;
    }

    public int getNumeroEsami() {
        return voti.length;
    }

    public void presentati() {
        System.out.println("Ciao, sono " + nome + " " + cognome + ", ho " + eta
                + " anni e la mia matricola è " + matricola);
    }

    // ---------------------------------------------------------------
    // METODO STATICO: appartiene alla classe, non a un oggetto.
    // Si chiama con  Studente.getStudentiCreati()
    // NON può usare "this" né gli attributi di istanza (di quale oggetto
    // sarebbero?).
    // ---------------------------------------------------------------
    public static int getStudentiCreati() {
        return prossimaMatricola - 1000;
    }

    // ---------------------------------------------------------------
    // toString(): restituisce una rappresentazione testuale dell'oggetto.
    // Viene chiamato AUTOMATICAMENTE quando stampiamo l'oggetto o lo
    // concateniamo a una stringa.
    // @Override indica che stiamo RIDEFINENDO un metodo ereditato
    // (da Object): lo capiremo bene nella lezione 8!
    // ---------------------------------------------------------------
    @Override
    public String toString() {
        return String.format("Studente[%d] %s %s, %d anni, media %.2f",
                matricola, nome, cognome, eta, getMedia());
    }
}
