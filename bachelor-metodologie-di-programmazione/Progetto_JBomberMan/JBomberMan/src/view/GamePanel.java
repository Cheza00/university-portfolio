package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.ExplosionHandler;
import model.ModelAssets;
import model.PlayerModel;

/**
 * Main class for the view, to handle the view of the game 
 * and drew the objects on the screen.
 * Uses the Singleton pattern.
 * The class is the Observer of the PlayerModel class
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class GamePanel extends JPanel implements Runnable, Observer {
	
	private static GamePanel gamePanel;
	private PlayerView playerView; 
	private ExplosionHandler explosionHandler = new ExplosionHandler();
	
	ModelAssets model = ModelAssets.getInstance();
	 
	private final int originalTileSize = 16; // 16x16, the standard size for many retro games
	// but I want to scale it for the modern desktop resolutions 
	private final int scale = 3;
	private final int tileSize = originalTileSize * scale; // actual tile size 48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	private TileManager tileManager;
	public UI ui = new UI(this);
	private boolean isTitle;
	
	private int gameState;
	private boolean gameFinished;
	private int lives;
	private int bomberMan;
	private int score;
	
	public static List<Point> replacedTilePositions = new ArrayList<>();

	/**
	 * Gets the single instance of this class, if present; 
	 * otherwise, it creates one using the private constructor
	 * @return		the instance of the class
	 */
	public static GamePanel getInstance() {
		if (gamePanel == null) gamePanel = new GamePanel();
		return gamePanel;
	}
	
	/**
	 * Private class constructor
	 */
	private GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight + 48));
		this.setBackground(Color.black); // maybe that's not necessary
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		tileManager = new TileManager(this);
		playerView = new PlayerView(53, 48);
		isTitle = true;
	}
	
	/**
	 * Method called by the controller to call the repaint method 
	 */
	public void updateView() {
		repaint();
	}
	
	/**
	 * Updates playerView with the informations
	 * arrived from the playerModel
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PlayerModel) {
			PlayerModel playerModel = (PlayerModel) o;
			playerView.setViewX(playerModel.getX());
			playerView.setViewY(playerModel.getY());
			playerView.setViewDirection(playerModel.getDirection());
			playerView.setViewSpriteNumber(playerModel.getSpriteNumber());
			playerView.setViewInvincible(playerModel.isInvincible()); 
			
		}
	}
	
	/**
	 * Main method to repaint the screen at every update of the thread
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// TITLE SCREEN
		if (isTitle) {
			ui.draw(g2, gameState, gameFinished, lives, bomberMan, score);
		}
		
		// OTHER
		else {	
			// TILE
			tileManager.draw(g2); // it needs to be before the player one, to layer the images
			
			
			// OBJECT
			for(int i = 0; i < model.obj.length; i++) { // after tile and before player
				if(PowerUpView.powerUpsView[i] != null) {
					PowerUpView.draw(g2, PowerUpView.powerUpsView[i]);
				}
			} 
			
			// PLAYER
			drawPlayer(g2);
			
			// MONSTER
			for(int i = 0; i < MonsterView.getMonstersView().length; i++) { // after tile and before player
				if(MonsterView.getMonstersView()[i] != null && MonsterView.getMonstersView()[i].getIsVisible()) {
				//if(model.monster[i] != null && model.monster[i].getIsVisible()) {
					drawMonster(g2, i);
				}
			} 
			
			// BOMBS
			BombView.bombsView.forEach(bomb -> drawBomb(g2, bomb, bomb.getX(), bomb.getY()));
			
			// EXPLOSIONS
			for(int i = 0; i < Explosion.getExplosions().length; i++) {
				if(Explosion.getExplosions()[i] != null) {
					drawExplosion(g2, Explosion.getExplosions()[i]);
				}
			}
			
			
			// UI
			ui.draw(g2, gameState, gameFinished, lives, bomberMan, score);
			
		}
		
		g2.dispose(); //it's a good practice to save resources
	}
	
	/**
	 * Draws the player on the screen 
	 * @param g2		graphics context to draw on the game panel
	 */
	public void drawPlayer(Graphics2D g2) {
		BufferedImage image = null;
		
		try {
			switch(playerView.getViewDirection()) {
			case "up":
				if(playerView.getViewSpriteNumber() == 1)
					image = playerView.up1;	
				else if(playerView.getViewSpriteNumber() == 2) 
					image = playerView.up2;	
				else if(playerView.getViewSpriteNumber() == 3) 
					image = playerView.up3;	
				else if(playerView.getViewSpriteNumber() == 4) 
					image = playerView.up4;	
				break;
			case "down":
				if(playerView.getViewSpriteNumber() == 1) 
					image = playerView.down1;	
				else if(playerView.getViewSpriteNumber() == 2) 
					image = playerView.down2;	
				else if(playerView.getViewSpriteNumber() == 3) 
					image = playerView.down3;	
				else if(playerView.getViewSpriteNumber() == 4) 
					image = playerView.down4;	
				break;
			case "left":
				if(playerView.getViewSpriteNumber() == 1) 
					image = playerView.left1;	
				else if(playerView.getViewSpriteNumber() == 2) 
					image = playerView.left2;	
				else if(playerView.getViewSpriteNumber() == 3) 
					image = playerView.left3;	
				else if(playerView.getViewSpriteNumber() == 4) 
					image = playerView.left4;	
				break;
			case "right":
				if(playerView.getViewSpriteNumber() == 1) 
					image = playerView.right1;	
				else if(playerView.getViewSpriteNumber() == 2) 
					image = playerView.right2;	
				else if(playerView.getViewSpriteNumber() == 3) 
					image = playerView.right3;	
				else if(playerView.getViewSpriteNumber() == 4) 
					image = playerView.right4;	
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(playerView.getViewInvincible()) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // it will be 70% transparent
		}
		
		g2.drawImage(image, playerView.getViewX(), playerView.getViewY(), null);
		
		g2.setColor(Color.red);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	/**
	 * Draws the monster on the screen
	 * @param g2		graphics context to draw on the game panel
	 * @param i			the index of the monster to draw
	 */
	public void drawMonster(Graphics2D g2, int i) {
		BufferedImage image = null;
		MonsterView currentMonster = MonsterView.getMonstersView()[i];
		
		try {
			if(currentMonster.getViewSpriteNumber() == 1) {
				image = currentMonster.up1;	
			}
			if(currentMonster.getViewSpriteNumber() == 2) {
				image = currentMonster.up2;	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		g2.drawImage(image, currentMonster.getViewX(), currentMonster.getViewY(), null);
	}
	
	/**
	 * Draws the bomb on the screen 
	 * @param g2		graphics context to draw on the game panel
	 * @param bomb		the BombView object to draw on the screen 
	 * @param cellX		the x-coordinate for the tile where to draw the bomb
	 * @param cellY		the y-coordinate for the tile where to draw the bomb
	 */
	public void drawBomb(Graphics2D g2, BombView bomb, int cellX, int cellY) {
		BufferedImage image = null;
		switch(bomb.getSpriteNumber()) {
		case 0:
			image = bomb.bomb0;	
			break;
		case 1:
			image = bomb.bomb1;	
			break;
		case 2:
			image = bomb.bomb2;	
			break;
		case 3:
			image = bomb.bomb3;	
			break;
		case 4:
			image = bomb.bomb4;	
			break;
		case 5:
			image = bomb.bomb5;	
			break;
		case 6:
			image = bomb.bomb6;	
			break;
		case 7:
			image = bomb.bomb7;	
			break;
		case 8:
			image = bomb.bomb8;	
			break;
		case 9:
			image = bomb.bomb9;	
			break;
		case 10:
			image = bomb.bomb10;	
			break;
		case 11:
			image = bomb.bomb11;	
			break;
		case 12:
			image = bomb.bomb12;
			break;
		case 13:
			image = bomb.bomb13;	
			break;
		case 14:
			image = bomb.bomb14;	
			break;
		case 15:
			image = bomb.bomb15;	
			break;
		}
		int bombX = cellX * tileSize + (tileSize - image.getWidth()) / 2;
		int bombY = cellY * tileSize + (tileSize - image.getHeight()) / 2;
		g2.drawImage(image, bombX, bombY, null);
	}

	/**
	 * Class to draw and manage the explosion
	 * @param g2			graphics context to draw on the game panel
	 * @param explosion		the explosion to draw
	 */
	public void drawExplosion(Graphics2D g2, Explosion explosion) {
		//explosion.getX() represents the x-coordinate in tiles
		BufferedImage central, horizontal, vertical, leftEdge, rightEdge, topEdge, bottomEdge = null;
		
		switch(explosion.getSpriteNumber()) {
		case 1:
			central = explosion.central1;	
			horizontal = explosion.horizontal1;
			vertical = explosion.vertical1;
			leftEdge = explosion.leftEdge1;
			rightEdge = explosion.rightEdge1;
			topEdge = explosion.topEdge1;
			bottomEdge = explosion.bottomEdge1;
			break;
		case 2:
			central = explosion.central2;	
			horizontal = explosion.horizontal2;
			vertical = explosion.vertical2;
			leftEdge = explosion.leftEdge2;
			rightEdge = explosion.rightEdge2;
			topEdge = explosion.topEdge2;
			bottomEdge = explosion.bottomEdge2;
			break;
		case 3:
			central = explosion.central3;	
			horizontal = explosion.horizontal3;
			vertical = explosion.vertical3;
			leftEdge = explosion.leftEdge3;
			rightEdge = explosion.rightEdge3;
			topEdge = explosion.topEdge3;
			bottomEdge = explosion.bottomEdge3;	
			break;
		case 4:
			central = explosion.central4;	
			horizontal = explosion.horizontal4;
			vertical = explosion.vertical4;
			leftEdge = explosion.leftEdge4;
			rightEdge = explosion.rightEdge4;
			topEdge = explosion.topEdge4;
			bottomEdge = explosion.bottomEdge4;	
			break;
		case 5:
			central = explosion.central5;	
			horizontal = explosion.horizontal5;
			vertical = explosion.vertical5;
			leftEdge = explosion.leftEdge5;
			rightEdge = explosion.rightEdge5;
			topEdge = explosion.topEdge5;
			bottomEdge = explosion.bottomEdge5;
			break;
		case 6:
			central = explosion.central6;	
			horizontal = explosion.horizontal6;
			vertical = explosion.vertical6;
			leftEdge = explosion.leftEdge6;
			rightEdge = explosion.rightEdge6;
			topEdge = explosion.topEdge6;
			bottomEdge = explosion.bottomEdge6;
			break;
		case 7:
			central = explosion.central7;	
			horizontal = explosion.horizontal7;
			vertical = explosion.vertical7;
			leftEdge = explosion.leftEdge7;
			rightEdge = explosion.rightEdge7;
			topEdge = explosion.topEdge7;
			bottomEdge = explosion.bottomEdge7;	
			break;
		case 8:
			central = explosion.central8;	
			horizontal = explosion.horizontal8;
			vertical = explosion.vertical8;
			leftEdge = explosion.leftEdge8;
			rightEdge = explosion.rightEdge8;
			topEdge = explosion.topEdge8;
			bottomEdge = explosion.bottomEdge8;
			break;
		case 9:
			central = explosion.central9;	
			horizontal = explosion.horizontal9;
			vertical = explosion.vertical9;
			leftEdge = explosion.leftEdge9;
			rightEdge = explosion.rightEdge9;
			topEdge = explosion.topEdge9;
			bottomEdge = explosion.bottomEdge9;
			break;
		default:
			central = explosion.central1;	
			horizontal = explosion.horizontal1;
			vertical = explosion.vertical1;
			leftEdge = explosion.leftEdge1;
			rightEdge = explosion.rightEdge1;
			topEdge = explosion.topEdge1;
			bottomEdge = explosion.bottomEdge1;
			break;
		}
			
		
		int explosionX = explosion.getX() * tileSize + (tileSize - central.getWidth()) / 2;
		int explosionY = explosion.getY() * tileSize + (tileSize - central.getWidth()) / 2;
		
		g2.drawImage(central, explosionX, explosionY, null);
		
		int num;
		int i;
		for(i = 1; i < Explosion.getFirepower(); i++) {
			num = tileManager.mapTileNum[explosion.getX()-i][explosion.getY()];
			if(num == 18) {
				g2.drawImage(horizontal, explosionX-48*i, explosionY, null);
				replacedTilePositions.add(new Point(explosion.getX() - i, explosion.getY()));
				tileManager.mapTileNum[explosion.getX()-i][explosion.getY()] = 10;
			}
			else if(num == 10) {
				g2.drawImage(horizontal, explosionX-48*i, explosionY, null);
				explosionHandler.checkCollisionMonsters(explosion.getX()-i, explosion.getY(), tileSize);
				explosionHandler.checkCollisionPlayer(explosion.getX()-i, explosion.getY(), tileSize);
			}
			else {
				break;
			}
		}
		num = tileManager.mapTileNum[explosion.getX()-i][explosion.getY()];
		if(num == 18) {
			g2.drawImage(leftEdge, explosionX-48*i, explosionY, null);
			replacedTilePositions.add(new Point(explosion.getX() - i, explosion.getY()));
			tileManager.mapTileNum[explosion.getX()-i][explosion.getY()] = 10;
			//assetSetter.checkForPowerUp(explosion.getX()-i, explosion.getY());
		}
		else if(num == 10) {
			g2.drawImage(leftEdge, explosionX-48*i, explosionY, null);
			explosionHandler.checkCollisionMonsters(explosion.getX()-i, explosion.getY(), tileSize);
			explosionHandler.checkCollisionPlayer(explosion.getX()-i, explosion.getY(), tileSize);
		}
		
		i = 0;
		for(i = 1; i < Explosion.getFirepower(); i++) {
			num = tileManager.mapTileNum[explosion.getX()+i][explosion.getY()];
			if(num == 18) {
				g2.drawImage(horizontal, explosionX+48*i, explosionY, null);
				replacedTilePositions.add(new Point(explosion.getX() + i, explosion.getY()));
				tileManager.mapTileNum[explosion.getX()+i][explosion.getY()] = 10;
				//assetSetter.checkForPowerUp(explosion.getX()-i, explosion.getY());
			}
			else if(num == 10) {
				g2.drawImage(horizontal, explosionX+48*i, explosionY, null);
				explosionHandler.checkCollisionMonsters(explosion.getX()+i, explosion.getY(), tileSize);
				explosionHandler.checkCollisionPlayer(explosion.getX()+i, explosion.getY(), tileSize);
			}
			else {
				break;
			}
		}
		num = tileManager.mapTileNum[explosion.getX()+i][explosion.getY()];
		if(num == 18) {
			g2.drawImage(rightEdge, explosionX+48*i, explosionY, null);
			replacedTilePositions.add(new Point(explosion.getX() + i, explosion.getY()));
			tileManager.mapTileNum[explosion.getX()+i][explosion.getY()] = 10;
		}
		else if(num == 10) {
			g2.drawImage(rightEdge, explosionX+48*i, explosionY, null);
			explosionHandler.checkCollisionMonsters(explosion.getX()+i, explosion.getY(), tileSize);
			explosionHandler.checkCollisionPlayer(explosion.getX()+i, explosion.getY(), tileSize);
		}
		
		i = 0;
		for(i = 1; i < Explosion.getFirepower(); i++) {
			num = tileManager.mapTileNum[explosion.getX()][explosion.getY()-i];
			if(num == 18) {
				g2.drawImage(vertical, explosionX, explosionY-48*i, null);
				replacedTilePositions.add(new Point(explosion.getX(), explosion.getY() - i));
				tileManager.mapTileNum[explosion.getX()][explosion.getY()-i] = 10;
			}
			else if(num == 10) {
				g2.drawImage(vertical, explosionX, explosionY-48*i, null);
				explosionHandler.checkCollisionMonsters(explosion.getX(), explosion.getY()-i, tileSize);
				explosionHandler.checkCollisionPlayer(explosion.getX(), explosion.getY()-i, tileSize);
			}
			else {
				break;
			}
		}
		num = tileManager.mapTileNum[explosion.getX()][explosion.getY()-i];
		if(num == 18) {
			g2.drawImage(topEdge, explosionX, explosionY-48*i, null);
			replacedTilePositions.add(new Point(explosion.getX(), explosion.getY() - i));
			tileManager.mapTileNum[explosion.getX()][explosion.getY()-i] = 10;
		}
		else if(num == 10) {
			g2.drawImage(topEdge, explosionX, explosionY-48*i, null);
			explosionHandler.checkCollisionMonsters(explosion.getX(), explosion.getY()-i, tileSize);
			explosionHandler.checkCollisionPlayer(explosion.getX(), explosion.getY()-i, tileSize);
		}
		
		i = 0;
		for(i = 1; i < Explosion.getFirepower(); i++) {
			num = tileManager.mapTileNum[explosion.getX()][explosion.getY()+i];
			if(num == 18) {
				g2.drawImage(vertical, explosionX, explosionY+48*i, null);
				replacedTilePositions.add(new Point(explosion.getX(), explosion.getY() + i));
				tileManager.mapTileNum[explosion.getX()][explosion.getY()+i] = 10;
				break;
			}
			else if(num == 10) {
				g2.drawImage(vertical, explosionX, explosionY+48*i, null);
				explosionHandler.checkCollisionMonsters(explosion.getX(), explosion.getY()+i, tileSize);
				explosionHandler.checkCollisionPlayer(explosion.getX(), explosion.getY()+i, tileSize);
			}
			else {
				break;
			}
		}
		num = tileManager.mapTileNum[explosion.getX()][explosion.getY()+i];
		if(num == 18) {
			g2.drawImage(bottomEdge, explosionX, explosionY+48*i, null);
			replacedTilePositions.add(new Point(explosion.getX(), explosion.getY() + i));
			tileManager.mapTileNum[explosion.getX()][explosion.getY()+i] = 10;
		}
		else if(num == 10) {
			g2.drawImage(bottomEdge, explosionX, explosionY+48*i, null);
			explosionHandler.checkCollisionMonsters(explosion.getX(), explosion.getY()+i, tileSize);
			explosionHandler.checkCollisionPlayer(explosion.getX(), explosion.getY()+i, tileSize);
		}	
	}

	/**
	 * Getter for the tile size
	 * @return		the integer corresponding to the tile size in pixels
	 */
	public int getTileSize() {
		return tileSize;
	}
	
	/**
	 * Resets the map
	 */
	public void setTileManager() {
		tileManager = new TileManager(this);
	}
	
	/**
	 * Setter for the gameState to use in the controller
	 * @param gameState		the current gameState
	 */
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	/**
	 * Setter for the gameFinished field to use in the controller
	 * @param gameFinished		true if the levels have all been completed, false otherwise
	 */
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	/**
	 * Setter for the lives field to use in the view.
	 * @param lives			the lives of the player to display in the ui
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**
	 * Setter for the bomberMans left before game Over
	 * @param bomberMan			the bomberMan-lives of the player to display in the ui
	 */
	public void setBomberMan(int bomberMan) {
		this.bomberMan = bomberMan;
	}
	
	/**
	 * Setter for the score
	 * @param score		the new score to display in the ui
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Getter for the playerView
	 * @return		the playerView
	 */
	public PlayerView getPlayerView() {
		return playerView;
	}
	
	/**
	 * Getter for the tile manager
	 * @return		the tile manager
	 */
	public TileManager getTileManager() {
		return tileManager;
	}
	
	/**
	 * Getter for the instance of the ui
	 * @return		the instance of the ui
	 */
	public UI getUi() {
		return ui;
	}
	
	/**
	 * Setter for the isTitle field
	 * @param isTitle		true if we're in the title screen, false otherwise
	 */
	public void setIsTitle(boolean isTitle) {
		this.isTitle = isTitle;
	}
	
	@Override
	public void run() {
		// I don't really need this method, but it's required by the runnable interface
	}

}
