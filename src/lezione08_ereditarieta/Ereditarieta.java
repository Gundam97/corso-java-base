package lezione08_ereditarieta;

/*
 * =====================================================================
 *  LEZIONE 8 - EREDITARIETÀ: il main
 * =====================================================================
 *  Osserva con attenzione:
 *   1) l'ORDINE in cui vengono stampati i messaggi dei costruttori
 *   2) quale versione dei metodi viene eseguita per ogni oggetto
 */
public class Ereditarieta {

    public static void main(String[] args) {

        // -------------------------------------------------------------
        // 1. CATENA DEI COSTRUTTORI
        //    Creando un oggetto della sottoclasse, PRIMA viene eseguito il
        //    costruttore della superclasse, POI quello della sottoclasse.
        //    (Prima si costruiscono le fondamenta, poi i piani superiori.)
        // -------------------------------------------------------------
        System.out.println("Creo un Veicolo generico:");
        Veicolo carretto = new Veicolo("Generico", "Carretto", 1950);

        System.out.println("\nCreo un'Automobile:");
        Automobile panda = new Automobile("Fiat", "Panda", 2018, 5);

        System.out.println("\nCreo una Moto:");
        Moto ducati = new Moto("Ducati", "Monster", 2021, false);

        System.out.println("\nCreo un'AutoSportiva (3 livelli!):");
        AutoSportiva ferrari = new AutoSportiva("Ferrari", "Roma", 2023, 620);

        // -------------------------------------------------------------
        // 2. METODI EREDITATI: Automobile non definisce accelera() né frena(),
        //    ma li ha ereditati da Veicolo.
        // -------------------------------------------------------------
        System.out.println("\n--- Metodi ereditati ---");
        panda.accelera(50);
        panda.frena();
        System.out.println("Velocità Panda: " + panda.getVelocita());
        System.out.println("Anno Panda (metodo final ereditato): "
                + panda.getAnnoImmatricolazione());

        // -------------------------------------------------------------
        // 3. OVERRIDING: stesso metodo, comportamento diverso
        // -------------------------------------------------------------
        System.out.println("\n--- Overriding ---");
        System.out.println("Carretto clacson: " + carretto.suonaClacson());
        System.out.println("Panda clacson:    " + panda.suonaClacson());
        System.out.println("Ducati clacson:   " + ducati.suonaClacson());
        System.out.println("Ferrari clacson:  " + ferrari.suonaClacson()
                + "  (ereditato da Automobile)");

        // Tutti accelerano di 500 ma si fermano alla PROPRIA velocità massima
        carretto.accelera(500);
        panda.accelera(500);
        ducati.accelera(500);
        ferrari.accelera(500);
        System.out.println();
        System.out.println(carretto);
        System.out.println(panda);
        System.out.println(ducati);
        System.out.println(ferrari);

        // super.descrizione(): ogni livello aggiunge un pezzo
        System.out.println("\nDescrizione Ferrari: " + ferrari.descrizione());

        // -------------------------------------------------------------
        // 4. OVERLOADING nella sottoclasse
        // -------------------------------------------------------------
        System.out.println("\n--- Overloading ---");
        Automobile golf = new Automobile("VW", "Golf", 2020, 5);
        golf.accelera(30);        // versione ereditata accelera(int)
        System.out.println("Golf dopo accelera(30): " + golf.getVelocita());
        golf.accelera(30, true);  // versione nuova accelera(int, boolean)
        System.out.println("Golf dopo accelera(30, turbo): " + golf.getVelocita());

        // -------------------------------------------------------------
        // 5. METODI SPECIFICI delle sottoclassi
        // -------------------------------------------------------------
        System.out.println("\n--- Metodi specifici ---");
        golf.apriBagagliaio();    // rifiuta: è in movimento
        ducati.impenna();
        // carretto.impenna();    // ERRORE: Veicolo non ha il metodo impenna()

        // -------------------------------------------------------------
        // 6. ANTEPRIMA DI POLIMORFISMO (approfondito nella lezione 11)
        //    Una variabile di tipo Veicolo può contenere QUALSIASI oggetto
        //    che "è un" Veicolo. Il metodo eseguito dipende dall'OGGETTO
        //    reale, non dal tipo della variabile!
        // -------------------------------------------------------------
        System.out.println("\n--- Anteprima polimorfismo ---");
        Veicolo[] garage = {carretto, panda, ducati, ferrari};
        for (Veicolo v : garage) {
            System.out.printf("%-38s max %3d km/h, clacson: %s%n",
                    v.descrizione(), v.getVelocitaMassima(), v.suonaClacson());
        }

        // instanceof: verifica se un oggetto è di un certo tipo
        System.out.println("\nferrari instanceof AutoSportiva? " + (ferrari instanceof AutoSportiva));
        System.out.println("ferrari instanceof Automobile?   " + (ferrari instanceof Automobile));
        System.out.println("ferrari instanceof Veicolo?      " + (ferrari instanceof Veicolo));
        System.out.println("ferrari instanceof Object?       " + (ferrari instanceof Object));
        Veicolo qualcosa = ducati;
        System.out.println("qualcosa (una Moto) instanceof Automobile? " + (qualcosa instanceof Automobile));
        // NB: scrivere direttamente  ducati instanceof Automobile  dà ERRORE di
        // compilazione: il compilatore sa già che una Moto non potrà MAI
        // essere un'Automobile (sono "rami" diversi della gerarchia).
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Aggiungi una classe  Camion extends Veicolo  con un attributo
 *     caricoMassimo, velocità massima 90 e un clacson personalizzato.
 *  2. Aggiungi a Camion un metodo  carica(int kg)  che rifiuta carichi
 *     oltre il massimo.
 *  3. Crea una gerarchia  Persona -> Dipendente -> Manager:
 *       - Persona: nome, cognome, presentati()
 *       - Dipendente: stipendio, ridefinisce presentati() usando super
 *       - Manager: bonus, ridefinisce getStipendio() sommando il bonus
 *  4. Prova a far estendere AutoSportiva da una nuova classe: leggi
 *     l'errore. Poi prova a togliere super(...) dal costruttore di Moto.
 */
