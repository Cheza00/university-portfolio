# -*- coding: utf-8 -*-
"""
Created on Wed Feb 12 01:21:19 2020

@author: Utente
"""

def contoallarovescia_ric(n):
    if n <= 0:
        print("Via!")
    else: 
        print(n)
        contoallarovescia_ric(n-1)
        

def stampa_n_ric(s, n):
    if n <= 0:
        return
    print(s)
    stampa_n_ric(s, n-1) 