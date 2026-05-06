package ddh_zk;

import java.math.BigInteger;
import java.util.UUID;

import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKCommonInput;

/**
 * Concrete subclass of the ProverMessage class.
 * It construct a message to send the verifier the 
 * proof for its challenge.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class ProverProofMessage extends ProverMessage implements ZKCommonInput {

	private final BigInteger challenge;
	private final BigInteger y1;
	private final BigInteger y2;
	private final BigInteger z;
	private final BigInteger proverPublic;
	
	/**
	 * Class constructor 
	 * @param type				type of the message
	 * @param challenge			the challenge to which the prover is responding 
	 * @param y1				the first piece of the proof 
	 * @param y2				the second piece of the proof
	 * @param z					the third piece of the proof
	 * @param proverPublic		prover's public key
	 * @param proverUniqueID	prover's UUID
	 */
	public ProverProofMessage(MessageType type, BigInteger challenge, BigInteger y1, BigInteger y2, BigInteger z, BigInteger proverPublic, UUID proverUniqueID) {
		super(type, proverUniqueID);
		this.challenge = challenge;
		this.y1 = y1;
		this.y2 = y2;
		this.z = z;
		this.proverPublic = proverPublic;
	}

	/**
	 * Getter for the challenge to which the proof refers
	 * @return		the challenge
	 */
	public BigInteger getChallenge() {
		return challenge;
	}

	/**
	 * Getter for y1 for the challenge 
	 * @return		the y1 part of the proof for the challenge
	 */
	public BigInteger getY1() {
		return y1;
	}
	
	/**
	 * Getter for y2 for the challenge 
	 * @return		the y2 part of the proof for the challenge
	 */
	public BigInteger getY2() {
		return y2;
	}
	
	/**
	 * Getter for z for the challenge 
	 * @return		the z part of the proof for the challenge
	 */
	public BigInteger getZ() {
		return z;
	}

	/**
	 * Getter for the publicKey of the prover
	 * @return		the public key of the prover 
	 */
	public BigInteger getProverPublic() {
		return proverPublic;
	}

	/**
	 * Getter for the UUID
	 */
	public UUID getProverUniqueID() {
		return super.getProverUniqueID();
	}
}
