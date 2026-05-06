# -*- coding: utf-8 -*-
''' 
Abbiamo una lista contenente stringhe di interi positivi (in ogni stringa gli interi
compaiono separati da virgole).
Considera due stringhe A e B della lista e sia a il numero massimo in A e b il numero
massimo in B, diciamo che le due liste A e B sono compatibili se tutti i numeri di A e B
diversi dai due massimi a e b hanno valore che non supera  il minimo tra a e b.
Ad esempio
- A='4,6,4,5,7,3' e B='7,9,4,4,1' sono compatibili perche' tutti i numeri in A e B
  diversi da 7 e 9 sono inferiori a 7=min(7,9)
- A='4,6,4,5,7,3' e B='7,9,8,4,1' NON sono compatibili perche' in B e' presente il
  numero 8 diverso da 7 e da 9 e 8>7=min(7,9)

Progettare una funzione es1(ls) che prende in input  la lista di stringhe ls e
restituisce il massimo indice j per cui tutte le j+1 stringhe agli indici fino a j incluso
ls[0], ....ls[j] risultano a coppie compatibili.

Ad esempio es1(ls) con
ls=[
  '1,3,4,2,6',
  '1,4,3,3,5',
  '6,3,1,4,1',
  '1,7,4,3,2',
  '1,2,3,4,7',
  '1,6,3,4,2',
  '1,2,6,4,3',
  '7,2,3,4,1',
  '8,1,2,6,1',
  '1,4,3,2,7',
  '1,8,3,4,2',
  '9,2,3,4,9'
  ]
  restituisce 7 perchè le prime 8 stringhe sono a due a due compatibili,
  mentre se prendiamo le prime 8 stringhe ne esistono almeno due non compatibili.
      
NOTA: è VIETATO usare/importare ogni altra libreria a parte quelle già presenti

NOTA: il timeout previsto per questo esercizio è di 1.5 secondi per ciascun test (sulla VM)

ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8
    (ad esempio editatelo dentro Spyder)

'''

def es1(ls):
    # inserisci qui il tuo codice
    pass




if __name__ == '__main__':
    pass
    # inserisci qui i tuoi test personali
