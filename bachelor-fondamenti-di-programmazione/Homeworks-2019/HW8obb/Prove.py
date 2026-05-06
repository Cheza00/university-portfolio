# -*- coding: utf-8 -*-
"""
Created on Tue Dec 17 17:40:07 2019

@author: Utente
"""
"""
percorso , c = funzione
if c != 0:
t_finale = (tupla, percorso)
finale.append(t_finale)
"""

"""

def es1(ftesto):
    f = open(ftesto,'r')
    stringhe = f.readlines()
    f.close()
    matrice, parole = sep(stringhe)
    #print(parole)
    #print(matrice)
    d = trova_indici(matrice, parole)
    #print(d)
    finale = []
    path = ''
    #c = 0
    #posizione = (x,y)
    for parola in parole:
        #for lettera in parola:
            #print(lettera,parola)
        iniziale = parola[0]
        if iniziale not in d:
            finale.append(-1)
            break
        else:
            for tupla in d[iniziale]:
                x = tupla[0]
                y = tupla[1]
                percorso = trova_sequenza(d, parola, x, y, path)
                print(parola, tupla,percorso)
                
                    
    

def sep(stringhe):
    lista1 = []
    lista2 = [] 
    matrice = []
    parole = []
    for elemento in stringhe:
        if elemento != '\n':
            lista1 = stringhe[stringhe.index(elemento):]
            break   
    for elemento in lista1:
        #elemento = str(elemento)
        if elemento != '\n':
            matrice.append(elemento[:-1])
        else:
            lista2 = lista1[lista1.index(elemento):]
            break   
    for elemento in lista2:
        if elemento != '\n':
            parole.append(elemento[:-1])
    return matrice, parole


def trova_indici(matrice, parole):
    d = {}
    for riga in range(0, len(matrice)):            
        for colonna in range(0, len(matrice[0])):
            tupla = (riga,colonna)
            if matrice[riga][colonna] in d:
                d[matrice[riga][colonna]].append(tupla)
            else:
                d[matrice[riga][colonna]] = [tupla] 
    return d

def trova_sequenza(d, parola, x, y, path):
    print(x,y)
    if len(parola) == 2:
        if parola[1] in d:
            for elemento in d[parola[1]]:
                x2 = elemento[0]
                y2 = elemento[1]
                if x2 == x and y2 == (y + 1):
                    #tupla = (elemento[0],elemento[1])
                    return 'D',x,y
                elif elemento[0] == (x + 1) and elemento[1] == y:
                    #tupla = (elemento[0],elemento[1])
                    return 'G',x,y
                else: return 'N'
        else: return 'N',x,y
    #path = 'OK' + path
    ric = trova_sequenza(d, parola[1:], x, y, path)
    path = 'OK' + ric
    return path,x,y
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    def es1(ftesto):
    f = open(ftesto,'r')
    stringhe = f.readlines()
    f.close()
    matrice, parole = sep(stringhe)
    #print(parole)
    #print(matrice)
    d = trova_indici(matrice, parole)
    #print(d)
    finale = []
    path = ''
    #c = 0
    #posizione = (x,y)
# =============================================================================
#     for parola in parole:
#         #for lettera in parola:
#             #print(lettera,parola)
#         iniziale = parola[0]
#         if iniziale not in d:
#             finale.append(-1)
#             break
#         else:
#             for tupla in d[iniziale]:
#                 x = tupla[0]
#                 y = tupla[1]
#                 percorso = trova_sequenza(d, parola, x, y, path)
#                 print(parola, tupla,percorso)
# =============================================================================
                
                    
    

def sep(stringhe):
    lista1 = []
    lista2 = [] 
    matrice = []
    parole = []
    for elemento in stringhe:
        if elemento != '\n':
            lista1 = stringhe[stringhe.index(elemento):]
            break   
    for elemento in lista1:
        #elemento = str(elemento)
        if elemento != '\n':
            matrice.append(elemento[:-1])
        else:
            lista2 = lista1[lista1.index(elemento):]
            break   
    parole = separa_parole(lista2,parole)
    print(parole)
# =============================================================================
#     for elemento in lista2:
#         if elemento != '\n':
#             parole.append(elemento[:-1])
# =============================================================================
    return matrice, parole

def separa_parole(lista2,parole):
    if len(lista2) == 1:
        if lista2[0] != '\n':
            return parole.append(lista2[0][:-1])
            #return words
    #separa = separa_parole(lista2[1:],parole)
    parole.append(separa_parole(lista2[1:],parole)) 
    return parole


def trova_indici(matrice, parole):
    d = {}
    for riga in range(0, len(matrice)):            
        for colonna in range(0, len(matrice[0])):
            tupla = (riga,colonna)
            if matrice[riga][colonna] in d:
                d[matrice[riga][colonna]].append(tupla)
            else:
                d[matrice[riga][colonna]] = [tupla] 
    return d

# =============================================================================
# def trova_sequenza(d, parola, x, y, path):
#     print(x,y)
#     if len(parola) == 2:
#         if parola[1] in d:
#             for elemento in d[parola[1]]:
#                 x2 = elemento[0]
#                 y2 = elemento[1]
#                 if x2 == x and y2 == (y + 1):
#                     #tupla = (elemento[0],elemento[1])
#                     return 'D',x,y
#                 elif elemento[0] == (x + 1) and elemento[1] == y:
#                     #tupla = (elemento[0],elemento[1])
#                     return 'G',x,y
#                 else: return 'N'
#         else: return 'N',x,y
#     #path = 'OK' + path
#     ric = trova_sequenza(d, parola[1:], x, y, path)
#     path = 'OK' + ric
#     return path,x,y
# =============================================================================
        
    
            
    
    
    
    
    def es1(ftesto):
    f = open(ftesto,'r')
    stringhe = f.readlines()
    f.close()
    matrice, parole = sep(stringhe)
    #print(parole)
    #print(matrice)
    d = trova_indici(matrice, parole)
    #print(d)
    finale = []
    path = ''
    #c = 0
    #posizione = (x,y)
    for parola in parole:
        #for lettera in parola:
            #print(lettera,parola)
        iniziale = parola[0]
        if iniziale not in d:
            finale.append(-1)
            break
        else:
            for tupla in d[iniziale]:
                x = tupla[0]
                y = tupla[1]
                percorso = trova_sequenza(d, parola, x, y, path)
                print(parola, tupla,percorso)
                
                    
    

def sep(stringhe):
    lista1 = []
    lista2 = [] 
    matrice = []
    parole = []
    for elemento in stringhe:
        if elemento != '\n':
            lista1 = stringhe[stringhe.index(elemento):]
            break   
    for elemento in lista1:
        #elemento = str(elemento)
        if elemento != '\n':
            matrice.append(elemento[:-1])
        else:
            lista2 = lista1[lista1.index(elemento):]
            break   
    for elemento in lista2:
        if elemento != '\n':
            parole.append(elemento[:-1])
    return matrice, parole


def trova_indici(matrice, parole):
    d = {}
    for riga in range(0, len(matrice)):            
        for colonna in range(0, len(matrice[0])):
            tupla = (riga,colonna)
            if matrice[riga][colonna] in d:
                d[matrice[riga][colonna]].append(tupla)
            else:
                d[matrice[riga][colonna]] = [tupla] 
    return d

def trova_sequenza(d, parola, x, y):
    print(x,y)
    print(parola)
    
    if len(parola) == 2:
        if parola[1] in d: 
            if (x+1,y) in d[parola[1]]:
# =============================================================================
#             #for elemento in d[parola[1]]:
# =============================================================================
# =============================================================================
#                 print(elemento)
#                 x2 = elemento[0]
#                 y2 = elemento[1]
# =============================================================================
# =============================================================================
#                 if x2 == x and y2 == (y-1):
#                     #tupla = (elemento[0],elemento[1])
#                     x = x2
#                     y = y2
# =============================================================================
                return 'D'
# =============================================================================
#                 if x2 == (x+1) and y2 == y:
#                     #tupla = (elemento[0],elemento[1])
#                     x = x2
#                     y = y2
# =============================================================================
                
            if (x,y+1) in d[parola[1]]:
                return 'G'
# =============================================================================
#                 else:
#                     pass
# =============================================================================
                 #   x = x2
                  #  y = y2
                   # return 'N',x2,y2
        else: return 'c'
    #path = 'OK' + path
    #x2 = x
    #print(x2)
    #y2 = y 
    #print(y2)
    #ric = trova_sequenza(d, parola[1:], x, y, path)
    #path = trova_sequenza(d, parola[1:], x2, y2, path)[0]
    path += path + trova_sequenza(d, parola[1:])
    return path
    """
    
    
    
    
    
    def es1(ftesto):
    f = open(ftesto,'r')
    stringhe = f.readlines()
    f.close()
    matrice, parole = sep(stringhe)
    print(parole)
    print(matrice)
    d = trova_indici(matrice, parole)
    #print(d)
    finale = []
    # path = ''
    c = 0
    x = 0
    y = 0
    #stringa = ''
    #posizione = (x,y)
    
    for parola in parole: 
        #for lettera in parola:
#         iniziale = parola[0]
        if  parola == '' or parola[0] not in d:
            finale.append(-1)
        else:
            c = 0
            for tupla in d[parola[0]]:
                #k = 1
                stringa = ""
                #print(stringa)
                posizionex = tupla[0]
                posizioney = tupla[1]
                x = tupla[0] 
                y = tupla[1]
                for lettera in parola[1:]:
                 #   k = 1
                    if lettera not in d:
                        finale.append(-1)
                        c = 1
                        break
                    else:
                        for tupla in d[lettera]:
                   #         k = 1
                            #if (tupla[0] == x and tupla[1] == (y+1)) and (tupla[0] == (x+1) and tupla[1] == y):
                                
                            if tupla[0] == x and tupla[1] == (y+1):
                                x = tupla[0]
                                y = tupla[1] 
                                stringa += 'D'
                                break
                            
                            elif tupla[0] == (x+1) and tupla[1] == y:
                                x = tupla[0]
                                y = tupla[1] 
                                stringa += 'G' 
                                break
                            #else:
                  #              k = 0
                    #if k == 0:
                     #   finale.append(-1)
                if c != 0:
                    break
                if len(stringa) == (len(parola)-1):
                    finale.append((posizionex,posizioney,stringa))
                    c = 1
                    break
                
        if len(stringa) != (len(parola)-1):
            for tupla in d[parola[0]]:
                print(stringa,parola,tupla)
                stringa = ""
                posizionex = tupla[0]
                posizioney = tupla[1]
                x =  tupla[0] 
                y = tupla[1]
                for lettera in parola[1:]:
                    if lettera not in d:
                        finale.append(-1)
                        c = 1
                        break
                    else:
                        for tupla in d[lettera]:
                            if tupla[0] == (x+1) and tupla[1] == y:
                                x = tupla[0]
                                y = tupla[1] 
                                stringa += 'G' 
                                break
                            elif tupla[0] == x and tupla[1] == (y+1):
                                x = tupla[0]
                                y = tupla[1] 
                                stringa += 'D'
                                break
                if c != 0:
                    break
                if len(stringa) == (len(parola)-1):
                    finale.append((posizionex,posizioney,stringa))
                    #c = 1
                    break
                else:
                    finale.append(-1)
                    break
            
                    
    return finale
    
                    
                        
                    
#                 percorso = trova_sequenza(d, parola, x, y, path)
#                 print(parola, tupla,percorso)
# =============================================================================
                
                    
    

def sep(stringhe):
    lista1 = []
    lista2 = [] 
    matrice = []
    parole = []
    for elemento in stringhe:
        if elemento != '\n':
            lista1 = stringhe[stringhe.index(elemento):]
            break   
    for elemento in lista1:
        if elemento != '\n':
            matrice.append(elemento[:-1])
        else:
            lista2 = lista1[lista1.index(elemento):]
            break 
    parole = separa_parole(lista2)
    parole = separa_inverso(parole)
    for elemento in parole:
        if '\n' in elemento:        
            parole[parole.index(elemento)] = elemento[:-1]
    i = 0
    while i < len(parole):
            
            #print(parole)
            #print(i)            
            if ' ' in parole[i]:
                parole = parole[:i] + parole[i].split(' ') + parole[(i+1):]
                #i += 1
            i += 1
            #print(parole)
    return matrice, parole

def separa_inverso(parole):
    if parole[-1] != '\n':
        return parole
    return separa_inverso(parole[:-1])
    
def separa_parole(lista2):
    if lista2[0] != '\n':
        return lista2
    return separa_parole(lista2[1:])
          


def trova_indici(matrice, parole):
    d = {}
    for riga in range(0, len(matrice)):            
        for colonna in range(0, len(matrice[0])):
            tupla = (riga,colonna)
            if matrice[riga][colonna] in d:
                d[matrice[riga][colonna]].append(tupla)
            else:
                d[matrice[riga][colonna]] = [tupla] 
    return d

# =============================================================================
# def trova_sequenza(d, parola, x, y, path):
#     print(x,y)
#     if len(parola) == 2:
#         if parola[1] in d:
#             for elemento in d[parola[1]]:
#                 x2 = elemento[0]
#                 y2 = elemento[1]
#                 if x2 == x and y2 == (y + 1):
#                     #tupla = (elemento[0],elemento[1])
#                     return 'D',x,y
#                 elif elemento[0] == (x + 1) and elemento[1] == y:
#                     #tupla = (elemento[0],elemento[1])
#                     return 'G',x,y
#                 else: return 'N'
#         else: return 'N',x,y
#     #path = 'OK' + path
#     ric = trova_sequenza(d, parola[1:], x, y, path)
#     path = 'OK' + ric
#     return path,x,y
# =============================================================================
        