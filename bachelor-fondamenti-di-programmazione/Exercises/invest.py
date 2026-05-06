# -*- coding: utf-8 -*-
"""
Created on Wed Jan  8 12:03:48 2020

@author: Utente

Scrivere una funzione 'invest' nel file 'invest.py', che prende in input un
capitale, un interesse annuale e un numero di anni e ritorna come intero il 
capitale maturato dopo un investimento di n anni all'interesse i. Usare la 
formula maturato = capitale * (1+interesse/100)**anni.
Usare il seguente programma per testare la corretta implementazione: 
test_invest.py.txt
"""

def invest(capitale,i,n):
    maturato = capitale * (1+i/100)**n
    return int(maturato)