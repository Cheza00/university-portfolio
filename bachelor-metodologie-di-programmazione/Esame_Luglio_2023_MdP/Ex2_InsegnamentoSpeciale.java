package ex2;

/**
 * Classe per gli insegnamenti speciali che estende la classe per gli insegnamenti
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex2_InsegnamentoSpeciale extends Ex2_Insegnamento{
	private String nomeAttivita;
	private String descrizioneAttivita;
	
	/**
	 * costruttore degli oggetti della classe 
	 * @param nomeCorso  		nome del corso 
	 * @param nomeDocente		nome del docente che tiene il corso 
	 * @param cognomeDocente	cognome del docente che tiene il corso
	 * @param annoCorso			anno in cui si tiene il corso
	 * @param semestre			semestre durante il quale si tiene il coso
	 * @param nomeAttivita		nome dell'attivita' formativa avanzata
	 * @param descrizioneAttivita		descrizione dell'attivita' formativa avanzata
	 */
	public Ex2_InsegnamentoSpeciale(String nomeCorso, String nomeDocente, String cognomeDocente, Ex2_AnnoCorso annoCorso, Ex2_Semestre semestre, String nomeAttivita, String descrizioneAttivita) {
		super(nomeCorso, nomeDocente, cognomeDocente, annoCorso, semestre);
		this.nomeAttivita = nomeAttivita;
		this.descrizioneAttivita = descrizioneAttivita;
	}

	/**
	 * getter per il nome dell'attivita'
	 * @returns nome dell attivita'
	 */
	public String getNomeAttivita() {
		return nomeAttivita;
	}

	/**
	 * getter per la descrizione dell'attivita'
	 * @returns descrizione dell'attivita'
	 */
	public String getDescrizioneAttivita() {
		return descrizioneAttivita;
	}
	
	
	
	

}
