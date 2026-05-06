package ex2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe per InfoStud che gestisce l'insieme degli studenti e dei corsi di studi
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex2_InfoStud {
	// utilizza il singleton pattern
	private static Ex2_InfoStud instance;
	private Set<Ex2_Studente> insiemeStudenti;
	private Set<Ex2_CorsoDegliStudi> insiemeCorsiStudi;
	
	/**
	 * punto di accesso pubblico per creare un'istanza, e' l'unico metodo
	 * che puo' chiamare il costruttore privato, dopo aver verificato che non 
	 * esistono altre istanze, altrimenti ritorna quella esistente
	 * @returns ritorna l'istanza appena creata o l'unica gia' presente
	 */
	public static Ex2_InfoStud getInstance() {
		if (instance == null) instance = new Ex2_InfoStud();
		return instance;
	}
	
	/**
	 * costruttore privato che non puo' essere chiamato da nessuno se non dai metodi statici 
	 * della classe di appartenenza, in questo caso viene chiamato da getInstance()
	 */
	private Ex2_InfoStud() {
		insiemeStudenti = new HashSet<>(); // uso l'hashSet perche' non ho bisogno di uno specifico ordinamento o di mantenere ripetizioni
		insiemeCorsiStudi = new HashSet<>();
	}

	// non ho bisogno di implementare degli iterator, in quanto faccio uso di Set, che 
	// implementa gia' l'interfaccia Iterable e definisce gia' iterator. Mi bastano quindi 
	// i getter per restituire gli insiemi e permettere a chi le ha richiesti di iterarci 
	// come necessario con ad esempio dei cicli
	
	/**
	 * ritorna l'insieme di tutti gli studenti 
	 * @returns insieme degli studenti 
	 */
	public Set<Ex2_Studente> getInsiemeStudenti() {
		return insiemeStudenti;
	}

	/**
	 * ritorna gli insiemi di tutti i corsi di studi 
	 * @returns insieme dei corsi degli studi 
	 */
	public Set<Ex2_CorsoDegliStudi> getInsiemeCorsiStudi() {
		return insiemeCorsiStudi;
	}
	
	/**
	 * permette di aggiungere un'istanza di Ex2_Studente all'insieme degli studenti gestito 
	 * e mantenuto da questa classe
	 * @param studente  l'istanza di studente che si vuole aggiungere all'insieme
	 */
	public void addStudente(Ex2_Studente studente) {
		insiemeStudenti.add(studente);
	}
	
	/**
	 * permette di togliere uno studente dall'insieme degli studenti 
	 * @param studente   elemento da rimuovere
	 */
	public void removeStudente(Ex2_Studente studente) {
		insiemeStudenti.remove(studente);
	}
	
	/**
	 * metodo per aggiungere un nuovo corso degli studi 
	 * @param corsoStudi  il corso di studi che si vuole aggiungere
	 */
	public void addCorsoStudi(Ex2_CorsoDegliStudi corsoStudi) {
		insiemeCorsiStudi.add(corsoStudi);
	}
	
	/**
	 * metodo che permette la rimozione di un corso di studi dall'insieme con i corsi di studi 
	 * @param corsoStudi  il corso di studi che voglio rimuovere 
	 */
	public void removeCorsoStudi(Ex2_CorsoDegliStudi corsoStudi) {
		insiemeCorsiStudi.remove(corsoStudi);
	}
	
	/**
	 * metodo realizzato con gli stream per fornire una mappa che ha per chiave il voto acquisito e
	 * come valori un insieme degli studenti che hanno acquisito quel voto in un determinato
	 * insegnamento passato come argomento
	 * @param insegnamento 	
	 * @returns la mappa costruita
	 */
	public Map<Integer, Set<Ex2_Studente>> getMappaVotiStudenti(Ex2_Insegnamento insegnamento) {
		return insiemeStudenti.stream()
							.filter(studente -> studente.getEsami().containsKey(insegnamento.hashCode())) // filtra gli studenti 
							.collect(Collectors.groupingBy(studente -> studente.getEsami().get(insegnamento.hashCode()), Collectors.toSet())); 
		// groupingBy raggruppa gli elementi in una mappa. Gli elementi vengono raggruppati in un Set, come specificato nel secondo parametro che passo al metodo
		// (altrimenti se non lo avessi specificato sarebbero stati messi in una lista)
	}
	
	/**
	 * metodo realizzato con gli stream che fornisce una mappa tra gli studenti e la media
	 * dei voti acquisiti
	 * @returns la mappa costruita
	 */
	public Map<Ex2_Studente, Double> getMediaVoti() {
		return insiemeStudenti.stream() // Stream<Studente>
				.collect(Collectors.toMap(Function.identity(), studente -> studente.getEsami().values().stream().collect(Collectors.averagingInt(voto -> voto))));
		// values() prende i valori nella mappa esami restituita da getEsami() sottoforma di Collection, che poi rendo uno stream di Integer
		// (i voti sono memorizzati nella mappa come Integer, non posso avere un voto registrato come ad esempio 28.5).
		// Infine, avaragingInt è un metodo che restituisce la media dei valori, prendendo in input una funzione a valori interi applicata agli elementi in input
	}
	
}
