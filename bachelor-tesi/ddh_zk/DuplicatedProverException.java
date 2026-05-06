package ddh_zk;

/**
 * Exception class thrown if the prover already has an active communication with the verifier
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class DuplicatedProverException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 2621057536562286251L;

	/**
	 * Class constructor
	 * @param message		exception message 
	 */
	public DuplicatedProverException(String message) {
        super(message);
    }
	
}
