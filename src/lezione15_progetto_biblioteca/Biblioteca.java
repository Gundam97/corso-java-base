package lezione15_progetto_biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Il "cuore" del progetto: gestisce catalogo, utenti e prestiti.
 *
 * Concetti usati:
 *  - collezioni (List, Map)
 *  - polimorfismo: il catalogo contiene Materiale di ogni tipo
 *  - instanceof per distinguere ciò che è Prestabile
 *  - OVERLOADING dei metodi cerca(...)
 *  - eccezioni checked personalizzate
 */
public class Biblioteca {

    private final String nome;
    private final List<Materiale> catalogo = new ArrayList<>();
    private final Map<String, Utente> utenti = new HashMap<>();      // tessera -> utente
    private final Map<String, Prestito> prestitiAttivi = new HashMap<>(); // codice -> prestito

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    // ------------------------------------------------------------------
    // Gestione catalogo e utenti
    // ------------------------------------------------------------------
    public void aggiungi(Materiale m) {
        catalogo.add(m);
    }

    public void iscrivi(Utente u) {
        utenti.put(u.getTessera(), u);
    }

    public Materiale trovaPerCodice(String codice) {
        for (Materiale m : catalogo) {
            if (m.getCodice().equals(codice)) return m;
        }
        return null;
    }

    // ------------------------------------------------------------------
    // OVERLOADING: tre modi diversi di cercare
    // ------------------------------------------------------------------

    /** Cerca per parola nel titolo (senza distinguere maiuscole/minuscole). */
    public List<Materiale> cerca(String parola) {
        List<Materiale> risultati = new ArrayList<>();
        for (Materiale m : catalogo) {
            if (m.getTitolo().toLowerCase().contains(parola.toLowerCase())) {
                risultati.add(m);
            }
        }
        return risultati;
    }

    /** Cerca i libri di un certo genere. */
    public List<Materiale> cerca(Genere genere) {
        List<Materiale> risultati = new ArrayList<>();
        for (Materiale m : catalogo) {
            if (m instanceof Libro libro && libro.getGenere() == genere) {
                risultati.add(m);
            }
        }
        return risultati;
    }

    /** Cerca per intervallo di anni. */
    public List<Materiale> cerca(int annoDa, int annoA) {
        List<Materiale> risultati = new ArrayList<>();
        for (Materiale m : catalogo) {
            if (m.getAnno() >= annoDa && m.getAnno() <= annoA) {
                risultati.add(m);
            }
        }
        return risultati;
    }

    // ------------------------------------------------------------------
    // Prestiti
    // ------------------------------------------------------------------
    public Prestito presta(String codice, String tessera, LocalDate data) throws PrestitoException {
        Utente utente = utenti.get(tessera);
        if (utente == null) {
            throw new PrestitoException("Tessera " + tessera + " inesistente");
        }

        Materiale m = trovaPerCodice(codice);
        if (m == null) {
            throw new PrestitoException("Nessun materiale con codice " + codice);
        }

        // Solo ciò che implementa Prestabile può essere prestato
        if (!(m instanceof Prestabile prestabile)) {
            throw new PrestitoException("'" + m.getTitolo() + "' è solo in consultazione");
        }
        if (!prestabile.isDisponibile()) {
            throw new PrestitoException("'" + m.getTitolo() + "' è già in prestito");
        }
        if (contaPrestiti(utente) >= Utente.MAX_PRESTITI) {
            throw new PrestitoException(utente.getNome() + " ha già "
                    + Utente.MAX_PRESTITI + " prestiti attivi");
        }

        prestabile.setDisponibile(false);
        Prestito p = new Prestito(m, utente, data);
        prestitiAttivi.put(codice, p);
        return p;
    }

    public void restituisci(String codice) throws PrestitoException {
        Prestito p = prestitiAttivi.remove(codice);
        if (p == null) {
            throw new PrestitoException("Il materiale " + codice + " non risulta in prestito");
        }
        ((Prestabile) p.materiale()).setDisponibile(true);
    }

    private int contaPrestiti(Utente u) {
        int n = 0;
        for (Prestito p : prestitiAttivi.values()) {
            if (p.utente().equals(u)) n++;
        }
        return n;
    }

    public List<Prestito> prestitiScaduti(LocalDate oggi) {
        List<Prestito> scaduti = new ArrayList<>();
        for (Prestito p : prestitiAttivi.values()) {
            if (p.isScaduto(oggi)) scaduti.add(p);
        }
        return scaduti;
    }

    // ------------------------------------------------------------------
    // Statistiche e stampa
    // ------------------------------------------------------------------
    public Map<String, Integer> statistichePerTipo() {
        Map<String, Integer> stat = new HashMap<>();
        for (Materiale m : catalogo) {
            String tipo = m.getClass().getSimpleName();
            stat.put(tipo, stat.getOrDefault(tipo, 0) + 1);
        }
        return stat;
    }

    public void stampaCatalogo() {
        System.out.println("Catalogo di " + nome + " (" + catalogo.size() + " elementi):");
        List<Materiale> ordinato = new ArrayList<>(catalogo);
        ordinato.sort(Comparator.comparing(Materiale::getTitolo));
        for (Materiale m : ordinato) {
            System.out.println("  " + m); // toString() polimorfico
        }
    }
}
