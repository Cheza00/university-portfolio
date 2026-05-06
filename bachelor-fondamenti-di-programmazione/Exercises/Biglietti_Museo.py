# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 17:38:43 2020

@author: Utente

Scrivere una funzione che dati in ingresso il costo del biglietto per visitare 
un museo e il numero di studenti di una classe in gita scolastica, ritorna la
spesa totale, considerando che se gli studenti sono compresi fra 10 e 30 il
biglietto per studente costa il 20% in meno, se sono 31 o più il 30% in meno.
"""

def costo_museo(costo,studenti):
    if studenti < 10:
        spesa = costo * studenti
    elif studenti < 31:
        spesa = (costo - costo*0.2) * studenti  # i float si scrivono con il punto
    else:
        spesa = (costo - costo*0.3) * studenti
    return spesa