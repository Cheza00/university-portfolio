# -*- coding: utf-8 -*-
"""
Created on Sun Nov 17 17:52:01 2019

@author: Utente
"""

   
'''

def es1(ftesto):
    d = {}
    l = []
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    print(type(stringhe))
    f.close()
    i = 0
    stringhe.reverse()
    for x in stringhe:
        if len(x) >= 2:
            stringhe[i] = x.replace('\n','')
            if stringhe[i+1] >=2:
                stringhe[i+1] += stringhe[i] 
                #print(l)
        i += 1
    print(stringhe)
    

def es1(ftesto):
    d = {}
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    #print(type(stringhe))
    f.close()
    i = 0
    c = 0
    #stringhe.reverse()
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
             i += 1 #stringhe[i+1] += stringhe[i] 
        c += 1
    print(d)






def es1(ftesto):
    d = {} 
    insieme = set()
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    #print(type(stringhe))
    f.close()
    i = 0
    c = 0
    #stringhe.reverse()
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
             i += 1 #stringhe[i+1] += stringhe[i] 
        c += 1
    print(d)
    for chiave in d:
        conteggio = 
        for carattere in d[chiave]:
            for d in d[chiave][carattere]:
                insieme.add(d[chiave][carattere:carattere+val-1])
    print(insieme)
    














def es1(ftesto):
    d = {} 
    diz = {}
    #insieme = set()
    #insieme2 = set()
    f = open(ftesto,'r')
    val = int(f.readline())
    #n = val
    stringhe = f.readlines()
    #print(type(stringhe))
    f.close()
    i = 0
    c = 0
    #stringhe.reverse()
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
             i += 1 #stringhe[i+1] += stringhe[i] 
        c += 1
    print(d)
    #for j in range(0,len(d[0])):
    j = 0
    while val <= len(d[0]):
        print(diz)
        diz[j] = d[0][j:val]
        val += 1
        j += 1
    print(diz)
    p = 0
    for elemento in diz[p]:
        for k in d:
            contenitore = d[k]
            if contenitore.find(elemento) == -1:
                diz[p] = 0
                break
            print('primo',diz)
        p += 1
        print('secondo',diz)
    print('terzo',diz)
                
                

















def es1(ftesto):
    d = {} 
    diz = {}
    insieme = set()
    insieme2 = set()
    insieme3 = set()
    f = open(ftesto,'r')
    val = int(f.readline())
    #n = val
    stringhe = f.readlines()
    #print(type(stringhe))
    f.close()
    i = 0
    c = 0
    #stringhe.reverse()
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
             i += 1 #stringhe[i+1] += stringhe[i] 
        c += 1
    print(d)
    #for j in range(0,len(d[0])):
    j = 0
   # insieme3.clear()
    while val <= len(d[0]):
        #print(diz)
        insieme = d[0][j:val]
        val += 1
        j += 1
    print(insieme)
    for a in d:
        insiemi(val, d, a, insieme, insieme2, insieme3)
        insieme.clear()
        insieme.copy(insieme3)
        
    p = 0
    for elemento in diz[p]:
        for k in d:
            contenitore = d[k]
            if contenitore.find(elemento) == -1:
                diz[p] = 0
                break
            print('primo',diz)
        p += 1
        print('secondo',diz)
    print('terzo',diz)
                
                
def insiemi(val, d, a, insieme, insieme2, insieme3):
    j = 0
    insieme2.clear()
    insieme3.clear()
    while val <= len(d[a]):
        #print(diz)
        insieme2 = d[0][j:val]
        val += 1
        j += 1
    insieme3 = insieme.intersection(insieme2)
    return insieme3    
    
    
    
    
    
    def es1(ftesto):
    d = {} 
    lista = []
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    f.close()
    i = 0
    c = 0
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
            c += 1
            i += 1
    j = 0
    while val <= len(d[0]):
        lista.append(d[0][j:val])
        val += 1
        j += 1
    lista2 = lista.copy()
    for elemento in lista:
        for k in d:
            contenitore = d[k]
            if contenitore.count(elemento) != 1:
                lista2.remove(lista2[lista2.index(elemento)])
                break
    lista3 = []
    stringa = lista2[0]
    k = 0
    for elemento in d:
        contenitore = d[k]
        lista3.append(contenitore.find(stringa))
        k += 1
    return lista3
    
    
    
    
    
    
    def es1(ftesto):
    d = {} 
    lista = []
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    f.close()
    unisci_stringhe(d, stringhe)
    dividi_stringhe(val, lista, d)
    lista2 = lista.copy()
    #c = len(lista)
    for i in lista:
        for k in d:
            #contenitore = d[k]
            if d[k].count(i) != 1:
                lista2.remove(i)
                break
    lista3 = []
    #stringa = lista2[0]
    k = 0
    for elemento in d:
        #contenitore = d[k]
        lista3.append(d[k].find(lista2[0]))
        k += 1
    return lista3


def unisci_stringhe(d, stringhe):
    i = 0
    c = 0
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
            c += 1
            i += 1
    return d
            
            
def dividi_stringhe(val, lista, d):
    j = 0
    while val <= len(d[0]):
        lista.append(d[0][j:val])
        val += 1
        j += 1
    return lista, d