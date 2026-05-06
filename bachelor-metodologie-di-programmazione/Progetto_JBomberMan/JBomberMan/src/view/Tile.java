package view;

import java.awt.image.BufferedImage;

/**
 * Class to represent a single tile of the game map 
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Tile {
	private BufferedImage image;
	// it contains some information that should be in the model, but 
	// it would have been so unnecessarily complicated and it would have 
	// added complexity to manage the game map
	private boolean collision = false;
	private boolean isBreakable; // initialized at false
	
	/**
	 * Getter for the image
	 * @return	the image used for that tile 
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Sets the new image to use for the tile 
	 * @param image		the image for the tile
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Getter for the collision
	 * @return  whether it is possible to collide on the tile (if it is a solid one) or not
	 */
	public boolean getCollision() {
		return collision;
	}

	/**
	 * Sets whether it is possible to collide on the tile or not (if the player can walk 
	 * on it, collision will be set on false)
	 * @param collision 	boolean value for the collision
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	/**
	 * Getter for the isBreakable field 
	 * @return		true if the tile can break, false otherwise
	 */
	public boolean getIsBreakable() {
		return isBreakable;
	}

	/**
	 * Sets if the tile is breakable or not 
	 * @param isBreakable		boolean value, true if the tile can break, false otherwise
	 */
	public void setIsBreakable(boolean isBreakable) {
		this.isBreakable = isBreakable;
	}
}
