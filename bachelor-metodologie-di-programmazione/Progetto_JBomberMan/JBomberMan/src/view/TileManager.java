package view;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * Class to manage the loading of the maps used for the levels
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class TileManager {
	
	private GamePanel gamePanel;
	private Tile[] tiles;
	int mapTileNum[][];
	
	/**
	 * Class constructor
	 */
	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		tiles = new Tile[30];
		mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	/**
	 * Assigns the images for the tiles of the map
	 */
	public void getTileImage() {
		
		setup(10, "bg", false);
		setup(11, "border1", true);
		setup(12, "border2", true);
		setup(13, "angularBL", true);
		setup(14, "angularBR", true);
		setup(15, "angularTL", true);
		setup(16, "angularTR", true);
		setup(17, "hardWall", true);
		setup(18, "softWall", true);
	}
	
	// so I don't have to scale in the game loop anymore
	/**
	 * Method to retrieve the image from the resource folder and assign them to a position in the tiles array
	 * @param index			the array position where i want to insert a certain type of tile
	 * @param imageName		the name of the image to use in the path
	 * @param collision		if the collision can happen with that tile, for example it's set at false for the floor-tiles
	 */
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			
			tiles[index] = new Tile();
			tiles[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName+ ".png")));
			tiles[index].setImage(uTool.scaleImage(tiles[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
			tiles[index].setCollision(collision);
			if(index == 18) 
				tiles[index].setIsBreakable(true);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to load the map from a .txt file with numbers representing each tile
	 */
	public void loadMap(String filePath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int row = 0;
			
			while (row < gamePanel.maxScreenRow) {
			    String line = br.readLine();
			    if (line == null) {
			        break;
			    }
			    
			    String[] numbers = line.split(" ");
			    
			    for (int col = 0; col < gamePanel.maxScreenCol && col < numbers.length; col++) {
			        int num = Integer.parseInt(numbers[col]);
			        mapTileNum[col][row] = num;
			    }
			    
			    row++;
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * Method to draw the map on the screen
	 * @param g2		graphics context to draw on the game panel
	 */
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];	
			// old g2.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			g2.drawImage(tiles[tileNum].getImage(), x, y, null);
			col++;
			x += gamePanel.getTileSize();
			
			if(col == gamePanel.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gamePanel.getTileSize();
			}
		}
		
	}
	
	/**
	 * Gets the array with all the tiles
	 * @return		the array of the tiles
	 */
	public Tile[] getTiles() {
		return tiles;
	}
	
	/**
	 * Gets the matrix of the map 
	 * @return		the matrix of the current map
	 */
	public int[][] getMapTileNum() {
		return mapTileNum;
	}
}
