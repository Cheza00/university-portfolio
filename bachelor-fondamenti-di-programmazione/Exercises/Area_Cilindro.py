# -*- coding: utf-8 -*-
"""
Created on Wed Jan 22 11:49:04 2020

@author: Utente
"""

def area_cilindro(raggio=1,altezza=1):
    pigreco = 3.14159
    area = pigreco * raggio ** 2
    circonferenza = 2 * pigreco * raggio
    return 2 * area + altezza * circonferenza

def area_sfera(raggio):
    return 4 * 3.14 * raggio ** 2

def volume_sfera(raggio):
    return 4/3 * 3.14 * raggio ** 3

def frutta(x):
    return x in ['mela','pera','uva']  #ritorna True o False. Sostituisce lunga serie di if - elif

def frutta2(x):
    if x == 'mela':
        return True
    elif x == 'pera':
        return True
    elif x == 'uva':
        return True
    else:
        return False
    


if __name__ == '__main__':
    print(area_cilindro(1,2))
    print(area_sfera(1))    