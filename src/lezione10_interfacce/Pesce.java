package lezione10_interfacce;

/*
 *  Il Pesce è un Animale che sa nuotare, ma NON volare.
 */
public class Pesce extends Animale implements Nuotatore {

    private final int profondita;

    public Pesce(String nome, int profondita) {
        super(nome);
        this.profondita = profondita;
    }

    @Override
    public String verso() {
        return "... (blub)";
    }

    @Override
    public void nuota() {
        System.out.println("  " + nome + " muove la coda e sfreccia sott'acqua.");
    }

    @Override
    public int getProfonditaMassima() {
        return profondita;
    }
}
