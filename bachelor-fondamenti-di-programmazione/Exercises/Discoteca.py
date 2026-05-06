# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 18:39:29 2020

@author: Utente

Scrivere una funzione che dati in ingresso il numero dei maschi e delle femmine
che entrano in discoteca, la funzione calcola e stampa a video il prezzo 
totale sapendo che i primi pagano 12€ e le seconde 10€
"""

def disco(maschi,femmine):
    totale = maschi * 12 + femmine * 10
    print("La spesa totale è: ",totale)
    #return totale