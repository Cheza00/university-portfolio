# -*- coding: utf-8 -*-
"""
Created on Mon Aug 24 13:57:33 2020

@author: Utente
"""

def width(img):
    '''Ritorna la larghezza dell'immagine img.'''
    return len(img[0])

def height(img):
    '''Ritorna l'altezza dell'immagine img.'''
    return len(img)

import png

def save(filename, img):
    '''Salva un'immagine in formato PNG.'''
    pyimg = png.from_array(img, 'RGB')
    pyimg.save(filename)
    
#save('img_small.png', img)


def create(iw, ih, c=(0,0,0)): #c parametro opzionale
    '''Crea e ritorna un'immagine di larghezza iw,
    altezza ih e riempita con il colore c'''
    
    # L'immagine inizialmente vuota
    img = []
    # Per ogni riga,
    for _ in range(ih):
        # inizializza la riga vuota
        row = []
        # e per ogni pixel della riga,
        for _ in range(iw):
            # aggiunge un pixel del colore c.
            row.append(c)
        # Aggiunge la riga all'immagine
        img.append(row)
    return img


def draw_quad_simple(img, x, y, w, h, c):
    '''Disegna su img un rettangolo con lo spigolo in
    alto a sinistra in (x, y), larghezza w, altezza h
    e di colore c. Va in errore se il rettangolo
    fuoriesce dall'immagine.'''
    
    # Per ogni riga j del rettangolo,
    for j in range(y, y+h):
        # per ogni colonna i della riga j,
        for i in range(x, x+w):
            # imposta il colore del pixel a c
            img[j][i] = c
            
            
def inside(img, i, j):
    '''Ritorna True se il pixel (i, j) è dentro
    l'immagine img, False altrimenti'''
    iw, ih = width(img), height(img)
    return 0 <= i < iw and 0 <= j < ih

def draw_quad(img, x, y, w, h, c):
    '''Disegna su img un rettangolo con lo spigolo 
    in alto a sinistra in (x, y), larghezza w,
    altezza h e di colore c.'''
    for j in range(y,y+h):
        for i in range(x,x+w):
            # Disegna il pixel solo se è dentro
            if inside(img,i,j):
                img[j][i] = c
                
def draw_checkers(img, s, c0, c1):
    '''Disegna su img una scacchiera di quadratini,
    ognuno di lato s, coi colori c0 e c1'''
    # Per ogni indice di riga,
    for jj in range(height(img)//s):
        # per ogni indice di colonna
        for ii in range(width(img)//s):
            # seleziona il colore
            if (ii + jj) % 2: c = c1
            else: c = c0
            # e disegna il quadratino
            draw_quad(img,ii*s,jj*s,s,s,c)
# rivedere funzionamento
            

def draw_gradienth(img, c0, c1):
    '''Disegna su img un gradiente di colore da 
    sinistra a destra, dal colore c0 al colore c1'''
    r0, g0, b0 = c0
    r1, g1, b1 = c1
    for j in range(height(img)):
        for i in range(width(img)):
            # float da 0 a 1
            u = i / width(img)
            # Interpola i canali
            r = round(r0 * (1-u) + r1 * u)
            g = round(g0 * (1-u) + g1 * u)
            b = round(b0 * (1-u) + b1 * u)
            img[j][i] = (r,g,b)
            
def draw_gradientv(img, c0, c1):
    '''Disegna su img un gradiente di colore dall'
    alto in basso, dal colore c0 al colore c1'''
    r0, g0, b0 = c0
    r1, g1, b1 = c1
    for j in range(height(img)):
        for i in range(width(img)):
            v = j / height(img)
            r = round(r0 * (1-v) + r1 * v)
            g = round(g0 * (1-v) + g1 * v)
            b = round(b0 * (1-v) + b1 * v)
            img[j][i] = (r,g,b)
            
def draw_gradient_quad(img, c00, c01, c10, c11):
    '''Disegna un gradiente di colore combinato
    orizzontale e verticale con c00 in alto a 
    sinistra, c01 in basso a sinistra, c10 in
    alto a destra e c11 in basso a destra'''
    for j in range(height(img)):
        for i in range(width(img)):
            u = i / width(img)
            v = j / height(img)
            c = [0,0,0]
            for k in range(3):
                c[k] = round(c00[k]*(1-u)*(1-v) +
                             c01[k]*(1-u)*v +
                             c10[k]*u*(1-v) +
                             c11[k]*u*v)
            img[j][i] = tuple(c)
            

def load(filename):
    '''Carica l'immagine in formato PNG dal file
    filename, la converte nel formato a matrice
    di tuple e la ritorna'''
    with open(filename, 'rb') as f:
        # legge l'immagine come RGB a 256 valori
        r = png.Reader(file=f)
        iw, ih, png_img, _ = r.asRGB8()
        # converte in lista di liste di tuple
        img = []
        for png_row in png_img:
            row = []
            # l'immagine PNG ha i colori in
            # un unico array, quindi li leggiamo
            # tre alla volta in una tupla
            for i in range(0,len(png_row),3):
                row.append( ( png_row[i+0],
                              png_row[i+1],
                              png_row[i+2] ) )
            img.append( row )
    return img


def copy(dst, src, dx, dy, sx, sy, w, h):
    '''Copia la porzione rettangolare dell'immagine 
    src con spigolo in alto a sinistra in (sx, sy)
    e dimensioni w, h sull'immagine dst a partire 
    da (dx, dy)'''
    for j in range(h):
        for i in range(w):
            di, dj = i+dx, j+dy
            si, sj = i+sx, j+sy
            if (inside(dst, di, dj) and
                  inside(src, si, sj)):
                dst[dj][di] = src[sj][si]
                
def border(img, s, c):
    '''Ritorna una nuova immagine che è l'immagine
    img contornata da una cornice di spessore s
    e colore c'''
    w, h = width(img), height(img)
    ret = create(w+s*2, h+s*2, c)
    copy(ret, img, s, s, 0, 0, w, h)
    return ret


def fliph(img):
    '''Ritorna una nuova immagine che e' l'immagine
    img ruotata intorno al suo asse verticale, cioè
    i pixels sono scambiati orizzontalmente'''
    w, h = width(img), height(img)
    ret = create(w, h)
    for j in range(h):
        for i in range(w):
            ret[j][i] = img[j][w - 1 - i]
    return ret

def flipv(img):
    '''Ritorna una nuova immagine che è l'immagine
    img ruotata intorno al suo asse orizzontale,
    cioè i pixels sono scambiati verticalmente'''
    w, h = width(img), height(img)
    ret = create(w, h)
    for j in range(h):
        for i in range(w):
            ret[j][i] = img[h - 1 - j][i]
    return ret

def rotate(img):
    '''Ritorna una nuova immagine che è l'immagine
    img ruotata intorno all'angolo inferiore
    sinistro, equivalente a scambiare le righe 
    con le colonne'''
    w, h = width(img), height(img)
    # altezza e larghezza sono invertite
    ret = create(h, w)
    for j in range(h):
        for i in range(w):
            ret[i][j] = img[h-1-j][i]
    return ret


def invert(img):
    '''Ritorna una nuova immagine che è l'immagine
    img con colori invertiti'''
    w, h = width(img), height(img)
    ret = create(w, h, (0,0,0))
    for j in range(h):
        for i in range(w):
            r, g, b = img[j][i]
            ret[j][i] = (255 - r, 255 - g, 255 - b)
    return ret


def filter(img, func):
    ''' Ritorna una nuova immagine che è l'immagine 
    img con colori filtrati da func'''
    w, h = width(img), height(img)
    ret = create(w, h, (0,0,0))
    for j in range(h):
        for i in range(w):
            r, g, b = img[j][i]
            ret[j][i] = func(r, g, b)
    return ret

def invertf(r,g,b):
    return 255 - r, 255 - g, 255 - b

def grayf(r,g,b):
    gray = (r + g + b) // 3
    return gray, gray, gray

def contrastf(r,g,b):
    return ( max(0,min(255, (r - 128) * 2 + 128)),
             max(0,min(255, (g - 128) * 2 + 128)),
             max(0,min(255, (b - 128) * 2 + 128)))
 

def mosaic_nearest(img, s):
    '''Ritorna una nuova immagine ottenuta dividendo
    l'immagine img in quadrati di lato s e riempiendo
    ogni quadrato con il colore del suo angolo in
    alto a sinistra'''
    w, h = width(img), height(img)
    ret = create(w, h)
    # itera sui possibili quadrati
    for jj in range(h//s):
        for ii in range(w//s):
            #colore dell'angolo in alto a sinistra
            c = img[jj*s][ii*s]
            draw_quad(ret, ii*s, jj*s, s, s, c)
    return ret 

def average(img, i, j, w, h):
    '''Calcola la media dei valori dell'area
    [i,w-1]x[j,h-1].'''
    c = [0,0,0]
    for jj in range(j,j+h):
        for ii in range(i,i+w):
            for k in range(3):
                c[k] += img[jj][ii][k]
    for k in range(3):
        c[k] //= w*h
    return tuple(c)

def mosaic_average(img, s):
    '''Ritorna una nuova immagine ottenuta dividendo
    l'immagine img in quadrati di lato s e riempiendo
    ogni quadratino con la media dei suoi colori.'''
    w, h = width(img), height(img)
    ret = create(w, h)
    # itera sui possibili quadrati
    for jj in range(h//s):
        for ii in range(w//s):
            # colore medio dell'immagine
            c = average(img,ii*s,jj*s,s,s)
            draw_quad(ret, ii*s, jj*s, s, s, c)
    return ret

def mosaic_size(img, s):
    ''' Ritorna una nuova immagine ottenuta dividendo
    l'immagine img in quadratini di lato s e 
    disegnando all'interno di ognuno di essi,
    su sfondo nero, un quadratino centrale bianco di
    lato proporzionale alla luminosità media del
    corrispondente quadratino'''
    w, h = width(img), height(img)
    ret = create(w, h)
    # itera sui possibili quadrati
    for jj in range(h//s):
        for ii in range(w//s):
            # colore medio dell'immagine
            c = average(img,ii*s,jj*s,s,s)
            # lato del quadratino bianco
            r = round(s*(c[0]+c[1]+c[2])/(3*255))
            draw_quad(ret, ii*s+(s-r)//2,
                jj*s+(s-r)//2, r, r, (255,255,255))
    return ret


import random 

def scramble(img, d, s):
    '''Ritorna una nuova immagine ottenuta colorando
    ogni pixel (i, j) con il colore di un pixel
    scelto a caso nel quadratino centrato in (i, j)
    di lato 2*d + 1'''
    # settiamo il seed per generare la stessa
    # sequenza di numeri casuali
    random.seed(s)
    w, h = width(img), height(img)
    ret = create(w, h)
    for j in range(h):
        for i in range(w):
            # sceglie a caso un pixel nel quadrato
            ri = i + random.randint(-d,d)
            rj = j + random.randint(-d,d)
            # evitando che si esca dall'immagine
            ri = max(0, min(w-1, ri))
            rj = max(0, min(h-1, rj))
            ret[j][i] = img[rj][ri]
    return ret


import math

def lens(img, x, y, r, p):
    '''Ritorna una nuova immagine ottenuta dall'
    immagine img applicando una lente di raggio r,
    centrata in (x, y) e di power p. Se p = 1.0 la
    lente non distorce, se p > 1.0 la lente
    ingrandisce e se p < 1.0 riduce.'''
    w, h = width(img), height(img)
    ret = create(w, h)
    for j in range(h):
        for i in range(w):
            di, dj = i - x, j - y
            # distanza al quadrato da (x, y)
            d2 = di*di + dj*dj
            # se è nel raggio della lente
            if d2 < r*r:
                rr = math.sqrt(d2) / r
                if rr > 0:
                    ratio = (rr ** p) / rr
                else:
                    ratio = 1.0
                li = int((i-x)*ratio+x)
                lj = int((j-y)*ratio+y)
                if inside(img, li, lj):
                    ret[j][i] = img[lj][li]
                else:
                    ret[j][i] = (0,0,0)
            else:
                ret[j][i] = img[j][i]
    return ret

            
           