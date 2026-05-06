# -*- coding: utf-8 -*-
"""
Created on Fri Nov 13 21:58:59 2020

@author: Utente
"""

import html

class HTMLNode(object):
    def __init__(self,tag,attr,content,closed=True):
        self.tag = tag
        # dizionario degli attributi
        self.attr = attr
        # testo per nodi _text_ o lista dei figli
        self.content = content
        # True se il nodo ha la chiusura
        self.closed = closed
        
    # per distinguere i nodi testo
    def istext(self):
        return self.tag == '_text_'
    
    def open_tag(self):
        '''Ritorna la stringa del tag di inizio.'''
        if self.istext():
            return self.tag
        s = '<'+self.tag
        for k, v in self.attr.items():
            # usiamo escape per i valori
            s += ' {}="{}"'.format(
                k, html.escape(v,True))
        s += '>'
        return s
    
    def close_tag(self):
        '''Ritorna la stringa del tag di fine.'''
        return '</'+self.tag+'>'
    
    def print_tree(self, level=0):
        '''Stampa l'albero mostrando la struttura
        tramite indentazione'''
        if self.istext():
            print(' '*level + '_text_ ' +
                repr(self.content))
        else:
            print(' '*level + str(self))
            for child in self.content:
                child.print_tree(level+1)
    
    def to_string(self):
        '''Ritorna la stringa del documento HTML che 
        corrisponde all'albero di questo nodo.'''
        if self.istext():
            # usiamo escape per i caratteri speciali
            return html.escape(self.content,False)
        s = self.open_tag()
        doc = self.open_tag()
        if self.closed:
            for child in self.content:
                doc += child.to_string()
            doc += self.close_tag()
        return doc
    
    def __str__(self):
        '''Ritorna una rappresentazione semplice
        del nodo'''
        if self.istext(): return self.tag
        else: return '<{}>'.format(self.tag)
        

doc = HTMLNode('html',{},[
  HTMLNode('body',{},[
    HTMLNode('p',{},[
      HTMLNode('_text_',{},'Un paragrafo con '),
      HTMLNode('em',{},[
        HTMLNode('_text_',{},'enfasi')
      ]),
      HTMLNode('_text_',{},' e un\'immagine'),
      HTMLNode('img',{'src':'img_logo.png'},
          [],closed=False)
    ])
  ])
])
      
      
# stampa la struttura dell'albero
doc.print_tree()

# stampa la stringa HTML corrispondente
print(doc.to_string())


import html.parser

class _MyHTMLParser(html.parser.HTMLParser):
    def __init__(self):
        '''Crea un parser per la class HTMLNode'''
        # inizializza la class base super()
        super().__init__()
        self.root = None
        self.stack = []
    def handle_starttag(self, tag, attrs):
        '''Metodo invocato per tag aperti'''
        closed = tag not in ['img','br']
        node = HTMLNode(tag,dict(attrs),[],closed)
        if not self.root:
            self.root = node
        if self.stack:
            self.stack[-1].content.append(node)
        if closed:
            self.stack.append(node)
    def handle_endtag(self, tag):
        '''Metodo invocato per tag chiusi'''
        if self.stack and self.stack[-1].tag == tag:
            self.stack[-1].opentag = False
            self.stack = self.stack[:-1]
    def handle_data(self, data):
        '''Metodo invocato per il testo'''
        if not self.stack: return
        self.stack[-1].content.append(
            HTMLNode('_text_',{},data))
    def handle_comment(self, data):
        '''Metodo invocato per commenti HTML'''
        pass
    def handle_entityref(self, name):
        '''Metodo invocato per caratteri speciali'''
        if name in name2codepoint:
            c = unichr(name2codepoint[name])
        else:
            c = '&'+name
        if not self.stack: return
        self.stack[-1].content.append(
            HTMLNode('_text_',{},c))
    def handle_charref(self, name):
        '''Metodo invocato per caratteri speciali'''
        if name.startswith('x'):
            c = unichr(int(name[1:], 16))
        else:
            c = unichr(int(name))
        if not self.stack: return
        self.stack[-1].content.append(
            HTMLNode('_text_',{},c))
    def handle_decl(self, data):
        '''Metodo invocato per le direttive HTML'''
        pass

    
def parse(html):
    '''Esegue il parsing HTML del testo html e
    ritorna la radice dell'albero.'''
    parser = _MyHTMLParser()
    parser.feed(html)
    return parser.root

def fparse(fhtml):
    '''Esegue il parsing HTML del file fhtml e
    ritorna la radice dell'albero.'''
    with open(fhtml) as f:
        root = parse(f.read())
    return root

# Proviamo a fare il parsing del semplice file
# che abbiamo visto sopra.
doc = fparse('page_simple.html')
doc.print_tree()
print(doc.to_string())


def count(node):
    '''Ritorna il numero di nodi dell'albero di
    questo nodo'''
    cnt = 1
    if not node.istext():
        for child in node.content:
            cnt += count(child)
    return cnt

print('Numero di nodi:', count(doc))

def height(node):
    '''Ritorna l'altezza dell'albero con radice 
    questo nodo, cioè il massimo numero di nodi
    in un cammino radice-foglia'''
    h = 1
    if not node.istext():
        for child in node.content:
            h = max(h, height(child) + 1)
    return h

print('Altezza:', height(doc))

def find_by_tag(node, tag):
    '''Ritorna una lista dei nodi che hanno il tag'''
    ret = []
    if node.tag == tag: ret += [node]
    if not node.istext():
        for child in node.content:
            ret += find_by_tag(child,tag)
    return ret

for node in find_by_tag(doc,'a'):
    print(node.to_string())
    
for node in find_by_tag(doc,'p'):
    print(node.to_string())
    
def remove_by_tag(node, tag):
    '''Rimuove dall'albero tutti i nodi con il tag,
    esclusa la radice, cioè il nodo su cui è invocato
    il metodo.'''
    if node.istext(): return
    for child in node.content:
        remove_by_tag(child,tag)
    newcont = []
    for child in node.content:
        if child.tag == tag:
            if not child.istext():
                newcont += child.content
        else:
            newcont += [child]
    node.content = newcont
    
remove_by_tag(doc,'a')
print(doc.to_string())

remove_by_tag(doc,'_text_')
print(doc.to_string())

from urllib.request import urlopen

def print_stats(url):
    '''Stampa alcune statistiche della pagina web
    all'url specificato.'''
    with urlopen(url) as f:
        page = f.read().decode('utf8')
    doc = parse(page)
    print('Numero di nodi:', count(doc))
    print('Altezza:', height(doc))
    print('Numero di links:',
len(find_by_tag(doc,'a')))
    print('Numero di immagini:',
len(find_by_tag(doc,'img')))
    
print_stats('http://python.org')
print_stats('https://en.wikipedia.org/wiki/Python_(programming_language)')