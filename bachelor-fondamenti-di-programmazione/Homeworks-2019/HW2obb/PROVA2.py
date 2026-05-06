# -*- coding: utf-8 -*-
"""
Created on Fri Nov  1 22:23:47 2019

@author: Utente
"""

def es(lista1, lista2):
    lista3 = lista2.copy()
    var2 = 0
    for elemento in lista1:
        i = 0
        var = str(elemento)
        if var[0] == 'e':
            #print(var[1:])
            while i < len(lista3):
                if int(var[1:]) == lista3[i]:
                    lista3.remove(lista3[i])
                    #print(lista3)
                    break
                i += 1
        else:
            while i < len(lista3):
                if int(var[1:]) == lista3[i]:
                    var2 = lista3.pop(i+1)
                    lista3.insert(i,var2)
                    break
                i += 1
    return lista3



 es(lista1, lista2):
    lista3 = lista2.copy()
    lista4 = lista1.copy()
    for elemento in lista4:
        if elemento[0] == 'e':
            for x in lista3:
                #if elemento[1:] == str(x[1:]):
                if elemento[1:] == str(x):
                    lista3.remove(lista3.index())
            for y in lista4:
                if elemento[1:] == str(y[1:]):
                    lista4.remove(y)
                    
    for elemento in lista4:
        print(lista3)
        var = lista3.index(int(elemento[1:]))
        print(var)
        var2 = lista3.pop(var)
        print(var2)
        lista3.insert(var-1,var2)
        print(lista3)
    return lista3





    lista3 = lista2.copy()
    for elemento in lista1:
        #var = elemento
        if elemento[0] == 'e':
            #var3 = lista3.index(int(elemento[1:]))
            lista3.remove(lista3[lista3.index(int(elemento[1:]))])
        else:
            #var3 = lista3.index(int(elemento[1:]))
            #var2 = lista3.pop(var3+1)
            lista3.insert(lista3.index(int(elemento[1:])),lista3.pop(lista3.index(int(elemento[1:]))+1))
