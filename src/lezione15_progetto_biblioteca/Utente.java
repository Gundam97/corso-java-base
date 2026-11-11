package lezione15_progetto_biblioteca;

import java.util.Objects;

/** Un utente iscritto alla biblioteca. Può avere al massimo 3 prestiti. */
public class Utente {

    public static final int MAX_PRESTITI = 3;

    private final String tessera;
    private final String nome;

    public Utente(String tessera, String nome) {
        this.tessera = tessera;
        this.nome = nome;
    }

    public String getTessera() {
        return tessera;
    }

    public String getNome() {
        return nome;
    }

    // Due utenti sono lo stesso utente se hanno la stessa tessera
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente altro)) return false;
        return tessera.equals(altro.tessera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tessera);
    }

    @Override
    public String toString() {
        return nome + " (" + tessera + ")";
    }
}
