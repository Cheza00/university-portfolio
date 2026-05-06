# -*- coding: utf-8 -*-
"""
Created on Sun Oct  4 12:43:07 2020

@author: Utente
"""

def rot(lst):
    '''Ruota la lista data di una posizione 
    a destra.'''
    last = lst.pop()
    lst.insert(0, last)
    
def rotate(lst, k):
    '''Ruota la lista data di k pozizioni 
    a destra.'''
    for _ in range(k):
        rot(lst)
        
def test_rotate(lst, k):
    print(lst, " k =", k)
    rotate(lst, k)
    print(lst)