package ddhprova;

import java.math.BigInteger;

public class GroupElement {
    private final BigInteger value;
    private final BigInteger order;  // Ordine del gruppo

    public GroupElement(BigInteger value, BigInteger order) {
        this.value = value;
        this.order = order;
    }

    public GroupElement pow(BigInteger exponent) {
        // Calcola g^exponent % order (o l'operazione equivalente nel tuo gruppo)
        return new GroupElement(value.modPow(exponent, order), order);
    }

    public GroupElement multiply(GroupElement other) {
        // Calcola g * other % order (o l'operazione equivalente nel tuo gruppo)
        return new GroupElement(value.multiply(other.value).mod(order), order);
    }

    public boolean equals(GroupElement other) {
        return this.value.equals(other.value) && this.order.equals(other.order);
    }

    public BigInteger getOrder() {
        return order;
    }

    // Altre operazioni necessarie per il tuo specifico gruppo crittografico
}

