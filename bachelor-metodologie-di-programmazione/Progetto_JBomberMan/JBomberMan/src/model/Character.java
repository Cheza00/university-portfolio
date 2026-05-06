package model;

/**
 * Subclass of the Entity class, models the basic aspects of 
 * moving characters such as the player and the enemies
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public abstract class Character extends Entity {

	protected int speed;
	private int defaultSpeed;
	protected String direction;
	
	protected int spriteCounter = 0;
	protected int spriteNumber = 1;
	
	private boolean invincible = false;
	private int invincibleCounter;
	
	private boolean knockBack = false;
	private int knockBackCounter;
	
	/**
	 * Getter for the speed 
	 * @return		the integer for the speed, in pixel per repaint
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the character
	 * @param speed		integer for the new speed, in pixel per repaint
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Gets the default speed of the character
	 * @return		an integer for the default speed
	 */
	public int getDefaultSpeed() {
		return defaultSpeed;
	}

	/**
	 * Sets the default speed of the character
	 * @param defaultSpeed	integer for the new value to set 
	 */
	public void setDefaultSpeed(int defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}

	/**
	 * Gets the direction where the character is facing or is moving to 
	 * @return		the String for the direction, one between "up",
	 * 				"down", "left", or "right"
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction where the character is facing or is moving to 
	 * @param direction		a String with the new direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Gets the sprite counter, a value to avoid to change sprite too quickly
	 * @return		the integer for the sprite counter
	 */
	public int getSpriteCounter() {
		return spriteCounter;
	}

	/**
	 * Gets the sprite counter, a value to avoid to change sprite too quickly
	 * @param spriteCounter 	the value for the new sprite counter
	 */
	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
	
	/**
	 * Increments the sprite counter by one
	 */
	public void incrementSpriteCounter() {
		spriteCounter++;
	}

	/**
	 * Gets the number to indicate the sprite to use
	 * @return	the integer corresponding to the sprite number
	 */
	public int getSpriteNumber() {
		return spriteNumber;
	}

	/**
	 * Increments the sprite number by one, to pass to the next one
	 */
	public void incrementSpriteNumber() {
		spriteNumber++;
	}
	
	/**
	 * Resets the sprite number to the default value (1)
	 */
	public void resetSpriteNumber() {
		spriteNumber = 1;
	}

	/**
	 * Getter for the invincible field 
	 * @return	true if the character is in it's invincibility period, false otherwise
	 */
	public boolean isInvincible() {
		return invincible;
	}

	/**
	 * Sets the invincible field
	 * @param invincible	boolean to mark the character as invincible or not (respectively at the start 
	 * and at the end of the invincibility period after an hit or a respawn)
	 */
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	/**
	 * Gets the counter for the invincibility period
	 * @return		the integer representing the counter
	 */
	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	/**
	 * Increments the counter
	 */
	public void incrementInvincibleCounter() {
		invincibleCounter++;
	}

	/**
	 * Resets the counter to 0 
	 */
	public void resetInvincibleCounter() {
		invincibleCounter = 0;
	}
	
	/**
	 * Getter for the knockBack field
	 * @return	true if the character has been hit or false otherwise 
	 */
	public boolean isKnockBack() {
		return knockBack;
	}

	/**
	 * Sets the knockBack field 
	 * @param knockBack		boolean set to true at the end of an animation of knockback, to false 
	 * 						at the end of it until the next hit occur 
	 */
	public void setKnockBack(boolean knockBack) {
		this.knockBack = knockBack;
	}

	/**
	 * Getter for the counter of the knockback animation (to control its duration)
	 * @return 		the integer of the counter
	 */
	public int getKnockBackCounter() {
		return knockBackCounter;
	}

	/**
	 * Increments the counter
	 */
	public void incrementKnockBackCounter() {
		knockBackCounter++;
	}
	
	/**
	 * Resets the counter to 0
	 */
	public void resetKnockBackCounter() {
		knockBackCounter = 0;
	}
}
