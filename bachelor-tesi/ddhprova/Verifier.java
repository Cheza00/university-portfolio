package ddhprova;

import java.math.BigInteger;
import java.security.SecureRandom;

class Verifier {
    private final GroupElement g;
    private final GroupElement h;
    private final GroupElement w;
    private final SecureRandom random;

    public Verifier(GroupElement g, GroupElement h) {
        this.g = g;
        this.h = h;
        this.random = new SecureRandom();
        this.w = generateW();
    }

    private GroupElement generateW() {
        BigInteger k1 = new BigInteger(g.getOrder().bitLength(), random);
        BigInteger k2 = new BigInteger(h.getOrder().bitLength(), random);
        return g.pow(k1).multiply(h.pow(k2));
    }

    public boolean verify(GroupElement u, GroupElement v, BigInteger challenge) {
        GroupElement leftSide = u.pow(challenge).multiply(v);
        GroupElement rightSide = w.pow(challenge);
        return leftSide.equals(rightSide);
    }
}
