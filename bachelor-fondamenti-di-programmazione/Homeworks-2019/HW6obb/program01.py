# -*- coding: utf-8 -*-
''' 
    In un immagine a sfondo nero  e' disegnata  una griglia  
    dove  alcuni segmenti che ne connettono i nodi in orizzontale 
    o in verticale sono stati cancellati (i nodi della griglia sono in 
    verde mentre i segmenti sono in rosso).
    La dimensione del lato dei quadrati della griglia non è data.

    Si veda ad esempio la figura foto_1.png.
    Progettare la funzione es1(fimm, k) che prende in input l'indirizzo 
    dell'immagine contenente la griglia ed un intero k e restituisce un intero. 
    L'intero restituito e' il numero di 
    quadrati rossi (con pixel verdi) di lato k (steps della griglia) che sono presenti nell'immagine.
    Ad esempio  es1(foto_1.png,2) deve restituire 2 (i due quadrati rossi presenti nella 
    sottogriglia hanno il vertice in alto a sinistra con coordinate (3,0) e 
    (4,2) nelle coordinate della griglia, rispettivamente)

    Per caricare e salvare  file PNG si possono usare load e save della libreria immagini allegata.

    NOTA: il timeout previsto per questo esercizio è di 1 secondo per ciascun test

    ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8 
    (ad esempio editatelo dentro Spyder)

'''
import immagini

def es1(fimm,k):
    imm = immagini.load(fimm)
    Verde = (0,255,0)
    Rosso = (255,0,0)
    
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
        
    seg = 0                                     #trovo la lunghezza del segmento
    for pixel in imm[ymin][xmin+1:]:
        if pixel != Verde:
            seg += 1
        else:
            break
    
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
    
    i = 1
    contatore = 0
    for riga in range(ymin, ymax - (k * (seg+1) -1), seg+1): 
        for colonna in range(xmin,xmax - (k * (seg+1) -1),seg+1):
            conta_seg = 0
            c = 0
            while conta_seg < 1*k:
                if imm[riga][colonna + (seg+1)*i -1] != Rosso:
                    c += 1
                    break
                conta_seg += 1
                i += 1
           
            i = 1
            if c == 0:
                while conta_seg < 2*k:
                    if imm[riga + (seg+1)*i -1][colonna] != Rosso:
                        c += 1
                        break
                    conta_seg += 1
                    i += 1            

            i = 1
            if c == 0:
                while conta_seg < 3*k:
                    if imm[riga + (seg+1)*k][colonna + (seg+1)*i -1] != Rosso:
                        c += 1
                        break
                    conta_seg += 1
                    i += 1
            
            i = 1
            if c == 0:
                while conta_seg < 4*k:
                    if imm[riga + (seg+1)*i -1][colonna + (seg+1)*k] != Rosso:
                        c += 1
                        break
                    conta_seg += 1
                    i += 1
            
            if c == 0:
                contatore += 1
            i = 1

    return contatore
            
            
            
            
    
    
                
        



if __name__ == '__main__':
    pass
    # inserisci qui i tuoi test
