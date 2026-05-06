# -*- coding: utf-8 -*-
"""
Created on Mon Nov  9 17:04:52 2020

@author: Utente
"""

# crea yna lista dall'insieme
def intersezione_liste(lst1, lst2):
    inter = set(lst1) & set(lst2)
    return list(inter)


primi = [2,3,5,7,11,13,17,19,23,29,31,37,41]
fib = [1,1,2,3,5,8,13,21,34,55,89]

print(intersezione_liste(primi, fib))


w1 = [ 'quali', 'sono', 'le', 'parole', 'in', 'comune' ]
w2 = [ 'sono', 'le', 'parole', 'che', 'appaiono',
      'sia', 'in', 'w1', 'che', 'in', 'w2' ]

print(intersezione_liste(w1, w2))