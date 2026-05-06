# -*- coding: utf-8 -*-
"""
Created on Sun Dec 29 18:30:14 2019

@author: Utente

Scrivere uno script python che prende in input due float a e b e calcola e 
stampa c, corrispondente all'ipotenusa del triangolo rettangolo avente per 
cateti due lati di lunghezza a e b, rispettivamente
"""

a = float(input("Dammi il primo cateto: "))
b = float(input("Dammi il secondo cateto: "))
c = (a**2 + b**2)**(1/2)
print("L'ipotenusa del triangolo è: ", c)