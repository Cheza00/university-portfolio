#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 09:18:13 2019

@author: angelo

Scrivere una funziona RICORSIVA che prende come argomento una stringa di caratteri e una lettera e ritorna il numero di volte che la lettera è presente nella stringa.

es: conta('abracadabra', 'a') -> 5
    conta('abracadabra', 'b') -> 2
    conta('abracadabra', 'z') -> 0
"""

def conta(stringa, lettera):
    if len(stringa) == 1:
        if stringa == lettera:
            return 1
        else:
            return 0
    if stringa[0] == lettera:
        contatore = 1
    else:
        contatore = 0
    return contatore + conta(stringa[1:], lettera)


def conta2(stringa, lettera, contatore = 0):
    if len(stringa) == 1:
        if stringa == lettera:
            return 1 + contatore
        else:
            return contatore
    if stringa[0] == lettera:
        contatore += 1
    return conta2(stringa[1:], lettera, contatore)











