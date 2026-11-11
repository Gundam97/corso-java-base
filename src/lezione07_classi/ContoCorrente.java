package lezione07_classi;

/*
 * =====================================================================
 *  LEZIONE 7 - CLASSI E OGGETTI  (file 2 di 3: ContoCorrente)
 * =====================================================================
 *
 *  Un esempio concreto del PERCHÉ l'incapsulamento è importante.
 *
 *  Se il saldo fosse "public", chiunque potrebbe scrivere:
 *        conto.saldo = 1_000_000;     // soldi dal nulla!
 *        conto.saldo = -500;          // saldo negativo senza controlli!
 *
 *  Rendendolo "private" e offrendo solo i metodi deposita() e preleva(),
 *  la classe GARANTISCE che il saldo sia sempre coerente.
 *  L'oggetto è responsabile dei propri dati.
 */
public class ContoCorrente {

    private final String intestatario;
    private final String iban;
    private double saldo;
    private int numeroOperazioni;

    public ContoCorrente(String intestatario, String iban) {
        this.intestatario = intestatario;
        this.iban = iban;
        this.saldo = 0;
    }

    // Overloading del costruttore: conto con deposito iniziale
    public ContoCorrente(String intestatario, String iban, double depositoIniziale) {
        this(intestatario, iban);
        deposita(depositoIniziale);
    }

    /** Deposita una somma sul conto. Restituisce true se l'operazione è riuscita. */
    public boolean deposita(double importo) {
        if (importo <= 0) {
            System.out.println("  [!] Importo da depositare non valido: " + importo);
            return false;
        }
        saldo += importo;
        numeroOperazioni++;
        return true;
    }

    /** Preleva una somma, solo se c'è disponibilità. */
    public boolean preleva(double importo) {
        if (importo <= 0) {
            System.out.println("  [!] Importo da prelevare non valido: " + importo);
            return false;
        }
        if (importo > saldo) {
            System.out.printf("  [!] Saldo insufficiente: richiesti %.2f, disponibili %.2f%n",
                    importo, saldo);
            return false;
        }
        saldo -= importo;
        numeroOperazioni++;
        return true;
    }

    /** Un oggetto può interagire con un ALTRO oggetto dello stesso tipo. */
    public boolean bonifico(ContoCorrente destinatario, double importo) {
        if (this == destinatario) {
            System.out.println("  [!] Non puoi fare un bonifico a te stesso.");
            return false;
        }
        if (this.preleva(importo)) {
            destinatario.deposita(importo);
            return true;
        }
        return false;
    }

    // Solo getter: saldo e intestatario si possono LEGGERE ma non impostare
    // liberamente dall'esterno.
    public double getSaldo() {
        return saldo;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public int getNumeroOperazioni() {
        return numeroOperazioni;
    }

    @Override
    public String toString() {
        return String.format("Conto di %-8s (%s): %10.2f €  [%d operazioni]",
                intestatario, iban, saldo, numeroOperazioni);
    }
}
