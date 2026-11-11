package lezione15_progetto_biblioteca;

/*
 * =====================================================================
 *  LEZIONE 15 - PROGETTO FINALE: GESTIONE DI UNA BIBLIOTECA
 * =====================================================================
 *
 *  Un piccolo progetto che mette insieme TUTTO quello visto finora.
 *  Per ogni file è indicato quali concetti usa.
 *
 *    Genere.java                     enum con attributi
 *    Prestabile.java                 interfaccia con metodo default
 *    Materiale.java      (sei qui)   classe astratta, incapsulamento
 *    Libro.java                      extends + implements, overriding
 *    Rivista.java                    extends, NON prestabile
 *    Dvd.java                        extends + implements, override di un default
 *    Utente.java                     classe semplice, equals/hashCode
 *    Prestito.java                   record
 *    PrestitoException.java          eccezione checked personalizzata
 *    Biblioteca.java                 collezioni, overloading, polimorfismo
 *    ProgettoBiblioteca.java         il main
 *
 *                 Materiale (abstract)
 *                /        |          \
 *            Libro     Rivista       Dvd
 *              |                      |
 *              +---- Prestabile ------+   (interface)
 */
public abstract class Materiale {

    // static: contatore condiviso per generare codici univoci
    private static int contatore = 1;

    private final String codice;
    private final String titolo;
    private final int anno;

    protected Materiale(String prefisso, String titolo, int anno) {
        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("Il titolo è obbligatorio");
        }
        this.codice = prefisso + "-" + String.format("%03d", contatore++);
        this.titolo = titolo;
        this.anno = anno;
    }

    /** Ogni tipo di materiale descrive i propri dettagli. */
    public abstract String getDettagli();

    public String getCodice() {
        return codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnno() {
        return anno;
    }

    @Override
    public String toString() {
        String stato = "";
        // instanceof con pattern matching: se è prestabile mostro lo stato
        if (this instanceof Prestabile p) {
            stato = p.isDisponibile() ? " [disponibile]" : " [IN PRESTITO]";
        } else {
            stato = " [solo consultazione]";
        }
        return String.format("%-7s %-32s %d  %s%s", codice, titolo, anno, getDettagli(), stato);
    }
}
