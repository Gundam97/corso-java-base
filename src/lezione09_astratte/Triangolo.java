package lezione09_astratte;

public class Triangolo extends Forma {

    private final double a, b, c; // i tre lati

    public Triangolo(String colore, double a, double b, double c) {
        super("Triangolo", colore);
        // Validazione: in un triangolo ogni lato è minore della somma degli altri due
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Lati non validi per un triangolo");
            // "throw" lancia un'eccezione: le vedremo nella lezione 12
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getPerimetro() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        // Formula di Erone
        double s = getPerimetro() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
