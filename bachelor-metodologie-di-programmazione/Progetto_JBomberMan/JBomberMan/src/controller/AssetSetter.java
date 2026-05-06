package controller;

import model.Bat;
import model.ModelAssets;
import model.PowerUp;
import model.Slime;
import view.GamePanel;
import view.MonsterView;
import view.PowerUpView;

/**
 * Class to place the various objects and monsters on the map
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class AssetSetter {
	private ModelAssets model;
	private GamePanel gamePanel;
	private int tileSize;
	
	public AssetSetter() {
		this.model = ModelAssets.getInstance();
		this.gamePanel = GamePanel.getInstance();
		tileSize = gamePanel.getTileSize();
	}
	
	/**
	 * Methods to create new power ups to place in the map
	 * @param name		the type of the power up
	 * @param x			the x-coordinate of the tile 
	 * @param y			the y-coordinate of the tile 
	 */
	public void setObject(String name, int x, int y) {
		
		for(int i = 0; i < model.obj.length; i++) {
			if(model.obj[i] == null) {
				model.obj[i] = new PowerUp(name, x*tileSize, y*tileSize);
				PowerUpView.powerUpsView[i] = new PowerUpView(name, x*tileSize, y*tileSize);
				break;
			}
		}
	}	
	
	/**
	 * Method to initialize the array of the monsters
	 */
	public void setMonster() {
		int i = 0;
		model.monster[i] = new Bat();
		model.monster[i].setX(2*tileSize);
		model.monster[i].setY(9*tileSize);
		MonsterView.addMonstersView(model.monster[i].getName(), model.monster[i].getX(), model.monster[i].getY(), i);
		i++;
		model.monster[i] = new Bat();
		model.monster[i].setX(11*tileSize);
		model.monster[i].setY(2*tileSize);
		MonsterView.addMonstersView(model.monster[i].getName(), model.monster[i].getX(), model.monster[i].getY(), i);
		i++;
		model.monster[i] = new Bat();
		model.monster[i].setX(13*tileSize);
		model.monster[i].setY(7*tileSize);
		MonsterView.addMonstersView(model.monster[i].getName(), model.monster[i].getX(), model.monster[i].getY(), i);
		i++;
		model.monster[i] = new Bat();
		model.monster[i].setX(0*tileSize);
		model.monster[i].setY(0*tileSize);
		model.monster[i].setIsVisible(false);
		MonsterView.addMonstersView(model.monster[i].getName(), model.monster[i].getX(), model.monster[i].getY(), i, false);
		i++;
				
	}
}
