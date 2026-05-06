# -*- coding: utf-8 -*-
"""
Created on Mon Feb  8 21:36:15 2021

@author: Utente
"""

from random import random, uniform, randint
from math import pi, sqrt, sin, cos
from PyQt5.QtGui import *
from PyQt5.QtCore import *
from PyQt5.QtWidgets import *
# from gwidget import run_app

class PaintInfo:
    def __init__(self):
        self.mouse_pressed = False
        self.mouse_x = 0
        self.mouse_y = 0
        self.mouse_px = 0
        self.mouse_py = 0
        self.key = ''
        self.size = (0, 0)
        
class _GWidget(QWidget):
    def __init__(self):
        super().__init__()
        self.image = QImage(self.width(),
            self.height(), QImage.Format_ARGB32)
        self.paint_handler = None
        self.info = PaintInfo()
        self.info.size = self.width(), self.height()
        self.setMouseTracking(True)
    def drawing(self):
        painter = QPainter(self.image)
        painter.setRenderHints(
            QPainter.Antialiasing,True)
        painter.setRenderHints(
            QPainter.SmoothPixmapTransform,True)
        painter.info = self.info
        self.paint_handler(painter)
        self.info.mouse_px = self.info.mouse_x
        self.info.mouse_py = self.info.mouse_y
        self.update()
    def paintEvent(self, event):
        painter = QPainter(self)
        painter.drawImage(0, 0, self.image)
    def resizeEvent(self,event):
        prev_img = self.image
        self.image = QImage(self.width(),
            self.height(), QImage.Format_ARGB32)
        self.image.fill(QColor(0,0,0).rgb())
        painter = QPainter(self.image)
        painter.drawImage(0, 0, prev_img)
        self.info.size = self.width(), self.height()
        self.update()
    def mousePressEvent(self,event):
        self.info.mouse_pressed = True
    def mouseReleaseEvent(self,event):
        self.info.mouse_pressed = False
    def mouseMoveEvent(self,event):
        self.info.mouse_x = event.x()
        self.info.mouse_y = event.y()
    def keyPressEvent(self,event):
        if not event.text():
            super().keyPressEvent(event)
        self.info.key = event.text()
    def keyReleaseEvent(self,event):
        self.info.key = ''

def run_app(paint,w,h):
    app = QApplication.instance()
    if not app: app = QApplication([])
    widget = _GWidget()
    widget.resize(w,h)
    widget.setWindowTitle('Fondamenti di Programmazione')
    widget.paint_handler = paint
    timer = QTimer()
    timer.setInterval(1000/60)
    timer.timeout.connect(widget.drawing)
    widget.show()
    timer.start()
    app.exec_()


class vec2f(object):
    '''Vettore bidimensionele di float'''
    def __init__(self,x,y):
        self.x = float(x)
        self.y = float(y)
    def __add__(self,other):
        '''Somma di vettori'''
        return vec2f(self.x+other.x,self.y+other.y)
    def __sub__(self,other):
        '''Somma con vettore opposto'''
        return vec2f(self.x-other.x,self.y-other.y)
    def __neg__(self):
        '''Vettore opposto'''
        return vec2f(-self.x,-self.y)
    def __mul__(self,other):
        '''Prodotto per scalare'''
        return vec2f(self.x*other,self.y*other)
    def __truediv__(self,other):
        '''Divisione per scalare'''
        return vec2f(self.x/other,self.y/other)
    def length(self):
        '''Lunghezza del vettore'''
        return sqrt( self.x*self.x + self.y*self.y )
    def normalized(self):
        '''Vettore normalizzato'''
        l = self.length()
        if l < 0.000001:
            return vec2f(0,0)
        else:
            return vec2f(self.x/l,self.y/l)
    def clamped(self,maxlen):
        '''Vettore con lunghezza massima maxlen'''
        l = self.length()
        if l > maxlen:
            return self * maxlen / l
        else:
            return vec2f(self.x,self.y)
        
class Params:
    '''Parametri della simulazione'''
    def __init__(self):
        # Numero particelle
        self.num = 4
        # Minimo e massimo raggio
        self.radius_min = 25.0
        self.radius_max = 25.0
        # Massima intensità delle velocità
        self.vel = 4.0
        # Densità
        self.density = 1.0/(pi*25*25)
        # Fattore d'attrazione verso il mouse
        self.force_mouse = 0.0
        #Fattore di decelerazione 
        self.force_drag = 0.002
        # Max intensità forza casuale
        self.force_random = 0.0
        # Accelerazione di gravità
        self.force_gravity = 0.0
        # Dissolvenza dei movimenti precedenti
        self.fade = True
        # Posizione relativa al mouse
        self.mouse = False
        # Max timer
        self.timer = 0
    
# Parametri della simulazione
params = Params()

# funzioni utili su vettori 
def length(v): return v.length()
def normalize(v): return v.normalized()
def clamp(v,maxlength): return v.clamped(maxlength)

def dot(v0,v1):
    '''Prodotto scalare'''
    return v0.x*v1.x,v0.y*v1.y

def random_pos(x0, y0, x1, y1):
    '''Vettore random tra i valori dati'''
    return vec2f(uniform(x0,x1), uniform(y0,y1))

def random_dir():
    '''Vettore random di lunghezza 1'''
    a = uniform(0,2*pi)
    return vec2f(cos(a),sin(a))

def random_vec(maxlen):
    '''Vettore random di lunghezza al più maxlen'''
    return random_dir() * random() * maxlen

# Dimensione dello spazio di simulazione
size = vec2f(500, 300)

class Particle(object):
    '''Rappresenta una particella tramite i suoi'''
    def __init__(self):
        '''parametri di simulazione'''
        # Raggio
        self.radius = 25.0
        # Posizione, inizialmente al centro
        self.pos = size/2
        # Velocità, inizialmente 0
        self.vel = vec2f(0,0)
        # Accelerazione, inizialmente 0
        self.acc = vec2f(0,0)
        # Massa
        self.mass = 1.0
        # Se simulata calcoleremo il moto
        self.simulated = True
        # Colore
        self.color = QColor(128,128,128,200)
        # Colore quando non simulata
        self.color_paused = QColor(128,255,128,200)
        # Timer, se 0 disattivato
        self.timer = 0
        # Attiva le collisioni tra particelle
        self.collisions = False
        
# Lista delle particelle
particles = []

def init():
    '''Crea e inizializza le particelle,
    per adesso una sola particella immobile'''
    global particles
    particles = [Particle()]
    
def v2qt(v):
    '''Converte un vec2f v in un vettore di Qt'''
    return QPointF(v.x, v.y)

def clear(painter):
    '''Pulisce lo schermo'''
    painter.fillRect(0,0,size.x,size.y,
        QColor(255,255,255))
    
def set_color(painter,c):
    '''Setta il color di brush e pen'''
    painter.setPen(QPen(QColor(0,0,0,c.alpha()),2))
    painter.setBrush(c)
    
def draw(painter):
    '''Disegna le particelle'''
    clear(painter)
    for p in particles:
        if p.simulated:
            set_color(painter,p.color)
        else:
            set_color(painter,p.color_paused)
        painter.drawEllipse(v2qt(p.pos),
            p.radius, p.radius)
        # Disegna una linea dal centro della paricella
        # che ne indica la velocità
        painter.drawLine(v2qt(p.pos),
            v2qt(p.pos+normalize(p.vel)*p.radius))

# Inizializza le particelle
#init()

# Esegue la simulazione chiamando paint ogni frame
#run_app(paint, int(size.x), int(size.y))

def handle_walls():
    '''Gestisce le collisioni particelle-bordi'''
    for p in particles:
        # Bordo sinistro
        if p.pos.x < p.radius:
            p.pos.x = p.radius
            p.vel.x = abs(p.vel.x)
        # Bordo destro
        if p.pos.x > size.x - p.radius:
            p.pos.x = size.x - p.radius
            p.vel.x = -abs(p.vel.x)
        # Bordo alto
        if p.pos.y < p.radius:
            p.pos.y = p.radius
            p.vel.y = abs(p.vel.y)
        # Bordo basso
        if p.pos.y > size.y - p.radius:
            p.pos.y = size.y - p.radius
            p.vel.y = -abs(p.vel.y)
            
def update():
    '''Aggiorna posizioni e moto delle particelle'''
    for p in particles:
        # salta se non simulata
        if not p.simulated: continue
        # aggiorna posizione con la velocità
        p.pos += p.vel
    # Gestisce le collisioni particelle-bordi
    handle_walls()
        
def paint(painter):
    '''Aggiorna la simulazione ad ogni frame'''
    # Aggiorna posizioni e moto delle particelle
    update()
    # Disegna il nuovo stato della simulazione 
    draw(painter)
    
def init_particle():
    '''Crea e inizializza una particella'''
    p = Particle()
    p.vel = vec2f(1,4)
    return p

def init():
    '''Crea e inizializza le particelle'''
    global particles
    particles = [init_particle()]
    

init()
run_app(paint, int(size.x), int(size.y))
            
#    Parameters
#    ----------
#    painter : TYPE
#        DESCRIPTION.
#
#    Returns
#    -------
#    None.
#
#    '''