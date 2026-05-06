# -*- coding: utf-8 -*-
"""
Created on Mon Aug 24 11:47:57 2020

@author: Utente
"""


def camel(s):
    for c in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ':
        s = s.replace(c, " "+c.lower())
    return s.split()


def noalpha(s):
    '''Ritorna una stringa contenente tutti i
    caratteri non alfabetici contenuti in s,
    senza ripetizioni (funziona per qualsiasi lingua)'''
    noa = ''
    for c in s:
        if not c.isalpha() and c not in noa:
            noa += c
    return noa


def words(s):
    '''Ritorna la lista delle parole contenute
    nella stringa s'''
    noa = noalpha(s)
    for c in noa:
        s = s.replace(c, ' ')
    return s.lower().split()


def fwords(fname,encoding):
    with open(fname, encoding=encoding) as f:
        testo = f.read()
    return words(testo)

'''esempio:
    parole = fwords('alice.txt','utf-8-sig')
    print(len(parole))
    print(parole[1000:1005])
   contiene le ripetizioni
   
   senza ripetizioni:
    parole_uniche = set(fwords('alice.txt','utf-8-sig'))
    print(len(parole_uniche))
    
    (Project Gutemberg)'''
   
    
    
def wfreq(fname, ricerca, enc):
    '''Ritorna un dizionario che ad ogni parola nella 
    lista ricerca associa la sua frequenza 
    percentuale nel file fname. Il file è
    decodificato tramite la codifica enc.'''
    
    #ottiene la lista delle parole
    parole = fwords(fname, enc)
    #prepara il dizionario delle frequenze
    frequenze = {}
    #per ogni parola nella ricerca
    for parola in ricerca:
        #calcola le occorrenze
        occ = parole.count(parola.lower())
        #calcola frequenza percentuale
        freq = occ*100/len(parole)
        #aggiorna il dizionario
        frequenze[parola] = round(freq,3)
    return frequenze

def scores(fnames, ricerca, enc):
    '''Ritorna un dizionario che ad ogni nome di file 
    in fnames associa il suo punteggio relativamente 
    alla lista di parole ricerca. I file sono 
    decodificati tramite la codifica enc.'''
    frequenze = {}
    for fname in fnames:
        #dizionario delle frequenze di fname
        f = wfreq(fname, ricerca, enc)
        #score arrotondata
        frequenze[fname] = round(sum(f.values()), 3)
    return frequenze

def extract_value(kv): return kv[1]
def searchdocument(fnames, ricerca, enc):
    '''Ritorna la lista ordinata per score dei
    documenti in fnames per le parole in ricerca.'''
    s = scores(fnames, ricerca, enc)
    return sorted(s.items(), key=extract_value, reverse=True)

'''rivedere funzionamento'''
