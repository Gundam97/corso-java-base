package lezione15_progetto_biblioteca;

/** Eccezione CHECKED: chi chiede un prestito deve gestire il caso di rifiuto. */
public class PrestitoException extends Exception {

    public PrestitoException(String messaggio) {
        super(messaggio);
    }
}
