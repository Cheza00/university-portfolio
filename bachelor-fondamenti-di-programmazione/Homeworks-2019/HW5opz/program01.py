# -*- coding: utf-8 -*-
'''

    Una serie di posters rettangolari sono stati affissi ad un muro. 
    I loro lati sono orizzontali e verticali. Ogni poster puo' essere parzialmente o 
    totalmente coperto dagli altri. 
    Chiameremo perimetro  la lunghezza del contorno dell'unione di tutti i posters sul muro.
    Si guardi la figura posters.png in cui i posters sulla parete compaiono in bianco 
    coi bordi blu e la si confronti con la figura posters1.png in cui in rosso 
    vengono evidenziati i soli bordi che contribuiscono al perimetro.

   Vogliamo un programma che calcola il perimetro dei poster e produce una immagine simile
   a posters1.png. 
   Progettare dunque una funzione es1(ftesto, filepng) che prende  come parametri 
   - ftesto, l'indirizzo di un file di testo contenente le informazioni sulla posizione 
    dei poster sul muro,
   - filepng, nome del file immagine in formato PNG da produrre 
   e restituisce il perimetro dei poster come numero di pixel rossi.

   Il file di testo contiene tante righe quanti sono i poster, nell'ordine in cui sono stati
   affissi alla parete. 
   In ciascuna  riga ci sono le coordinate intere del vertice in basso a sinistra e del vertice 
   in alto a destra del poster. 
   I valori di queste coordinate sono dati come coppie ordinate 
   della coordinata x seguita dalla coordinata y.
   Si veda ad esempio il file rettangoli_1.txt contenente le specifiche per i 7 posters in posters.png.
   
   L'immagine da salvare nel filepng deve avere lo sfondo nero, 
   altezza h +10 e larghezza w+10 dove h e' la coordinata x massima del muro su  cui  
   compaiono  poster e   w la coordinata y massima del muro su cui compaiono posters.
   I bordi visibili dei poster
   sono colorati di rosso o di verde a seconda se appartengono al perimetro, o meno.
   Nota che un pixel si trova sul perimetro  (e quindi è rosso) se nel suo 
   vicinato (gli 8 pixel attorno) si trova almeno un pixel esterno a tutti i poster.

   Per caricare e salvare i file PNG si possono usare le funzioni load e save presenti
   nel modulo  immagini.

   es1('rettangoli_1.txt', 'test_1.png') deve costruire un file PNG uguale a posters1.png
   e restituire il valore 1080. 
   
    NOTA: il timeout previsto per questo esercizio è di 1.5 secondi per ciascun test

    ATTENZIONE: quando caricate il file assicuratevi che sia nella codifica UTF8 
    (ad esempio editatelo dentro Spyder)
'''

import immagini

def es1(ftesto, filepng ):
    # inserisci qui il tuo codice
    pass





if __name__ == '__main__':
    pass
    # inserisci qui i tuoi test
