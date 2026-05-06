# -*- coding: utf-8 -*-
"""
Created on Tue Jan 21 21:59:33 2020

@author: Utente
"""

def hms(nsec):
    hh = nsec // 3600
    nsec = nsec % 3600
    mm = nsec // 60
    ss = nsec % 60
    return hh, mm, ss
    