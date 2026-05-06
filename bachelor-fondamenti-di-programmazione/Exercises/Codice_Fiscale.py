# -*- coding: utf-8 -*-
"""
Created on Thu Jan 30 14:13:48 2020

@author: Utente
"""

def print_cf(cf):
    print('Cognome (tre lettere):', cf[0:3])
    print('Nome (tre lettere):', cf[3:6])
    print('Data di nascita e sesso:', cf[6:11])
    print('Comune di nascita:', cf[11:15])
    print('Carattere di controllo:', cf[-1])