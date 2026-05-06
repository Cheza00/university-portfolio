package ex2;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe per gli Studenti
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex2_Studente { 
	private final int matricola; // e' final, una volta inizializzata nel costruttore, non puo' piu' cambiare
	private String nome;
	private String cognome;
	private int annoImmatricolazione;
	private String nomeCorsoStudi;
	private Map<Integer, Integer> esami;
	
	/**
	 * Costruttore della classe Ex2_Studente
	 * @param matricola  matricola dello studente
	 * @param nome  nome dello studente
	 * @param cognome  cognome dello studente
	 * @param annoImmatricolazione  anno di immatricolazione dello studente
	 * @param corsoStudi corso di studi intrapreso dallo studente, da cui poi tramite getter prelevo il nome
	 */
	public Ex2_Studente(int matricola, String nome, String cognome, int annoImmatricolazione, Ex2_CorsoDegliStudi corsoStudi) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.annoImmatricolazione = annoImmatricolazione;
		// avrei potuto passare direttamente solo la stringa, ma cosi' sono sicura di passare il nome corretto
		// per il corso di studi dello studente direttamente con il getter
		this.nomeCorsoStudi = corsoStudi.getNomeCorsoStudi(); 
		// quando creo uno studente per la prima volta col costruttore, non avra' ancora 
		// concluso alcun esame, se non in casi specifici in cui comunque potranno essere aggiunti
		// successivamente con il metodo adatto, pertanto inizializzo voti creando un HashMap vuoto
		esami = new HashMap<>(); 
	} 

	/**
	 * getter per la matricola dello studente 
	 * @returns matricola dello studente
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * getter per il nome dello studente
	 * @returns nome dello studente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * getter per il cognome dello studente
	 * @returns cognome dello studente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * getter per l'anno in cui si è immatricolato lo studente
	 * @returns anno di immatricolazione dello studente
	 */
	public int getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}

	/**
	 * getter per il nome del corso di studi frequentato dallo studente
	 * @returns nome del corso di studi
	 */
	public String getNomeCorsoStudi() {
		return nomeCorsoStudi;
	}
	
	/**
	 * getter per i voti ottenuti negli esami
	 * @returns la mappa degli identificativi degli insegnamenti e dei voti ottenuti
	 */
	public Map<Integer, Integer> getEsami() {
		return esami;
	}
	
	/**
	 * ottiene l'hashcode per l'insegnamento per usarlo come chiave, associa il valore 
	 * del voto alla chiave per rappresentare un esame e lo aggiunge alla mappa 
	 * @param insegnamento un oggetto di tipo Insegnamento 
	 * @param voto	il voto per l'esame di quell'insegnamento
	 */
	public void addEsami(Ex2_Insegnamento insegnamento, int voto) {
		int identificativo = insegnamento.hashCode();	// forse potevo usare direttamente un codice per il corso, ma l'hashcode e' piu' corretto per identificare univocamente oggetti
		esami.put(identificativo, voto); // non mi serve fare il casting esplicito, grazie al meccanismo di autoboxing,
							   // che consente il casting implicito da int a Integer
	}
}
