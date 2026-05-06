# -*- coding: utf-8 -*-
"""
Created on Sun Dec 29 19:12:10 2019

@author: Utente

Scrivere uno script python che prende in input tre float a, b, c, e calcola e 
stampa le due radici x dell'equazione: CodeCogsEqn1.gif (ax^2 + bx + c = 0)
"""

a = float(input("Dammi il primo numero: "))
b = float(input("Dammi il secondo numero: "))
c = float(input("Dammi il terzo numero: "))
print("La prima radice dell'equazione è", (-b - (b**2 - 4*a*c)**(1/2))/2*a, "e la seconda è", (-b + (b**2 - 4*a*c)**(1/2))/2*a)
