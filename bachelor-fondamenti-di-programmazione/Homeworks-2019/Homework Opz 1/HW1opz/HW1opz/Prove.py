# -*- coding: utf-8 -*-
"""
Created on Wed Oct 23 00:13:41 2019

@author: Utente
"""

def es1(S,m):
    conta = 0
    lista = S.split(sep=',')
    #lista2 = []
    #for elemento in lista:
        #lista2.append(int(elemento))
    j = 0 
    while j <= len(lista):
        somma = 0
        i = j
        #print (i)
        if int(lista[j]) == m:
            conta += 1
        if int(lista[j]) < m:
            while i < len(lista) and somma <= m:
                somma += int(lista[i])
                #print(somma)
                if somma == m:
                    conta += 1
                i += 1    
        j += 1
    return conta
        


