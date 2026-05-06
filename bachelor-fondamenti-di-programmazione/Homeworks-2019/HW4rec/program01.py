'''
    All'interno di un file di testo, sono elencate una serie di parole uniche di varie lunghezze.
    Tutte le parole sono anagrammi di alcune parole, che chiameremo generatori, delle quali,
    però, ne ignoriamo il numero.
    Nel generare gli anagrammi è possibile che siano stati fatti degli errori, in particolare
    può essere stata aggiunta una lettera.
    Quindi per ogni generatore le parole che gli corrispondono hanno:
    - le stesse lettere
    - oppure una lettera in più

    Progettare la funzione es(ftesto_in, ftesto_out, k) che prende come parametri:
    - ftesto_in:    il nome di un file come quello su descritto
    - ftesto_out:   il nome di un file in cui dovete scrivere le parole
    - k:            il numero di parole da scrivere su ciascuna riga nell'output
    Le parole da scrivere nel file ftesto_out sono quelle appartenenti al generatore
    che ha il maggior numero di anagrammi nel file di testo, ordinate lessicograficamente.
    Il file ftesto_out deve contenere le parole trovate, separate da spazio,
    k parole per linea (tranne l'ultima che può avere meno di k parole)

    NOTA: una parola può corrispondere a più di un generatore.
    NOTA: potete assumere che il generatore del massimo numero di parole generate sia sempre unico.

    Ad esempio, se il file esempio.txt contiene le seguenti parole

    trota
    tratto
    arto
    parto
    taro
    trotta
    rotta
    orta
    porta

    la funzione es('esempio.txt', 'test_esempio.txt', 2) deve scrivere nel file test_esempio.txt le due righe

    arto orta
    parto porta
    rotta taro
    trota

    che sono tutte generate dalla parola 'arto' (o da un suo anagramma)

    Il timeout per ciascun test è di 1 secondo sulla VM.
    E' vietato usare altre librerie.

'''

def es(ftesto_in, ftesto_out, k):
    # inserite qui il vostro codice
    pass

if __name__ == '__main__':
    pass
    # inserite qui i vostri test
