package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;

import model.Bomb;
import model.ModelAssets;
import model.Monster;
import model.PlayerModel;
import view.BombView;
import view.Explosion;
import view.GameFrame;
import view.GamePanel;
import view.MonsterView;
import view.PowerUpView;
import view.UI;

/**
 * Main class for the controller that handle the game loop
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class JBomberMan implements Runnable {
	
	private static Thread gameThread;
	private static GamePanel gamePanel;
	private KeyHandler keyHandler;
	private PlayerModel playerModel;
	private CollisionChecker collChecker;
	private static AssetSetter assetSetter;
	private Sound music = new Sound();
	private Sound soundEffect = new Sound();
	@SuppressWarnings("unused")
	private static UI ui = new UI(gamePanel);
	private ModelAssets model = ModelAssets.getInstance();
	private List<Integer> indexes = new ArrayList<>();
	private int monstersToKill = 4; 
		
	private int FPS = 60;
	
	private int standCounter = 0;
	
	private static boolean gameFinished;
	
	
	/**
	 * main method that initializes the game and starts it
	 * @param args
	 */
	public static void main(String[] args) {
		gamePanel = GamePanel.getInstance();
		GameFrame frame = new GameFrame("JBomberMan", gamePanel);
		
		// The icon for the game
		ImageIcon gameIcon = new ImageIcon(JBomberMan.class.getResource("/bombs/icon.png"));
		frame.setIconImage(gameIcon.getImage());
		
		JBomberMan jbm = new JBomberMan();
		GameStateManager.setGameState(GameStateManager.getTitleState());
		setupGame(); // I want to call it before the game starts
		startGameThread(jbm);	
	}
	
	/**
	 * Class constructor for the game where the main components are initialized
	 */
	public JBomberMan() { 
        gamePanel = GamePanel.getInstance();
        collChecker = new CollisionChecker();
        
        playerModel = PlayerModel.getInstance();
        playerModel.addObserver(gamePanel);
        
        this.keyHandler = new KeyHandler(this);
        gamePanel.addKeyListener(keyHandler);
        setPlayerDefaultValues();
    }
	
	/**
	 * Sets the default values for the player's position, speed, 
	 * direction and invincibility stat for the beginning 
	 * of the game
	 */
	public void setPlayerDefaultValues() {
		setDefaultPosition();
		playerModel.setDefaultSpeed(2);
		playerModel.setSpeed(playerModel.getDefaultSpeed());
	}
	
	/**
	 * Sets the starting position, direction and invincibility state.
	 * This method is used both at the beginning of the game and 
	 * when the player looses three heart-lives
	 */
	public void setDefaultPosition() {
		playerModel.setX(53);
		playerModel.setY(48);
		playerModel.setDirection("down");
		playerModel.setInvincible(true);
	}
	
	/**
	 * Initializes the monster array with the assetSetter
	 */
	public static void setupGame() {
		assetSetter = new AssetSetter();
		assetSetter.setMonster();
	}
	
	/**
	 * Method to start the game thread
	 * @param jbm		the instance of this JBomberMan class for the game
	 */
	public static void startGameThread(JBomberMan jbm) {
		gameThread = new Thread(jbm);
		gameThread.start();
	}
	
	/**
	 * Run method of the gameThread. It calls the update methods FPS-times per second.
	 * After every update, it sleeps for the remaining time until the next one
	 */
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			gamePanel.updateView();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update method for the game thread. Updates and handles the main aspects 
	 * of the game following the game logic
	 */
	public void update() {
		gamePanel.setGameState(GameStateManager.getGameState());
		if(GameStateManager.getGameState() == GameStateManager.getPlayState()) 
			updatePlayState();
			
		if(GameStateManager.getGameState() == GameStateManager.getTitleState()) 
			gamePanel.setIsTitle(true);
		
		if(GameStateManager.getGameState() == GameStateManager.getPauseState()) {
			gamePanel.updateView();
		}
		if(GameStateManager.getGameState() == GameStateManager.getGameOverState()) {
			gamePanel.updateView();
		}
	}
	
	/**
	 * Manages the updates to do while in play state:
	 * checks the bombs, moves the player, moves the monsters, checks the collisions
	 */
	public void updatePlayState() {
		// OTHER PRELIMINAR CHECKS
		if(PlayerModel.flagFireUp != 0) {
			Explosion.setFirepower(PlayerModel.flagFireUp);
			PlayerModel.flagFireUp = 0;
		}
		
		// CHECKS FOR POSSIBLE NEW POWERUPS
		for (Point point : GamePanel.replacedTilePositions) {
			checkForPowerUp((int)point.getX(), (int)point.getY());
		}
		GamePanel.replacedTilePositions.clear();
		
		// BOMBS AND EXPLOSIONS
		manageBombs();
		
		// PLAYER
		if(playerModel.isKnockBack()) 
			updatePlayerKnockBack();
		else {
			updatePlayer();
		}
			
		// MONSTERS
		if(monstersToKill != 0) {
			manageMonsters();
		}
			
		// CHECKING FOR GAME OVER
		if(PlayerModel.getLives() <= 0) {
			PlayerModel.decreaseBomberMan();
			if( PlayerModel.getBomberMan() < 0) {
				GameStateManager.setGameState(GameStateManager.getGameOverState());
				stopMusic();
				playSE(12);
			}
			else {
				PlayerModel.setLives(3 + PlayerModel.getLives());
				setDefaultPosition();
			}
		}
		
		// UPDATES FOR THE VIEW
		gamePanel.setGameState(GameStateManager.getGameState());
		gamePanel.setLives(PlayerModel.getLives());
		gamePanel.setGameFinished(gameFinished);
		gamePanel.setBomberMan(PlayerModel.getBomberMan());
		gamePanel.setScore(PlayerModel.getScore());

	}
	
	/**
	 * Method to manage the bombs placed by the player,
	 * their counters and their explosion
	 */
	public void manageBombs() {
		if(Bomb.getCoolDown() > 0)
			Bomb.decreaseCoolDown();
		
		indexes = IntStream.range(0, Bomb.getBombs().size())
			    .filter(i -> {
			        Bomb bomb = Bomb.getBombs().get(i);
			        bomb.incrementTimeElapsed();
			        return bomb.getTimeElapsed() == bomb.getTimeToDetonate();
			    })
			    .boxed()
			    .collect(Collectors.toList());

		manageExplosion(indexes);
		
		BombView.bombsView.forEach(bomb -> {
		    bomb.incrementSpriteCounter();
		    if (bomb.getSpriteCounter() > 4) {
		        if (bomb.getSpriteNumber() != 15) {
		            bomb.incrementSpriteNumber();
		        } else {
		            bomb.resetSpriteNumber();
		        }
		        bomb.resetSpriteCounter();
		    }
		});
		
		Explosion[] explosions = Explosion.getExplosions();
		for (int i = 0; i < explosions.length; i++) {
		    Explosion explosion = explosions[i];
		    if (explosion != null) {
		        explosion.incrementExplosionCounter();
		        if (explosion.getExplosionCounter() == 60) {
		            explosions[i] = null;
		        } else {
		            explosion.incrementSpriteCounter();
		            if (explosion.getSpriteCounter() > 6) {
		                if (explosion.getSpriteNumber() != 9) {
		                    explosion.incrementSpriteNumber();
		                } else {
		                    explosion.resetSpriteNumber();
		                }
		                explosion.resetSpriteCounter();
		            }
		        }
		    }
		}
		
	}
	
	/**
	 * Manages the player knock-back duration and collisions
	 */
	public void updatePlayerKnockBack() {
		// CHECK TILE COLLISION
		playerModel.setCollisionOn(false);
		collChecker.checkTile(playerModel);	
		
		// CHECK OBJECT COLLISION
		int objIndex = collChecker.checkObject(playerModel, true);
		
		// CHECK MONSTER COLLISION
		int monsterIndex = collChecker.checkMonster(playerModel, model.monster);
		if (monsterIndex != 999) {
			contactMonster(monsterIndex);
		}
		
		// CHECK BOMB COLLISION
		collChecker.checkBomb(playerModel);
		
		if(playerModel.isCollisionOn()) {
			playerModel.resetKnockBackCounter();
			playerModel.setKnockBack(false);
			playerModel.setSpeed(playerModel.getDefaultSpeed());
		}
		else if(!playerModel.isCollisionOn()) {
			playerModel.movePlayer();
		}
		
		playerModel.incrementKnockBackCounter();
		if(playerModel.getKnockBackCounter() == 10) {
			playerModel.resetKnockBackCounter();
			playerModel.setKnockBack(false);
			playerModel.setSpeed(playerModel.getDefaultSpeed());
		}
	}
	
	/**
	 * Updates the player's model and checks its collisions
	 */
	public void updatePlayer() {
		
		if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
			
			if(keyHandler.upPressed) 
				playerModel.setDirection("up");			
			else if(keyHandler.downPressed) 
				playerModel.setDirection("down");			
			else if(keyHandler.leftPressed) 
				playerModel.setDirection("left");			
			else if(keyHandler.rightPressed) 
				playerModel.setDirection("right");
			
			// CHECK TILE COLLISION
			playerModel.setCollisionOn(false);
			collChecker.checkTile(playerModel);	
			
			// CHECK OBJECT COLLISION
			int objIndex = collChecker.checkObject(playerModel, true);
			playerModel.pickUpObject(objIndex);
			if (objIndex != 999) {
				PlayerModel.setScore(PlayerModel.getScore()+300);
				PowerUpView.powerUpsView[objIndex] = null;
			}
			
			// CHECK MONSTER COLLISION
			int monsterIndex = collChecker.checkMonster(playerModel, model.monster);
			contactMonster(monsterIndex);	
			
			// CHECK BOMB COLLISION
			collChecker.checkBomb();
			
			playerModel.movePlayer();	
			
			playerModel.incrementSpriteCounter();
			if(playerModel.getSpriteCounter() > 15) {
				if(playerModel.getSpriteNumber() < 4) 
					playerModel.incrementSpriteNumber();
				else if(playerModel.getSpriteNumber() == 4)
					playerModel.resetSpriteNumber();
				playerModel.setSpriteCounter(0);
			}
		}
		// IF THE PLAYER IS NOT IN MOVEMENT, MAKE IT STAND STILL
		else {
			standCounter++;
			if(standCounter == 20) {
				playerModel.resetSpriteNumber();
				playerModel.standStill();
				standCounter = 0;
			}
		}
		
		// CHECK INVINCIBILITY
		if(playerModel.isInvincible()) {
			playerModel.incrementInvincibleCounter();
			if(playerModel.getInvincibleCounter() > 120) {
				playerModel.setInvincible(false);
				playerModel.resetInvincibleCounter();
			}
		}
	}
	
	/**
	 * If the player touches a monster, it loses an heart life
	 * and triggers the knock-back animation
	 */
	public void contactMonster(int i) {
		if(i != 999 && !playerModel.isInvincible()) {
			PlayerModel.decrementLives();
			playerModel.setInvincible(true);
			knockBack(model.monster[i]);
		}
	}
	
	/**
	 * Handles the beginning of the knock-back changing 
	 * the player direction and speed and setting the knock-back
	 * flag to true.
	 * @param monster 		the monster object that caused the 
	 * 						knock-back animation
	 */
	public void knockBack(Monster monster) {
		playerModel.setDirection(monster.getDirection());
		playerModel.setSpeed(playerModel.getSpeed()+1);
		playerModel.setKnockBack(true);
	}
	
	/**
	 * Method to manage the monsters' model-aspects
	 */
	public void manageMonsters() {
		for(int i = 0; i < model.monster.length; i++) {
			if (model.monster[i] != null) {
				if(model.monster[i].isInvincible()) {
					model.monster[i].incrementInvincibleCounter();
					if(model.monster[i].getInvincibleCounter() > 120) {
						model.monster[i].setInvincible(false);
						model.monster[i].resetInvincibleCounter();	
					}
				}
				updateMonster(i);
				if(model.monster[i].getLives() <= 0) {
					monstersToKill--;
					model.monster[i] = null;
					PlayerModel.setScore(PlayerModel.getScore()+500);
					MonsterView.getMonstersView()[i] = null;
				}
			}
		}
	}
	
	/**
	 * Method to move the monster and check its collisions
	 * @param i
	 */
	public void updateMonster(int i) {
		boolean contactPlayer = false;
		Monster currentMonster = (Monster)model.monster[i];
		currentMonster.setAction();
		currentMonster.setCollisionOn(false);
		collChecker.checkTile(currentMonster);
		collChecker.checkObject(currentMonster, false);
		collChecker.checkEntity(currentMonster, model.monster);
		collChecker.checkBomb(currentMonster);
		contactPlayer = collChecker.checkPlayer(currentMonster);
		
		// if the monster hits the player
		if(contactPlayer) {
			if(!playerModel.isInvincible()) {
				PlayerModel.decrementLives();
				playerModel.setInvincible(true);
				knockBack(model.monster[i]);
			}
		}
		model.monster[i].updateMonsterModel();
		MonsterView.updateMonstersView(i, model.monster[i].getX(), model.monster[i].getY(),
									model.monster[i].getSpriteNumber()); 
	}
	
	/**
	 * Method to randomly place powerups in the map when some soft-walls are broken
	 * @param tileX		the x-coordinate of the tile for the powerup 
	 * @param tileY		the y-coordinate of the tile for the powerup
	 */
	public void checkForPowerUp(int tileX, int tileY) {
		Random random = new Random();
		int number = random.nextInt(100) + 1;
		
		if(number < 60) {
			if(number < 20)
				setPowerup("PowerUpBomb", tileX, tileY);
			else if(number < 40)
				setPowerup("PowerUpSpeed", tileX, tileY);
			else if(number < 60)
				setPowerup("PowerUpFireUp", tileX, tileY);
		}
	}

	
	/**
	 * Sets the correct powerup with the asset setter
	 * @param name		the type of the powerup
	 * @param tileX		the x-coordinate of the tile for the powerup 
	 * @param tileY		the y-coordinate of the tile for the powerup
	 */
	public void setPowerup(String name, int tileX, int tileY) {
		assetSetter.setObject(name, tileX, tileY);
	}
		
	/**
	 * Restarts just the level after a game over. The score of the level is set to zero again
	 */
	public void retry() {
		PlayerModel.setBomberMan(3);
		GameStateManager.setGameState(GameStateManager.getPlayState());
		playMusic(15);
		setDefaultPosition();
		playerModel.setSpeed(2);
		PlayerModel.setLives(3);
		PlayerModel.setMaxNumBombs(1);
		Explosion.resetFirepower();
		// restarto anche lo score, ma non il livello (mappa?)
		assetSetter.setMonster();
		gamePanel.setTileManager();
		Bomb.getBombs().clear();
		BombView.bombsView.clear();
		for(int i = 0; i < Explosion.getExplosions().length; i++) {
			Explosion.getExplosions()[i] = null;
		}
		for(int i = 0; i < model.obj.length; i++) {
			model.obj[i] = null;
			PowerUpView.powerUpsView[i] = null;
		}
	}
	
	/**
	 * Restarts all the configuration of the game if, after a game over, 
	 * the player decides to quit to title screen
	 */
	public void restart() {
		retry();
		GameStateManager.setGameState(GameStateManager.getTitleState());
		this.stopMusic();
		gamePanel.setTileManager();
		// reimposta anche il livello
	}

	/**
	 * Method to place a new bomb, if the maximum number of bombs is not 
	 * yet met and if the cooldown time from the last bomb is elapsed 
	 */
	public void placeBomb() {
		if(Bomb.getCoolDown() == 0 && Bomb.getBombs().size() < PlayerModel.getMaxNumBombs()) {
			playSE(7);
			Bomb newBomb = new Bomb();
			newBomb.setX((playerModel.getX() + gamePanel.getTileSize() / 2) / gamePanel.getTileSize());
			newBomb.setY((playerModel.getY() + gamePanel.getTileSize() / 2) / gamePanel.getTileSize());
			Bomb.getBombs().add(newBomb);
			
			BombView newBombView = new BombView(newBomb.getX(), newBomb.getY());
			BombView.bombsView.add(newBombView);
			gamePanel.updateView();
			
			Bomb.resetCoolDown();
		}
	} 
	
	/**
	 * Handles the beginning of a new explosion for all the 
	 * bombs that are detonating. For each one of those bombs
	 * it creates a new Explosion object
	 * @param indexes		the List with the indexes of all the bombs
	 * 						that are detonating 
	 */
	public void manageExplosion(List<Integer> indexes) {
		for(Integer index: indexes) {
			Bomb.getBombs().remove((int)index);
			BombView explodingBomb = BombView.bombsView.get(index);
			for(int i = 0; i < Explosion.getExplosions().length; i++) {
				if(Explosion.getExplosions()[i] == null) {
					Explosion.getExplosions()[i] = new Explosion(explodingBomb.getX(), explodingBomb.getY());
					break;
				}
			}
			BombView.bombsView.remove((int)index);
		}
		indexes.clear();
	}

	/**
	 * Method to start playing the selected music 
	 * @param i			the index for the music to play looped
	 */
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	/**
	 * Stops the music 
	 */
	public void stopMusic() {
		music.stop();
	}
	
	/**
	 * Plays the selected Sound Effect
	 * @param i			the index for the sound effect to play
	 */
	public void playSE(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}

}
