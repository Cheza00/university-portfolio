''' 
Il sindaco si una città deve pianificare un nuovo quartiere.  Voi fate
parte dello studio di architetti che deve progettare il quartiere.  Vi
viene fornito un file che contiene divisi in righe, le informazioni
che descrivono in pianta le fasce East-West (E-W) di palazzi, ciascuno
descritto da larghezza, altezza, colore da usare in pianta.

I palazzi devono essere disposti in pianta rettangolare
in modo che:
  - tutto intorno al quartiere ci sia una strada di larghezza minima
    indicata.
  - in direzione E-W (orizzontale) ci siano le strade principali,
    dritte e della stessa larghezza minima, a separare una fascia di
    palazzi E-W dalla successiva.  Ciascuna fascia E-W di palazzi può
    contenere un numero variabile di palazzi.  Se una fascia contiene
    un solo palazzo verrà disposto al centro della fascia.
  - in direzione North-South (N-S), tra ciascuna coppia di palazzi
    consecutivi, ci dev'essere almeno lo spazio per una strada
    secondaria, della stessa larghezza minima delle altre.

Vi viene chiesto di calcolare la dimensione minima dell'appezzamento
che conterrà i palazzi.  Ed inoltre di costruire la mappa che li
mostra in pianta.

Il vostro studio di architetti ha deciso di disporre i palazzi in modo
che siano **equispaziati** in direzione E-W, e di fare in modo che
ciascuna fascia E-W di palazzi sia distante dalla seguente dello
spazio minimo necessario alle strade principali.

Per rendere il quartiere più vario, il vostro studio ha deciso che i
palazzi, invece di essere allineati con il bordo delle strade
principali, devono avere se possibile un giardino davanti (a S) ed uno
dietro (a N) di uguale profondità.  Allo stesso modo, dove possibile,
lo spazio tra le strade secondarie ed i palazzi deve essere
distribuito uniformemente in modo che tutti possano avere un giardino
ad E ed uno a W di uguali dimensioni.  Solo i palazzi che si
affacciano sulle strade sul lato sinistro e destro della mappa non
hanno giardino su quel lato.

Vi viene fornito un file txt che contiene i dati che indicano quali
palazzi mettere in mappa.  Il file contiene su ciascuna riga, seguiti
da 1 virgola e/o 0 o più spazi o tab, gruppi di 5 valori interi che
rappresentano per ciascun palazzo:
  - larghezza
  - altezza
  - canale R del colore
  - canale G del colore
  - canale B del colore

Ciascuna riga contiene almeno un gruppo di 5 interi positivi relativi
ad un palazzo da disegnare. Per ciascun palazzo dovete disegnare un
rettangolo del colore indicato e di dimensioni indicate

Realizzate la funzione ex(file_dati, file_png, spaziatura) che:
  - legge i dati dal file file_dati
  - costruisce una immagine in formato PNG della mappa e la salva nel
    file file_png
  - ritorna le dimensioni larghezza,altezza dell'immagine della mappa

La mappa deve avere sfondo nero e visualizzare tutti i palazzi come segue:
  - l'argomento spaziatura indica il numero di pixel da usare per lo
    spazio necessario alle strade esterne, principali e secondarie,
    ovvero la spaziatura minima in orizzontale tra i rettangoli ed in
    verticale tra le righe di palazzi
  - ciascun palazzo è rappresentato da un rettangolo descritto da una
    quintupla del file
  - i palazzi descritti su ciascuna riga del file devono essere
    disegnati, centrati verticalmente, su una fascia in direzione
    E-W della mappa
  - i palazzi della stessa fascia devono essere equidistanti
    orizzontalmente l'uno dall'altro con una **distanza minima di
    'spaziatura' pixel tra un palazzo ed il seguente** in modo che tutti
    i primi palazzi si trovino sul bordo della strada verticale di
    sinistra e tutti gli ultimi palazzi di trovino sul bordo della
    strada di destra
    NOTA se la fascia contiene un solo palazzo dovrà essere disegnato
    centrato in orizzontale
  - ciascuna fascia di palazzi si trova ad una distanza minima in
    verticale dalla seguente per far spazio alla strada principale
    NOTE la distanza in verticale va calcolata tra i due palazzi più
    alti delle due fasce consecutive. 
    Il palazzo più grosso della prima riga si trova appoggiato al
    bordo della strada principale E-W superiore. 
    Il palazzo più grosso dell'ultima riga si trova appoggiato al
    bordo della strada principale E-W inferiore 
  - l'immagine ha le dimensioni minime possibili, quindi:
     - esiste almeno un palazzo della prima/ultima fascia a
       'spaziatura' pixel dal bordo superiore/inferiore
     - esiste almeno una fascia che ha il primo ed ultimo palazzo a
       'spaziatura' pixel dal bordo sinistro/destro
     - esiste almeno una fascia che non ha giardini ad E ed O

    NOTA: nel disegnare i palazzi potete assumere che le coordinate
        saranno sempre intere (se non lo sono avete fatto un errore).
    NOTA: Larghezza e altezza dei rettangoli sono tutti multipli di due.
'''
import images

def ex(file_dati, file_png, spaziatura):
    dizionario = {}
    with open(file_dati, encoding='utf8') as f:
        testo = f.read()
    testo = testo.split('\n')
    lista = []
    lista2 = []
    i = 0
    for elemento in testo:
        elemento = elemento.replace('\t', ',').replace(' ',',')
        elemento = elemento.split(',')
        if elemento != []:
            lista.append(elemento)
        i += 1
    k = 0
    conta = 0
    for elemento in lista:  
        if elemento == '':
            continue
        lista2.append([])
        for stringa in elemento:
            if stringa == '':
                continue
            if conta == 0:
                lista2[-1].append([])
            if stringa != '':
                lista2[-1][-1].append(int(stringa.replace(',','')))
                conta += 1
            if conta == 5:
                conta = 0
        k += 1
    i = 0
    for elemento in lista2:
        if elemento == [[]]:
            lista2.pop(i)
            i += 1
            continue
        for x in elemento:
            if x == []:
                lista2[i].pop(lista2[i].index(x))
        i += 1
    print(lista2)
    i = 0
    max_larg = -1
    lunghezza = -1
    for elemento in lista2:
        if elemento == []:
            continue
        dizionario[i] = []
        tot_larg = 0
        max_alt = -1 
        for x in elemento:
            if x == []:
               continue 
            tot_larg += int(x[0])
           # print(elemento,x)
            if int(x[1]) > max_alt:
                max_alt = int(x[1])
        dizionario[i].append(tot_larg)
        dizionario[i].append(max_alt)
        dizionario[i].append(len(elemento))
        lunghezza_fascia = tot_larg + ((len(elemento) + 1) * spaziatura)
        dizionario[i].append(lunghezza_fascia)
        if lunghezza_fascia > lunghezza:
            lunghezza = lunghezza_fascia           
        if tot_larg > max_larg:
            max_larg = tot_larg
        i += 1
    valori = list(dizionario.values())
    cont = 0
    for elemento in valori:
        cont += elemento[1]
    altezza = (len(dizionario) + 1) * spaziatura + cont
 
    img = []
    for _ in range(altezza):
        row = []
        for _ in range(lunghezza):
            row.append((0,0,0))
        img.append(row) 
    i = 0
    # fascia
    riga_attuale = spaziatura
    for elemento in lista2:
        if elemento == []:
            i += 1
            continue
        print(elemento)
        colonna_attuale = spaziatura
        giardino = 0 
        if len(elemento) != 1:
            giardino = (lunghezza - (dizionario[i][0] + (dizionario[i][2] + 1) * spaziatura)) / ((dizionario[i][2] - 2) * 2 + 2)
        else:
            colonna_attuale = (lunghezza // 2) - (dizionario[i][0] // 2)
        dizionario[i].append(giardino)
        dizionario[i].append([])
        # palazzo
        j = 0
        for palazzo in elemento:
            if palazzo == []:
                j += 1
                continue
            r,g,b = int(palazzo[2]), int(palazzo[3]), int(palazzo[4])
            x, y = colonna_attuale, riga_attuale + ((dizionario[i][1]-int(palazzo[1]))//2)
            #print(x,y)
            dizionario[i][5].append((x,y))
            colonna_attuale += (int(lista2[i][j][0]) + int((2 * giardino)) + spaziatura) 
            for pixel_y in range(y,y+int(palazzo[1])):
                for pixel_x in range(x,x+int(palazzo[0])):
                    img[pixel_y][pixel_x] = (r,g,b)
            j += 1
        riga_attuale += (dizionario[i][1] + spaziatura)
        i += 1 
    print(dizionario)
    images.save(img, file_png)
    return (lunghezza, altezza)

       

if __name__ == '__main__':
    # inserisci qui i tuoi test personali per debuggare
    pass

# spazio
# fascia
# spazio
# fascia
# spazio
