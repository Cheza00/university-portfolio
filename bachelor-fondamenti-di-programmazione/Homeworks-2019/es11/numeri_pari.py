#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 14:11:13 2019

@author: angelo

Scrivere una funzione RICORSIVA che prende una lista di interi e restituisce il numero di numeri pari nella lista.

es: pari([1,4,6,34,22,3,55,31]) -> 4

lista
[a] + pari(lista)
"""

def pari(lista):
    if len(lista) == 1:
        return 1 - (lista[0] % 2)
    pari_coda = pari(lista[1:])
    return 1 - (lista[0] % 2) + pari_coda