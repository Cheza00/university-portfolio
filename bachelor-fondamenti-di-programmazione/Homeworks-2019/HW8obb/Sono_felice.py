# -*- coding: utf-8 -*-
"""
Created on Tue Dec 17 22:25:11 2019

@author: Utente
"""

def prova_del_cazzo(lista):
    lista2 = []
    if lista[0] != 'a':
        return lista[0]
    lista2.append(prova_del_cazzo(lista[1:])[:-1])
    return lista2