package lezione10_interfacce;

/*
 *  Seconda interfaccia: la capacità di nuotare.
 */
public interface Nuotatore {

    void nuota();

    int getProfonditaMassima();

    default boolean puoImmergersiA(int metri) {
        return metri <= getProfonditaMassima();
    }
}
