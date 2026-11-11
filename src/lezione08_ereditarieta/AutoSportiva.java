package lezione08_ereditarieta;

/*
 *  AutoSportiva EXTENDS Automobile  (che a sua volta EXTENDS Veicolo)
 *  ------------------------------------------------------------------
 *  Ereditarietà a più livelli: AutoSportiva eredita TUTTO da Automobile,
 *  e quindi anche tutto ciò che Automobile ha ereditato da Veicolo.
 *
 *     Object  <-  Veicolo  <-  Automobile  <-  AutoSportiva
 *
 *  La parola "final" sulla classe impedisce che qualcuno la estenda:
 *     class SuperSportiva extends AutoSportiva  ->  ERRORE
 *  (Esempio reale: la classe String è final.)
 */
public final class AutoSportiva extends Automobile {

    private final int cavalli;

    public AutoSportiva(String marca, String modello, int anno, int cavalli) {
        super(marca, modello, anno, 2); // le sportive hanno 2 porte
        this.cavalli = cavalli;
        System.out.println("  (costruttore di AutoSportiva eseguito)");
    }

    // Ridefinisce di nuovo un metodo già ridefinito da Automobile
    @Override
    public int getVelocitaMassima() {
        return 320;
    }

    // Usa la versione del padre (Automobile) e la arricchisce
    @Override
    public String descrizione() {
        return super.descrizione() + ", " + cavalli + " CV";
    }

    // suonaClacson() NON viene ridefinito: si usa quello di Automobile
}
