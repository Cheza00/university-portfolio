package ddh_zk;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKCommonInput;
import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKVerifier;

/**
 * Class to simulate the behavior of the Verifier.
 * It implements SCAPI ZKVerifier interface and 
 * the Singleton pattern (useful when wanting
 * a single verifier for possible multiple
 * provers)
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Verifier implements ZKVerifier {
	
	private BigInteger privateKey;
	private BigInteger publicKey;
	
	private static Verifier instance;
	private Map<UUID, ProverInfo> proverInfoMap;
	
	private static final BigInteger G = DDHDemo.getG();
	private static final BigInteger P = DDHDemo.getP();
	
	/**
	 * Method to return the instance of the verifier, if already 
	 * present, or to create and return it otherwise
	 * @return			the single instance of the verifier
	 */
	public static Verifier getInstance() {
		if(instance == null) 
			instance = new Verifier();
		return instance;
	}
	
	/**
	 * Private constructor of the class 
	 */
	private Verifier() {
		proverInfoMap = new HashMap<>();
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
	 * Upon receiving prover's HelloMessage, the method checks if there isn't 
	 * already an open communication with the same prover and sends it a challenge
	 * @param message		the HelloMessage from the prover
	 * @throws DuplicatedProverException 	if the prover already has an open communication
	 */
	public void receiveMessageHelo(ProverHelloMessage message) throws DuplicatedProverException {
		UUID proverUniqueID = message.getProverUniqueID();
		if (!proverInfoMap.containsKey(proverUniqueID)) {
			BigInteger challenge = generateChallenge();
			BigInteger proverPublicKey = message.getProverPublicKey();
			BigInteger sharedSecret = getSharedSecret(proverPublicKey);
			proverInfoMap.put(proverUniqueID, new ProverInfo(proverPublicKey, challenge, sharedSecret));
			DDHDemo.insertVerifierMessage(new VerifierChallengeMessage(MessageType.MESSAGGIO_CHALLENGE, challenge, publicKey, proverUniqueID));
		}
		else
			throw new DuplicatedProverException("Prover " + proverUniqueID + " cannot initiate a new communication without terminating the previous one.");
	}
	
	/**
	 * Private method to calculate the shared secret C used during the 
	 * verification process
	 * @param proverPublicKey		prover's public key taken from the message
	 * @return		the shared secret
	 */
	private BigInteger getSharedSecret(BigInteger proverPublicKey) {
		return proverPublicKey.modPow(privateKey, P);
	}
	
	/**
	 * Generates the challenge to send to the prover
	 * @return		the generated challenge
	 */
	private static BigInteger generateChallenge() {
		return new BigInteger(P.bitLength(), new SecureRandom()).mod(P.subtract(BigInteger.ONE)).add(BigInteger.ONE); 
	}
	
	/**
	 * Upon receiving the proof from the prover, the verifier verifies it 
	 * @param message		the message containing prover proof for the challenge
	 * @return				true, if the proof is correct, false otherwise
	 */
	public void receiveProof(ProverProofMessage message) {
		boolean verified = verify((ZKCommonInput)message);
		proverInfoMap.remove(message.getProverUniqueID());
		sendTerminationMessage(verified, message.getProverUniqueID());
	}

	/**
	 * Method from the ZKVerifier interface, to verify the proof sent by the prover
	 */
	@Override
	public boolean verify(ZKCommonInput arg0) {
			
		ProverProofMessage message = (ProverProofMessage) arg0;
		UUID proverUniqueID = message.getProverUniqueID();
		ProverInfo proverInfo = proverInfoMap.get(proverUniqueID);
		
		BigInteger y1 = message.getY1();
		BigInteger y2 = message.getY2();
		BigInteger z = message.getZ();
		
		boolean firstControl = G.modPow(z, P).equals(proverInfo.getProverPublicKey().modPow(proverInfo.getChallenge(), P).multiply(y1).mod(P));
		boolean secondControl = publicKey.modPow(z,P).equals(proverInfo.getSharedSecret().modPow(proverInfo.getChallenge(), P).multiply(y2).mod(P));
		
		return firstControl && secondControl;
	}
	
	/**
	 * Private method to send a termination message after the verification phase
	 * @param verified			the result of the verification process
	 * @param proverUniqueID	the prover's UUID 
	 */
	private void sendTerminationMessage(boolean verified, UUID proverUniqueID) {
		DDHDemo.insertVerifierMessage(new VerifierTerminationMessage(MessageType.MESSAGGIO_TERMINATE, verified, proverUniqueID));
	}
	
	/**
	 * Sends a message to terminate the thread
	 */
	public void endCommunication() {
		DDHDemo.insertVerifierMessage(new VerifierEndCommMessage(MessageType.MESSAGGIO_ENDCOMMUNICATION));
	}
	
}
