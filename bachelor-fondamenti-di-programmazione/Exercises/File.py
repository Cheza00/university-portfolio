# -*- coding: utf-8 -*-
"""
Created on Mon Oct  5 09:30:44 2020

@author: Utente
"""

def ricerca_linee(nome_file, encoding, stringa):
    '''Ritorna la lista dei numeri delle linee del
    file nome_file in cui appare la stringa.'''
    with open(nome_file, encoding=encoding) as f:
        lista_indici = []
        indice_corrente = 1
        for linea in f:
            if linea.find(stringa) != -1:
                lista_indici.append(indice_corrente)
            indice_corrente += 1
        return lista_indici
    