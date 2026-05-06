# -*- coding: utf-8 -*-
"""
Created on Thu Sep  3 11:27:19 2020

@author: Utente
"""

'''
class NomeTipo:
    def __init__(self, parametri):
        self.nome_variabile = valore
        ...
    definizione dei metodi ...
'''
'''
class Color:
    def __init__(self, r, g, b):
        self.r = r
        self.g = g
        self.b = b
'''

class Color:
    def __init__(self, r, g, b):
        self.r, self.g, self.b = r, g, b
    def inverse(self):
        return Color(255 - self.r,
            255 - self.g, 255 - self.b)
    def __str__(self):
        return 'Color({},{},{})'.format(
            self.r,self.g,self.b)
    def __add__(self, other):
        return Color(self.r+other.r,
            self.g+other.g,self.b+other.b)
    def __mul__(self, f):
        return Color(self.r*f, self.g*f, self.b*f)
    
        
# crea un oggetto e poi chiama
# il costruttore NomeTipo.__init__
'''oggetto = NomeTipo(argomenti)'''

# Creazione di un oggetto di tipo Color
c1 = Color(255,0,0)
print(type(c1))
# Valore dell'attributo r dell'oggetto creato
print(c1.r)
print(c1.g)
print(c1.b)

# Un altro oggetto di tipo Color
c2 = Color(0,255,0)
print(type(c2))
# Il valore dell'attributo r è diverso
print(c2.r)
print(c2.g)
print(c2.b)

# Infatti i due oggetti hanno identità differenti
print(id(c1))
print(id(c2))

# Modifica l'attributo g dell'oggetto in c1
c1.g = 128
print(c1.g)
# Il corrispondente attributo di c2 non è cambiato
print(c2.g)

'''
class NomeTipo:
    def nome_metodo(self, parametri):
        istruzioni
'''
c = Color(255,0,0)
print(c.r, c.g, c.b)
ci = c.inverse()
print(ci.r, ci.g, ci.b)

print(c1)

        
import png

class Image:
    def __init__(self, w, h):
        '''Crea un'immagine di dimensioni w x h
        riempita con colore nero'''
        # L'attributo _pixels deve rimanere nascosto
        self.pixels = []
        for j in range(h):
            row = []
            for i in range(w):
                row.append(Color(0,0,0))
            self.pixels.append(row)
    def width(self):
        '''Ritorna la larghezza dell'immagine'''
        return len(self.pixels[0])
    def height(self):
        '''Ritorna l'altezza dell'immagine'''
        return len(self.pixels)
    def set_pixel(self, i, j, color):
        '''Imposta il colore del pixel (i, j)'''
        if (0 <= i < self.width() and
            0 <= j < self.height()):
            self.pixels[j][i].r = color.r
            self.pixels[j][i].g = color.g
            self.pixels[j][i].b = color.b
    def get_pixel(self, i, j):
        '''Ritorna l'oggetto Color del pixel (i,j)'''
        if (0 <= i < self.width() and
            0 <= j < self.height()):
            return self.pixels[j][i]
    def load(self, filename):
        '''Carica l'immagine dal file filename'''
        with open(filename, 'rb') as f:
            r = png.Reader(file=f)
            iw, ih, png_img, _ = r.asRGB8()
            img = []
            for png_row in png_img:
                row = []
                for i in range(0,len(png_row),3):
                    row.append( Color(png_row[i+0],
                        png_row[i+1],png_row[i+2]) )
                img.append( row )
    def save(self, filename):
        '''Salva l'immagine nel file filename'''
        pixels = []
        for j in range(self.height()):
            pixels.append([])
            for i in range(self.width()):
                c = self.get_pixel(i,j)
                pixels[-1] += [c.r,c.g,c.b]
        pyimg = png.from_array(pixels, 'RGB')
        pyimg.save(filename)
    def draw_quad(self, x, y, w, h, c):
        '''Disegna sull'immagine un rettangolo con
        spigolo in (x,y), dimensioni wxh e 
        colore c'''
        for j in range(y, y+h):
            for i in range(x, x+w):
                self.set_pixel(i,j,c)
    def draw_gradienth(self, c0, c1):
        '''Disegna sull'immagine un gradiente 
        orizzontale dal colore c0 al colore c1'''
        for j in range(self.height()):
            for i in range(self.width()):
                u = float(i) / float(self.width())
                self.set_pixel(i,j,c0*(1-u)+c1*u)
    def __str__(self):
        return 'Image@{}x{}'.format(
            self.width(),self.height())
        

img = Image(256,128)
img.draw_gradienth(Color(255,128,128),
    Color(128,255,128))
img.draw_quad(32,32,64,64,Color(0,200,255))
img.save('img_draw.png')
print(img)
            