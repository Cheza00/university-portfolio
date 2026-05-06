package ddh_zk;

import java.util.UUID;

/**
 * Class for the termination message, to end the communication with 
 * a single, specific prover, declaring acceptance or rejection of the demonstration.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class VerifierTerminationMessage extends VerifierMessage {

	private final boolean verified;
	
	/**
	 * Class constructor 
	 * @param type				message type 
	 * @param verified			boolean to state verifier's acceptance or rejection of the proof
	 * @param proverUniqueID	UUID of the prover to whom the message is addressed
	 * @see VerifierMessage
	 */
	public VerifierTerminationMessage(MessageType type, boolean verified, UUID proverUniqueID) {
		super(type, proverUniqueID);
		this.verified = verified;
	}

	/**
	 * Getter for the variable verified
	 * @return		true if the verifier accepted the proof, false otherwise
	 */
	public boolean isVerified() {
		return verified;
	}

	
}
