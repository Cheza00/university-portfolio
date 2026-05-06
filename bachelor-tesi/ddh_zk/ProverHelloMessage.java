package ddh_zk;

import java.math.BigInteger;
import java.util.UUID;

/**
 * Subclass of the ProverMessage class.
 * It's the specific class to manage hello messages from the Prover
 * to start a communication with the verifier
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class ProverHelloMessage extends ProverMessage {

	/**
	 *  Prover id, useful because the verifier may possibly communicate
	 *  with more provers at the same time
	 */
	private final BigInteger proverPublicKey;
	
	/**
	 * Class constructor
	 * @param type		message type to pass to the constructor of the superclass
	 * @param proverUniqueID		prover's id number
	 * @param proverPublicKey		prover's public key
	 * @see ProverMessage
	 */
	public ProverHelloMessage(MessageType type, UUID proverUniqueID, BigInteger proverPublicKey) {
		super(type, proverUniqueID);
		this.proverPublicKey = proverPublicKey;
	}

	/**
	 * Getter for prover's public key
	 * @return		prover's public key
	 */
	public BigInteger getProverPublicKey() {
		return proverPublicKey;
	}

	/**
	 * Getter for prover's UUID
	 */
	public UUID getProverUniqueID() {
		return super.getProverUniqueID();
	}
}
