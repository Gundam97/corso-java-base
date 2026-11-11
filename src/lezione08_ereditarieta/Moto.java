package lezione08_ereditarieta;

/*
 *  Moto EXTENDS Veicolo
 *  Un'altra sottoclasse: stessa superclasse, comportamenti diversi.
 */
public class Moto extends Veicolo {

    private final boolean haSidecar;

    public Moto(String marca, String modello, int anno, boolean haSidecar) {
        super(marca, modello, anno);
        this.haSidecar = haSidecar;
    }

    @Override
    public int getVelocitaMassima() {
        return haSidecar ? 120 : 220;
    }

    @Override
    public String suonaClacson() {
        return "Meep meep!";
    }

    // La moto frena più bruscamente: ridefiniamo frena()
    @Override
    public void frena() {
        velocita = Math.max(0, velocita - 40);
    }

    public void impenna() {
        if (haSidecar) {
            System.out.println("  Con il sidecar non si impenna!");
        } else if (velocita < 30) {
            System.out.println("  Troppo lento per impennare.");
        } else {
            System.out.println("  " + marca + " impenna!");
        }
    }
}
