# -*- coding: utf-8 -*-
'''
Un pixel artist di fama mondiale di nome Fred Zuppa ha recentemente
prodotto diversi capolavori sottoforma di immagini quadrate raster
codificate su pixels in scala di grigi. Le immagini che ha disegnato
possono prendere valori da 0 a 255 compresi. Sfortunatamente le famose
opere sono andate perdute in quanto il suo disco rigido (ahilui!) ha
smesso di funzionare e ovviamente il buon Fred e' disperato. I
programmi per recuperarle dal filesystem non funzionano purtroppo e
cosi' Fred si affida al suo amico informatico di fiducia, il quale gli
dice:

   "Fratello, in verita' ti dico, se ti ricordi la dimensione delle
   immagini e i valori dei pixel di cui erano formate e delle
   proprieta' particolari delle tue opere, allora possiamo provare a
   scrivere un generatore ricorsivo che le produca tutte in base ai
   tuoi input, cosi' facendo possiamo provare a recuperarle!"

Il mattino seguente Fred riesce a dare le informazioni necessarie
sottoforma di:
   1. `D` parametro intero che descrive la dimensione dell'immagine
       quadrata.
   2. `colors` una lista di interi che descrive i colori delle
      immagini di Fred.  I colori di Fred sono compresi fra 0, 255.
      colors puo' essere quindi [128, 0, 255] mentre NON puo' essere
      [-100, 999]
   3. Un testo `img_properties` che descrive le proprieta' delle sue
      immagini: Il testo puo' descrivere nessuna proprita' (stringa
      vuota) oppure puo' descrivere una proprieta' che riguarda i
      pattern che le immagini devono contenere.

       Ad esempio:

       Se `img_properties` e' vuota allora le immagini non devono soddisfare
       nessuna proprieta'. Viceversa se `img_properties` e' uguale a
       'pattern_{type}_' allora signifca che le immagini devono
       mostrare il pattern di tipo `type` specificato nella stringa.
       Il pattern puo' essere di un solo tipo.

       I tipi di pattern possibili sono i quattro seguenti:
          a) 'pattern_diff_': se presente indica che presa
          arbitrariamente nelle immagini di Fred una sottoimmagine
          di dimensione uguale a 2x2, questa sottoimmagine deve avere i
          pixel di colore tutti diversi.

                 valid        not valid
            |  96 | 255 |   |   0 | 255 |
            | 128 |   0 |   | 255 |  96 |


          b) 'pattern_cross_': se presente indica che presa
          arbitrariamente nelle immagini di Fred una sottoimmagine
          di dimensione uguale a 2x2, questa sottoimmagine deve
          avere i pixel sulla diagonale uguali fra loro e i pixel
          sulla antidiagonale uguale fra loro ma pixel delle due
          diagonali devono essere diverse.

               valid          not valid     not valid
            |  96 | 255 |   |  0 | 255 |   | 61 | 61 |
            | 255 |  96 |   | 96 |   0 |   | 61 | 61 |

          c) 'pattern_hrect_': se presente indica che presa
          arbitrariamente nelle immagini di Fred una sottoimmagine di
          dimensione 2x2, questa sottoimmagine deve avere i pixel
          sulle righe tutti uguali ma righe adiacenti di colore
          diverso.

                 valid       not valid        not valid
            |   0 |   0 |   | 255 | 255 |    | 43 | 43 |
            | 128 | 128 |   | 0   | 255 |    | 43 | 43 |

          d) 'pattern_vrect_': se presente indica che presa
          arbitrariamente nelle immagini di Fred una sottoimmagine di
          dimensione 2x2, questa sottoimmagine deve avere i pixel
          sulle colonne tutti uguali ma colonne adiacenti di colore
          diverso.

                valid         not  valid    not valid
             | 0 | 255 |     | 0  | 0  |    | 22 | 22 |
             | 0 | 255 |     | 0  | 255|    | 22 | 22 |

Implementare la funzione ricorsiva o che usa metodi ricorsivi:
  
      images = ex(colors, D, img_properties)

che prende in ingresso la lista di colori `colors`, la dimensione
delle immagini `D` e una stringa `img_properties` che ne descrive le
proprieta' e generi ricorsivamente tutte le immagini seguendo le
proprieta' suddette.  La funzione deve restituire l'elenco di tutte le
immagini come una lista di immagini.  Ciascuna immagine e' una tupla di
tuple dove ogni intensita' di grigio e' un intero.
L'ordine in cui si generano le immagini non conta.

     Esempio: immagine 2x2 di zeri (tutto nero) e':
        img = ( (0, 0), (0, 0), )


Il timeout per ciascun test è di 1 secondo.

***
E' fortemente consigliato di modellare il problema come un albero di
gioco, cercando di propagare le solo le "mosse" necessarie nella
ricorsione e quindi nella costruzione della soluzione in maniera
efficiente; oppure, in maniera alternativa, cercate di "potare" l'albero di
gioco il prima possibile.
***

Potete visualizzare tutte le immagini da generare invocando

     python test_01.py data/images_data_15.json

questo salva su disco tutte le immagini attese del test 15 e crea
un file HTML di nome `images_data_15.html` nella directory radice
del HW con cui e' possibile vedere le immagini aprendo il file html
con browser web.
'''


def ex(colors, D, img_properties):
    riga = [-1 for _ in range(D)]
    disposizioni = []
    if img_properties ==  "pattern_cross_":
        lista2 = [-1 for _ in range(D)]
        disposizioni = cross(colors, riga, lista2, disposizioni)
        immagini = costruisci_immagine_cross(disposizioni, D)
        return immagini
    elif img_properties == "pattern_vrect_":
        disposizioni = fun_pattern_rect(riga, disposizioni, 0, D, colors, colors)
        immagini = costruisci_immagine_vrect(D, disposizioni)
        return immagini
    elif img_properties == "pattern_hrect_":
        disposizioni = fun_pattern_rect(riga, disposizioni, 0, D, colors, colors)
        immagini = costruisci_immagine_hrect(D, disposizioni)
        return immagini
    elif img_properties == "pattern_diff_":
        # creo una matrice più grande in modo da non dover fare i controlli sugli indici per non finire out of range
        riga2 = [-1 for _ in range(D+2)]
        immagine = []
        immagini = []
        for _ in range(D+2):
            immagine.append(riga2[:])
        # parto da indici 1 1, invece che 0 0 per non sovrascrivere la cornice della matrice
        immagini = fun_pattern_diff(immagine, immagini, D, 1, 1, colors)
        immagini = trasforma_in_tuple(immagini)
        return immagini
    else: 
        disposizioni = fun_no_pattern(riga, disposizioni, 0, D, colors)
        immagini = []
        immagine = [-1 for _ in range(D)]
        immagini = genera_immagini_random(D, disposizioni, 0, immagini, immagine)
        return immagini


def cross(colors, lista, lista2, disposizioni):
    if len(colors) == 2:
        for i in range(len(lista)):
            if i % 2:
                lista[i] = colors[1]
                lista2[i] = colors[0]
            else:
                lista[i] = colors[0]
                lista2[i] = colors[1]
        disposizioni.append(lista[:])
        disposizioni.append(lista2[:])
        return disposizioni
    else:
        for colore in colors[1:]:
            for i in range(len(lista)):
                if i % 2:
                    lista[i] = colore
                    lista2[i] = colors[0]
                else:
                    lista[i] = colors[0]
                    lista2[i] = colore
            disposizioni.append(lista[:])
            disposizioni.append(lista2[:])
        cross(colors[1:], lista, lista2, disposizioni)
        return disposizioni
   
def costruisci_immagine_cross(disposizioni, D):
    img = [-1 for _ in range(D)]
    immagini = []
    for i in range(0,len(disposizioni)):
        if i % 2:
            immagini = (immagine_cross_ric(immagini, img, disposizioni[i], disposizioni[i-1], D, 0))
        else:
            immagini = (immagine_cross_ric(immagini, img, disposizioni[i], disposizioni[i+1], D, 0))
    return immagini
           
def immagine_cross_ric(immagini, img, riga1, riga2, D, i):       
    img[i] = tuple(riga1)    
    if i == D-1:
        immagini.append(tuple(img))
        return immagini
    else:
        immagine_cross_ric(immagini, img, riga2, riga1, D, i+1)
        return immagini
    
    
    
def fun_pattern_rect(riga, disposizioni, i, D, colors, colori):
    if i == D:
        disposizioni.append(riga[:])
        return disposizioni
    else:
        for colore in colori:
            riga[i] = colore
            fun_pattern_rect(riga, disposizioni, i+1, D, colors, colors[:colors.index(colore)]+colors[colors.index(colore)+1:])
        return disposizioni
                    
def costruisci_immagine_vrect(D, disposizioni):
    immagini = []
    for disposizione in disposizioni:
        immagine = []
        for i in range(D):
            immagine.append(tuple(disposizione))
        immagini.append(tuple(immagine))
    return immagini

def costruisci_immagine_hrect(D, disposizioni):
    immagini = []
    for disposizione in disposizioni:
        immagine = []
        for colore in disposizione:
            riga = [colore for _ in range(D)]
            immagine.append(tuple(riga))
        immagini.append(tuple(immagine))
    return immagini



def fun_no_pattern(riga, disposizioni, i, D, colors):
    if i == D:
        disposizioni.append(riga[:])
        return disposizioni
    else:
        for colore in colors:
            riga[i] = colore
            fun_no_pattern(riga, disposizioni, i+1, D, colors)
        return disposizioni 

def genera_immagini_random(D, disposizioni, i, immagini, immagine):
    if i == D:
        immagini.append(tuple(immagine))
        return immagini
    else:
        for disposizione in disposizioni:
            immagine[i] = tuple(disposizione)
            genera_immagini_random(D, disposizioni, i+1, immagini, immagine)
        return immagini 



def fun_pattern_diff(immagine, immagini, D, i, j, colors):
    # indice di riga
    if i == D+1:
        i = 1
        j += 1
    # indice di colonna
    if j == D+1:
        immagini.append(copia(immagine))
        return immagini     
    for colore in colors:
        # se ci sono pixel a sx, in alto a sx, in alto e in alto a dx, li controllo
        if immagine[j][i-1]==colore or immagine[j-1][i-1]==colore or immagine[j-1][i]==colore or immagine[j-1][i+1]==colore:
                continue
        immagine[j][i] = colore
        fun_pattern_diff(immagine, immagini, D, i+1, j, colors)
    return immagini

def copia(immagine):
    temp = []
    for x in range(1,len(immagine)-1):
        temp.append(immagine[x][1:-1])
    return temp

def trasforma_in_tuple(immagini):
    immagini2 = []
    for immagine in immagini:
        immagine2 = []
        for riga in immagine:
            immagine2.append(tuple(riga))
        immagini2.append(tuple(immagine2))
    return immagini2
        
if __name__ == '__main__':
    # inserisci qui i tuoi tests
    pass

#                  self.check_imports(allowed=['program01', '_io']), \
