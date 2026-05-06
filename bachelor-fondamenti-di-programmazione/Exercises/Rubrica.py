# -*- coding: utf-8 -*-
"""
Created on Sat Aug 22 12:12:19 2020

@author: Utente
"""

def ricerca(elenco, nome):
    '''Ritorna il numero di telefono nell'elenco
    per la riga con nome nome.'''
    nome = nome.lower()
    elenco = elenco.lower().splitlines()
    for e in elenco:
        e_nome, e_num = e.split(':')
        if nome == e_nome.strip():
            return e_num.strip()
    return 'Non esiste'


def rubrica_to_dict(elenco):
    '''Convertire un elenco da testo a tabella.'''
    d = {}
    elenco = elenco.lower().splitlines()
    for e in elenco:
        nome, numero = e.split(':')
        d[nome.strip()] = numero.strip()
    return 



