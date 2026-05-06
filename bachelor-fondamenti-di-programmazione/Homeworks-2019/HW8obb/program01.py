# -*- coding: utf-8 -*-
'''
    Data una matrice di caratteri ed una parola, diciamo che la  parola e' presente
    nella matrice se e' possibile ottenerla collezionando i caratteri
    che si incontrano con una serie di spostamenti tra celle adiacenti.
    I soli spostamenti permessi sono:
    a) da una cella alla cella adiacente a destra (D)
    b) da una cella alla cella in basso (B)
    la parola, se presente nella matrice, e'  individuata dalle coordinate
    di riga e colonna della cella da cui si parte e dalla stringa di 'D' e 'B'
    che denota la sequenza di spostamenti da effettuare per collezionare i suoi caratteri.

    Si consideri ad esempio la matrice

      ANTANDBER
      LNOANRLNT
      EIOSGEARO
      SSUNALSIC
      AANDEOAAO

    in questa matrice:
    'ANGELO' e' presente ed e' individuata da (1,3) e 'DGDGG'
    'ANDREA' e' presente ed e' individuata da (0,3) e 'DDGGD'
    'ENRICO' e' presente ed e' individuata da (0,7) e 'GGGDG'
    'ALESSANDRO', 'ANTONIO' e 'ALBERTO' sono parole non presenti.

    Abbiamo una matrice ed una lista di parole e vogliamo sapere quali sono presenti
    nella matrice e quali no.

    Progettare una funzione es1(ftesto) che preso l'indirizzo di un file
    di testo in cui e' registrata la matrice e la lista di parole da ricercare e
    restituisce una lista.
    Nella lista restituita all'i-esimo posto troviamo:
    - -1 se la parola non e' presente nella matrice.
    - la posizione dell'i-esima parola della lista nella matrice
      (vale a dire la terna (riga,colonna,s) con (riga,colonna) coordinate iniziali
      della cella d'inizio degli spostamenti ed s stringa che denota gli spostamenti).
      Nel caso la parola sia presente piu' volte nella matrice deve essere restituita
      la posizione piu' in alto  a sinistra in cui compare e nel caso compaia
      piu' volte a partire dalla stessa casella delle diverse stringhe che
      la individuano va presa quella che precede le altre lessicograficamente.

    Ad esempio, Per la matrice vista  sopra e la lista
    ['ALBERTO','ALESSANDRO','ANDREA', 'ANGELO', 'ANTONIO', 'ENRICO']
    la funzione es1 restituira' la lista
    [-1,-1,(0,3, 'DDGGD'),(1,3, 'DGDGG'),-1,(0,7, 'GGGDG')]

    Il file ftesto  contiene  la matrice  e, di seguito  l'elenco delle parole.
    Una serie di 1 o piu'  linee vuote precede la reppresentazione della matrice,
    separa il diagramma dall'elenco delle parole e segue l'elenco delle parole.
    La matrice  e' registrata per righe (una riga per linea e in linee consecutive) gli
    elementi di ciascuna riga sono adiacenti a formare una stringa.
    La lista delle parole occupa  invece linee consecutive con  una o piu' parole o
    separate da spazi per ciascuna linea.
    Per un esempio si veda il file esempio_Disney.pdf

    NOTA: il timeout previsto per questo esercizio è di 1 secondo per ciascun test

    NOTA: almeno una delle funzioni realizzate DEVE essere ricorsiva, ad esempio
    potete scandire la matrice iterativamente e le lettere della parola cercata ricorsivamente.

    NON usate nessuna libreria.

    ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8
    (ad esempio editatelo dentro Spyder)

'''

# Codice di Monti

def cicla(a,f):
    while a=='\n': a=f.readline()
    return a
   
def leggiFile(ftesto):
    ''' legge la matrice M e la lista delle parole PAROLE. 
    Il file comincia con una serie di linee vuote. 
    poi una di seguito all'altra le righe della matrice rappresentate come stringhe 
    la matrice e' seguita da una serie di righe vuote e poi comincia la lista 
    delle parole, una o piu' parole per riga separate da spazi.
    Al temine eventualmente una serie di righe bianche''' 
    with open(ftesto) as f:
        a=f.readline()
        a=cicla(a,f)
        M=[]
        while a!='\n':
            M.append(list(a))
            a=f.readline()
        r=['\n']*len(M[0])
        M+=[r]
        print(M)
        a=cicla(a,f)
        PAROLE=[]
        while a!='\n' and a!='':
            b=a.split() 
            PAROLE.extend(b)
            a=f.readline()
    return M,PAROLE
    

def es1(ftesto):
    M,PAROLE=leggiFile(ftesto)
    lista=[]
    d=costruisci(M)
    alf=set(d)
    print(alf)
    for x in PAROLE:
        y=set(x)
        if y-alf==set():
            lista.append(cerca(x,M,d))
        else:
            lista.append(-1)
    return lista

def costruisci(M):
    d={}
    for i in range(len(M)-1):
        for j in range(len(M[0])-1):
            try:
                d[M[i][j]].append((i,j))
            except:
                d[M[i][j]]=[(i,j)]
    print(d)
    return d

        
def cerca(x,M,d):
    b=list(x)
    print(b)
    for i,j in d[x[0]]:
        a=cerca1(i,j,b,M,1)
        if a!=None:
            return (i,j,a)
    return -1


def cerca1(i,j,p,M,l):
    if l==len(p):return ''
    if  M[i][j+1]==p[l]:
        x=cerca1(i,j+1,p,M,l+1)
        if x!=None: return 'D' +x
    if  M[i+1][j]==p[l]:
        x=cerca1(i+1,j,p,M,l+1)
        if x!=None: return 'G' +x
    return None

              
if __name__ == '__main__':
    print(es1('esempio1.txt'))
    # inserite qui i vostri test








'''
# MIO CODICE VECCHIO
def es1(ftesto):
    f = open(ftesto,'r')
    stringhe = f.readlines()
    f.close()
    matrice, parole = sep(stringhe)
    print(parole)
    print(matrice)
    d = trova_indici(matrice, parole)
    #print(d)
    finale = []
    path = ''
    #c = 0
    #posizione = (x,y)
    for parola in parole:
        pass
        #for lettera in parola:
            #print(lettera,parola)
#        iniziale = parola[0]
#        if iniziale not in d:
 #           finale.append(-1)
#          break
 #       else:
#            for tupla in d[iniziale]:
#                x = tupla[0]
 #               y = tupla[1]
 #              percorso = trova_sequenza(d, parola, x, y, path)
  #              print(parola, tupla,percorso)
                
                    
    

def sep(stringhe):
    lista1 = []
    lista2 = [] 
    matrice = []
    parole = []
    for elemento in stringhe:
        if elemento != '\n':
            lista1 = stringhe[stringhe.index(elemento):]
            break   
    for elemento in lista1:
        #elemento = str(elemento)
        if elemento != '\n':
            matrice.append(elemento[:-1])
        else:
            lista2 = lista1[lista1.index(elemento):]
            break   
    for elemento in lista2:
        if elemento == '\n':
            pass
        elif '\n' in elemento:
            if ' ' in elemento:
                parole += elemento[:-1].split(' ')
            else:
                parole.append(elemento[:-1])
        else:
            if ' ' in elemento:
                parole += elemento.split(' ')
            else:
                parole.append(elemento)
    return matrice, parole


def trova_indici(matrice, parole):
    d = {}
    for riga in range(0, len(matrice)):            
        for colonna in range(0, len(matrice[0])):
            tupla = (riga,colonna)
            if matrice[riga][colonna] in d:
                d[matrice[riga][colonna]].append(tupla)
            else:
                d[matrice[riga][colonna]] = [tupla] 
    return d

def trova_sequenza(d, parola, x, y, path):
    print(x,y)
    if len(parola) == 2:
        if parola[1] in d:
            for elemento in d[parola[1]]:
                x2 = elemento[0]
                y2 = elemento[1]
                if x2 == x and y2 == (y + 1):
                    #tupla = (elemento[0],elemento[1])
                    return 'D',x,y
                elif elemento[0] == (x + 1) and elemento[1] == y:
                    #tupla = (elemento[0],elemento[1])
                    return 'G',x,y
                else: return 'N'
        else: return 'N',x,y
    #path = 'OK' + path
    ric = trova_sequenza(d, parola[1:], x, y, path)
    path = 'OK' + ric
    return path,x,y
        
    
        






if __name__ == '__main__':
    pass
    # inserite qui i vostri test
'''