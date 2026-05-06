package model;

import java.awt.Rectangle;
import java.util.Observable;

/**
 * Class to model the general and basic aspects of an entity in this game, such as 
 * the position where to draw on the gamePanel or the collision
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class Entity extends Observable  {

	protected int x; 
	protected int y;
	 
	public Rectangle solidArea;
	protected int solidAreaDefaultX;
	protected int solidAreaDefaultY;
	protected boolean collisionOn = false;
	
	// I leave just the default constructor without explicitly writing it 
	
	/**
	 * Getter for the x-coordinate
	 * @return		an integer representing the x-coordinate in pixel
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Setter for the x-coordinate
	 * @param x		the x-coordinate, measured in pixels
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Getter for the y-coordinate
	 * @return		an integer representing the y-coordinate in pixel
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter for the y-coordinate
	 * @param y		the y-coordinate, measured in pixels
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for the solid area of the entity, useful in other classes to check the collisions
	 * @return		the Rectangle object of the solid area
	 */
	public Rectangle getSolidArea() {
		return solidArea;
	}

	/**
	 * Sets the solid area
	 * @param solidArea		the Rectangle object to set the solid area
	 */
	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	/**
	 * Getter for the default value of the x component (of the upper-left vertex) of the solid area,
	 * useful to have because the solid area can be modified during the collision-checking
	 * @return		the default value of the x component
	 */
	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}

	/**
	 * Sets the default value of the x component of the solid area
	 * @param solidAreaDefaultX		the new default value to set 
	 */
	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}

	/**
	 * Getter for the default value of the y component (of the upper-left vertex) of the solid area,
	 * useful to have because the solid area can be modified during the collision-checking
	 * @return		the default value of the y component
	 */
	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	/**
	 * Sets the default value of the y component of the solid area
	 * @param solidAreaDefaultY		the new default value to set 
	 */
	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

	/**
	 * It's used as a getter to verify if there is a collision on the entity
	 * @return		true if a collision is happening on the entity, false otherwise
	 */
	public boolean isCollisionOn() {
		return collisionOn;
	}

	/**
	 * Setter for the collisionOn variable
	 * @param collisionOn	the boolean value for the new collision state to set 
	 */
	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
	
}

