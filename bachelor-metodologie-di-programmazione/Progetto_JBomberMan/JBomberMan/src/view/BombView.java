package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Class about the view-part of the bombs, to pass the position 
 * without the need for the model and the view to communicate
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class BombView {
	
	public static ArrayList<BombView> bombsView = new ArrayList<>();
	
	private int x;
	private int y;
	private int spriteCounter;
	private int spriteNumber;
	// I don't put them private for practical reasons
	BufferedImage bomb0, bomb1, bomb2, bomb3, bomb4, bomb5, bomb6, bomb7;
	BufferedImage bomb8, bomb9, bomb10, bomb11, bomb12, bomb13, bomb14, bomb15;
	
	/**
	 * Class constructor 
	 * @param x 	x coordinate expressed as the tile number
	 * @param y		y coordinate expressed as the tile number
	 */
	public BombView(int x, int y) {
		this.x = x;
		this.y = y;
		
		getBombImage();
	}
	
	/**
	 * Method to assign the images to draw the animation for the bombs
	 */
	public void getBombImage() {
		
		bomb0 = setup("tile000");
		bomb1 = setup("tile001");
		bomb2 = setup("tile002");
		bomb3 = setup("tile003");
		bomb4 = setup("tile004");
		bomb5 = setup("tile005");
		bomb6 = setup("tile006");
		bomb7 = setup("tile007");
		bomb8 = setup("tile008");
		bomb9 = setup("tile009");
		bomb10 = setup("tile010");
		bomb11 = setup("tile011");
		bomb12 = setup("tile012");
		bomb13 = setup("tile013");
		bomb14 = setup("tile014");
		bomb15 = setup("tile015");
		
	}
	
	/**
	 * Method to retrieve and assign the image
	 * @param imageName		the name of the image to substitute in the path for the resource folder
	 * @return				the image 
	 */
	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/bombs/" + imageName + ".png"));
			
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
	 * Getter for the x coordinate of the bomb (the tile of the bomb)
	 * @return		the x tile of the bomb
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of the bomb (expressed as the tile number)
	 * @param x		the coordinate of the column where I want the image to be drawn
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the y coordinate of the bomb (the tile of the bomb)
	 * @return		the y tile of the bomb
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the bomb (expressed as the tile number)
	 * @param y		the coordinate of the row where I want the image to be drawn
	 */
	public void setY(int y) {
		this.y = y;
	}
}
