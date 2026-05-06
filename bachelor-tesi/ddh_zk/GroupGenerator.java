package ddh_zk;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Class to generate prime numbers and group generators
 * with a specific length expressed in bits
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class GroupGenerator {
	
	/**
	 * Method to generate a prime number 
	 * @param bitLength		the number of bits we want as length
	 * @return				a prime BigInteger
	 */
    public static BigInteger generatePrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, new SecureRandom());
    }

    /**
     * Method to generate a group generator 
     * @param p		group order 
     * @return		a generator for a cyclic group with order p
     */
    public static BigInteger generateGenerator(BigInteger p) {
    	int bitLength = p.bitLength();
        BigInteger two = BigInteger.valueOf(2);
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        
        BigInteger g;
        do {
            g = new BigInteger(bitLength, new SecureRandom());
        } while (g.compareTo(two) < 0 || g.compareTo(pMinusOne) >= 0 ||
                 !g.modPow(pMinusOne.divide(two), p).equals(BigInteger.ONE));

        return g;
    }
}
