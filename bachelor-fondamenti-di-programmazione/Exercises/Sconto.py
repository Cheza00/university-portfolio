# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 17:33:21 2020

@author: Utente

Scrivere una funzione che dati in ingresso il prezzo di un prodotto e il valore
percentuale dello sconto, la funzione calcola e stampa a video il prezzo finale
"""

def sconto(prezzo,sconto):
    finale = prezzo - (prezzo*sconto/100)
    print("Il prezzo finale è: ",finale)
    return finale