# -*- coding: utf-8 -*-
"""
Created on Tue Oct  6 09:08:45 2020

@author: Utente
"""

from urllib.request import urlopen

# apre una pagina web
with urlopen('http://python.org') as f:
    page = f.read()
    
# dati in binario
print(page[:50])

# dati come testo
page = page.decode('utf8')
print(page[:50])


'''
url = ('https://upload.wikimedia.org/wikipedia/' +
       'commons/thumb/d/df/Face-plain.svg/' +
       '200px-Face-plain.svg.png')
with urlopen(url) as f:
    img = f.read()
    
with open('face.png', 'wb') as f:
    f.write(img)
'''    
#CONTROLLA PROBLEMI CON IMMAGINE