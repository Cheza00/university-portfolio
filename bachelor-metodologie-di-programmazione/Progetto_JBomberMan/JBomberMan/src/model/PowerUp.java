package model;

/**
 * Class used just to keep track in the model of the powerups on the map.
 * It extends the superclass SuperObject
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class PowerUp extends SuperObject {
	
	/**
	 * Class constructor 
	 * @param name		A String to represent the type of the powerup
	 */
	public PowerUp(String name, int x, int y) {	
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * Getter for the x-coordinate
	 * @return		an integer representing the x-coordinate in pixel
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for the y-coordinate
	 * @return		an integer representing the y-coordinate in pixel
	 */
	public int getY() {
		return y;
	}
		
}

