package model;

import java.awt.Rectangle;

/**
 * Abstract class with the basic aspects to keep track of powerups in general.
 * It extends the class Entity
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class SuperObject extends Entity {

	protected String name;
	protected boolean collision; // Default value is false 
	protected int x;
	protected int y;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	
	/**
	 * Getter for the name of the object
	 * @return		a String with the name 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the collision of the object
	 * @return		a boolean that represent if the collision checking 
	 * 				for that object is activated or not
	 */
	public boolean getCollision() {
		return collision;
	}
	
	/**
	 * Sets the collision
	 * @param collision
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
}
