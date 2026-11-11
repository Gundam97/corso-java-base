package lezione09_astratte;

/*
 *  Cerchio è una sottoclasse CONCRETA di Forma: deve quindi implementare
 *  getArea() e getPerimetro(). Se ne dimentichiamo uno, il compilatore
 *  dà errore: "Cerchio is not abstract and does not override abstract
 *  method getPerimetro() in Forma".
 */
public class Cerchio extends Forma {

    private final double raggio;

    public Cerchio(String colore, double raggio) {
        super("Cerchio", colore);
        this.raggio = raggio;
    }

    @Override
    public double getArea() {
        return Math.PI * raggio * raggio;
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * raggio;
    }

    public double getRaggio() {
        return raggio;
    }
}
