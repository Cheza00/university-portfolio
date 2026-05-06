package model;

import java.awt.Rectangle;

/**
 * Abstract class for the monsters and their general aspects
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class Monster extends Character {
	
	protected String name;
	protected int maxLife;
	protected int lives;
	protected boolean changeDirection;
	
	private boolean isVisible = true;
	
	/**
	 * Class constructor (it can be used by the subclasses)
	 * @param name		the name of the monster type
	 * @param maxLife	the max life that monster can have (it depends on the type of monster)
	 * @param speed		the speed of the monster (it depends on the type of monster)
	 */
	public Monster(String name, int maxLife, int speed) {
		this.name = name;
		this.maxLife = maxLife;
		lives = maxLife;
		this.speed = speed;
		
		solidArea = new Rectangle(2, 2, 44, 44);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	/**
	 * Abstract method to set the movement pattern of the monsters
	 * (every type of monster implements it's own method with its own behaviour)
	 */
	public abstract void setAction();
	
	/**
	 * Method to update the position of the monsters (if there isn't a collision)
	 * and the sprite number relative to the sprite to draw
	 */
	public void updateMonsterModel() {
		
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				y -= speed;
				break;
			case "down":
				y += speed;
				break;
			case "left":
				x -= speed;
				break;
			case "right":
				x += speed;
				break;
			}
		}
			
		// in this case I do not use the methods because I only have two possible sprite numbers
		spriteCounter++;
		if(spriteCounter > 30) {
			if(spriteNumber == 1) 
				spriteNumber = 2;
			else if(spriteNumber == 2)
				spriteNumber = 1;
			spriteCounter = 0;
		}
	}

	/**
	 * Getter for the name of the monster
	 * @return		a String containing the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the maxLife of a monster 
	 * @return		an integer corresponding to the life
	 */
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * Getter for the current lives left to the monster
	 * @return
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Decreases the life counter by one
	 */
	public void decreaseLives() {
		lives--;
	}
	
	/**
	 * Sets changeDirection. This method can be used by the controller.
	 * @param chDir
	 */
	public void setChangeDirection(boolean chDir) {
		changeDirection = chDir;
	}

	/**
	 * Getter for the isVisible field
	 * @return		true if a monster is visible, false otherwise
	 */
	public boolean getIsVisible() {
		return isVisible;
	}

	/**
	 * Setter for the isVisible field 
	 * @param isVisible		the new value for the field
	 */
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
}

