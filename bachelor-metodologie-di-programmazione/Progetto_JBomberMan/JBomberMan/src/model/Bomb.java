package model;

import java.util.ArrayList;

/**
 * Class for the bomb objects that are placed by the bomber.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 * 
 */
public class Bomb extends SuperObject {

    private final int timeToDetonate;
    private int timeElapsed;
    
    private static int coolDown;
    private static final int maxBombCooldown = 60;
    private boolean canCollide;
    
    private static ArrayList<Bomb> bombs = new ArrayList<>();
    
    /**
     * Class constructor. It constructs a bomb object.
     */
    public Bomb() {
        this.timeToDetonate = 120;
        this.timeElapsed = 0;
        this.collision = true;
    }

    /**
     * Getter for the max time to wait between the placement 
     * of a bomb and the explosion
     * @return		the time it takes for a bomb to detonate
     */
	public int getTimeToDetonate() {
		return timeToDetonate;
	}

	/**
	 * Gets the time elapsed since the bomb was placed
	 * @return		the time elapsed
	 */
	public int getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * Increments the time elapsed by 1
	 */
	public void incrementTimeElapsed() {
		timeElapsed++;
	}

	/**
	 * Cooldown counter before placing another bomb
	 * @return		the time as an int before being able to place another bomb
	 */
	public static int getCoolDown() {
		return coolDown;
	}

	/**
	 * Decreases cooldown by 1
	 */
	public static void decreaseCoolDown() {
		coolDown--;
	}
	
	/**
	 * Resets the cooldown counter to maxBombCooldown
	 */
	public static void resetCoolDown() {
		coolDown = maxBombCooldown;
	}

	/**
	 * Gets the ArrayList of the bombs
	 * @return		 the ArrayList containing all the bombs on the screen (the model part)
	 */
	public static ArrayList<Bomb> getBombs() {
		return bombs;
	}
	
	/**
	 * Adds a bomb in the bombs ArrayList
	 * @param bomb		the bomb object to add
	 */
	public static void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	/**
	 * Getter for the boolean canCollide, that's used to check if the player
	 * has moved from where it placed the bomb to start the collision checking
	 * @return 	the boolean to state if the bomb can collide or not 
	 * 			(otherwise the player may remain stuck inside the bomb)
	 */
	public boolean getCanCollide() {
		return canCollide;
	}

	/**
	 * States that the bomb can not start colliding
	 */
	public void setCanCollide() {
		canCollide = true;
	}

}
