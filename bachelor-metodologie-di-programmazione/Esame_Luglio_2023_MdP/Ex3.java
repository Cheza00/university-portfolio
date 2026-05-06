package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// commentare, decidere se limitare con hashset, commento su soluzione con stream

/**
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex3 {

	/**
	 * metodo che puo' essere chiamato dall'esterno per poi chiamare quello effettivamente ricorsivo, privato
	 * @param lista  la lista da cui voglio filtrare e capovolgere gli elementi
	 * @param insiemeFiltro		gli elementi che voglio filtrare per non avere alla fine
	 * @returns 	l'ArrayList finale
	 */
	public static <T> List<T> invertiFiltraLista(List<T> lista, Set<T> insiemeFiltro) {				
		List<T> finale = new ArrayList<>();				// creo un ArrayList inizialmente vuoto 
		
		finale = invertiFiltraLista(lista, finale, insiemeFiltro,  lista.size()-1);	// chiamo il metodo ricorsivo, passandogli anche l'indice dell'elemento
																					// che voglio considerare, parto in questo caso dall'ultimo
		
		return finale;										// ritorno il nuovo ArrayList creato in precedenza, non modifico la lista in input
	}	
	
	/**
	 * metodo privato ricorsivo 
	 * @param lista 	la lista in input 
	 * @param finale 	la lista che restituiro' alla fine, in cui aggiungo gli elementi man mano
	 * @param insiemeFiltro   insieme contenente gli elementi da filtrare
	 * @param k			indice di lista che voglio considerare
	 * @returns 		l'ArrayList finale filtrato e capovolto rispetto a lista
	 */
	private static <T> List<T> invertiFiltraLista(List<T> lista, List<T> finale, Set<T> insiemeFiltro, int k) {
		if (k < 0) return finale;						// se ho raggiunto sforato la lista, sono nel caso base e devo ritornare
		T elemento = lista.get(k);						// mi salvo l'elemento alla posizione k della lista in una variabile d'appoggio
		if (!insiemeFiltro.contains(elemento))			// se l'elemento non e' uno di quelli che non voglio avere
			finale.add(elemento);						// aggiungo all'ArrayList finale l'elemento
		return invertiFiltraLista(lista, finale, insiemeFiltro, --k);	// chiamata ricorsiva, con l'indice per la posizione precedente
	}
	
	// sarebbe stato possibile anche filtrare con gli stream, ad esempio con filter(elemento -> !insiemeFiltro.contains(elemento)), ma l'avrei 
	// dovuto fare non in maniera ricorsiva, quindi ho preferito attenermi maggiormente alla traccia e fare entrambe le funzioni
	// richieste ricorsivamente e contemporaneamente.
    
}
