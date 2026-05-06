# -*- coding: utf-8 -*-
"""
Created on Sat Oct  3 11:20:09 2020

@author: Utente
"""
import os    # necessario?

class FSNode(object):
    def __init__(self, path):
        self.path = path
        self.content = [] # lista dei nodi figli
    def __str__(self):
        return 'FSNode("'+self.path+'")'
    
def gen_fstree(path):
    '''Genera l'albero partendo dal percorso path e
    ritorna il nodo radice'''
    node = FSNode(path)
    if os.path.isdir(path):
        for name in os.listdir(path):
            if name.startswith('.'): continue
            fullpath = os.path.join(path, name)
            node.content += [gen_fstree(fullpath)]
    return node

def print_fstree(node, level):
    '''Stampa l'albero con radice node'''
    # os.path.basename ritorna l'ultima componente
    print('  '*level + os.path.basename(node.path))
    # stampa ricorsivamente i sottoalberi dei nodi figli
    for child in node.content:
        print_fstree(child, level + 1)
        
def count_fstree(node):
    '''Ritorna il numero di nodi dell'albero di
    radice root'''
    count = 1
    # per ogni nodo figlio, 
    for child in node.content:
        # conta i nodi nel suo sottoalbero 
        count += count_fstree(child)
    return count

def count_fstree_print(node,level):
    '''Ritorna il numero di nodi dell'albero di
    radice root'''
    print('  '*level + os.path.basename(node.path))
    count = 1
    for child in node.content:
        count += count_fstree_print(child,level+1)
    print('  '*level,'->',count)
    return count

def find_fstree(node, name):
    '''Ritorna una lista dei nodi dell'albero di
    radice root con nome name'''
    ret = []
    if os.path.basename(node.path) == name:
        ret += [node]
    # per ogni nodo figlio,
    for child in node.content:
        # cerca ricorsivamente nel suo sottoalbero
        ret += find_fstree(child, name)
    return ret
# CONTROLLA PERCHE' NON FUNZIONA
