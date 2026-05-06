# -*- coding: utf-8 -*-

''' 
Abbiamo una stringa int_seq contenente una sequenza di interi non-negativi
    separati da virgole ed un intero positivo subtotal.

Progettare una funzione ex1(int_seq, subtotal) che
    riceve come argomenti la stringa int_seq e l'intero subtotal e
    restituisce il numero di sottostringhe di int_seq
    la somma dei cui valori è subtotal.

Ad esempio, per int_seq='3,0,4,0,3,1,0,1,0,1,0,0,5,0,4,2' e subtotal=9,
    la funzione deve restituire 7.

Infatti:
'3,0,4,0,3,1,0,1,0,1,0,0,5,0,4,2'
 _'0,4,0,3,1,0,1,0'_____________
 _'0,4,0,3,1,0,1'_______________
 ___'4,0,3,1,0,1,0'_____________
____'4,0,3,1,0,1'_______________
____________________'0,0,5,0,4'_
______________________'0,5,0,4'_
 _______________________'5,0,4'_

NOTA: è VIETATO usare/importare ogni altra libreria a parte quelle già presenti

NOTA: il timeout previsto per questo esercizio è di 1 secondo per ciascun test (sulla VM)

ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8
    (ad esempio editatelo dentro Spyder)
'''

def ex1(int_seq, subtotal):
    interi = int_seq.split(',')
    i = 0
    n = len(interi)
    f = 0
    tot = 0
    c = 0
    while f < n:
        while (tot <= subtotal) and (f < n):
            tot += int(interi[f])
            f += 1
            if tot == subtotal:
                c += 1
                c = funzione(c, interi, subtotal, i, f, tot)
                print(c)
        while (tot >= subtotal) and (f < n):
            tot -= int(interi[i])
            i += 1
            if tot == subtotal:
                c += 1
                print(c)
    return c

def funzione(c, interi, subtotal, i, f, tot):
    i += 1
    tot -= int(interi[i])
    while tot == subtotal and i <= f:
        c += 1
        i += 1
        tot -= int(interi[i])
    return c


if __name__ == '__main__':
    # Inserisci qui i tuoi test personali
    pass

