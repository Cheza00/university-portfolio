#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 10:38:13 2019

@author: angelo

Scrivere una funzione RICORSIVA che prende una stringa di caratteri e un intero k e conta all'interno della stringa quante sequenze di caratteri adiacenti uguali lunghe k ci sono.

es: sequenze('aaabbccccddd', 3)->4
    sequenze('aabbececdd', 2)->3

"""

def sequenze(stringa, k):
    if len(stringa) < k:
        return 0
    conta = 0
    if stringa[0]*k == stringa[:k]:
#        if len(set(stringa[:k])) == 1:
        conta = 1
    return conta + sequenze(stringa[1:], k)


def sequenze2(stringa, k):
    if len(stringa) < k:
        return 0
    return sequenze2(stringa[1:], k) + int(stringa[:k] == stringa[0]*k)

def sequenze3(stringa, k):
    if len(stringa) < k:
        return 0
    if len(stringa) == k:
        if stringa[0]*k == stringa[:k]:
            return 1
        else:
            return 0
    return sequenze3(stringa[:k], k) + sequenze3(stringa[1:], k)









