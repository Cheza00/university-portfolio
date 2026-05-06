#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 10:07:46 2019

@author: angelo
Scrivere una funzione RICORSIVA che prende come parametro una stringa di caratteri e ritorna il numero di coppie di caratteri adiacenti uguali nella stringa.

es: "adiacenti('cappotto')->2"
    "adiacenti('rosso')->1"
    "adiacenti('giallorosso')->2"
    "adiacenti('aaaaa')->4"
"""

def adiacenti(stringa, precedente = ''):
    if len(stringa) == 1:
        return int(precedente == stringa)
    return adiacenti(stringa[1:], stringa[0]) + int(precedente == stringa[0])