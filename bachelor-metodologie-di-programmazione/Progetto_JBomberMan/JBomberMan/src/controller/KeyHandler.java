package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GamePanel;

/**
 * Class to handle what to do every time a key is pressed
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class KeyHandler implements KeyListener {

	private JBomberMan jbm;
	private GamePanel gamePanel = GamePanel.getInstance();
	boolean upPressed, downPressed, leftPressed, rightPressed;
	private int currentGameState;
	private SaveLoad saveLoad = new SaveLoad();
	
	/**
	 * Class constructor
	 * @param jbm		istance of the main controller class
	 */
	public KeyHandler(JBomberMan jbm) {
		this.jbm = jbm;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// I don't need this method, but I need it here because I'm implementing the KeyListener interface		
	}

	/**
	 * Method to manage the events to activate when a key of the keyboard is pressed 
	 * in each of the states of the game
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		currentGameState = GameStateManager.getGameState();
		
		// TITLE STATE
		if(currentGameState == GameStateManager.getTitleState()) 
			titleState(code);
		
		// PLAY STATE
		else if(currentGameState == GameStateManager.getPlayState()) 
			playState(code);
		
		// PAUSE STATE
		else if(currentGameState == GameStateManager.getPauseState()) 
			pauseState(code);
		
		// GAMEOVER STATE
		else if(currentGameState == GameStateManager.getGameOverState()) 
			gameOverState(code);
		
		// PROFILE STATE
		else if(currentGameState == GameStateManager.getProfileState())
			profileState(code);
	}
	
	/**
	 * Method to handle the specific case of a key pressed 
	 * when on the title screen
	 * @param code		the integer representing the key event
	 */
	public void titleState(int code) {
		if(gamePanel.getUi().titleScreenState == 0) {	
			if(code == KeyEvent.VK_UP) {
				gamePanel.getUi().commandNum--;
				if(gamePanel.getUi().commandNum < 0)
					gamePanel.getUi().commandNum = 2;
				jbm.playSE(10);
			}
			if(code == KeyEvent.VK_DOWN) {
				gamePanel.getUi().commandNum++;
				if(gamePanel.getUi().commandNum > 2)
					gamePanel.getUi().commandNum = 0;
				jbm.playSE(10);
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gamePanel.getUi().commandNum == 0) {
					gamePanel.getUi().titleScreenState = 1;
					jbm.playSE(11);
				}
				if(gamePanel.getUi().commandNum == 1) {
					saveLoad.load();
					jbm.playSE(11);
					GameStateManager.setGameState(GameStateManager.getPlayState());
					gamePanel.setIsTitle(false);
					gamePanel.setBackground(Color.gray);
					jbm.playSE(9);
					jbm.playMusic(15);
				}
				if(gamePanel.getUi().commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else if(gamePanel.getUi().commandNum == 1) {
			if(code == KeyEvent.VK_ENTER) {
					jbm.playSE(11);
					gamePanel.getUi().titleScreenState = 2;
				}
		}
		else if(gamePanel.getUi().titleScreenState == 2) {
			saveLoad.save();
			if(code == KeyEvent.VK_UP) {
				gamePanel.getUi().commandNum--;
				if(gamePanel.getUi().commandNum < 0)
					gamePanel.getUi().commandNum = 1;
				jbm.playSE(10);
			}
			if(code == KeyEvent.VK_DOWN) {
				gamePanel.getUi().commandNum++;
				if(gamePanel.getUi().commandNum > 1)
					gamePanel.getUi().commandNum = 0;
				jbm.playSE(10);
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gamePanel.getUi().commandNum == 0) {
					jbm.playSE(11);
					GameStateManager.setGameState(GameStateManager.getPlayState());
					gamePanel.setIsTitle(false);
					gamePanel.setBackground(Color.gray);
					jbm.playSE(9);
					jbm.playMusic(15);
				}
				if(gamePanel.getUi().commandNum == 1) {
					gamePanel.getUi().commandNum = 0;
					gamePanel.getUi().titleScreenState = 0;
				}
			}
		}
	}
	
	/**
	 * Method to handle the specific case of a key 
	 * pressed while playing the game
	 * @param code		the integer representing the key event
	 */
	public void playState(int code) {
		if(code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}	
		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			jbm.stopMusic();
			GameStateManager.setGameState(GameStateManager.getPauseState());
		}
		if(code == KeyEvent.VK_C) {
			jbm.stopMusic();
			GameStateManager.setGameState(GameStateManager.getProfileState());
		}
		if(code == KeyEvent.VK_SPACE) {
			jbm.placeBomb();
		}
	}
	
	/**
	 * Method to handle the specific case of a key 
	 * pressed while the game is paused
	 * @param code		the integer representing the key event
	 */
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			GameStateManager.setGameState(GameStateManager.getPlayState());
			jbm.playMusic(15);
		}
		if(code == KeyEvent.VK_ENTER) {
			jbm.stopMusic();
			GameStateManager.setGameState(GameStateManager.getTitleState());
			gamePanel.getUi().commandNum = 0;
			gamePanel.getUi().titleScreenState = 0;
			gamePanel.setBackground(Color.black);
			jbm.restart();
		}
	}
	
	/**
	 * Method to handle the specific case of a key 
	 * pressed while on the Game Over screen
	 * @param code		the integer representing the key event
	 */
	public void gameOverState(int code) {
		
		if(code == KeyEvent.VK_UP) {
			gamePanel.getUi().commandNum--;
			if(gamePanel.getUi().commandNum < 0)
				gamePanel.getUi().commandNum = 1;
			jbm.playSE(10);
		}
		if(code == KeyEvent.VK_DOWN) {
			gamePanel.getUi().commandNum++;
			if(gamePanel.getUi().commandNum > 1)
				gamePanel.getUi().commandNum = 0;
			jbm.playSE(10);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gamePanel.getUi().commandNum == 0) {
				jbm.retry();
			}
			else if(gamePanel.getUi().commandNum == 1) {
				gamePanel.getUi().commandNum = 0;
				gamePanel.getUi().titleScreenState = 0;
				gamePanel.setBackground(Color.black);
				jbm.restart();
			}
		}
	}
	
	/**
	 * Method to handle the specific case of a key 
	 * pressed while on the profile screen
	 * @param code		the integer representing the key event
	 */
	public void profileState(int code) {
		if(code == KeyEvent.VK_C) {
			GameStateManager.setGameState(GameStateManager.getPlayState());
			jbm.playMusic(15);
		}
	}

	/**
	 * Method to manage the release of a key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}	
		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		
	}
}
