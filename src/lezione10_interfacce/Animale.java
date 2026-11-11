package lezione10_interfacce;

/*
 *  Classe astratta di base per gli animali (ripasso della lezione 9).
 *  Contiene lo STATO comune (nome) e i comportamenti comuni.
 */
public abstract class Animale {

    protected final String nome;

    protected Animale(String nome) {
        this.nome = nome;
    }

    public abstract String verso();

    public void presentati() {
        System.out.println("  Sono " + nome + " (" + getClass().getSimpleName()
                + ") e faccio: " + verso());
    }

    public String getNome() {
        return nome;
    }
}
