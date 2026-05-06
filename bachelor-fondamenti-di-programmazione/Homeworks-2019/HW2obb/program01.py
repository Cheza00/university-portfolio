'''
Si e' appena svolta una gara automobilistica con n piloti aventi per identificativi 
gli interi 1,2...n.
Nel corso della gara assistiamo a due tipi di eventi:
- il pilota i viene superato dal suo immediato inseguitore 
  (questo evento e' segnalato tramite una stringa 's'+str(i))
- il pilota i abbandona la gara 
  (questo evento e' segnalato dalla stringa 'e'+str(i))
Disponiamo di una  lista lista1 in cui sono raccolti sotto forma di stringhe e in ordine
temporale di occorrenza i due tipi di eventi:
-una stringa 's'+str(i) indica che il pilota i e' stato superato del suo immediato inseguitore.
-una stringa 'e'+str(i) indica che il pilota i abbandona la gara.
Disponiamo anche di una lista lista2 che riporta gli identificativi dei piloti nell'ordine 
in cui appaiono alla griglia di partenza ( in   lista2[i], 0<=i<n, troviamo 
l'identificativo del pilota che occupa l'i-ma posizione nella griglia di partenza).
Quindi il primo è in posizione 0.
Vogliamo ottenere una lista lista3 che riporti l'ordine d'arrivo  dei  piloti che portano a termine la gara
(se m piloti portano a termine la gara la lista lista3 avra' lunghezza m ed  
 lista3[i] sara' l'identificativo del pilota che nella gara arriva in posizione i-1).

Progettare una funzione es(lista1,lista2) che date
-lista 1: la lista dei due tipi di  eventi che si sono verificati nel corso della gara
-lista 2: la lista con le posizioni di partenza dei piloti
restituisce la lista contenente  gli identificativi dei  piloti che portano a termine la gara
in ordine di arrivo crescente. 
Attenzione, al termine dell'esecuzione della funzione le  liste lista1 e lista2 non  devono 
risultare modificate.
  
Ad esempio nel caso di:
lista1=['e2','s4','s3','s3','e1','s5'] e lista2=[5,3,2,4,1] i 6 eventi determinano i 
seguenti 6 cambiamenti nell'ordine di graduatoria:

5 ->e2-> 5 ->s4-> 5 ->s3-> 5 ->s3-> 5 ->e1-> 5->s5->4 
3        3        3        1        1        4      5
2        4        1        3        4        3      3
4        1        4        4        3
1                 

la funzione deve dunque restituire la lista [4,5,3]

NOTA: è VIETATO usare/importare ogni altra libreria a parte quelle già presenti

NOTA: il timeout previsto per questo esercizio è di 1.5 secondi per ciascun test (sulla VM)

ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8
    (ad esempio editatelo dentro Spyder)

'''

def es(lista1, lista2):
    lista3 = lista2[:]
    for elemento in lista1:
        if elemento[0] == 'e':
            lista3.remove(lista3[lista3.index(int(elemento[1:]))])
        else:
            var2 = lista3.index(int(elemento[1:]))
            lista3.insert(var2,lista3.pop(var2+1))
    return lista3



if __name__ == '__main__':
    pass
    # inserisci qui i tuoi test personali
