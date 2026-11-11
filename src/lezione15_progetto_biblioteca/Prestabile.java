package lezione15_progetto_biblioteca;

/**
 * Interfaccia: la CAPACITÀ di essere dato in prestito.
 * Libri e DVD la implementano, le riviste no (si consultano in sede).
 */
public interface Prestabile {

    boolean isDisponibile();

    void setDisponibile(boolean disponibile);

    /** Metodo default: per quanti giorni si può tenere. Ridefinibile. */
    default int getGiorniPrestito() {
        return 30;
    }
}
