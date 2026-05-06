

def es57(griglia):
    '''
     Il puzzle dei grattacieli posiziona dei grattacieli in una griglia quadrata NxN in modo
     che ogni riga e colonna contenga solo grattacieli di altezza diversa (valori interi da 1 a N)
     Per ciascuna riga e colonna, lungo i 4 lati della griglia all'inizio è dato solo il numero
     di grattacieli che sono visibili da quel lato lungo quella riga/colonna.
     Date le quattro liste Nord, Est, Sud e Ovest ciascuna di lunghezza N tali che per 0<i<N:
    - la lista Nord e' tale che Nord[i] contiene il numero di grattaceli visibili  nella colonna i della griglia guardando da nord.
    - la lista Est e' tale che Est[i] contiene il numero di grattaceli visibili  nella riga i della griglia guardando da est.
    - la lista Sud e' tale che Sud[i] contiene il numero di grattaceli visibili nella colonna i della griglia guardando da sud.
    - la lista Ovest e' tale che Ovest[i] contiene il numero di grattaceli visibili riga i della griglia guadando da ovest.
     il gioco consiste nel trovare le posizioni dei grattacieli.

    Realizzate la funzione es57(griglia) che, data
    - una griglia quadrata NxN (rappresentata tramite lista di liste in cui ciascuna lista e'
    una riga della matrice) se la griglia rappresenta un puzzle dei grattacieli deve restituire
    una tupla in cui compaiono nell'ordine le 4 liste Nord, Est, Sud e Ovest.
    Qualora la griglia non sia un puzzle per grattacieli (vale a dire che nelle righe o colonne
    della  griglia  ci sono numeri ripetuti o numeri al di fuori del range che va da 1 a N o
    valori non numerici) allora la funzione restituisce 4 liste vuote.
    Ad esempio per griglia= [[1,2,3,4],[4,1,2,3],[3,4,1,2],[2,3,4,1]] viene restituita la tupla
    ([2,2,2,1],[1,2,2,2],[3,2,1,4],[4,1,2,3]).
    Per griglia= [[1,2,3,4],[4,1,'c',3],[3,4,1,2],[2,3,4,1]] oppure
    griglia= [[1,2,3,4],[4,1,2,3],[3,4,2,1],[2,3,4,1]]
    oppure griglia= [[1,2,3,4],[4,1,2,3],[3,4,1,2],[2,3,6,1]] viene restituita la tupla([],[],[],[]).
    '''
    N = []
    S = []
    E = []
    O = []
    l = len(griglia)
    # controllo che nelle righe ci siano solo valori numerici e diversi tra loro
    for elemento in griglia:
        stringa = ''
        for valore in elemento:
            if str(valore) in stringa or str(valore).isalpha() or valore > l:
                return ([],[],[],[]) 
            else: 
                stringa += str(valore)
    # controllo nelle colonne (ho già controllato i casi in cui ci potrebbero essere lettere o out of range)
    for i in range(l):
        stringa = ''
        for elemento in griglia:
            if str(elemento[i]) in stringa:
                return ([],[],[],[]) 
            else: 
                stringa += str(elemento[i])
    # costruisco una lista per trovarmi le liste nord e sud 
    x = 0
    lista = []
    while x < l:
        lista.append([])
        for elemento in griglia:
            lista[x].append(elemento[x])
        x += 1
    # compongo le 4 liste
    # lista ovest
    for elemento in griglia:
        massimo = 0
        c = 0
        j = 0
        while j in range(l):
            if elemento[j] > massimo:
                c += 1
                massimo = elemento[j]
            j += 1
        O.append(c) 
    # lista est
    for elemento in griglia:
        c = 0
        j = l-1
        massimo = 0
        while j >= 0:
            if elemento[j] > massimo:
                c += 1
                massimo = elemento[j]
            j -= 1
        E.append(c)  
    # lista nord
    for elemento in lista:
        c = 0
        j = 0
        massimo = 0
        while j in range(l):
            if elemento[j] > massimo:
                c += 1
                massimo = elemento[j]
            j += 1
        N.append(c)   
    # lista sud
    for elemento in lista:
        c = 0
        j = l-1
        massimo = 0
        while j >= 0:
            if elemento[j] > massimo:
                c += 1
                massimo = elemento[j]
            j -= 1
        S.append(c)      
    return (N, E, S, O)

# un modo più efficiente consisteva nell'accorpare a due a due i cicli per le liste 
# n-s-e-o (molto simili tra loro), richiamandole come funzioni e passando dei parametri
# per ipostarle correttamente nei vari casi.