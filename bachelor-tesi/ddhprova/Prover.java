package ddhprova;

import java.math.BigInteger;
import java.security.SecureRandom;

class Prover {
    private final GroupElement u;
    private final GroupElement v;
    private final BigInteger x;
    private final SecureRandom random;

    public Prover(GroupElement u, GroupElement v) {
        this.u = u;
        this.v = v;
        this.random = new SecureRandom();
        this.x = generateX();
    }

    private BigInteger generateX() {
        return new BigInteger(u.getOrder().bitLength(), random);
    }

    public GroupElement prove(BigInteger challenge) {
        return u.pow(challenge).multiply(v.pow(x));
    }
}


