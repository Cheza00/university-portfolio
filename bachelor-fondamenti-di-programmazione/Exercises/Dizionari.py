# -*- coding: utf-8 -*-
"""
Created on Wed Nov  4 02:46:11 2020

@author: Utente
"""

def aggiorna_dict(d, d2):
    '''Aggiunge a d gli elementi in d2.'''
    for k, v in d2.items():
        d[k] = v
        
rubrica = { 'Sergio': '112233',
            'Mario': '654321'}
rubrica2 = { 'Sergio': '333333',
             'Maria': '222222'}
aggiorna_dict(rubrica, rubrica2)
print(rubrica)

cubi = { i: i**3 for i in range(3) }
print(cubi)