package lezione13_collezioni;

/*
 *  Una classe che implementa l'interfaccia Comparable<Prodotto> della
 *  libreria standard di Java.
 *
 *  Comparable definisce l'ORDINAMENTO NATURALE di un oggetto, con il
 *  metodo  compareTo(altro)  che restituisce:
 *     un numero NEGATIVO  se this viene PRIMA di altro
 *     ZERO                se sono equivalenti
 *     un numero POSITIVO  se this viene DOPO di altro
 *
 *  Grazie a questo, Collections.sort(lista) sa come ordinare i Prodotti.
 *  È un ottimo esempio di INTERFACCIA usata "per davvero".
 */
public class Prodotto implements Comparable<Prodotto> {

    private final String nome;
    private final double prezzo;
    private final String categoria;

    public Prodotto(String nome, double prezzo, String categoria) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getCategoria() {
        return categoria;
    }

    // Ordinamento naturale: alfabetico per nome
    @Override
    public int compareTo(Prodotto altro) {
        return this.nome.compareTo(altro.nome);
    }

    @Override
    public String toString() {
        return String.format("%-10s %6.2f € (%s)", nome, prezzo, categoria);
    }
}
