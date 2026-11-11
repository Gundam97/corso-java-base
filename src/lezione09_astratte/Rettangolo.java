package lezione09_astratte;

public class Rettangolo extends Forma {

    protected final double base;
    protected final double altezza;

    public Rettangolo(String colore, double base, double altezza) {
        this("Rettangolo", colore, base, altezza);
    }

    // Costruttore protected usato dalla sottoclasse Quadrato per passare
    // un nome diverso.
    protected Rettangolo(String nome, String colore, double base, double altezza) {
        super(nome, colore);
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double getArea() {
        return base * altezza;
    }

    @Override
    public double getPerimetro() {
        return 2 * (base + altezza);
    }
}

/*
 *  Un Quadrato È UN Rettangolo con i lati uguali: può estenderlo e
 *  non deve riscrivere né getArea() né getPerimetro().
 *
 *  (Nota: in un file .java ci può essere UNA SOLA classe public, il cui
 *   nome coincide con il file. Altre classi non public sono ammesse, ma
 *   di solito si preferisce un file per classe.)
 */
class Quadrato extends Rettangolo {

    public Quadrato(String colore, double lato) {
        super("Quadrato", colore, lato, lato);
    }
}
