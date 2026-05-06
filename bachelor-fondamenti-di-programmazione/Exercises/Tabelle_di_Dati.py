# -*- coding: utf-8 -*-
"""
Created on Mon Nov  9 17:14:54 2020

@author: Utente
"""

colonne = ['nome', 'anno', 'telefono']

dati = [
  { 'nome':'Sofia', 'anno':1973, 'tel':'5553546' },
  { 'nome':'Bruno', 'anno':1981, 'tel':'5558432' },
]
# notare come la virgola finale venga omessa

print(dati)
# il print non è particolarmente utile per la stampa di 
# questa tipologia di dati. 

# Per dati non troppo grandi, meglio usare la funzione pprint()
from pprint import pprint
pprint(dati)



def colonna(dati,chiave):
    '''Ritorna la lista dei valori per la colonna
    specificata da chiave.'''
    valori = []
    for dato in dati:
        valori.append( dato[chiave] )
    return valori

nomi = colonna(dati,'nome')
pprint(nomi)


def sottotabella(dati,chiavi):
    '''Ritorna la tabella che include solo le
    colonne specificate da chiavi.'''
    ndati = []
    for dato in dati:
        ndato = {}
        for chiave in chiavi:
            ndato[chiave] = dato[chiave]
        ndati.append( ndato )
    return ndati

pprint(sottotabella(dati,['nome','anno']))


def ricerca(dati,nome,chiave):
    for dato in dati:
        if dato['nome'] == nome:
            return dato[chiave]
    return None

print(ricerca(dati,'Sofia','tel'))


indice = {}
for numero_riga, dato in enumerate(dati):
    indice[dato['nome']] = numero_riga
    
def ricerca_con_indice(dati,indice,nome,chiave):
    if nome in indice:
        numero_riga = indice[nome]
        dato = dati[numero_riga]
        return dato[chiave]
    else:
        return None
    
print(ricerca_con_indice(dati,indice,'Sofia','tel'))



# dati non ordinati
pprint(dati)

# dati ordinati per nome
def nome_dato(dato): return dato['nome']
pprint(sorted(dati,key=nome_dato))

# dati ordinati per anno di nascita
def anno_dato(dato): return dato['anno']
pprint(sorted(dati,key=anno_dato))

# dati ordinati per anno di nascita inverso
pprint(sorted(dati,key=anno_dato,reverse=True))