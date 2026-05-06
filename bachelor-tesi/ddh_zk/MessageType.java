package ddh_zk;

/**
 * Enum for the possible type of a message between prover and verifier
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public enum MessageType {
	
	MESSAGGIO_HELLO,
	MESSAGGIO_CHALLENGE,
	MESSAGGIO_PROOF,
	MESSAGGIO_TERMINATE,
	MESSAGGIO_ENDCOMMUNICATION
	
}
