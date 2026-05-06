package controller;

import model.Bomb;
import model.Entity;
import model.ModelAssets;
import model.Monster;
import model.PlayerModel;
import model.Character;
import view.GamePanel;
import view.Tile;

/**
 * Class to check the collisions between the
 * various elements of the game
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class CollisionChecker {
	
	GamePanel gamePanel;
	private ModelAssets model;
	private PlayerModel playerModel = PlayerModel.getInstance();
	private int tileSize;
	
	/**
	 * Class constructor 
	 */
	public CollisionChecker() {
		this.gamePanel = GamePanel.getInstance();
		model = ModelAssets.getInstance();
		tileSize = gamePanel.getTileSize();
	}
	
	/**
	 * Main method to check monsters and player collisions 
	 * with the tiles of the map
	 * @param entity		the character I want to check collisions for
	 */
	public void checkTile(Character entity) {
		int entityLeftX = entity.getX() + entity.solidArea.x;
		int entityRightX = entity.getX() + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.getY() + entity.solidArea.y;
		int entityBottomY = entity.getY() + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftX / tileSize;
		int entityRightCol = entityRightX / tileSize;
		int entityTopRow = entityTopY / tileSize;
		int entityBottomRow = entityBottomY / tileSize;

	    boolean collisionOn = false;
	    
	    switch (entity.getDirection()) {
	        case "up":
	            entityTopRow = (entityTopY - entity.getSpeed()) / tileSize;
	            if (checkCollision(entityLeftCol, entityTopRow) || checkCollision(entityRightCol, entityTopRow)) {
	                collisionOn = true;
	            }
	            break;
	        case "down":
	            entityBottomRow = (entityBottomY + entity.getSpeed()) / tileSize;
	            if (checkCollision(entityLeftCol, entityBottomRow) || checkCollision(entityRightCol, entityBottomRow)) {
	                collisionOn = true;
	            }
	            break;
	        case "left":
	            entityLeftCol = (entityLeftX - entity.getSpeed()) / tileSize;
	            if (checkCollision(entityLeftCol, entityTopRow) || checkCollision(entityLeftCol, entityBottomRow)) {
	                collisionOn = true;
	            }
	            break;
	        case "right":
	            entityRightCol = (entityRightX + entity.getSpeed()) / tileSize;
	            if (checkCollision(entityRightCol, entityTopRow) || checkCollision(entityRightCol, entityBottomRow)) {
	                collisionOn = true;
	            }
	            break;
	    }

	    entity.setCollisionOn(collisionOn);
	}

	/**
	 * Auxiliary method to improve code readability. 
	 * It's called by the checkTile method
	 * @see CollisionChecker#checkTile(Character)
	 * @param col		the tile the left or right side of the character (it's solid area) is touching
	 * @param row		the tile the top or bottom side of the character (it's solid area) is touching 
	 * @return			true if the tile is a solid one, false if the entities can walk on it
	 */
	private boolean checkCollision(int col, int row) {
	    Tile[] tiles = gamePanel.getTileManager().getTiles();
	    int tileNum = gamePanel.getTileManager().getMapTileNum()[col][row];
	    return tiles[tileNum].getCollision();
	}

	/**
	 * Method to check player collision with the objects,
	 * in particular with power-ups and with the exit 
	 * @param entity		the entity I want to check the collision of 
	 * @param player		true if the entity is the player, false otherwise. 
	 * 						Only the player can pick up power-ups and use exits 
	 * @return				the index of the object touched by the player
	 */
	public int checkObject(Character entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < model.obj.length; i++) {
			
			if(model.obj[i] != null) {
				entity.solidArea.x = entity.getX() + entity.solidArea.x;
				entity.solidArea.y = entity.getY() + entity.solidArea.y;
				
				model.obj[i].solidArea.x = model.obj[i].getX() + model.obj[i].solidArea.x;
				model.obj[i].solidArea.y = model.obj[i].getY() + model.obj[i].solidArea.y;
				
				switch(entity.getDirection()) {
				case "up":
					entity.solidArea.y -= entity.getSpeed();
					break;
				case "down":
					entity.solidArea.y += entity.getSpeed();
					break;
				case "left":
					entity.solidArea.x -= entity.getSpeed();
					break;
				case "right":
					entity.solidArea.x += entity.getSpeed();
					break;
				}
				
				if(entity.solidArea.intersects(model.obj[i].solidArea)) {
					if(model.obj[i].getCollision() == true) {
						entity.setCollisionOn(true);
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.getSolidAreaDefaultX();
				entity.solidArea.y = entity.getSolidAreaDefaultY();
				model.obj[i].solidArea.x = model.obj[i].getSolidAreaDefaultX();
				model.obj[i].solidArea.y = model.obj[i].getSolidAreaDefaultY();
			}
		}
		
		return index;
	}
	
	/**
	 * Method to check if the player is colliding with a monster 
	 * @param entity		the player we're checking the collision of 
	 * @param target		the monster's array 
	 * @return				the index of the monster the player is colliding with
	 */
	public int checkMonster(Character entity, Monster[] target) {
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			
			if(target[i] != null && target[i].getIsVisible()) {
				entity.solidArea.x = entity.getX() + entity.solidArea.x;
				entity.solidArea.y = entity.getY() + entity.solidArea.y;
				
				target[i].solidArea.x = target[i].getX() + target[i].solidArea.x;
				target[i].solidArea.y = target[i].getY() + target[i].solidArea.y;
				
				switch(entity.getDirection()) {
				case "up":
					entity.solidArea.y -= entity.getSpeed();
					break;
				case "down":
					entity.solidArea.y += entity.getSpeed();
					break;
				case "left":
					entity.solidArea.x -= entity.getSpeed();
					break;
				case "right":
					entity.solidArea.x += entity.getSpeed();					
					break;
				}
				
				if(entity.solidArea.intersects(target[i].solidArea)) {
					entity.setCollisionOn(true);
					index = i;
				}
				
				entity.solidArea.x = entity.getSolidAreaDefaultX();
				entity.solidArea.y = entity.getSolidAreaDefaultY();
				target[i].solidArea.x = target[i].getSolidAreaDefaultX();
				target[i].solidArea.y = target[i].getSolidAreaDefaultY();
			}
		}
		
		return index;
	}
	
	/**
	 * Method to check the collisions between monsters
	 * @param entity		a specific monster 
	 * @param target		the array of monsters
	 * @return				the index of the monster who's colliding with the one in input 
	 */
	public int checkEntity(Character entity, Character[] target) {
		
		int index = 999;
		for(int i = 0; i < target.length; i++) {
			
			if(target[i] != null) {
				entity.solidArea.x = entity.getX() + entity.solidArea.x;
				entity.solidArea.y = entity.getY() + entity.solidArea.y;
				
				target[i].solidArea.x = target[i].getX() + target[i].solidArea.x;
				target[i].solidArea.y = target[i].getY() + target[i].solidArea.y;
				
				switch(entity.getDirection()) {
				case "up":
					entity.solidArea.y -= entity.getSpeed();
					break;
				case "down":
					entity.solidArea.y += entity.getSpeed();
					break;
				case "left":
					entity.solidArea.x -= entity.getSpeed();
					break;
				case "right":
					entity.solidArea.x += entity.getSpeed();
					break;
				}
				
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i] != entity) {					
						entity.setCollisionOn(true);
						index = i;
					}
				}
				
				entity.solidArea.x = entity.getSolidAreaDefaultX();
				entity.solidArea.y = entity.getSolidAreaDefaultY();
				target[i].solidArea.x = target[i].getSolidAreaDefaultX();
				target[i].solidArea.y = target[i].getSolidAreaDefaultY();
			}
		}
		
		return index;
	}
	
	/**
	 * Method to check if a monster is colliding with a player 
	 * @param entity		the monster I want to check the collision of
	 * @return				a boolean value, true if the monster is entered in contact 
	 * 						with the player, false otherwise 
	 */
	public boolean checkPlayer(Character entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.getX() + entity.solidArea.x;
		entity.solidArea.y = entity.getY() + entity.solidArea.y;
		
		playerModel.solidArea.x = playerModel.getX() + playerModel.solidArea.x;
		playerModel.solidArea.y = playerModel.getY() + playerModel.solidArea.y;
		
		switch(entity.getDirection()) {
		case "up":
			entity.solidArea.y -= entity.getSpeed();
			break;
		case "down":
			entity.solidArea.y += entity.getSpeed();
			break;
		case "left":
			entity.solidArea.x -= entity.getSpeed();
			break;
		case "right":
			entity.solidArea.x += entity.getSpeed();
			break;
		}
		
		if(entity.solidArea.intersects(playerModel.solidArea)) {
			entity.setCollisionOn(true);
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.getSolidAreaDefaultX();
		entity.solidArea.y = entity.getSolidAreaDefaultY();
		playerModel.solidArea.x = playerModel.getSolidAreaDefaultX();
		playerModel.solidArea.y = playerModel.getSolidAreaDefaultY();
	
		return contactPlayer;
	}
	
	
	/**
	 * Method to check if the player is entering in contact with a bomb
	 */
	public void checkBomb() {
		playerModel.solidArea.x = playerModel.getX() + playerModel.solidArea.x;
		playerModel.solidArea.y = playerModel.getY() + playerModel.solidArea.y;
		
		int playerLeftX = playerModel.solidArea.x;
		int playerRightX = playerModel.solidArea.x + playerModel.solidArea.width;
		int playerTopY = playerModel.solidArea.y;
		int playerBottomY = playerModel.solidArea.y + playerModel.solidArea.height;
		
		int playerLeftCol = playerLeftX / gamePanel.getTileSize();
		int playerRightCol = playerRightX / gamePanel.getTileSize();
		int playerTopRow = playerTopY / gamePanel.getTileSize();
		int playerBottomRow = playerBottomY / gamePanel.getTileSize();
		
		for(Bomb bomb:Bomb.getBombs()) {	
			
			if(bomb.getCanCollide()) {
				bomb.solidArea.x = bomb.getX() * gamePanel.getTileSize() + bomb.solidArea.x;
				bomb.solidArea.y = bomb.getY() * gamePanel.getTileSize() + bomb.solidArea.y;
				
			    switch (playerModel.getDirection()) {
			        case "up":
			        	playerModel.solidArea.y -= playerModel.getSpeed();
			            break;
			        case "down":
			        	playerModel.solidArea.y += playerModel.getSpeed();
			            break;
			        case "left":
			        	playerModel.solidArea.x -= playerModel.getSpeed();
			            break;
			        case "right":
			        	playerModel.solidArea.x += playerModel.getSpeed();
			            break;
			        default:
			        	break;
			    }

			    if (playerModel.solidArea.intersects(bomb.solidArea)) {
			    	playerModel.setCollisionOn(true);
			    }
			    
			    playerModel.solidArea.x = playerModel.getSolidAreaDefaultX();
			    playerModel.solidArea.y = playerModel.getSolidAreaDefaultY();
			    
			    bomb.solidArea.x = bomb.getSolidAreaDefaultX();
			    bomb.solidArea.y = bomb.getSolidAreaDefaultY();
				
			}
			// if the bomb couldn't collide with the player is because the player had just placed the bomb, 
			// so it was still in the same tile. When the player move in another tile I can start make the bomb start colliding
			else {
				if((playerLeftCol != bomb.getX() || playerTopRow != bomb.getY()) &&
					(playerLeftCol != bomb.getX() || playerBottomRow != bomb.getY()) &&
					(playerRightCol != bomb.getX() || playerTopRow != bomb.getY()) &&
					(playerRightCol != bomb.getX() || playerBottomRow != bomb.getY())) {
					
					bomb.setCanCollide();
				}
			}

			
		}
	}
	
	/**
	 * Method to check monster collision with the bombs
	 * @param entity		the monster I want to check the collision of 
	 */
	public void checkBomb(Character entity) {
		entity.solidArea.x = entity.getX() + entity.solidArea.x;
		entity.solidArea.y = entity.getY() + entity.solidArea.y;
		for(Bomb bomb:Bomb.getBombs()) {	
			switch (entity.getDirection()) {
			case "up":
				entity.solidArea.y -= entity.getSpeed();
				break;
			case "down":
				entity.solidArea.y += entity.getSpeed();
				break;
			case "left":
				entity.solidArea.x -= entity.getSpeed();
				break;
			case "right":
				entity.solidArea.x += entity.getSpeed();
				break;
			}

			if (entity.solidArea.intersects(bomb.solidArea)) {
				entity.setCollisionOn(true);
			}
			entity.solidArea.x = entity.getSolidAreaDefaultX();
			entity.solidArea.y = entity.getSolidAreaDefaultY();
		}
	}
}