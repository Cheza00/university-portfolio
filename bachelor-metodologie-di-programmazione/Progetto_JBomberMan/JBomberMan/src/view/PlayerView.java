package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to manage the position of the player for the view package
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class PlayerView {
	
	protected BufferedImage up1, up2, up3, up4 , down1, down2, down3, down4;
	protected BufferedImage left1, left2, left3, left4, right1, right2, right3, right4;
	private int viewX, viewY;
	private String viewDirection;
	private int viewSpriteNumber;
	private boolean viewInvincible;
	
	/**
	 * Class constructor
	 * @param x			the x-coordinate for where to draw the player in pixels
	 * @param y			the y-coordinate for where to draw the player in pixels
	 */
	public PlayerView(int x, int y) {
		setViewX(x); //53
		viewY = y;	//48
		viewDirection = "down";
		viewSpriteNumber = 1;
		getPlayerImage();	
	}
	
	/**
	 * Assigns the images to draw the animations of the player
	 */
	public void getPlayerImage() {
		
		up1 = setup("up1");
		up2 = setup("up2");
		up3 = setup("up3");
		up4 = setup("up4");
		down1 = setup("down1");
		down2 = setup("down2");
		down3 = setup("down3");
		down4 = setup("down4");
		left1 = setup("left1");
		left2 = setup("left2");
		left3 = setup("left3");
		left4 = setup("left4");
		right1 = setup("right1");
		right2 = setup("right2");
		right3 = setup("right3");
		right4 = setup("right4");
	}
	
	/**
	 * Method to retrieve the image from the resource folder
	 * @param imageName		the name of the image to substitute in the path for the resource folder
	 * @return				the image 
	 */
	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

	/**
	 * Gets the x-coordinate for the view package to draw the player
	 * @return		the x-coordinate in pixels
	 */
	public int getViewX() {
		return viewX;
	}
	
	/**
	 * Sets the x-coordinate to draw the player on screen
	 * @param x		the x-coordinate in pixels
	 */
	public void setViewX(int x) {
		viewX = x;
	}
	
	/**
	 * Gets the y-coordinate to draw the player on screen
	 * @return		the y-coordinate in pixels
	 */
	public int getViewY() {
		return viewY;
	}
	
	/**
	 * Sets the y-coordinate to draw the player on screen
	 * @param y		the y-coordinate in pixels
	 */
	public void setViewY(int y) {
		viewY = y;
	}
	
	/**
	 * Gets the direction of the player
	 * @return		the direction of the player
	 */
	public String getViewDirection() {
		return viewDirection;
	}
	
	/**
	 * Sets the direction of the player
	 * @param direction		the new direction of the player
	 */
	public void setViewDirection(String direction) {
		viewDirection = direction;
	}
	
	/**
	 * Gets the number of the sprite to use to draw the player
	 * @return		the integer for the sprite number
	 */
	public int getViewSpriteNumber() {
		return viewSpriteNumber;
	}
	
	/**
	 * Sets the number of the sprite to use to draw the player
	 * @param spriteNumber		the number of the sprite to use
	 */
	public void setViewSpriteNumber(int spriteNumber) {
		viewSpriteNumber = spriteNumber;
	}

	/**
	 * Gets the invincibility state of the player to modify how it's drawn
	 * @return		true if the player has to be drawn in its invincibility state
	 * 				false otherwise
	 */
	public boolean getViewInvincible() {
		return viewInvincible;
	}

	/**
	 * Sets the invincibility state of the player to modify how it's drawn
	 * @param viewInvincible		the new invincibility state
	 */
	public void setViewInvincible(boolean viewInvincible) {
		this.viewInvincible = viewInvincible;
	}
	
}
