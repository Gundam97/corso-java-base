package lezione15_progetto_biblioteca;

import java.time.LocalDate;

/**
 * Un record: dati immutabili di un prestito.
 * LocalDate è la classe standard di Java per rappresentare una data.
 */
public record Prestito(Materiale materiale, Utente utente, LocalDate dataInizio) {

    public LocalDate getScadenza() {
        int giorni = ((Prestabile) materiale).getGiorniPrestito();
        return dataInizio.plusDays(giorni);
    }

    public boolean isScaduto(LocalDate oggi) {
        return oggi.isAfter(getScadenza());
    }
}
