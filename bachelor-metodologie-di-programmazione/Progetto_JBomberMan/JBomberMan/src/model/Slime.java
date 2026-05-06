package model;

/**
 * 
 * Class to model the behaviour of slime-monsters 
 * It extends the superclass Monster
 * 
 * @author Scarselli Ilaria, matricola 1918975
 */
public class Slime extends Monster {
	
	private final char axis;
	
	/**
	 * Class constructor
	 */
	public Slime(char axis) {
		super("Green Slime", 1, 2);
		this.axis = axis;
		if(axis == 'h') // horizontal
			direction = "down";
		else 	// if it's v (vertical) or any other char 
			direction = "right";
	}
	
	/**
	 * Method to set the specific movement pattern for slime-monsters.
	 * The Slimes will move on a single axis, switching the direction when they hit something 
	 */
	public void setAction() {
		if(changeDirection) {
			if(axis == 'h')
				direction = (direction.equals("down")) ? "up" : "down"; // I reverse the direction on the same axis
			else
				direction = (direction.equals("right")) ? "left" : "right";
		}
	}

}
