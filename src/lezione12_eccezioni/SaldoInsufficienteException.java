package lezione12_eccezioni;

/*
 *  UN'ECCEZIONE PERSONALIZZATA
 *  ---------------------------
 *  Le eccezioni sono CLASSI come tutte le altre. Per crearne una nostra
 *  basta ESTENDERE Exception (checked) o RuntimeException (unchecked).
 *
 *  Così il nome stesso dell'eccezione spiega il problema, e possiamo
 *  aggiungere informazioni utili (qui: quanto mancava).
 */
public class SaldoInsufficienteException extends Exception {

    private final double mancante;

    public SaldoInsufficienteException(double richiesto, double disponibile) {
        // passiamo il messaggio al costruttore di Exception
        super(String.format("Saldo insufficiente: richiesti %.2f €, disponibili %.2f €",
                richiesto, disponibile));
        this.mancante = richiesto - disponibile;
    }

    public double getMancante() {
        return mancante;
    }
}
