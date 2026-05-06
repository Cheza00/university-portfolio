package ddh_zk;

import java.util.UUID;

/**
 * Abstract subclass of the Message class. 
 * Outlines the basic aspects of verifier's messages to 
 * separate them from the prover ones.
 * It implements the ZKProverInput interface 
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class VerifierMessage extends Message {

	/**
	 * Class constructor for the case of the EndCommMessage. 
	 * Passes the type to the superclass constructor
	 * @param type		message type
	 * @see Message
	 */
	public VerifierMessage(MessageType type) {
		super(type);
	}

	/**
	 * Class constructor for all the verifier message types, except for the EndCommMessage
	 * @param type				message type
	 * @param proverUniqueID	UUID of the prover to whom the message is addressed
	 * @see Message
	 */
	public VerifierMessage(MessageType type, UUID proverUniqueID) {
		super(type, proverUniqueID);
	}
	
	
	/**
	 * Getter for the UUID of the prover
	 */
	public UUID getProverUniqueID() {
		return super.getProverUniqueID();
	}
}
