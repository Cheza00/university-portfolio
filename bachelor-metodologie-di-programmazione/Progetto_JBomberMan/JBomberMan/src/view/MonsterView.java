package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to manage the position of the monsters for the view package
 * so that the package does not need to communicate with the model
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class MonsterView {

	BufferedImage image;
	BufferedImage up1, up2, up3, up4 , down1, down2, down3, down4;
	BufferedImage left1, left2, left3, left4, right1, right2, right3, right4;
	private int viewX, viewY;
	private String viewDirection;
	private int viewSpriteNumber;
	private boolean viewInvincible;
	private boolean isVisible = true;
	
	private static MonsterView monstersView[] = new MonsterView[20];
	
	private GamePanel gamePanel = GamePanel.getInstance();
	
	/**
	 * Class constructor
	 * @param name		the type of the monster
	 * @param x			the x-coordinate of the monster in pixels
	 * @param y			the y-coordinate of the monster in pixels
	 */
	public MonsterView(String name, int x, int y) {
		viewX = x;
		viewY = y;
		viewDirection = "down";
		viewSpriteNumber = 1;
		image = setup("/greenslime1"); // for the title screen
		switch(name) {
		case "Green Slime":
			getMonsterImage("greenslime");
			break;
		case "Bat":
			getMonsterImage("bat");
			break;
		default: 
			break;
		}
	}
	
	/**
	 * Assigns the images to draw the animation for the monsters
	 * @param imageName		the type of the monster
	 */
	public void getMonsterImage(String imageName) {
		// the sprite are the same for every direction
		up1 = setup(imageName + "1");
		up2 = setup(imageName + "2");
	}
	
	/**
	 * Method to retrieve the image from the resource folder
	 * @param imageName		the name of the image to substitute in the path for the resource folder
	 * @return				the image 
	 */
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/monsters/" + imageName + ".png"));
			image = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * Gets the x-coordinate for the view package to draw the monster
	 * @return		the x-coordinate in pixels
	 */
	public int getViewX() {
		return viewX;
	}
	
	/**
	 * Sets the x-coordinate to draw the monster on screen
	 * @param x		the x-coordinate in pixels
	 */
	public void setViewX(int x) {
		viewX = x;
	}
	
	/**
	 * Gets the y-coordinate to draw the monster on screen
	 * @return		the y-coordinate in pixels
	 */
	public int getViewY() {
		return viewY;
	}
	
	/**
	 * Sets the y-coordinate to draw the monster on screen
	 * @param y		the y-coordinate in pixels
	 */
	public void setViewY(int y) {
		viewY = y;
	}
	
	/**
	 * Gets the direction of the monster
	 * @return		the direction of the monster
	 */
	public String getViewDirection() {
		return viewDirection;
	}
	
	/**
	 * Sets the direction of the monster
	 * @param direction		the new direction of the monster
	 */
	public void setViewDirection(String direction) {
		viewDirection = direction;
	}
	
	/**
	 * Gets the number of the sprite to use to draw the monster
	 * @return		the integer for the sprite number
	 */
	public int getViewSpriteNumber() {
		return viewSpriteNumber;
	}
	
	/**
	 * Sets the number of the sprite to use to draw the monster
	 * @param spriteNumber		the number of the sprite to use
	 */
	public void setViewSpriteNumber(int spriteNumber) {
		viewSpriteNumber = spriteNumber;
	}
	
	/**
	 * Gets the invincibility state of the monster to modify how it's drawn
	 * @return		true if the player has to be drawn in its invincibility state
	 * 				false otherwise
	 */
	public boolean getViewInvincible() {
		return viewInvincible;
	}

	/**
	 * Sets the invincibility state of the monster to modify how it's drawn
	 * @param viewInvincible		the new invincibility state
	 */
	public void setViewInvincible(boolean viewInvincible) {
		this.viewInvincible = viewInvincible;
	}
	
	/**
	 * Gets the array containing all the monsters to draw on the screen
	 * @return		the array with the information about the monsters for the view
	 */
	public static MonsterView[] getMonstersView() {
		return monstersView;
	}
	
	/**
	 * Adds a new monster in the array 
	 * @param name		the name of the monster
	 * @param x			the x-coordinate
	 * @param y			the y-coordinate
	 * @param i			the index in the array I want to add the monster
	 */
	public static void addMonstersView(String name, int x, int y, int i) {
		monstersView[i] = new MonsterView(name, x, y);
	}

	/**
	 * Adds a new monster in the array 
	 * @param name		the name of the monster
	 * @param x			the x-coordinate
	 * @param y			the y-coordinate
	 * @param i			the index in the array I want to add the monster
	 * @param isVisible 	if the monster can be drawn
	 */
	public static void addMonstersView(String name, int x, int y, int i, boolean isVisible) {
		monstersView[i] = new MonsterView(name, x, y);
		monstersView[i].isVisible = isVisible;
	}
	
	/**
	 * Updates the fields of the monster with the values passed by the controller
	 * @param i					the index of the array
	 * @param x					the x-coordinate to draw the monster
	 * @param y					the y-coordinate to draw the monster
	 * @param spriteNumber		the sprite number to use 
	 */
	public static void updateMonstersView(int i, int x, int y, int spriteNumber) {
		monstersView[i].viewX = x;
		monstersView[i].viewY = y;
		monstersView[i].viewSpriteNumber = spriteNumber;
	}

	/**
	 * Getter for the field isVisible
	 * @return		true if the monster can be drawn, false otherwise
	 */
	public boolean getIsVisible() {
		return isVisible;
	}
	
}

