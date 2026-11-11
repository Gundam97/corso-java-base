package lezione15_progetto_biblioteca;

/** Un Libro È UN Materiale e SA essere prestato. */
public class Libro extends Materiale implements Prestabile {

    private final String autore;
    private final Genere genere;
    private boolean disponibile = true;

    public Libro(String titolo, String autore, int anno, Genere genere) {
        super("LIB", titolo, anno);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public Genere getGenere() {
        return genere;
    }

    @Override
    public String getDettagli() {
        return autore + ", " + genere;
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }
    // getGiorniPrestito() non è ridefinito: vale il default (30 giorni)
}
