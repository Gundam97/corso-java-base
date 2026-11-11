package lezione07_classi;

/*
 * =====================================================================
 *  LEZIONE 7 - CLASSI E OGGETTI  (file 3 di 3: usiamo le classi)
 * =====================================================================
 *
 *  Qui CREIAMO degli oggetti a partire dalle classi Studente e
 *  ContoCorrente, e li usiamo.
 *
 *     Studente s = new Studente("Mario", "Rossi", 20);
 *     ^^^^^^^^ ^   ^^^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *     tipo     |   |   chiamata al costruttore
 *              |   crea un nuovo oggetto in memoria
 *              variabile che contiene il RIFERIMENTO all'oggetto
 *
 *  Per usare un oggetto si usa il PUNTO:
 *     s.presentati();      // chiama un metodo
 *     s.getNome();         // chiama un getter
 */
public class ClassiEOggetti {

    public static void main(String[] args) {

        System.out.println("===== STUDENTI =====");

        // CREAZIONE di oggetti (ISTANZIAZIONE)
        Studente mario = new Studente("Mario", "Rossi", 20);
        Studente giulia = new Studente("Giulia", "Bianchi", 22);
        Studente luca = new Studente("Luca", "Verdi"); // altro costruttore -> età 18

        // Ogni oggetto ha il suo stato indipendente
        mario.presentati();
        giulia.presentati();
        luca.presentati();

        // Usare getter e setter
        System.out.println("\nNome di Giulia tramite getter: " + giulia.getNome());
        luca.setEta(19);
        System.out.println("Luca ora ha " + luca.getEta() + " anni");

        // I setter proteggono l'oggetto da valori sbagliati
        luca.setEta(-5);   // rifiutato
        luca.setNome("");  // rifiutato

        // mario.eta = 200;  // ERRORE di compilazione: eta è private!

        // Metodi che modificano lo stato
        mario.aggiungiVoto(28);
        mario.aggiungiVoto(30);
        mario.aggiungiVoto(25);
        mario.aggiungiVoto(35); // rifiutato
        giulia.aggiungiVoto(24);

        System.out.printf("%nMedia di Mario: %.2f su %d esami%n",
                mario.getMedia(), mario.getNumeroEsami());

        // toString() viene chiamato automaticamente nella stampa
        System.out.println("\nStampa con toString():");
        System.out.println(mario);
        System.out.println(giulia);
        System.out.println(luca);

        // Membri STATICI: si usano tramite il nome della CLASSE
        System.out.println("\nStudenti creati finora: " + Studente.getStudentiCreati());
        System.out.println("Ateneo: " + Studente.UNIVERSITA);

        // Array di oggetti
        Studente[] classe = {mario, giulia, luca};
        Studente migliore = classe[0];
        for (Studente s : classe) {
            if (s.getMedia() > migliore.getMedia()) {
                migliore = s;
            }
        }
        System.out.println("Studente con la media migliore: " + migliore.getNome());

        // ---------------------------------------------------------------
        // RIFERIMENTI e null
        // ---------------------------------------------------------------
        System.out.println("\n===== RIFERIMENTI =====");
        Studente altro = mario;           // NON crea un nuovo studente!
        altro.setNome("Mariolino");       // modifica lo STESSO oggetto
        System.out.println("mario.getNome() = " + mario.getNome());
        System.out.println("mario == altro ? " + (mario == altro)); // stesso oggetto

        Studente nessuno = null;          // la variabile non punta a nessun oggetto
        // nessuno.presentati();          // NullPointerException a runtime!
        if (nessuno == null) {
            System.out.println("La variabile 'nessuno' non contiene alcun oggetto");
        }

        // ---------------------------------------------------------------
        // CONTO CORRENTE: l'incapsulamento in pratica
        // ---------------------------------------------------------------
        System.out.println("\n===== CONTI CORRENTI =====");
        ContoCorrente contoAnna = new ContoCorrente("Anna", "IT001", 500);
        ContoCorrente contoBruno = new ContoCorrente("Bruno", "IT002");

        contoBruno.deposita(200);
        contoAnna.preleva(120);
        contoAnna.preleva(10_000);              // rifiutato: saldo insufficiente
        contoBruno.deposita(-50);               // rifiutato: importo negativo
        contoAnna.bonifico(contoBruno, 100);

        System.out.println(contoAnna);
        System.out.println(contoBruno);

        // contoAnna.saldo = 1_000_000;   // ERRORE: saldo è private. Ottimo!
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Crea una classe  Rettangolo  con attributi privati base e altezza,
 *     un costruttore, getter/setter (che rifiutano valori <= 0) e i metodi
 *     getArea(), getPerimetro(), isQuadrato() e toString().
 *  2. Crea una classe  Libro  (titolo, autore, pagine, disponibile).
 *     Aggiungi i metodi presta() e restituisci() che cambiano lo stato
 *     solo se ha senso (non puoi prestare un libro già prestato!).
 *  3. Aggiungi a ContoCorrente un attributo statico che conta i conti
 *     aperti, e un metodo statico per leggerlo.
 *  4. Domanda: perché  getStudentiCreati()  è static, mentre  getMedia()
 *     no? Prova a rendere static getMedia(): che errore dà il compilatore?
 */
