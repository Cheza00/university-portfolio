#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 14:35:09 2019

@author: angelo

Scrivere una funzione RICORSIVA o che fa uso di una funzione ricorsiva che prende una stringa di caratteri e ritorna una lista contenente le sottostringhe composte da vocali ripetute consecutive.

es: vocali_ripetute('aaabbeeeeeiio')->
['aaa','eeeee', 'ii']
"""

def iniziale_ripetuta(stringa):
    """ funzione ricorsiva che ritorna la sottostringa composta dalla iniziale di stringa più tutte le sue ripetizioni adiacenti

    es: iniziale_ripetuta('aaabbeee')-> 'aaa'

Equivalente di questo codice

vocali = stringa[0]
for lettera in stringa[1:]:
    if lettera == stringa[0]:
        vocali += lettera
    else:
        break
return vocali
"""
    if len(stringa) <= 1:
        return stringa
    if stringa[0] == stringa[1]:
        return stringa[0]+iniziale_ripetuta(stringa[1:])
    return stringa[0]


def vocali_ripetute(stringa):
    if stringa == '':
        return []
    if stringa[0] in 'aeiou':
        vocali = iniziale_ripetuta(stringa)
        if len(vocali)>=2:
            return [vocali] + vocali_ripetute(stringa[len(vocali):])
    return vocali_ripetute(stringa[1:])






