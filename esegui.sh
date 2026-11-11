#!/usr/bin/env bash
# ------------------------------------------------------------------
#  Compila tutto il corso ed esegue una lezione.
#
#  Uso:
#     ./esegui.sh            -> mostra l'elenco delle lezioni
#     ./esegui.sh 7          -> compila ed esegue la lezione 7
#     ./esegui.sh 2b         -> lezione 2 bis (input da tastiera)
#     ./esegui.sh tutte      -> esegue tutte le lezioni in fila
# ------------------------------------------------------------------
set -e
cd "$(dirname "$0")"

declare -a LEZIONI=(
  "1|lezione01_ciaomondo.CiaoMondo"
  "2|lezione02_variabili.Variabili"
  "2b|lezione02_variabili.LeggereInput"
  "3|lezione03_condizioni.Condizioni"
  "4|lezione04_cicli.Cicli"
  "5|lezione05_array.Array"
  "6|lezione06_metodi.Metodi"
  "7|lezione07_classi.ClassiEOggetti"
  "8|lezione08_ereditarieta.Ereditarieta"
  "9|lezione09_astratte.ClassiAstratte"
  "10|lezione10_interfacce.Interfacce"
  "11|lezione11_polimorfismo.Polimorfismo"
  "12|lezione12_eccezioni.Eccezioni"
  "13|lezione13_collezioni.Collezioni"
  "14|lezione14_enum_record.EnumERecord"
  "15|lezione15_progetto_biblioteca.ProgettoBiblioteca"
)

compila() {
  rm -rf out
  mkdir -p out
  javac -encoding UTF-8 -d out $(find src -name "*.java")
}

esegui() {
  echo "=================================================================="
  echo " >> java $1"
  echo "=================================================================="
  java -cp out "$1"
  echo
}

if [ -z "$1" ]; then
  echo "Uso: ./esegui.sh <numero lezione> | tutte"
  echo
  for voce in "${LEZIONI[@]}"; do
    printf "  %-4s %s\n" "${voce%%|*}" "${voce#*|}"
  done
  exit 0
fi

compila

if [ "$1" = "tutte" ]; then
  for voce in "${LEZIONI[@]}"; do
    [ "${voce%%|*}" = "2b" ] && continue   # salta quella interattiva
    esegui "${voce#*|}"
  done
  exit 0
fi

for voce in "${LEZIONI[@]}"; do
  if [ "${voce%%|*}" = "$1" ]; then
    esegui "${voce#*|}"
    exit 0
  fi
done

echo "Lezione '$1' non trovata. Lancia ./esegui.sh senza argomenti per l'elenco."
exit 1
