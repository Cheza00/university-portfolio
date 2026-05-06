# -*- coding: utf-8 -*-
"""
Created on Tue Oct  6 08:53:14 2020

@author: Utente
"""

import json

# definisce i dati
dati = [
  { 'nome':'Sofia', 'anno':1973, 'tel':'5553546'},
  { 'nome':'Bruno', 'anno':1981, 'tel':'5558432'},
  { 'nome':'Mario', 'anno':1992, 'tel':'5555092'},
  { 'nome':'Alice', 'anno':1965, 'tel':'5553546'},
]

# salva i dati su disco
with open('dati.json', 'w') as f:
    json.dump(dati,f)

# per verificare il salvataggio, rileggiamo i dati
with open('dati.json') as f:
    nuovi_dati = json.load(f)
print(dati == nuovi_dati)
print(nuovi_dati)