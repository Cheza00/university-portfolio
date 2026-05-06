package controller;

/**
 * Utility class to set and retrieve the current game state to use them in various
 * parts of the project
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class GameStateManager {
	// GAME STATE
	private static int gameState;
	private static final int titleState = 0;
	private static final int playState = 1;
	private static final int pauseState = 2;
	private static final int profileCreationState = 3;
	private static final int gameOverState = 4;
	private static final int profileState = 5;
	
	/**
	 * Getter for the gameState
	 * @return		the integer representing the current state of the game
	 */
	public static int getGameState() {
		return gameState;
	}
	
	/**
	 * Sets the new game state
	 * @param	modality	the integer representing the new game state I want to transition in
	 */
	public static void setGameState(int modality) {
		gameState = modality;
	}

	/**
	 * Getter for the title state
	 * @return		the integer representing the title state
	 */
	public static int getTitleState() {
		return titleState;
	}

	/**
	 * Getter for the normal play state
	 * @return		the integer representing the play state
	 */
	public static int getPlayState() {
		return playState;
	}

	/**
	 * Getter for the pause state
	 * @return		the integer representing the pause state
	 */
	public static int getPauseState() {
		return pauseState;
	}

	
	/**
	 * Getter for the profile-selection state
	 * @return		the integer representing the profile-selection state
	 */
	public static int getProfileCreationState() {
		return profileCreationState;
	}

	/**
	 * Getter for the game over state 
	 * @return		the integer representing the game over state
	 */
	public static int getGameOverState() {
		return gameOverState;
	}

	/**
	 * Getter fot the profile state
	 * @return		the integer representing the profile state
	 */
	public static int getProfileState() {
		return profileState;
	}
	
}
