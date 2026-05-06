package ddh_zk;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKProver;
import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKProverInput;

/**
 * Class to simulate the behavior of a Prover.
 * It implements SCAPI ZKProver interface.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Prover implements ZKProver {
	
	private UUID uniqueID;
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger challenge;
	private BigInteger verifierPublicKey;
	private BigInteger sharedSecret;  // Not strictly necessary for this demo, but may be useful in future applications
	private static final BigInteger G = DDHDemo.getG();
	private static final BigInteger P = DDHDemo.getP();
	
	/**
	 * Class constructor
	 */
	public Prover() {
		uniqueID = UUID.randomUUID();
		generateKeys();	
	}
	
	/**
	 * Generates a pair of private and public keys 
	 */
	private void generateKeys() {
		BigInteger minKey = BigInteger.valueOf(2);
	    BigInteger maxKey = P.subtract(BigInteger.valueOf(2));
	    privateKey =  new BigInteger(maxKey.bitLength(), new SecureRandom()).mod(maxKey.subtract(minKey)).add(minKey);
		
		publicKey = G.modPow(privateKey, P);
	}

	/**
	 * Method to start the communication with the verifier adding an HelloMessage to
	 * the MessageQueue managed by the common thread
	 */
	public void startCommunication() {
		DDHDemo.insertProverMessage(new ProverHelloMessage(MessageType.MESSAGGIO_HELLO, uniqueID, publicKey));
	}
	
	/**
	 * Method to receive the message containing the challenge from 
	 * the verifier and to call the prove method to compute the proof 
	 * @param messaggio		verifier's message containing the proof 
	 */
	public void receiveChallenge(VerifierChallengeMessage messaggio) {
		verifierPublicKey = messaggio.getPublicKey();
		sharedSecret = getSharedSecret();
		prove((ZKProverInput)messaggio);
	}
	
	/**
	 * Method from the ZKProver interface, to compute a proof for the challenge
	 * given by the verifier.
	 */
	@Override
	public void prove(ZKProverInput arg0) {
		BigInteger phi = P.subtract(BigInteger.ONE);
		VerifierChallengeMessage message = (VerifierChallengeMessage) arg0;
		challenge = message.getChallenge();
		
		BigInteger randomR = generateRandomNumber(P);
		BigInteger y1 = G.modPow(randomR, P);
		BigInteger y2 = verifierPublicKey.modPow(randomR, P);
		BigInteger z = randomR.add(privateKey.multiply(challenge)).mod(phi);
		
		DDHDemo.insertProverMessage(new ProverProofMessage(MessageType.MESSAGGIO_PROOF, challenge, y1, y2, z, publicKey, uniqueID));    

	}	
	
	/**
	 * Method to calculate the secret shared between prover and verifier
	 * @return		the BigInteger representing the shared secret
	 */
	private BigInteger getSharedSecret() {
		return verifierPublicKey.modPow(privateKey, P);
	}
	
	/**
	 * Method to generate a random number between 0 and p
	 * @param max 		the maximum number wanted, p
	 * @return			a random BigInteger
	 */
	private static BigInteger generateRandomNumber(BigInteger max) {
        SecureRandom random = new SecureRandom();
        BigInteger result;
        do {
            result = new BigInteger(max.bitLength(), random);
        } while (result.compareTo(BigInteger.ZERO) < 0 || result.compareTo(max) >= 0);
        return result;
    }
	
	/**
	 * Getter for the publicKey of the prover
	 * @return		the publicKey
	 */
	public BigInteger getPublicKey() {
        return publicKey;
    }
	
	/**
	 * Getter for the UUID associated with the prover
	 * @return		the UUID
	 */
	public UUID getUniqueID() {
		return uniqueID;
	}

}
