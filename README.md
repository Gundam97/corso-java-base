# Corso di Java da zero

Un percorso in 15 lezioni per imparare le basi di Java, dai primi passi alla programmazione a oggetti.
Ogni lezione è un programma Java **eseguibile** e **commentato in italiano**: si legge il codice, si esegue, si confronta l'output con i commenti e poi si fanno gli esercizi in fondo al file.

> Requisito: Java 17 o superiore (`java -version` per controllare).

---

## Come eseguire le lezioni

### Da terminale (macOS / Linux)

```bash
./esegui.sh          # elenco delle lezioni
./esegui.sh 1        # esegue la lezione 1
./esegui.sh tutte    # le esegue tutte in fila
```

### A mano, senza script (vale anche su Windows)

```bash
javac -encoding UTF-8 -d out src/lezione01_ciaomondo/CiaoMondo.java
java -cp out lezione01_ciaomondo.CiaoMondo
```

`javac` **compila** (da `.java` a `.class` nella cartella `out`), `java` **esegue**.
Per le lezioni composte da più file conviene compilare tutto insieme:
`javac -encoding UTF-8 -d out $(find src -name "*.java")`.

### Con IntelliJ IDEA

1. *File → Open* e scegli la cartella `corso-java-base`.
2. Tasto destro su `src` → *Mark Directory as → Sources Root*.
3. Imposta un JDK 17+ (*File → Project Structure → SDK*).
4. Apri un file con il `main` e premi il triangolo verde ▶ accanto alla classe.

---

## Il percorso

| #  | Lezione | Argomenti | File da cui partire |
|----|---------|-----------|---------------------|
| 1  | Ciao mondo | Struttura di un programma, `main`, stampa, commenti, compilazione | `CiaoMondo.java` |
| 2  | Variabili | Tipi primitivi, `String`, operatori, casting, `final`, `var` | `Variabili.java` |
| 2b | Input | Leggere da tastiera con `Scanner` | `LeggereInput.java` |
| 3  | Condizioni | `if` / `else`, operatori logici, ternario, `switch` classico e moderno | `Condizioni.java` |
| 4  | Cicli | `for`, `while`, `do-while`, `break`, `continue`, cicli annidati | `Cicli.java` |
| 5  | Array | Array, for-each, algoritmi classici (max, ricerca, ordinamento), matrici | `Array.java` |
| 6  | Metodi | Parametri, `return`, **overloading**, passaggio per valore, ricorsione, scope | `Metodi.java` |
| 7  | Classi e oggetti | Attributi, costruttori, `this`, **incapsulamento**, getter/setter, `static`, `toString` | `Studente.java` → `ClassiEOggetti.java` |
| 8  | Ereditarietà | **`extends`**, `super`, **overriding**, `@Override`, `protected`, `final` | `Veicolo.java` → `Ereditarieta.java` |
| 9  | Classi astratte | `abstract`, metodi astratti, contratti tra classi | `Forma.java` → `ClassiAstratte.java` |
| 10 | Interfacce | **`interface`**, **`implements`**, implementazione multipla, metodi `default` e `static`, classi anonime | `Volante.java` → `Interfacce.java` |
| 11 | Polimorfismo | Upcasting, downcasting, `instanceof`, binding dinamico, **overloading vs overriding**, `equals`/`hashCode` | `Polimorfismo.java` |
| 12 | Eccezioni | `try` / `catch` / `finally`, `throw`, `throws`, checked vs unchecked, eccezioni personalizzate | `Eccezioni.java` |
| 13 | Collezioni e generics | `ArrayList`, `HashSet`, `HashMap`, `Comparable`, `Comparator`, lambda, classi generiche | `Collezioni.java` |
| 14 | Enum e record | `enum` con attributi e metodi, `record` | `EnumERecord.java` |
| 15 | **Progetto finale** | Una biblioteca che usa tutto quanto visto | `Materiale.java` → `ProgettoBiblioteca.java` |

Ogni file con più classi indica in testa **l'ordine di lettura**.

---

## Mappa dei concetti chiave

| Concetto | Dove si vede meglio |
|----------|---------------------|
| Overloading di metodi | Lezione 6 (`somma(...)`), Lezione 15 (`Biblioteca.cerca(...)`) |
| Overloading di costruttori | Lezione 7 (`Studente`, `ContoCorrente`) |
| Overriding | Lezione 8 (`getVelocitaMassima`, `suonaClacson`), Lezione 9 (`getArea`) |
| `super(...)` e `super.metodo()` | Lezione 8 (`Automobile.descrizione()`) |
| `extends` | Lezione 8, 9, 15 |
| `implements` (anche multiplo) | Lezione 10 (`Anatra implements Volante, Nuotatore`) |
| Classe astratta vs interfaccia | Commenti in `Volante.java` (lezione 10) |
| Overloading vs overriding a confronto | Tabella in `Automobile.java` + punto 5 di `Polimorfismo.java` |
| Incapsulamento | Lezione 7 (`ContoCorrente`) |
| Polimorfismo | Lezione 11 (e anteprime in 8, 9, 10) |

---

## Suggerimenti per chi insegna

- **Fate prevedere l'output.** Prima di eseguire una lezione, chiedete allo studente cosa si aspetta che venga stampato. È il modo più rapido per scoprire cosa non è chiaro.
- **Rompete il codice di proposito.** Molti commenti dicono «prova a decommentare questa riga». Leggere gli errori del compilatore insieme insegna moltissimo.
- **Una lezione non è un incontro.** Le lezioni 1-6 sono rapide; dalla 7 in poi conviene dedicare a ciascuna più tempo, perché lì si costruisce il modo di pensare «a oggetti».
- **Le lezioni 7 → 11 sono una progressione unica:** classe → ereditarietà → astrazione → interfacce → polimorfismo. Se un concetto non è chiaro, conviene tornare indietro di una lezione invece di andare avanti.
- **Gli esercizi** sono in fondo a ogni file, in ordine di difficoltà crescente. Lo studente può risolverli creando nuovi file nello stesso package.

## Struttura del progetto

```
corso-java-base/
├── README.md
├── esegui.sh
└── src/
    ├── lezione01_ciaomondo/
    ├── lezione02_variabili/
    ├── ...
    └── lezione15_progetto_biblioteca/
```
