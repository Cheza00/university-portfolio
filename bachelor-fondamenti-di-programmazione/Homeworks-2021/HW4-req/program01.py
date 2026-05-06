# -*- coding: utf-8 -*-
'''
Anche nella poesia, così come nella musica, esiste la nozione di ritmo.
In musica, il ritmo è quella proprietà della canzone che stai ascoltando che ti fa
battere il piede o le mani a tempo, o ti guida mentre stai ballando.
Analogamente, anche le poesie o i poemi non scritti in versi liberi hanno un ritmo, determinato
dalle sillabe che compongono le parole dei loro versi.
Alcune poesie o poemi poi hanno un ritmo sempre uguale in ogni riga, mentre altri hanno un ritmo
che cambia leggermente tra una riga e la successiva.
Vorremmo quindi provare a stimare quanto una poeasia o poema è regolare o irregolare in termini di ritmo
contenuto nei versi. Un ritmo regolare significa che le varie righe della poesia o poema
hanno tutte esattamente lo stesso ritmo, diremo quindi che sono in sync.
Al contrario, un ritmo irregolare varie ad ogni riga, diremo quindi in quel caso che le
righe della poesia o poema sono out of sync.

Ogni parola in qualunque lingua è composta di sillabe che vengono pronunciate usando l'intonazione della
voce per dare loro un accento più o meno forte. Ad esempio, in italiano la parola "casa" ha un accento
sulla prima sillaba. Esiste un modo per specificare questo, si chiama trascrizione fonetica
di una parola: nell'esempio, la trascrizione fonetica di casa è kˈaza, dove l'apostrofo
indica che sulla prima sillaba (ka) c'è un accento primario.

Vogliamo scrivere un programma che, dato un file di testo con encoding 'utf-8'
contenente i versi di una poesia o poema divisi in un certo numero di righe 
(possono esserci anche righe vuote, che ignoreremo),
trovi gli accenti di ogni parola e quindi di tutta la poesia, mantenendo però la suddivisione
del testo per righe, com'era nel testo originale.
Per farlo, sfrutteremo due funzioni, "phones_for_word" e "stresses", del modulo "pronouncing"
(https://pronouncing.readthedocs.io/en/latest/).
Data quindi una riga (non vuota) della poesia, chiameremo la funzione phones_for_word su ogni parola,
ottenendo da essa la traduzione fonetica della parola (casa -> k'asa).
La funzione potrebbe restituire più di una traduzione fonetica per la stessa parola nel
caso di parole che possono essere pronunciate in diversi modi, in quel caso considereremo
solo la prima traduzione fonetica restituita.
A quel punto, chiameremo la funzione stresses che, data in input la traduzione phonetica
della parola, ci restituirà una stringa di accenti, in cui: 0=nessun accento, 1=accento primario,
2=accento secondario. Per il nostro scopo, considereremo solo gli accenti primari, quindi
tralasceremo i secondari considerandoli come assenza di accento (come se fossero degli zeri).
Inoltre, nel tradurre frasi intere in accenti, aggiungeremo uno zero tra ogni coppia di parole
(vedi anche Nota 2 sotto).

Nota 1: nel tradurre parole in fonemi, la funzione phones_for_word potrebbe restituire
una lista vuota (perché la parola è sconosciuta); in quel caso considereremo come accenti
corrispondenti alla parola una sequenza di zeri pari alla lunghezza della parola divisa
per due (parte intera del risultato); ad esempio, siccome "pierc" non ha traduzione
fonetica, considereremo come stringa di accenti "00"

Nota 2: per semplificare, ci sarà sempre uno zero extra dopo l'ultima parola di ogni riga

Ad esempio, dalla seguente riga di testo: "IN the midway of this our mortal life,"
otterremo la lista di accenti: [0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0]
Perché:
IN -> ['IH0 N', 'IH1 N'] -> 0
spazio -> 0
the -> ['DH AH0', 'DH AH1', 'DH IY0'] -> 0
spazio -> 0
midaway - > ['M IH1 D W EY2'] -> 1,0
spazio -> 0
of -> ['AH1 V'] -> 1
spazio -> 0
this -> ['DH IH1 S', 'DH IH0 S'] -> 1
spazio -> 0
our -> ['AW1 ER0', 'AW1 R', 'AA1 R'] -> 1,0
spazio -> 0
mortal -> ['M AO1 R T AH0 L'] -> 1,0
spazio -> 0
life -> ['L AY1 F'] -> 1
spazio -> 0

Una volta tradotta tutta la poesia in sequenze di zeri e uni, avremo una lista di liste
di varie lunghezze, ad esempio:

IN the midway of this our mortal life,
I found me in a gloomy wood, astray
Gone from the path direct: and e'en to tell
It were no easy task, how savage wild
That forest, how robust and rough its growth,
Which to remember only, my dismay
Renews, in bitterness not far from death.

[
    [0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0],
    [1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0],
    [1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0],
    [1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0],
    [1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0],
    [1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0],
    [0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0]
]

Vogliamo trasformare questa lista di liste in una matrice di accenti,
aggiungendo un opportuno numero di zeri alla fine delle righe
eventualmente più corte della riga più lunga:

[
    [0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0],
    [1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0],
    [1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0],
    [1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0],
    [1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0],
    [1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0],
    [0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0]
]

A questo punto calcoliamo la sincronizzazione tra le tutte le coppie di righe
della matrice. Date due liste A e B, ciascuna di N valori 0 o 1, e un valore tau
compreso tra zero e N, definiamo l'indice di sincronizzazione tra A e B come:

       0.5 * (c(B|A) + c(A|B))
Sync = -----------------------
          sqrt(m(A)*m(B))

Dove:
    - c(B|A) è il numero di volte in cui un accento di B è preceduto da un accento di A 
      a una distanza <= tau
    - c(A|B) è il numero di volte in cui un accento di A è preceduto da un accento di A 
      a una distanza <= tau
    - m(A) è il numero di accenti in A
    - m(B) è il numero di accenti in B

e dove, ricordiamo, un valore di 1 in A o B rappresenta un accento.

NOTA: se m(A) == 0 o m(B) == 0 assumiamo come valore di Sync zero

Ad esempio, date le due sequenze:
    - A = [0, 0, 0, 0, 1, 0, 0, 1]
    - B = [1, 0, 1, 0, 1, 0, 0, 0]
    - tau = 3

avremo:
    - c(B|A) = 1, perché solo il terzo accento in B (posizione 4) è preceduto in A da un
      accento a una distanza inferiore o uguale a 3 (in questo caso l'1 in A coincide come
      posizione con l'1 in B)
    - c(A|B) = 2, perché entrambi gli accenti di A sono preceduti in B da due accenti entro
      3 posizioni
    - m(A) = 2
    - m(B) = 3
    - Sync = 0.5 * (1 + 2) / sqrt(2 * 3) = 0.6123724356957946

Data una poesia o poema in input, definiamo l'indice di sincronizzazione del testo come
la media tra i valori di Sync tra tutte le coppie di righe  di accenti (A1, A2)
dove A1 e A2 non sono la stessa lista
NOTA: due liste diverse possono comunque contenere gli stessi valori

Scrivere la funzione PoemSync che, dati in input:
- il path di un file di testo contenente una poesia
- il path del file di output in cui salvare la matrice di accenti
- il valore (intero) di tau

svolga le seguenti operazioni:
- apra il file della poesia e calcoli la matrice degli accenti
- salvi la matrice degli accenti nel file il cui path è specificato in input, nel formato specificato
- calcoli e ritorni l'indice di sincronizzazione del testo, arrotondato
  alla sesta cifra decimale (es: 0.6123724356957946 -> 0.612372)

nota: tornando alla matrice degli accenti riportata sopra, il file generato
da PoemSync conterrà:

000010010101001001000
101010000010010010000
101000100100010101010
101010100101010010000
101001001000101010000
101001001001001000000
010001000101010100000


ESEMPIO DI ESECUZIONE:

PoemSync("example.txt", "example.out.txt", 2)

contenuto di example.txt:
No one can tell me
Where the wind comes from
Where the wind comes from

no one can tell me
['no', 'one', 'can', 'tell', 'me']
[1, 0, 1, 0, 1, 0, 1, 0, 1, 0]
where the wind comes from
['where', 'the', 'wind', 'comes', 'from']
[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]
where the wind comes from
['where', 'the', 'wind', 'comes', 'from']
[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]

matrix:
1010101010
1000101010
1000101010

sync tra a=[1, 0, 1, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (4, 4) (c(a|b), m(a)) = (5, 5)
sync = 1.0062305898749053

sync tra a=[1, 0, 1, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (4, 4) (c(a|b), m(a)) = (5, 5)
sync = 1.0062305898749053

sync tra a=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 1, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (5, 5) (c(a|b), m(a)) = (4, 4)
sync = 1.0062305898749053

sync tra a=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (4, 4) (c(a|b), m(a)) = (4, 4)
sync = 1.0

sync tra a=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 1, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (5, 5) (c(a|b), m(a)) = (4, 4)
sync = 1.0062305898749053

sync tra a=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0] e
         b=[1, 0, 0, 0, 1, 0, 1, 0, 1, 0]
(c(b|a), m(b)) = (4, 4) (c(a|b), m(a)) = (4, 4)
sync = 1.0

PoemSync = 1.004154

TIMEOUT: 0.5s

'''

import pronouncing
import math

def PoemSync(inputfilename, outputfilename, tau): #1
    with open(inputfilename, encoding='utf8') as f:
        poema = f.readlines()
    poema = togli_char(poema)
    tot, cont, testo = crea_dizionario(poema, tau)
    with open(outputfilename, mode='w', encoding='utf8') as f:
        f.write(testo)
    media_sync = tot / cont
    return round(media_sync,6)


def togli_char(poema): #2
    poema2 = []
    for parola in poema:
        for lettera in parola:
            if not lettera.isalpha() and lettera != ' ':
                parola = parola.replace(lettera, ' ')
        if parola != " ":
            poema2.append(parola)
    return poema2


def crea_dizionario(poema, tau): #3
    dizionario = {}
    max_len = 0
    for riga in poema:
        verso = str(riga)
        dizionario[verso] = ['']
        riga = riga.split()
        for parola in riga:
            fonetica = pronouncing.phones_for_word(parola)
            if fonetica != []:
                accenti = pronouncing.stresses(fonetica[0])
                accenti = accenti.replace('2','0')
            else:
                accenti = '0' * (len(parola) // 2)
            dizionario[verso][0] += (accenti + '0')
        lunghezza = len(dizionario[verso][0])
        if lunghezza > max_len:
            max_len = lunghezza
    for elemento in dizionario:
        if len(dizionario[elemento][0]) < max_len:
            dizionario[elemento][0] = dizionario[elemento][0] + ('0' * (max_len - len(dizionario[elemento][0])))
        dizionario = salva_posizioni(elemento, dizionario[elemento][0], dizionario)
    return sync(poema, dizionario, tau)


def salva_posizioni(elemento, elemento0, dizionario): #4
    dizionario[elemento].append(set())    
    for i in range(len(elemento0)):
        if elemento0[i] == '1':
            dizionario[elemento][1].add(i)
    return dizionario


def sync(poema, dizionario, tau): #5
    concatenazioni = {}
    tot = 0
    cont = 0
    i = 0
    testo = ''
    for y in range(len(poema)):
        A = dizionario[poema[y]][0]
        testo = testo + A + '\n'
        if len(dizionario[poema[y]]) == 2:
            m_A = len(dizionario[poema[y]][1])
            dizionario[poema[y]].append(m_A)
        else:
            m_A = dizionario[poema[y]][2]
        tot, cont, concatenazioni = calcola_sync(i, tau, A, dizionario, poema, m_A, tot, cont, concatenazioni, dizionario[poema[y]][1])
        i += 1
    return tot, cont, testo


def calcola_sync(i, tau, A, dizionario, poema, m_A, tot, cont, concatenazioni, posizioniA): #6
    for x in range(i+1,len(poema)):
        B = dizionario[poema[x]][0]
        if len(dizionario[poema[x]]) == 2:
            m_B = len(dizionario[poema[x]][1])
            dizionario[poema[x]].append(m_B)
        else:
            m_B = dizionario[poema[x]][2]
        if m_A == 0 or m_B == 0:
            cont += 1
            continue
        if A+B in concatenazioni:
            c_a = concatenazioni[A+B]
            c_b = concatenazioni[B+A]
        else:
            c_a, c_b = confronta(posizioniA, dizionario[poema[x]][1], A, B, tau)
            concatenazioni[A+B] = c_a
            concatenazioni[B+A] = c_b
        tot += (0.5 * (c_b + c_a)) / math.sqrt(m_A * m_B)
        cont += 1
    return tot, cont, concatenazioni



def confronta(posizioniA, posizioniB, A, B, tau): #7
    c_a = 0
    c_b = 0
    for indice in posizioniA:
        if indice-tau >= 0:
            temp = B[indice-tau:indice+1]
        else:
            temp = B[:indice+1]
        if '1' in temp:
            c_a += 1
    for indice in posizioniB:
        if indice-tau >= 0:
            temp = A[indice-tau:indice+1]
        else:
            temp = A[:indice+1]
        if '1' in temp:
            c_b += 1
    return c_a, c_b


    
            
if __name__ == "__main__":
    # your tests go here
    pass


