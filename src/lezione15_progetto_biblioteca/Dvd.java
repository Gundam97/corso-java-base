package lezione15_progetto_biblioteca;

/** Un DVD si può prestare, ma solo per 7 giorni: ridefinisce il metodo default. */
public class Dvd extends Materiale implements Prestabile {

    private final int durataMinuti;
    private boolean disponibile = true;

    public Dvd(String titolo, int anno, int durataMinuti) {
        super("DVD", titolo, anno);
        this.durataMinuti = durataMinuti;
    }

    @Override
    public String getDettagli() {
        return durataMinuti + " min";
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    @Override
    public int getGiorniPrestito() { // override del metodo default
        return 7;
    }
}
