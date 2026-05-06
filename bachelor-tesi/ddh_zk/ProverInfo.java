package ddh_zk;

import java.math.BigInteger;

/**
 * Class to encapsulate prover information for easy storage 
 * and access in the verifier's map.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class ProverInfo {
    private final BigInteger proverPublicKey;
    private final BigInteger challenge;
    private final BigInteger sharedSecret;

    /**
     * Class constructor 
     * @param publicKey			prover's public key
     * @param challenge			challenge assigned to the prover
     * @param sharedSecret		shared secret of the prover
     */
    public ProverInfo(BigInteger publicKey, BigInteger challenge, BigInteger sharedSecret) {
        this.proverPublicKey = publicKey;
        this.challenge = challenge;
        this.sharedSecret = sharedSecret;
    }

    /**
     * Getter for the public key of the prover
     * @return		prover's public key
     */
    public BigInteger getProverPublicKey() {
        return proverPublicKey;
    }

    /**
     * Getter for the challenge assigned to the prover
     * @return		the challenge 
     */
    public BigInteger getChallenge() {
        return challenge;
    }

    /**
     * Getter for the shared secret
     * @return		the shared secret
     */
	public BigInteger getSharedSecret() {
		return sharedSecret;
	}
}
