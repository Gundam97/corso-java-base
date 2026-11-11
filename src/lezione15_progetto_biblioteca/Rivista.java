package lezione15_progetto_biblioteca;

/** Una Rivista È UN Materiale ma NON è prestabile: non implementa Prestabile. */
public class Rivista extends Materiale {

    private final int numero;

    public Rivista(String titolo, int numero, int anno) {
        super("RIV", titolo, anno);
        this.numero = numero;
    }

    @Override
    public String getDettagli() {
        return "numero " + numero;
    }
}
