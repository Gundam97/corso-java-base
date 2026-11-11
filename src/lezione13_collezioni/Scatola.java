package lezione13_collezioni;

/*
 *  UNA CLASSE GENERICA
 *  -------------------
 *  <T> è un "segnaposto" per un TIPO che verrà deciso da chi usa la classe.
 *  Così scriviamo il codice UNA volta sola e funziona con qualsiasi tipo,
 *  mantenendo i controlli del compilatore.
 *
 *     Scatola<String>  s = new Scatola<>("ciao");   // T = String
 *     Scatola<Integer> n = new Scatola<>(42);       // T = Integer
 *
 *  È esattamente il meccanismo che usano ArrayList<T>, HashMap<K,V>, ecc.
 *  (Per convenzione: T = Type, E = Element, K = Key, V = Value.)
 */
public class Scatola<T> {

    private T contenuto;

    public Scatola(T contenuto) {
        this.contenuto = contenuto;
    }

    public T apri() {
        return contenuto;
    }

    public void sostituisci(T nuovo) {
        this.contenuto = nuovo;
    }

    public boolean isVuota() {
        return contenuto == null;
    }

    @Override
    public String toString() {
        return "Scatola[" + contenuto + "]";
    }
}
