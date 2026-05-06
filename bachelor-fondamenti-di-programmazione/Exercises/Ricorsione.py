# -*- coding: utf-8 -*-
"""
Created on Fri Oct  2 17:50:26 2020

@author: Utente
"""

def permute(seq):
    '''Ritorna la lista di tutte le permutazioni
    della sequenza seq'''
    if len(seq) <= 1:
        perms = [seq]
    else:
        perms = []
        for i in range(len(seq)):
            # genera ricorsivamente le permutazioni
            # degli elementi escluso l'i-esimo
            sub = permute(seq[:i]+seq[i+1:])
            for p in sub: # mette in testa l'i-esimo elemento
                perms.append(seq[i:i+1]+p)
    return perms

def permute_print(seq):
    '''Ritorna la lista di tutte le permutazioni
    della sequenza seq'''
    print('  '*(3-len(seq)),'chiamata',seq)
    if len(seq) <= 1:
        perms = [seq]
    else:
        perms = []
        for i in range(len(seq)):
            sub = permute_print(seq[:i]+seq[i+1:])
            for p in sub:
                perms.append(seq[i:i+1]+p)
    print('  '*(3-len(seq)),'ritorna',perms)
    return perms