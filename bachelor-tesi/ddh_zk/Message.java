package ddh_zk;

import java.util.UUID;

import edu.biu.scapi.interactiveMidProtocols.zeroKnowledge.ZKCommonInput;

/**
 * Abstract superclass of the message hierarchy used by provers and verifiers to communicate
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class Message implements ZKCommonInput {
	 
	private MessageType type;
	private UUID proverUniqueID;
	
	/**
	 * Class constructor
	 * @param type		one of the possibilities listed in 
	 * 					MessageType enum
	 * @see MessageType 
	 */
	public Message(MessageType type, UUID proverUniqueID) {
		this.type = type;
		this.proverUniqueID = proverUniqueID;
	}
	
	/**
	 * Class constructor used by the EndCommMessage, that doesn't 
	 * need a UUID
	 * @param type		type of the message
	 */
	public Message(MessageType type) {
		this.type = type;
	}
	
	/**
	 * Getter for the type of the message 
	 * @return		the type of the message
	 */
	public MessageType getType() {
		return type;
	}
	
	/**
	 * Getter for the UUID associated with the message
	 * @return
	 */
	public UUID getProverUniqueID() {
		return proverUniqueID;
	}

}
