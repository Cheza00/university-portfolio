
''' 
Abbiamo una stringa S contenente una sequenza di interi non-negativi separati da virgole
ed un intero positivo m.

Progettare una funzione es1(S,m) che prende in input  la stringa S e l'intero m e
restituisce il numero di sottostringhe di S la somma dei cui valori e' m.

Ad esempio, per S='3,0,4,0,3,1,0,1,0,1,0,0,5,0,4,2' e m=9 la funzione deve restituire 7.

Infatti:
'3,0,4,0,3,1,0,1,0,1,0,0,5,0,4,2'
 _'0,4,0,3,1,0,1,0'_____________
 _'0,4,0,3,1,0,1'_______________
 ___'4,0,3,1,0,1,0'_____________
____'4,0,3,1,0,1'_______________
____________________'0,0,5,0,4'_
______________________'0,5,0,4'_
 _______________________'5,0,4'_

NOTA: è VIETATO usare/importare ogni altra libreria a parte quelle già presenti

NOTA: il timeout previsto per questo esercizio è di 1 secondo per ciascun test (sulla VM)

ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8
    (ad esempio editatelo dentro Spyder)
'''

def es1(S,m):
    conta = 0
    lista = S.split(sep=',')
    #lista2 = []
    #for elemento in lista:
        #lista2.append(int(elemento))
    j = 0 
    while j <= len(lista):
        somma = 0
        i = j
        #print (i)
        if int(lista[j]) == m:
            conta += 1
        if int(lista[j]) < m:
            while i < len(lista) and somma <= m:
                somma += int(lista[i])
                #print(somma)
                if somma == m:
                    conta += 1
                i += 1    
        j += 1
    return conta

#if lista[i]=0                        
        



if __name__ == '__main__':
    # inserisci qui i tuoi test
    pass
