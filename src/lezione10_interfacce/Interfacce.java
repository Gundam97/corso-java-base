package lezione10_interfacce;

/*
 * =====================================================================
 *  LEZIONE 10 - INTERFACCE: il main
 * =====================================================================
 */
public class Interfacce {

    // Questo metodo accetta QUALSIASI cosa sappia volare: animali, aerei,
    // droni... Non gli interessa cosa sia, solo che rispetti il contratto.
    static void spettacoloAereo(Volante[] partecipanti) {
        for (Volante v : partecipanti) {
            v.decolla();
            System.out.println("    -> raggiunge " + v.getAltitudineMassima() + " m");
            v.atterra();
        }
    }

    static void garaDiNuoto(Nuotatore[] partecipanti, int profonditaRichiesta) {
        for (Nuotatore n : partecipanti) {
            n.nuota();
            String esito = n.puoImmergersiA(profonditaRichiesta) ? "SÌ" : "NO";
            System.out.println("    -> può scendere a " + profonditaRichiesta + " m? " + esito);
        }
    }

    public static void main(String[] args) {

        Anatra paperino = new Anatra("Paperino");
        Pesce nemo = new Pesce("Nemo", 50);
        Aereo volo = new Aereo("ItaAir");

        // -------------------------------------------------------------
        // 1. Lo stesso oggetto può essere visto con TIPI DIVERSI
        // -------------------------------------------------------------
        System.out.println("=== Un'anatra ha tante 'facce' ===");
        Anatra comeAnatra = paperino;
        Animale comeAnimale = paperino;
        Volante comeVolante = paperino;
        Nuotatore comeNuotatore = paperino;

        comeAnimale.presentati();   // vede solo i metodi di Animale
        comeVolante.decolla();      // vede solo i metodi di Volante
        comeNuotatore.nuota();      // vede solo i metodi di Nuotatore
        // comeVolante.nuota();     // ERRORE: attraverso "Volante" non si vede nuota()
        System.out.println("  Sono tutti lo stesso oggetto? "
                + (comeAnatra == comeVolante && comeVolante == comeNuotatore));

        // -------------------------------------------------------------
        // 2. Un'interfaccia come tipo: oggetti diversissimi insieme
        // -------------------------------------------------------------
        System.out.println("\n=== Spettacolo aereo (Volante) ===");
        Volante[] volanti = {paperino, volo};
        spettacoloAereo(volanti);

        System.out.println("\n=== Gara di nuoto (Nuotatore) ===");
        Nuotatore[] nuotatori = {paperino, nemo};
        garaDiNuoto(nuotatori, 10);

        // -------------------------------------------------------------
        // 3. Metodo statico e costante dell'interfaccia
        // -------------------------------------------------------------
        System.out.println("\n=== Statici e costanti ===");
        Volante campione = Volante.chiVolaPiuInAlto(paperino, volo);
        System.out.println("  Vola più in alto: " + campione);
        System.out.println("  Costante: " + Volante.ALTITUDINE_MAX_TERRESTRE);

        // -------------------------------------------------------------
        // 4. instanceof con le interfacce
        // -------------------------------------------------------------
        System.out.println("\n=== Chi sa fare cosa? ===");
        Animale[] zoo = {paperino, nemo};
        for (Animale a : zoo) {
            a.presentati();
            if (a instanceof Volante) System.out.println("    ...e so volare!");
            if (a instanceof Nuotatore) System.out.println("    ...e so nuotare!");
        }

        // -------------------------------------------------------------
        // 5. CLASSE ANONIMA: implementare un'interfaccia "al volo",
        //    senza creare un file apposta. Utile per usi una-tantum.
        // -------------------------------------------------------------
        System.out.println("\n=== Classe anonima ===");
        Volante drone = new Volante() {
            @Override
            public void decolla() {
                System.out.println("  Drone: bzzzz, decollo verticale!");
            }

            @Override
            public int getAltitudineMassima() {
                return 120;
            }
        };
        drone.decolla();
        drone.atterra();
    }
}

/*
 * ---------------------------------------------------------------------
 *  ESERCIZI
 * ---------------------------------------------------------------------
 *  1. Crea un Pinguino: è un Animale, nuota ma NON vola.
 *  2. Crea un'interfaccia  Corridore  con  corri()  e  getVelocita().
 *     Fai in modo che Anatra la implementi (3 interfacce!) e crea un
 *     Ghepardo che sia Animale e Corridore.
 *  3. Crea un'interfaccia  Pagabile  con  double calcolaImporto().
 *     Implementala in  Fattura  e  Stipendio  (classi non imparentate) e
 *     scrivi un metodo che somma gli importi di un array di Pagabile.
 *  4. Cosa succede se due interfacce implementate hanno lo stesso metodo
 *     default? Prova! (il compilatore ti obbligherà a ridefinirlo)
 */
