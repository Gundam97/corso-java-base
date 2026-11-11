package lezione15_progetto_biblioteca;

import java.time.LocalDate;
import java.util.List;

/*
 * =====================================================================
 *  LEZIONE 15 - PROGETTO FINALE: il main
 * =====================================================================
 *  Simula una giornata in biblioteca. Prova a leggere ogni operazione e a
 *  PREVEDERE l'output prima di eseguire il programma!
 */
public class ProgettoBiblioteca {

    public static void main(String[] args) {

        Biblioteca bib = new Biblioteca("Biblioteca Civica");

        // ---- Popoliamo il catalogo (polimorfismo: tutti sono Materiale) ----
        bib.aggiungi(new Libro("I promessi sposi", "A. Manzoni", 1840, Genere.ROMANZO));
        bib.aggiungi(new Libro("Il nome della rosa", "U. Eco", 1980, Genere.GIALLO));
        bib.aggiungi(new Libro("Lo Hobbit", "J.R.R. Tolkien", 1937, Genere.FANTASY));
        bib.aggiungi(new Libro("Effective Java", "J. Bloch", 2018, Genere.INFORMATICA));
        bib.aggiungi(new Libro("Java: la guida completa", "H. Schildt", 2022, Genere.INFORMATICA));
        bib.aggiungi(new Rivista("Focus", 385, 2024));
        bib.aggiungi(new Rivista("Le Scienze", 670, 2024));
        bib.aggiungi(new Dvd("Nuovo Cinema Paradiso", 1988, 155));
        bib.aggiungi(new Dvd("La vita è bella", 1997, 116));

        bib.iscrivi(new Utente("T001", "Sara"));
        bib.iscrivi(new Utente("T002", "Paolo"));

        bib.stampaCatalogo();

        // ---- Ricerche: OVERLOADING del metodo cerca ----
        stampa("Ricerca per parola 'java'", bib.cerca("java"));
        stampa("Ricerca per genere INFORMATICA", bib.cerca(Genere.INFORMATICA));
        stampa("Ricerca per anni 1900-1990", bib.cerca(1900, 1990));

        // ---- Prestiti: qualcuno va bene, qualcuno no ----
        System.out.println("\n=== Prestiti ===");
        LocalDate oggi = LocalDate.of(2025, 3, 1);

        tentaPrestito(bib, "LIB-001", "T001", oggi);              // ok
        tentaPrestito(bib, "DVD-008", "T001", oggi);              // ok (7 giorni)
        tentaPrestito(bib, "LIB-001", "T002", oggi);              // già in prestito
        tentaPrestito(bib, "RIV-006", "T002", oggi);              // rivista: no
        tentaPrestito(bib, "LIB-003", "T999", oggi);              // tessera inesistente
        tentaPrestito(bib, "LIB-004", "T001", oggi);              // ok (3° prestito)
        tentaPrestito(bib, "LIB-005", "T001", oggi);              // limite superato!

        System.out.println();
        bib.stampaCatalogo();

        // ---- Controllo scadenze dopo 10 giorni ----
        LocalDate fraDieciGiorni = oggi.plusDays(10);
        System.out.println("\n=== Prestiti scaduti al " + fraDieciGiorni + " ===");
        for (Prestito p : bib.prestitiScaduti(fraDieciGiorni)) {
            System.out.println("  " + p.materiale().getTitolo() + " - " + p.utente()
                    + " - scaduto il " + p.getScadenza());
        }

        // ---- Restituzioni ----
        System.out.println("\n=== Restituzioni ===");
        try {
            bib.restituisci("DVD-008");
            System.out.println("  DVD-008 restituito");
            bib.restituisci("LIB-002"); // non era in prestito
        } catch (PrestitoException e) {
            System.out.println("  ERRORE: " + e.getMessage());
        }

        tentaPrestito(bib, "LIB-005", "T001", oggi.plusDays(10)); // ora c'è posto

        System.out.println("\n=== Statistiche ===");
        System.out.println("  " + bib.statistichePerTipo());
    }

    // Metodi di supporto per non ripetere codice nel main
    private static void tentaPrestito(Biblioteca bib, String codice, String tessera, LocalDate data) {
        try {
            Prestito p = bib.presta(codice, tessera, data);
            System.out.println("  OK: '" + p.materiale().getTitolo() + "' a " + p.utente()
                    + ", restituire entro il " + p.getScadenza());
        } catch (PrestitoException e) {
            System.out.println("  RIFIUTATO: " + e.getMessage());
        }
    }

    private static void stampa(String titolo, List<Materiale> risultati) {
        System.out.println("\n" + titolo + " -> " + risultati.size() + " risultati");
        for (Materiale m : risultati) {
            System.out.println("  " + m);
        }
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESTENSIONI DEL PROGETTO (da fare in autonomia)
 * ---------------------------------------------------------------------
 *  1. Aggiungi la classe  Audiolibro extends Materiale implements Prestabile
 *     con durata e narratore, prestabile per 14 giorni.
 *  2. Aggiungi un overloading  cerca(String parola, Genere genere).
 *  3. Calcola una multa di 0,50 € per ogni giorno di ritardo alla
 *     restituzione (suggerimento: java.time.temporal.ChronoUnit.DAYS.between).
 *  4. Crea un MENU interattivo con Scanner e switch:
 *        1) Mostra catalogo  2) Cerca  3) Presta  4) Restituisci  0) Esci
 *  5. Fai in modo che un utente possa vedere la lista dei propri prestiti.
 */
