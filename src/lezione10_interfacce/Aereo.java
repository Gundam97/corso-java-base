package lezione10_interfacce;

/*
 *  L'Aereo NON è un animale (non estende Animale), ma SA VOLARE.
 *  Questa è la forza delle interfacce: accomunano classi che non hanno
 *  nessuna parentela tra loro, solo una CAPACITÀ in comune.
 *
 *  Qui ridefiniamo anche il metodo default atterra().
 */
public class Aereo implements Volante {

    private final String compagnia;

    public Aereo(String compagnia) {
        this.compagnia = compagnia;
    }

    @Override
    public void decolla() {
        System.out.println("  Aereo " + compagnia + ": motori al massimo, decollo!");
    }

    @Override
    public int getAltitudineMassima() {
        return ALTITUDINE_MAX_TERRESTRE; // costante ereditata dall'interfaccia
    }

    @Override
    public void atterra() { // override del metodo DEFAULT
        System.out.println("  Aereo " + compagnia + ": carrello giù, atterraggio in pista.");
    }

    @Override
    public String toString() {
        return "Aereo " + compagnia;
    }
}
