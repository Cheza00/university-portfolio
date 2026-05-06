package model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class to model the model of the player.
 * Singleton pattern is used.
 * This class is the real Observable.
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class PlayerModel extends Character {

	private static int lives = 3;
	private static int bomberMan = 3;
	private ModelAssets model;
	private static PlayerModel playerModel;
	private int x, y;
	private static int maxNumBombs = 1;
	private static int playerLevel;
	
	// It's added to the profile score at the end of a completed level
	private static int score;
	
	public static int flagFireUp;
	
	/**
	 * Public getter for the instance of the PlayerModel class.
	 * Returns the instance if present, otherwise it creates it using
	 * the private constructor
	 * @return		the instance of the class
	 */
	public static PlayerModel getInstance() {
		if(playerModel == null) playerModel = new PlayerModel();
		return playerModel;
	}
	
	/**
	 * Private constructor 
	 */
	private PlayerModel() {
		solidArea = new Rectangle(8, 16, 26 , 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		model = ModelAssets.getInstance();
	}
	
	/**
	 * Method to notify the observer that the player is 
	 * standing still, so a change of sprite is needed
	 */
	public void standStill() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Method to notify the observer about the 
	 * movement of the player
	 */
	public void movePlayer() {
		// IF COLLISION IS FALSE, PLAYER CAN MOVE
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
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Method to pick up a power-up when the player touches it 
	 * @param index			the array index of the poweup to pick up
	 */
	public void pickUpObject(int index) {
		
		if(index != 999) {
			String objectName = model.obj[index].name;
			
			switch(objectName) {
			case "PowerUpBomb":
				maxNumBombs++;
				model.obj[index] = null;
				break;
			case "PowerUpFireUp":
				flagFireUp++;
				model.obj[index] = null;
				break;
			case "PowerUpSpeed":
				speed += 1;
				model.obj[index] = null;
				break;
			/*
			case "Portal":
			// se tutti i nemici sono stati sconfitti, passa allo scenario successivo
			// se il livello è il secondo, finisci il gioco 
				gamePanel.ui.gameFinished = true;
				gamePanel.stopMusic();
				gamePanel.playSE(4);
				break;
			 */
			}
		}
	}
	
	/**
	 * Getter for the x-coordinate in pixels
	 * @return x 		the x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for the y-coordinate in pixels
	 * @return y 		the y-coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter for the x-coordinate in pixels
	 * @param x		the new x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setter for the y-coordinate in pixels
	 * @param y		the new y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Getter for the lives of the player
	 * @return		the lives of the player
	 */
	public static int getLives() {
		return lives;
	}
	
	/**
	 * Decrements player's lives by one
	 */
	public static void decrementLives() {
		lives--;
	}
	
	/**
	 * Setter for the player's lives
	 * @param lives		the new number of the lives
	 */
	public static void setLives(int lives) {
		PlayerModel.lives = lives;
	}
	
	/**
	 * Getter for the max number of bombs that can be placed
	 * @return		the current max number of bombs that can be placed
	 */		
	public static int getMaxNumBombs() {
		return maxNumBombs;
	}

	/**
	 * Increases the max number of bombs placeable at the same time 
	 */
	public static void setMaxNumBombs(int maxNumBombs) {
		PlayerModel.maxNumBombs = maxNumBombs;
	}

	/**
	 * Getter for the number of of BomberMan-lives. 
	 * Each one of them is three heart-lives
	 * @return		the BomberMan-lives
	 */
	public static int getBomberMan() {
		return bomberMan;
	}

	/**
	 * Decreases the bomberMan lives by one
	 */
	public static void decreaseBomberMan() {
		bomberMan--;
	}
	
	/**
	 * Setter for the bomberMan-lives
	 * @param bomberMan		the new number for the bomberMan-lives
	 */
	public static void setBomberMan(int bomberMan) {
		PlayerModel.bomberMan = bomberMan;
	}

	/**
	 * Getter for the score of the current level
	 * @return		the current score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Setter for the score
	 * @param score		the new score
	 */
	public static void setScore(int score) {
		PlayerModel.score = score;
	}

	/**
	 * Getter for the player's level
	 * @return		the player's level
	 */
	public static int getPlayerLevel() {
		return playerLevel;
	}

	/**
	 * Setter for the player's level
	 * @param playerLevel		the new level
	 */
	public static void setPlayerLevel(int playerLevel) {
		PlayerModel.playerLevel = playerLevel;
	}
	
}
