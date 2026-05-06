# -*- coding: utf-8 -*-
"""
Created on Thu Dec  5 22:29:20 2019

@author: Utente

PROVE






import immagini

def es1(fimm,k):
    imm = immagini.load(fimm)
    Verde = (0,255,0)
    Rosso = (255,0,0)
    #Nero = (0,0,0)
    c = 0 
    
    for riga in range(0, len(imm)):            #trovo il primo vertice verde
        for colonna in range(0, len(imm[0])):
            if imm[riga][colonna] == Verde:
                xmin = colonna
                ymin = riga
                c += 1
                break
        if c != 0:
            break
    print(xmin,ymin)
    
# =============================================================================
#     c = 0
#     for riga in range(len(imm), 0, -1):            #trovo il primo vertice verde
#         for colonna in range(len(imm), 0, -1):
#             if imm[-riga][-colonna] == Verde:
#                 xmax = colonna
#                 ymax = riga
#                 c += 1
#                 break
#         if c != 0:
#             break
#     print(xmax,ymax)
# =============================================================================
    
    seg = 0                                     #trovo la lunghezza del segmento
    for pixel in imm[ymin][xmin+1:]:
        if pixel != Verde:
            seg += 1
        else:
            break
    print(seg)
    
    c = 0
    for riga in range(len(imm)-1, -1, -1):            #trovo l'ultimo vertice verde
        for colonna in range(len(imm[0])-1, -1, -1):
            if imm[riga][colonna] == Verde:
                xmax = colonna
                ymax = riga
                c += 1
                break
        if c != 0:
            break
    print(xmax, ymax)
    
    i = 1
    contatore = 0
    for riga in range(ymin, ymax - (k * (seg+1) -1), seg+1): 
        print("riga",riga)
        for colonna in range(xmin,xmax - (k * (seg+1) -1),seg+1):
            print("colonna",colonna)
            #i = 1
            conta_seg = 0
            c = 0
            while conta_seg < 1*k:
                print("primo",conta_seg)
                print("quadrati",contatore)
                if imm[riga][colonna + (seg+1)*i -1] != Rosso:
                    c += 1
                    print("stacco")
                    break
                conta_seg += 1
                i += 1
           
            i = 1
            if c == 0:
                #conta_seg = 0
                while conta_seg < 2*k:
                    print("secondo",conta_seg)
                    if imm[riga + (seg+1)*i -1][colonna] != Rosso:
                        print("stacco")
                        c += 1
                        break
                    conta_seg += 1
                    i += 1
            #else:
             #   break
            

            i = 1
            if c == 0:
                #conta_seg = 0
                while conta_seg < 3*k:
                    print("terzo",conta_seg)
                    if imm[riga + (seg+1)*k][colonna + (seg+1)*i -1] != Rosso:
                        print("stacco")
                        c += 1
                        break
                    conta_seg += 1
                    i += 1
            #else:
             #   break
            
            i = 1
            if c == 0:
                #conta_seg = 0
                while conta_seg < 4*k:
                    print("quarto",conta_seg)
                    if imm[riga + (seg+1)*i -1][colonna + (seg+1)*k] != Rosso:
                        print("stacco")
                        c += 1
                        break
                    conta_seg += 1
                    i += 1
            
            if c == 0:
                contatore += 1
            #else:
             #   break  
            i = 1

            
            #contatore += 1
    return contatore
            
            
"""

