# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 18:53:34 2020

@author: Utente

Scrivere una funzione che prende in input due interi e una stringa. 
In base al contenuto della stringa, che conterrà una operazione, la funzione 
eseguirà le 4 operazioni di base con i due interi: addizione, sottrazione, 
moltiplicazione, divisione.
"""

def operazioni(a,b,stringa):
    if stringa.lower() == 'addizione' or stringa.lower() == 'a' or stringa == '+':
        return a + b
    if stringa.lower() == 'sottrazione' or stringa.lower() == 's' or stringa == '-':
        return a - b    
    if stringa.lower() == 'moltiplicazione' or stringa.lower() == 'm' or stringa == '*':
        return a * b
    if stringa.lower() == 'divisione' or stringa.lower() == 'd' or stringa == '/':
        return a / b
      