package ddh_zk;

import java.util.UUID;

/**
 * Abstract subclass of the Message class. 
 * Outlines the basic aspects of prover's messages to 
 * separate them from the verifier ones.
 * It implements the ZKCommonInput interface 
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class ProverMessage extends Message {

	/**
	 * Class constructor. Passes the type to the superclass constructor
	 * @param type		message's type
	 * @param UUID 		prover's unique identifier
	 * @see Message
	 */
	public ProverMessage(MessageType type, UUID proverUniqueID) {
		super(type, proverUniqueID);
	}

	/**
	 * Getter for the unique identifier 
	 */
	public UUID getProverUniqueID() {
		return super.getProverUniqueID();
	}
}
