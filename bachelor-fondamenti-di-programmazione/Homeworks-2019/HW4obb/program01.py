
''' 
    Abbiamo n stringhe di caratteri.  
    All'interno delle stringhe  e' nascosta una parola segreta come sottostringa di 
    caratteri consecutivi. 
    Sappiamo con certezza che la parola si ripete uguale esattamente una volta in ciascuna 
    stringa ma non sappiamo dove. 
    Della parola conosciamo la lunghezza M e sappiamo che non ci sono altre sottostringhe 
    di lunghezza M che si ripetono una sola volta in tutte le stringhe. 
    Vogliamo sapere per ogni stringa  la posizione dove compare il primo carattere 
    della parola nascosta.
    Ad esempio per le 6 stringhe con parola nascosta di lunghezza 3:
    
    moneta
    maratoneta
    pitone
    onesto
    storione
    sonetto
    
    la parola nascosta è 'one' e le posizioni sono nell'ordine: 1, 5, 3, 0, 5, 1
    

    
    Progettare una funzione es1(ftesto) che prende in input  un file contenente la lunghezza 
    della parola nascosta e le n stringhe di caratteri e restituisce una lista di n interi.
    L'intero in  posizione i della lista e' la posizione dove compare il primo carattere 
    della parola nascosta nella stringa i. 
    
    Le informazioni nel file ftesto sono organizzate nel seguente modo:
    - la prima riga contiene la lunghezza della parola nascosta (un intero).
    - seguono poi le stringhe di caratteri, ciascuna stringa occupa una o piu' 
    righe consecutive del file ed e' separata dalla stringa seguente da una linea vuota.
    Ogni riga del file termina con un' andata a capo.
    Vedi ad esempio il file ft1.txt che codifica l'istanza vista prima.
    
    es('ft1.txt') deve restituire la lista [1,5,3,0,5,1]    
   

    NOTA: il timeout previsto per questo esercizio è di 1 secondo per ciascun test

    ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8 
    (ad esempio editatelo dentro Spyder)
   
'''


    
def es1(ftesto):
    d = {} 
    lista = []
    f = open(ftesto,'r')
    val = int(f.readline())
    stringhe = f.readlines()
    f.close()
    i = 0
    c = 0
    for x in stringhe:
        if i not in d:
            d[i] = ''
        if len(x) >= 2:
            stringhe[c] = x.replace('\n','')
            d[i] += stringhe[c]
        else: 
            c += 1
            i += 1
    j = 0
    while val <= len(d[0]):
        lista.append(d[0][j:val])
        val += 1
        j += 1
    lista2 = lista.copy()
    for elemento in lista:
        for k in d:
            if d[k].count(elemento) != 1:
                lista2.remove(lista2[lista2.index(elemento)])
                break
    lista3 = []
    for elemento in d:
        lista3.append(d[elemento].find(lista2[0]))
    return lista3
    
    

if __name__ == '__main__':
    # inserisci qui i tuoi test
    pass
