# -*- coding: utf-8 -*-
"""
Created on Fri Aug 21 11:57:12 2020

@author: Utente
"""

memo = {0:0, 1:1}

def fibonacci(n):
    if n in memo:
        return memo[n]
    
    res = fibonacci(n-1) + fibonacci(n-2)
    memo[n] = res
    return res