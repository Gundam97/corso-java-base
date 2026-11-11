package lezione10_interfacce;

/*
 *  L'Anatra:
 *    - È UN Animale            -> extends Animale
 *    - SA volare e nuotare     -> implements Volante, Nuotatore
 *
 *  Deve quindi implementare:
 *    - verso()                          (da Animale, astratto)
 *    - decolla(), getAltitudineMassima() (da Volante)
 *    - nuota(), getProfonditaMassima()   (da Nuotatore)
 *  I metodi default (atterra, puoImmergersiA) li riceve già pronti.
 */
public class Anatra extends Animale implements Volante, Nuotatore {

    public Anatra(String nome) {
        super(nome);
    }

    @Override
    public String verso() {
        return "Qua qua!";
    }

    @Override
    public void decolla() {
        System.out.println("  " + nome + " sbatte le ali e prende il volo.");
    }

    @Override
    public int getAltitudineMassima() {
        return 1_500;
    }

    @Override
    public void nuota() {
        System.out.println("  " + nome + " galleggia e pagaia con le zampe.");
    }

    @Override
    public int getProfonditaMassima() {
        return 2;
    }
}
