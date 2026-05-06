package ex2;

/**
 * Classe per i singoli Insegnamenti di un corso di studi
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Ex2_Insegnamento {
	private String nomeCorso;
	private String nomeDocente, cognomeDocente;
	private Ex2_AnnoCorso annoCorso;
	private Ex2_Semestre semestre;
	
	/**
	 * Costruttore degli oggetti della classe Ex2_Insegnamento
	 * @param nomeCorso  nome del corso
	 * @param nomeDocente  nome del docente del corso
	 * @param cognomeDocente  cognome del docente del corso
	 * @param annoCorso  anno in cui si tiene il corso
	 * @param semestre  semestre in cui si tiene il corso
	 */
	public Ex2_Insegnamento(String nomeCorso, String nomeDocente, String cognomeDocente, Ex2_AnnoCorso annoCorso, Ex2_Semestre semestre) {
		this.nomeCorso = nomeCorso;
		this.setNomeDocente(nomeDocente);
		this.setCognomeDocente(cognomeDocente);
		this.setAnnoCorso(annoCorso);
		this.setSemestre(semestre);
	}

	/**
	 * getter del nome del corso
	 * @returns nome del corso
	 */
	public String getNomeCorso() {
		return nomeCorso;
	}
	
	// da qui metto anche i setter per i campi rimanenti in quanto potrei ad un certo punto 
	// prevedere la possibilita' che il professore del corso o il momento in cui e' tenuto cambino
	// e potrei voler modificare le informazioni con dei setter

	/**
	 * getter del nome del docente
	 * @returns nome del docente che tiene il corso
	 */
	public String getNomeDocente() {
		return nomeDocente;
	}

	/**
	 * setter del nome del docente
	 * @param nomeDocente  nuovo nome del docente che terra' il corso
	 */
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}

	/**
	 * getter del cognome del docente
	 * @returns cognome del docente
	 */
	public String getCognomeDocente() {
		return cognomeDocente;
	}

	/**
	 * setter del cognome del nuovo docente che terra' il corso
	 * @param cognomeDocente  cognome del nuovo docente
	 */
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}

	/**
	 * getter per l'anno in cui si tiene il corso
	 * @returns anno in cui si tiene il corso
	 */
	public Ex2_AnnoCorso getAnnoCorso() {
		return annoCorso;
	}

	/**
	 * setter per l'anno in cui si tiene il corso se dovesse cambiare
	 * @param annoCorso nuovo anno in cui si tiene il corso
	 */
	public void setAnnoCorso(Ex2_AnnoCorso annoCorso) {
		this.annoCorso = annoCorso;
	}

	/**
	 * getter per il semestre in cui si tiene il corso 
	 * @returns semestre in cui si tiene il corso 
	 */
	public Ex2_Semestre getSemestre() {
		return semestre;
	}

	/**
	 * setter per modificare il semestre in cui si tiene il corso 
	 * @param semestre nuovo semestre in cui si tiene
	 */
	public void setSemestre(Ex2_Semestre semestre) {
		this.semestre = semestre;
	}

	/**
	 * Faccio l'override del metodo per calcolare l'hashCode, dato che andro' a usarlo
	 * in una HashMap
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCorso == null) ? 0 : nomeCorso.hashCode());
		result = prime * result + ((nomeDocente == null) ? 0 : nomeDocente.hashCode());
		result = prime * result + ((cognomeDocente == null) ? 0 : cognomeDocente.hashCode());
		// il metodo ordinal() mi restituisce la posizione della costante enumerativa all'interno
		// della dichiarazione della sua enum, ma partendo da zero. Aggiungendo 1 ottengo l'ordinale
		// corretto (PRIMO = 1, SECONDO = 2, ...) 
		result = prime * result + ((annoCorso == null) ? 0 : annoCorso.ordinal() + 1); 
		result = prime * result + ((semestre == null) ? 0 : semestre.ordinal() + 1);
		return result;
	}

	// dopo aver modificato hashCode() devo modificare anche equals():
	// vogliamo che se due oggetti sono equals, devono avere anche lo stesso hashcode, ma se due oggetti hanno lo stesso hashcode non e' detto che siano equals

	/**
	 * Eseguo l'Override anche del metodo equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Ex2_Insegnamento other = (Ex2_Insegnamento) obj;	
		// con gli if gestisco anche il caso in cui i riferimenti siano null
		if (nomeCorso == null) {
			if (other.nomeCorso != null)
				return false;
		} else if (!nomeCorso.equals(other.nomeCorso))
			return false;
		if (nomeDocente == null) {
			if (other.nomeDocente != null)
				return false;
		} else if (!nomeDocente.equals(other.nomeDocente))
			return false;
		if (cognomeDocente == null) {
			if (other.cognomeDocente != null)
				return false;
		} else if (!cognomeDocente.equals(other.cognomeDocente))
			return false;
		if (annoCorso != other.annoCorso) // posso usare != perche' essendo enum confronto i riferimenti
			return false;
		if (semestre != other.semestre)
			return false;
		return true; // se tutti i campi sono equals, restituisco true
	}




	

}
