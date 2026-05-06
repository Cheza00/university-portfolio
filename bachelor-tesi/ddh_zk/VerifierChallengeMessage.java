package ddh_zk;

import java.math.BigInteger;
import java.util.UUID;

import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKProverInput;

/**
 * Concrete subclass of the VerifierMessage class.
 * It construct a message to send the prover a 
 * verifier-chosen challenge.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class VerifierChallengeMessage extends VerifierMessage implements ZKProverInput {
	
	private final BigInteger challenge;
	private final BigInteger publicKey;

	/**
	 * Class constructor 
	 * @param type			the type of the message
	 * @param challenge		the challenge to send to the prover
	 * @param publicKey		the publicKey of the verifier 
	 */
	public VerifierChallengeMessage(MessageType type, BigInteger challenge, BigInteger publicKey, UUID proverUniqueID) {
		super(type, proverUniqueID);
		this.challenge = challenge;
		this.publicKey = publicKey;
	}

	/**
	 * Getter for the challenge
	 * @return		the BigInteger representing the challenge
	 */
	public BigInteger getChallenge() {
		return challenge;
	}

	/**
	 * Gets the public key of the verifier 
	 * @return		the BigInteger representing the publicKey
	 */
	public BigInteger getPublicKey() {
		return publicKey;
	}

	/**
	 * Getter for the prover UUID associated with the message 
	 */
	public UUID getProverUniqueID() {
		return super.getProverUniqueID();
	}
}
