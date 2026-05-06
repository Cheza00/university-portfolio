# -*- coding: utf-8 -*-
"""
Created on Tue Feb 11 17:39:30 2020

@author: Utente
"""

def fibonacci_ric(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci_ric(n-1) + fibonacci_ric(n-2)