def es3(ins1, ins2):
    '''
    progettare la funzione es3(ins1, ins2) che:
    - riceve  in input due insiemi  di numeri naturali
    - trova le terne (a,b,c) con a,b e c in insi1 con la proprieta' che a<b<c e a+b+c e' in insi2
    - restituisce l'insieme di tutte le triple trovate.
    Nella lista restituita le triple devono essere  rappresentate tramite tuple e le
    varie tuple devono comparire nella lista per somma di componenti crescenti e in caso di parita'
    in ordine lessicografico crescente.
    ESEMPIO:
    se ins1={ 2,4,5,6,8,9} e ins2={5,15,19,25} la funzione restituisce la lista
    [(2, 4, 9), (2, 5, 8), (4, 5, 6), (2, 8, 9), (4, 6, 9), (5, 6, 8)]
    '''
    lista = []                      # se la lughezza di ins1 è minore di 3 si può ritornare insieme vuoto
    for elemento in ins1:           # si può trasformare il primo insieme in lista e lavorare più agilmente su quello, anche sortandolo
        a = elemento
        for elemento2 in ins1:
            if a >= elemento2:
                continue
            b = elemento2
            for elemento3 in ins1:
                if b >= elemento3:
                    continue
                c = elemento3
                if a + b + c in ins2:
                    lista.append((a,b,c))
    lista.sort(key=lambda x: sum(x))
    return lista
    
                    
                    
                
