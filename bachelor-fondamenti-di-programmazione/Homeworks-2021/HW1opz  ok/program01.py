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
     lista = int_seq.split(sep=',')
     i = 0
     f = 0
     temp = 0
     conta = 0
     controllo = False
     parziale = int(lista[i])
     while f < len(lista):
         if i > f:
             f, parziale = iMaggiore(f, lista, parziale)
         if parziale == subtotal:
             conta, controllo, temp, f, parziale = uguale(conta, controllo, temp, f, lista, parziale)
         elif parziale < subtotal:
             f += 1
             if f < len(lista):
                 parziale += int(lista[f])
         else:   # quando è più grande
             parziale -= int(lista[i])
             i += 1
             controllo = False
             if temp >= i:
                 while f > temp:
                     parziale -= int(lista[f])
                     f -= 1
     conta = incrementa_i(lista, subtotal, parziale, conta, i, f, temp)
     return conta
 
def iMaggiore(f, lista, parziale):
    f += 1   
    if f < len(lista):
        parziale += int(lista[f])
    return f, parziale

def uguale(conta, controllo, temp, f, lista, parziale):
    conta += 1
    if controllo == False:
        temp = f
    f += 1
    controllo = True
    if f < len(lista):
        parziale += int(lista[f])
    return conta, controllo, temp, f, parziale
         
def incrementa_i(lista, subtotal, parziale, conta, i, f, temp):
    temp2 = temp
    temp = f
    while parziale == subtotal and i < temp:
        parziale -= int(lista[i])
        i += 1
        if parziale == subtotal:
            conta += 1
        while temp - 1 > temp2:
            if temp < len(lista):
                parziale -= int(lista[temp])
            temp -= 1
            if parziale == subtotal:
                conta += 1
        temp = f
    return conta
    


if __name__ == '__main__':
    # Inserisci qui i tuoi test personali
    pass