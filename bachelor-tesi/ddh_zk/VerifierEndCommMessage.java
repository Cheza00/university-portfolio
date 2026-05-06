package ddh_zk;

/**
 * Class for the end-communication message, to terminate the communication
 * ending the thread.
 * Concrete subclass of the VerifierMessage class.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class VerifierEndCommMessage extends VerifierMessage {
	
	/**
	 * Class constructor. It just needs the type,
	 * without the prover UUID 
	 * @param type		message type 
	 */
	public VerifierEndCommMessage(MessageType type) {
		super(type);
	}
}
