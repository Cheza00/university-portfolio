# -*- coding: utf-8 -*-
"""
Created on Sun Dec 29 18:38:47 2019

@author: Utente

Scrivere uno script python che prende in input tre float a, b, c, e calcola e 
stampa l'espressione: CodeCogsEqn.gif (a**2 + (b**3 * c**4)/(3*a) - b + a*c)
"""

a = float(input("Dammi il primo numero: "))
b = float(input("Dammi il secondo numero: "))
c = float(input("Dammi il terzo numero: "))
print("Il risultato dell'equazione è: ", a**2 + (b**3 * c**4)/(3*a) - b + a*c)