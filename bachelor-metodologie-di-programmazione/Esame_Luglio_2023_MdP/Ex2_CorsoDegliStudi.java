package ex2;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe per i corsi di studio
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex2_CorsoDegliStudi {
	private String nomeCorsoStudi;
	private Map<Ex2_Insegnamento, Integer> mappaInsegnamentoCfu;
	
	/**
	 * Costruttore della classe 
	 * @param nomeCorsoStudi il nome del corso di studio 
	 */
	public Ex2_CorsoDegliStudi(String nomeCorsoStudi) {
		this.nomeCorsoStudi = nomeCorsoStudi;
		mappaInsegnamentoCfu = new HashMap<>(); // posso creare un corso di studi e poi aggiungere man mano corsi con l'addInsegnamento
	}

	/**
	 * getter per il nome del corso di studi 
	 * @returns il nome del corso di studi
	 */
	public String getNomeCorsoStudi() {
		return nomeCorsoStudi;
	}

	/**
	 * getter per la mappa tra gli insegnamenti e i cfu
	 * @returns la mappa tra insegnamenti e cfu
	 */
	public Map<Ex2_Insegnamento, Integer> getMappaInsegnamentoCfu() {
		return mappaInsegnamentoCfu;
	}
	
	/**
	 * metodo che utilizza l'insegnamento come chiave, gli associa un numero di cfu
	 * e inserisce nella mappa
	 * @param insegnamento oggetto di tipo Ex2_Insegnamento
	 * @param cfu  numero dei cfu per quell'insegnamento
	 */
	public void addInsegnamento(Ex2_Insegnamento insegnamento, int cfu) { 
		// avrei potuto usare di nuovo anche qui l'identificativo (o il nome) dell'insegnamento, ma al contrario 
		// della mappa per ogni Studente qui non è specificato di essere interessati solo a quello, mi attengo meglio alla traccia
		mappaInsegnamentoCfu.put(insegnamento, cfu); // cfu diventa Integer sempre con autoboxing
	}
	
}
