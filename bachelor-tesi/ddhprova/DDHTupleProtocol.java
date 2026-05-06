package ddhprova;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DDHTupleProtocol {
	
    public static void main(String[] args) {
        // Esempio di utilizzo
        // Si presuppone che tu abbia una classe GroupElement funzionante.
        // Supponiamo che tu abbia già un'implementazione funzionante della classe GroupElement.
        // Inizializza g, h, u, v con valori appropriati.
        GroupElement g = new GroupElement(new BigInteger("5"), new BigInteger("27"));
        GroupElement h = new GroupElement(new BigInteger("7"), new BigInteger("27"));
        GroupElement u = new GroupElement(new BigInteger("5"), new BigInteger("27"));
        GroupElement v = new GroupElement(new BigInteger("7"), new BigInteger("27"));

        Verifier verifier = new Verifier(g, h);
        Prover prover = new Prover(u, v);

        // Verifier invia la sfida al Prover
        BigInteger challenge = new BigInteger(g.getOrder().bitLength(), new SecureRandom());

        // Prover risponde con la prova
        GroupElement proof = prover.prove(challenge);

        // Verifier verifica la prova
        boolean result = verifier.verify(u, v, challenge);

        System.out.println("Protocol result: " + result);
    }
}








