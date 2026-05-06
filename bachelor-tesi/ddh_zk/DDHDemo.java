package ddh_zk;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class containing the main to manage the interaction
 * between the provers and the verifier through a 
 * BlockingQueu for the messages and a common thread of
 * execution. 
 * This implementation is based on Chaum-Pedersen ZKP
 * applied to Decisional Diffie-Hellman assumption 
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class DDHDemo {
		
	private static final BigInteger P = GroupGenerator.generatePrime(512); //7, 101, 49499
    private static final BigInteger G = GroupGenerator.generateGenerator(P);	//3,  51, 2
	
	private static BlockingQueue<Message> messagesQueue;
	private static ArrayList<Prover> proversList; 
	
	/**
	 * Main method
	 * @param args
	 */
    public static void main(String[] args) {

    	proversList = new ArrayList<>();

        // Initializing Provers and Verifier
        Prover prover1 = new Prover();
        proversList.add(prover1);
        Prover prover2 = new Prover();
        proversList.add(prover2);
        Prover prover3 = new Prover();
        proversList.add(prover3);
        Verifier verifier = Verifier.getInstance();
    
        System.out.println("G = " + G);
        System.out.println("P = " + P);
        System.out.println("\n============");
       
        // Creating a queue for exchanging messages between Prover and Verifier
        messagesQueue = new LinkedBlockingQueue<>();

        Thread gestoreMessaggiThread = new Thread(() -> messageHandler(verifier, messagesQueue, proversList));
        gestoreMessaggiThread.start();
        
        prover1.startCommunication();
        prover1.startCommunication();
        prover2.startCommunication();
        prover1.startCommunication();
        //prover3.startCommunication();
        
        // Waiting for the end of the messages manager thread
        try {
            gestoreMessaggiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
    }
    
    /**
     * Method to manage the communication between provers and verifier 
     * @param verifier		the verifier
     * @param messagesQueue	message queue
     * @param proversList	the list containing all the prover objects that want to communicate
     */
    private static void messageHandler(Verifier verifier, BlockingQueue<Message> messagesQueue, ArrayList<Prover> proversList) {
        // Loop
        while (true) {
        	System.out.println("Up and Running!");
            try {
                // Receive a message from the queue
                Message message = messagesQueue.take();

                // Manage the message according to its type 
                switch (message.getType()) {
                	case MESSAGGIO_HELLO:
                		System.out.println("Prover " + message.getProverUniqueID() + " sends a message to the Verifier and waits for the challenge");
                		try {
                			verifier.receiveMessageHelo((ProverHelloMessage)message);
                		} catch (DuplicatedProverException e) {
                			System.out.println("Exception: " + e.getMessage());
                		}
                		break;
                    case MESSAGGIO_CHALLENGE:
                    	System.out.println("The Verifier sends the challenge");
                    	proversList.stream()
                        .filter(prover -> prover.getUniqueID() == message.getProverUniqueID())
                        .findFirst()
                        .ifPresent(prover -> prover.receiveChallenge((VerifierChallengeMessage)message));
                        break;
                    case MESSAGGIO_PROOF:
                    	System.out.println("Prover " + message.getProverUniqueID() + " sends the answer"); 
                    	verifier.receiveProof((ProverProofMessage) message);
                        break;
                    case MESSAGGIO_TERMINATE:
                        System.out.print("The Verifier has given the response: ");
                        VerifierTerminationMessage termMessage = (VerifierTerminationMessage)message;
                        if(termMessage.isVerified())
                        	System.out.println("Prover " + message.getProverUniqueID() + " has successfully demonstrated possession of the knowledge");
                        else
                        	System.out.println("Prover " + message.getProverUniqueID() + " did not successfully demonstrate possession of the knowledge");
                        if(messagesQueue.isEmpty())
                    		verifier.endCommunication();
                        break;
                    case MESSAGGIO_ENDCOMMUNICATION:
                    	System.out.println("The Verifier has concluded the communication");
                    	return;
                    default:
                        System.out.println("Invalid message for this protocol");
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Inserts a message from the prover into the queue
     * @param message		prover's message
     */
    public static void insertProverMessage(ProverMessage message) {
    	try {
			messagesQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Inserts a message from the verifier into the queue
     * @param message		verifier's message
     */
    public static void insertVerifierMessage(VerifierMessage message) {
    	try {
			messagesQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Getter for the value of p
     * @return		the BigInteger of the p value 
     */
    public static BigInteger getP() {
    	return P;
    }
    
    /**
     * Getter for the value of g
     * @return		the BigInteger of the g value 
     */
    public static BigInteger getG() {
    	return G;
    }

}



