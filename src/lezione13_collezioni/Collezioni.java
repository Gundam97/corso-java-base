package lezione13_collezioni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * =====================================================================
 *  LEZIONE 13 - COLLEZIONI E GENERICS
 * =====================================================================
 *
 *  Gli array hanno una dimensione FISSA. Nella pratica quasi sempre si
 *  usano le COLLEZIONI, che crescono e si restringono da sole.
 *
 *  Le tre più usate:
 *
 *   List<E>   (es. ArrayList)  -> sequenza ORDINATA, ammette DUPLICATI,
 *                                 accesso per indice.  [a, b, b, c]
 *   Set<E>    (es. HashSet)    -> insieme SENZA DUPLICATI, di solito
 *                                 senza ordine.        {a, b, c}
 *   Map<K,V>  (es. HashMap)    -> coppie CHIAVE -> VALORE, come un
 *                                 dizionario.          {"it" -> "Italia"}
 *
 *  NB: List, Set e Map sono INTERFACCE; ArrayList, HashSet e HashMap sono
 *  le CLASSI che le implementano. Si usa scrivere:
 *        List<String> nomi = new ArrayList<>();
 *  (variabile del tipo interfaccia -> polimorfismo! Se un domani vuoi una
 *   LinkedList, cambi solo la parte dopo il "new".)
 *
 *  GENERICS: il tipo tra < > dice cosa contiene la collezione.
 *  Il compilatore impedisce di metterci dentro altro.
 *
 *  WRAPPER: le collezioni contengono solo OGGETTI, non tipi primitivi.
 *  Per i primitivi esistono le classi "involucro":
 *     int -> Integer   double -> Double   boolean -> Boolean   char -> Character
 *  La conversione è automatica (AUTOBOXING / UNBOXING).
 */
public class Collezioni {

    public static void main(String[] args) {

        // =============================================================
        // 1. ARRAYLIST
        // =============================================================
        System.out.println("=== ArrayList ===");
        List<String> spesa = new ArrayList<>();

        spesa.add("pane");          // aggiunge in fondo
        spesa.add("latte");
        spesa.add("uova");
        spesa.add("latte");         // i duplicati sono ammessi
        spesa.add(0, "caffè");      // aggiunge in posizione 0

        System.out.println("Lista: " + spesa);
        System.out.println("Dimensione: " + spesa.size());   // size(), non length!
        System.out.println("Elemento 2: " + spesa.get(2));
        System.out.println("Contiene uova? " + spesa.contains("uova"));
        System.out.println("Posizione di 'latte': " + spesa.indexOf("latte"));

        spesa.set(1, "pane integrale");   // sostituisce
        spesa.remove("latte");            // rimuove la PRIMA occorrenza
        spesa.remove(0);                  // rimuove per indice
        System.out.println("Dopo le modifiche: " + spesa);

        // Scorrere una lista
        for (String articolo : spesa) {
            System.out.println("  - " + articolo);
        }

        // Autoboxing con i numeri
        List<Integer> numeri = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numeri.add(i * i);            // int -> Integer automaticamente
        }
        int sommaNumeri = 0;
        for (int n : numeri) {            // Integer -> int automaticamente
            sommaNumeri += n;
        }
        System.out.println("Quadrati: " + numeri + " somma = " + sommaNumeri);

        // ATTENZIONE: rimuovere elementi MENTRE si scorre con for-each causa
        // ConcurrentModificationException. Si usa un Iterator o removeIf:
        Iterator<Integer> it = numeri.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) it.remove();
        }
        System.out.println("Solo dispari: " + numeri);

        // =============================================================
        // 2. ORDINARE: Comparable e Comparator
        // =============================================================
        System.out.println("\n=== Ordinamento ===");
        List<Prodotto> catalogo = new ArrayList<>();
        catalogo.add(new Prodotto("Mouse", 25.90, "Informatica"));
        catalogo.add(new Prodotto("Caffè", 4.50, "Alimentari"));
        catalogo.add(new Prodotto("Monitor", 189.00, "Informatica"));
        catalogo.add(new Prodotto("Biscotti", 2.30, "Alimentari"));
        catalogo.add(new Prodotto("Cuffie", 59.99, "Audio"));

        // a) Ordine naturale (compareTo di Prodotto -> per nome)
        Collections.sort(catalogo);
        System.out.println("Per nome (Comparable):");
        catalogo.forEach(p -> System.out.println("  " + p));

        // b) Ordine alternativo con un Comparator.
        //    "p -> p.getPrezzo()" è una LAMBDA: una piccola funzione
        //    scritta al volo. Si legge "dato p, restituisci p.getPrezzo()".
        catalogo.sort(Comparator.comparing(p -> p.getPrezzo()));
        System.out.println("Per prezzo (Comparator):");
        for (Prodotto p : catalogo) System.out.println("  " + p);

        // c) Ordine decrescente
        catalogo.sort(Comparator.comparing(Prodotto::getPrezzo).reversed());
        System.out.println("Il più caro: " + catalogo.get(0).getNome());

        // removeIf con una lambda
        catalogo.removeIf(p -> p.getPrezzo() > 100);
        System.out.println("Senza prodotti sopra i 100€: " + catalogo.size() + " prodotti");

        // =============================================================
        // 3. HASHSET: niente duplicati
        // =============================================================
        System.out.println("\n=== HashSet ===");
        Set<String> visitatori = new HashSet<>();
        visitatori.add("anna");
        visitatori.add("bruno");
        boolean aggiunto = visitatori.add("anna");   // già presente!
        System.out.println("Anna aggiunta di nuovo? " + aggiunto);
        System.out.println("Visitatori unici: " + visitatori.size() + " " + visitatori);

        // Uso tipico: eliminare i duplicati da una lista
        List<String> conDuplicati = List.of("rosso", "blu", "rosso", "verde", "blu");
        Set<String> senzaDuplicati = new HashSet<>(conDuplicati);
        System.out.println("Colori unici: " + senzaDuplicati);

        // =============================================================
        // 4. HASHMAP: chiave -> valore
        // =============================================================
        System.out.println("\n=== HashMap ===");
        Map<String, String> capitali = new HashMap<>();
        capitali.put("Italia", "Roma");
        capitali.put("Francia", "Parigi");
        capitali.put("Spagna", "Madrid");
        capitali.put("Italia", "ROMA");      // stessa chiave: SOVRASCRIVE il valore

        System.out.println("Capitale d'Italia: " + capitali.get("Italia"));
        System.out.println("Capitale di Malta: " + capitali.get("Malta")); // null
        System.out.println("Con default: " + capitali.getOrDefault("Malta", "sconosciuta"));
        System.out.println("C'è la Spagna? " + capitali.containsKey("Spagna"));

        // Scorrere una mappa
        for (Map.Entry<String, String> voce : capitali.entrySet()) {
            System.out.println("  " + voce.getKey() + " -> " + voce.getValue());
        }

        // Esempio classico: CONTARE le occorrenze delle parole
        String testo = "il gatto e il cane e il topo";
        Map<String, Integer> conteggio = new HashMap<>();
        for (String parola : testo.split(" ")) {
            conteggio.put(parola, conteggio.getOrDefault(parola, 0) + 1);
        }
        System.out.println("Conteggio parole: " + conteggio);

        // Raggruppare: categoria -> lista di prodotti
        Map<String, List<String>> perCategoria = new HashMap<>();
        for (Prodotto p : catalogo) {
            perCategoria.computeIfAbsent(p.getCategoria(), k -> new ArrayList<>())
                    .add(p.getNome());
        }
        System.out.println("Per categoria: " + perCategoria);

        // =============================================================
        // 5. UNA CLASSE GENERICA FATTA DA NOI
        // =============================================================
        System.out.println("\n=== Generics: Scatola<T> ===");
        Scatola<String> scatolaTesto = new Scatola<>("un messaggio segreto");
        Scatola<Integer> scatolaNumero = new Scatola<>(42);
        Scatola<Prodotto> scatolaProdotto = new Scatola<>(new Prodotto("Tazza", 8, "Casa"));

        String s = scatolaTesto.apri();   // nessun cast necessario
        int n = scatolaNumero.apri();
        System.out.println(scatolaTesto + " / " + scatolaNumero + " / " + scatolaProdotto);
        System.out.println("Lunghezza del messaggio: " + s.length() + ", numero+1: " + (n + 1));
        // scatolaNumero.sostituisci("ciao"); // ERRORE: la scatola accetta solo Integer
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Crea una  List<Integer>  con 20 numeri casuali tra 1 e 100
 *     (usa  (int)(Math.random() * 100) + 1 ), poi stampa: max, min, media,
 *     e la lista ordinata.
 *  2. Rubrica: una  Map<String, String>  nome -> telefono, con aggiunta,
 *     ricerca e cancellazione di un contatto.
 *  3. Dato un testo, conta quante volte compare ogni LETTERA
 *     (Map<Character, Integer>).
 *  4. Ordina la lista di Studenti (lezione 7) per media decrescente usando
 *     un Comparator.
 *  5. Crea una classe generica  Coppia<A, B>  con due valori di tipo
 *     diverso e i metodi getPrimo() e getSecondo().
 */
