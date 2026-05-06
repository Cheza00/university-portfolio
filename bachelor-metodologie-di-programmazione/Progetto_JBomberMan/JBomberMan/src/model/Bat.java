package model;

import java.util.Random;

/**
 * Class to model the behaviour of bat-monsters 
 * It extends the superclass Monster
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Bat extends Monster {

	private int actionLockCounter;
	
	/**
	 * Class constructor
	 */
	public Bat() {
		super("Bat", 1, 3);
		direction = "down";
	}
	
	/**
	 * Method to set the specific movement pattern for bat-monsters
	 */
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 15) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick up a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}

}
