package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to model the graphic part of the explosions
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Explosion {
	private int spriteCounter;
	private int spriteNumber = 1;
	private int explosionCounter;
	private static int firepower = 1;
	private static Explosion[] explosions = new Explosion[5];
	private int x, y;
	
	// I preferred to leave the default visibility for practical reasons, to avoid creating the getter for every single sprite
	BufferedImage central1, central2, central3, central4, central5, central6, central7, central8, central9;
	BufferedImage horizontal1, horizontal2, horizontal3, horizontal4, horizontal5, horizontal6, horizontal7, horizontal8, horizontal9;
	BufferedImage vertical1, vertical2, vertical3, vertical4, vertical5, vertical6, vertical7, vertical8, vertical9;
	BufferedImage leftEdge1, leftEdge2, leftEdge3, leftEdge4, leftEdge5, leftEdge6, leftEdge7, leftEdge8, leftEdge9;
	BufferedImage rightEdge1, rightEdge2, rightEdge3, rightEdge4, rightEdge5, rightEdge6, rightEdge7, rightEdge8, rightEdge9;
	BufferedImage topEdge1, topEdge2, topEdge3, topEdge4, topEdge5, topEdge6, topEdge7, topEdge8, topEdge9;
	BufferedImage bottomEdge1, bottomEdge2, bottomEdge3, bottomEdge4, bottomEdge5, bottomEdge6, bottomEdge7, bottomEdge8, bottomEdge9;

	/**
	 * Class constructor 
	 * @param x 	x coordinate for the center of the explosion (where it starts, where the bomb is placed)
	 * @param y		y coordinate for the center of the explosion 
	 */
	public Explosion(int x, int y) {
		this.setX(x);
		this.setY(y);
		getExplosionImage();
	}
	
	/**
	 * Method to assign the image to draw for the explosion on a single tile
	 */
	public void getExplosionImage() {
	    central1 = setup("tile000");
	    central2 = setup("tile001");
	    central3 = setup("tile002");
	    central4 = setup("tile003");
	    central5 = setup("tile004");
	    central6 = setup("tile005");
	    central7 = setup("tile006");
	    central8 = setup("tile007");
	    central9 = setup("tile008");
	    
	    horizontal1 = setup("tile009");
	    horizontal2 = setup("tile010");
	    horizontal3 = setup("tile011");
	    horizontal4 = setup("tile012");
	    horizontal5 = setup("tile013");
	    horizontal6 = setup("tile014");
	    horizontal7 = setup("tile015");
	    horizontal8 = setup("tile016");
	    horizontal9 = setup("tile017");
	    
	    vertical1 = setup("tile018");
	    vertical2 = setup("tile019");
	    vertical3 = setup("tile020");
	    vertical4 = setup("tile021");
	    vertical5 = setup("tile022");
	    vertical6 = setup("tile023");
	    vertical7 = setup("tile024");
	    vertical8 = setup("tile025");
	    vertical9 = setup("tile026");
	    
	    leftEdge1 = setup("tile027");
	    leftEdge2 = setup("tile028");
	    leftEdge3 = setup("tile029");
	    leftEdge4 = setup("tile030");
	    leftEdge5 = setup("tile031");
	    leftEdge6 = setup("tile032");
	    leftEdge7 = setup("tile033");
	    leftEdge8 = setup("tile034");
	    leftEdge9 = setup("tile035");
	    
	    rightEdge1 = setup("tile036");
	    rightEdge2 = setup("tile037");
	    rightEdge3 = setup("tile038");
	    rightEdge4 = setup("tile039");
	    rightEdge5 = setup("tile040");
	    rightEdge6 = setup("tile041");
	    rightEdge7 = setup("tile042");
	    rightEdge8 = setup("tile043");
	    rightEdge9 = setup("tile044");
	    
	    topEdge1 = setup("tile045");
	    topEdge2 = setup("tile046");
	    topEdge3 = setup("tile047");
	    topEdge4 = setup("tile048");
	    topEdge5 = setup("tile049");
	    topEdge6 = setup("tile050");
	    topEdge7 = setup("tile051");
	    topEdge8 = setup("tile052");
	    topEdge9 = setup("tile053");
	    
	    bottomEdge1 = setup("tile054");
	    bottomEdge2 = setup("tile055");
	    bottomEdge3 = setup("tile056");
	    bottomEdge4 = setup("tile057");
	    bottomEdge5 = setup("tile058");
	    bottomEdge6 = setup("tile059");
	    bottomEdge7 = setup("tile060");
	    bottomEdge8 = setup("tile061");
	    bottomEdge9 = setup("tile062");
	    
	}

	/**
	 * Method to retrieve and assign the image
	 * @param imageName		the name of the image to substitute in the path for the resource folder
	 * @return				the image 
	 */
	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/explosion/" + imageName + ".png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

	/**
	 * Getter for the sprite counter (that counts the frames before having to change to the next sprite)
	 * @return	the integer for the sprite counter 
	 */
	public int getSpriteCounter() {
		return spriteCounter;
	}

	/**
	 * Increments the sprite counter by one
	 */
	public void incrementSpriteCounter() {
		spriteCounter++;
	}
	
	/**
	 * Method to reset the sprite counter after a certain period of time is elapsed
	 */
	public void resetSpriteCounter() {
		spriteCounter = 0;
	}

	/**
	 * Gets the sprite number
	 * @return		an integer representing the sprite number
	 */
	public int getSpriteNumber() {
		return spriteNumber;
	}

	/**
	 * Increment the sprite number by one
	 */
	public void incrementSpriteNumber() {
		spriteNumber++;
	}
	
	/**
	 * Decrement the sprite number by one
	 */
	public void decrementSpriteNumber() {
		spriteNumber--;
	}
	
	/**
	 * Resets the sprite number to 0
	 */
	public void resetSpriteNumber() {
		spriteNumber = 0;
	}

	/**
	 * Getter for the explosion counter, to manage the explosion duration
	 * @return		the explosion counter
	 */
	public int getExplosionCounter() {
		return explosionCounter;
	}

	/**
	 * Increments the explosion counter by one
	 */
	public void incrementExplosionCounter() {
		explosionCounter++;
	}

	/**
	 * Gets the distance covered by an explosion 
	 * @return		the integer representing the number of tiles from the center reached by the explosion
	 */
	public static int getFirepower() {
		return firepower;
	}

	/**
	 * Sets the firepower (one more tile can be reached by the explosion in a + pattern for each increment)
	 * @param firepower		the firepower to add
	 */
	public static void setFirepower(int firepower) {
		Explosion.firepower += firepower;
	}
	
	/**
	 * Resets the firepower, for example when the player move to another map or looses three lives
	 */
	public static void resetFirepower() {
		firepower = 1;
	}

	/**
	 * Gets the array of all the explosions
	 * @return		the array 
	 */
	public static Explosion[] getExplosions() {
		return explosions;
	}
	
	/**
	 * Gets the element at the index i in the array of the explosions 
	 * (overloading)
	 * @param i		index of the element I want
	 * @return		the single Explosion object 
	 */
	public static Explosion getExplosions(int i) {
		return explosions[i];
	}

	/**
	 * Getter for the x coordinate of the center of the explosion
	 * @return		the x coordinate  of the center of the explosion
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of the center of the explosion
	 * @param x		the pixel where I want the image to be drawn
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the y coordinate of the center of the explosion
	 * @return		the y coordinate  of the center of the explosion
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the center of the explosion
	 * @param y		the pixel where I want the image to be drawn
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
}
