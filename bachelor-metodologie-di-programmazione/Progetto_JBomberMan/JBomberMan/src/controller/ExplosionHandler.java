package controller;

import model.ModelAssets;
import model.Monster;
import model.PlayerModel;

/**
 * Class to handle the collision of the explosion with the player and the monster
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class ExplosionHandler {

	private PlayerModel playerModel = PlayerModel.getInstance();
	private ModelAssets model = ModelAssets.getInstance();
	
	/**
	 * Check the collision with the player 
	 * @param cellX		the x-coordinate of the tile to check (as the number of the tile)
	 * @param cellY		the y-coordinate of the tile to check (as the number of the tile)
	 * @param tileSize	the dimension of the tile in pixels
	 */
	public void checkCollisionPlayer(int cellX, int cellY, int tileSize) {
		
		int playerLeftX = playerModel.getX() + playerModel.getSolidArea().x;
		int playerRightX = playerModel.getX() + playerModel.getSolidArea().x + playerModel.getSolidArea().width;
		int playerTopY = playerModel.getY() + playerModel.getSolidArea().y;
		int playerBottomY = playerModel.getY() + playerModel.getSolidArea().y + playerModel.getSolidArea().height;
		
		int playerLeftCol = playerLeftX / tileSize;
		int playerRightCol = playerRightX / tileSize;
		int playerTopRow = playerTopY / tileSize;
		int playerBottomRow = playerBottomY / tileSize;
		
		if((playerLeftCol == cellX && playerTopRow == cellY) ||
			(playerLeftCol == cellX && playerBottomRow == cellY) ||
			(playerRightCol == cellX && playerTopRow == cellY) ||
			(playerRightCol == cellX && playerBottomRow == cellY)) {
			if(!playerModel.isInvincible()) {
				PlayerModel.setLives(PlayerModel.getLives() - 3);
				playerModel.setInvincible(true);
			}
		}	
	}
	
	/**
	 * Check possible collisions with all the monsters in the monsters array
	 * @param cellX		the x-coordinate of the tile to check (as the number of the tile)
	 * @param cellY		the y-coordinate of the tile to check (as the number of the tile)
	 * @param tileSize	the dimension of the tile in pixels
	 */
	public void checkCollisionMonsters(int cellX, int cellY, int tileSize) {
		for(int i = 0; i < model.monster.length; i++) { 
			if(model.monster[i] != null) {
				Monster currentMonster = model.monster[i];
				int monsterLeftX = currentMonster.getX() + currentMonster.getSolidArea().x;
				int monsterRightX = currentMonster.getX() + currentMonster.getSolidArea().x + currentMonster.getSolidArea().width;
				int monsterTopY = currentMonster.getY() + currentMonster.getSolidArea().y;
				int monsterBottomY = currentMonster.getY() + currentMonster.getSolidArea().y + currentMonster.getSolidArea().height;
				
				int monsterLeftCol = monsterLeftX / tileSize;
				int monsterRightCol = monsterRightX / tileSize;
				int monsterTopRow = monsterTopY / tileSize;
				int monsterBottomRow = monsterBottomY / tileSize;
				
				if((monsterLeftCol == cellX && monsterTopRow == cellY) ||
						(monsterLeftCol == cellX && monsterBottomRow == cellY) ||
						(monsterRightCol == cellX && monsterTopRow == cellY) ||
						(monsterRightCol == cellX && monsterBottomRow == cellY)) {
					if(!model.monster[i].isInvincible()) {
						model.monster[i].decreaseLives();
						model.monster[i].setInvincible(true);
					}
				}
			}
		} 
	}
}
