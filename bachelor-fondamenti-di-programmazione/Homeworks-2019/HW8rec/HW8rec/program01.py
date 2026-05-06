# -*- coding: utf-8 -*-
"""
Questo problema è ispirato al gioco "Il corvo parlante" della Settimana Enigmistica,
in cui il corvo ci dice che un qualche oggetto manca e va trovato nella scena,
ma la frase che appare nel fumetto è spezzettata e rimescolata.
Il problema da risolvere è ricostruire il testo originale riattaccando tra loro
i pezzi di testo detti dal corvo.

Viene data una lista di stringhe che, opportunamente combinate, formano un testo.
Le stringhe sono costruite in modo che un suffisso di una sia compatibile col
prefisso di una o più altre, ovvero abbiano una sottostringa in comune nelle
estremità opposte, di lunghezza imprecisata.
Esempio: "cavallo" è compatibile sia con "allora" ('allo') che con "logica"
('lo' ma anche 'ca' scambiando l'ordine delle due parole) e combinate possono
produrre rispettivamente "cavallora", "cavallogica" oppure "logicavallo".
In caso ci fossero più sottostringhe comuni per la stessa coppia di parole,
si prenda la più lunga.
Esempio: "patata" è compatibile con "atalanta" sia per la sottostringa 'a'
che per 'ata', si consideri 'ata'.

Esite un'unica sequenza delle stringhe in modo che ciascuna coppia di parole
consecutive sia compatibile per costruire il testo.
Si deve costruire un programma che fa uso di almeno una funzione ricorsiva
per trovare l'unica sequenza e, quindi, il testo originario.

Nello specifico, si costruisca una funzione es(fparole,ftesto) che prende
come argomenti:
  - fparole: il percorso di un file di testo che contiene, su righe successive, le stringhe,
  - ftesto:  il percorso di un file in cui dovete scrivere il testo ricostruito
e che ritorna la lista degli indici delle stringhe in input nell'ordine
corretto per ottenere il testo originale (sconosciuto).

ES: se le stringhe nel file sono 'ottuso' 'iodato' 'coniglio'
l'unica sequenza corretta da scrivere nel file ftesto è
    conigliodatottuso
e la funzione deve tornare la lista di indici [2,1,0]

ATTENZIONE: per superare il test di ricorsione:
    * definite la funzione ricorsiva a livello esterno oppure come metodo di una classe
    * non racchiudete la chiamata ricorsiva in un try/except

Il timeout previsto per ciascun test è di 1 secondo.
Sono proibite tutte le librerie.

"""

def es(fparole, ftesto):
    pass
    # inserite qui il vostro codice










if __name__ == '__main__':
    pass
    # inserite qui i vostri test

