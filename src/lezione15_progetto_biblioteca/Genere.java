package lezione15_progetto_biblioteca;

/** Enum con un attributo: l'etichetta leggibile del genere. */
public enum Genere {
    ROMANZO("Romanzo"),
    GIALLO("Giallo"),
    FANTASY("Fantasy"),
    SAGGIO("Saggio"),
    INFORMATICA("Informatica");

    private final String etichetta;

    Genere(String etichetta) {
        this.etichetta = etichetta;
    }

    @Override
    public String toString() {
        return etichetta;
    }
}
