# -*- coding: utf-8 -*-
"""
Created on Wed Jan 29 21:17:04 2020

@author: Utente
"""

def check_date(mese, giorno):
    if giorno < 1: return False
    if mese == 'feb':
        return giorno <= 28
    elif mese == ['apr', 'giu', 'set', 'nov']:
        return giorno <= 30
    elif mese == ['gen', 'mar', 'mag', 'lug', 'ago', 'ott', 'dic']:
        return giorno <= 31
    else: 
        return False 