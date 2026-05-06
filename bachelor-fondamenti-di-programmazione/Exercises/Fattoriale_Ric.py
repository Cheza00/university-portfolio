# -*- coding: utf-8 -*-
"""
Created on Tue Feb 11 17:22:43 2020

@author: Utente
"""

def fattoriale_ric(n):
    if n == 0:
        return 1
    return n * fattoriale_ric(n-1)


def fattoriale_ric2(n):
    if n == 0:
        return 1
    else:
        ricors = fattoriale_ric2(n-1)
        risultato = n * ricors
        return risultato