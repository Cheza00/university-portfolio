package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

/**
 * Class to draw all the user interface screens. 
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class UI {

	private GamePanel gamePanel;
	private Graphics2D g2;
	private Font arial_40, arial_80B;
	private BufferedImage heartImage;
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1: the basic command guide
	private final int tileSize = 48;
	
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int gameOverState = 4;
	private final int profileState = 5;
	
	private double playTime;
	private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	private NewProfileDialog newProfileDialog;
	
	private String selectedImagePath;
	
	/**
	 * Class constructor to initialize the fonts and some icons
	 * @param gamePanel 		the gamePanel
	 */
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		Heart heart = new Heart();
		heartImage = heart.getImage();
	}
	
	/**
	 * draw method to choose which screen to draw based on the game state the game is into 
	 * @param g2 			graphics context to draw on the game panel
	 * @param gameState		the current game state
	 * @param gameFinished	if the game is finished
	 * @param lives			player's lives
	 * @param bomberMan		how many bomberMan are left before the game over
	 * @param score			player score in the level
	 */
	public void draw(Graphics2D g2, int gameState, boolean gameFinished, int lives, int bomberMan, int score) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if (gameState == titleState) 
		    drawTitleScreen();
		// PLAY STATE
		else if (gameState == playState)
		    drawPlayScreen(gameFinished, lives, bomberMan, score);
		// PAUSE STATE   
		else if (gameState == pauseState)
		    drawPauseScreen();
		// GAME OVER
		else if (gameState == gameOverState) 
		    drawGameOverScreen(); 
		// PROFILE STATE
		else if (gameState == profileState) {
			drawProfileScreen();
		}
	}
	
	/**
	 * Draws the title screen and the other screens seen before playing 
	 */
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "JBomberMan";
			int x = getXForCenteredText(text);
			int y = tileSize * 3;
			
			// SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x+4, y+4);
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// BOMBERMAN IMAGE
			x = gamePanel.screenWidth/2 - tileSize;
			y += tileSize*2;
			g2.drawImage(gamePanel.getPlayerView().down1, x, y, tileSize*2, tileSize*2, null);
			
			x -= tileSize*2;
			y += 20;
			g2.drawImage(getMonsterImage(), x, y, tileSize*2, tileSize*2, null);
			
			x += tileSize*4;
			g2.drawImage(getMonsterImage(), x, y, tileSize*2, tileSize*2, null);
			
			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
			
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y += tileSize*3.5;
			g2.drawString(text, x, y);
			if (commandNum == 0) 
				g2.drawString(">", x - tileSize, y);
			
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y += tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) 
				g2.drawString(">", x - tileSize, y);
			
			text = "QUIT";
			x = getXForCenteredText(text);
			y += tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) 
				g2.drawString(">", x - tileSize, y);
		}
		else if(titleScreenState == 1) {
			drawNewProfileScreen();
		}
		else if(titleScreenState == 2) {
			// Keys Guide Screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "HOW TO PLAY";
			int x = getXForCenteredText(text);
			int y = tileSize*3;
			g2.drawString(text, x, y); 
			
			g2.setFont(g2.getFont().deriveFont(30F));
			text = "Press UP/DOWN/LEFT/RIGHT to move";
			x = getXForCenteredText(text);
			y = tileSize*5;
			g2.drawString(text, x, y); 
			
			text = "Press SPACEBAR to place a bomb";
			x = getXForCenteredText(text);
			y += tileSize/2;
			g2.drawString(text, x, y); 

			text = "Press P to pause";
			x = getXForCenteredText(text);
			y += tileSize/2;
			g2.drawString(text, x, y); 
			
			text = "Press C to see profile info";
			x = getXForCenteredText(text);
			y += tileSize/2;
			g2.drawString(text, x, y); 
			
			text = "Find the exit and defeat all enemies";
			x = getXForCenteredText(text);
			y += tileSize;
			g2.drawString(text, x, y); 
			
			text = "to go to the next level!!";
			x = getXForCenteredText(text);
			y += tileSize/2;
			g2.drawString(text, x, y); 
			 
			g2.setFont(g2.getFont().deriveFont(42F));
			text = "PLAY";
			x = getXForCenteredText(text);
			y += tileSize*2;
			g2.drawString(text, x, y);
			if (commandNum == 0) 
				g2.drawString(">", x - tileSize, y);
			
			text = "BACK";
			x = getXForCenteredText(text);
			y += tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) 
				g2.drawString(">", x - tileSize, y);
			
		}
		
	}
	
	/**
	 * Draws the screen of the playable game
	 * @param gameFinished	if the game is completed or not
	 * @param lives			player's lives
	 */
	public void drawPlayScreen(boolean gameFinished, int lives, int bomberMan, int score) {
		if(gameFinished) 
			drawWinningScreen();	
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.black); 
			g2.drawImage(heartImage, tileSize, tileSize*12, tileSize, tileSize, null);
			g2.drawString("x " + lives, 98, 615); // y for the text indicates the baseline
			
			g2.drawImage(gamePanel.getPlayerView().down1, tileSize*4, tileSize*12, tileSize, tileSize, null);
			g2.drawString("x " + bomberMan, 98 + tileSize*3, 615);
			
			g2.drawString("SCORE: " + score, 98 + tileSize*6, 615);
		}
	}
	
	/**
	 * Screen to draw when winning the final level
	 */
	public void drawWinningScreen() {
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		String text;
		int textLength;
		int x;
		int y;
		
		text = "You found the treasure!";
		textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();		
		x = gamePanel.screenWidth/2 - textLength/2;
		y = gamePanel.screenHeight/2 - (tileSize*3);
		g2.drawString(text, x, y);
		
		text = "Your Time is: " + decimalFormat.format(playTime) + "!";
		textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();		
		x = gamePanel.screenWidth/2 - textLength/2;
		y = gamePanel.screenHeight/2 + (tileSize*4);
		g2.drawString(text, x, y);
		
		g2.setFont(arial_80B);
		g2.setColor(Color.yellow);
		text = "Congratulations!";
		textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();		
		x = gamePanel.screenWidth/2 - textLength/2;
		y = gamePanel.screenHeight/2 + (tileSize*2);
		g2.drawString(text, x, y);
		
		// move this in the controller
		//JBomberMan.gameThread = null; // this stops the thread and the game
	}
	
	/**
	 * Draws the paused screen
	 */
	public void drawPauseScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gamePanel.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(35F));
		text = "BACK TO TITLE SCREEN";
		x = getXForCenteredText(text);
		y += tileSize*2;
		g2.drawString(text, x, y);
		if (commandNum == 0) 
			g2.drawString(">", x - tileSize, y);
		
		text = "OR PRESS P TO RESUME";
		x = getXForCenteredText(text);
		y += tileSize;
		g2.drawString(text, x, y);
	}
	
	/**
	 * Draws the game over screen
	 */
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over";
		// SHADOW
		g2.setColor(Color.black);
		x = getXForCenteredText(text);
		y = tileSize*4;
		g2.drawString(text, x, y);
		// MAIN
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXForCenteredText(text);
		y += tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		// Back to the title screen
		text = "Quit";
		x = getXForCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	
	/**
	 * Draws a JFrame for visualizing the profile info
	 */
	public void drawProfileScreen() {
		// CREATE A FRAME
		final int frameX = tileSize*2;
		final int frameY = tileSize;
		final int frameWidth = tileSize*10;
		final int frameHeight = tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(25F));
		
		int textX = frameX + 20;
		int textY = frameY + tileSize;
		final int lineHeight = 40;
		
		// NAMES
		g2.drawString("Profile picture", textX, textY+lineHeight);
		textY += lineHeight * 4;
		g2.drawString("Nickname", textX, textY);
		textY += lineHeight;
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Points for the next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Total score", textX, textY);
		textY += lineHeight;
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + tileSize;
		String value;
		
		if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
	        try {
	            BufferedImage profileImage = ImageIO.read(new File(selectedImagePath));
	            int imageWidth = profileImage.getWidth();
	            int imageHeight = profileImage.getHeight();
	            g2.drawImage(profileImage, tailX - tileSize, textY, imageWidth, imageHeight, null);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		textY = frameY + tileSize + lineHeight*4;
		value = newProfileDialog.nickname;
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = "0";
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = "0";
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = "0";
		textX = getXForAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
	}
	
	public void drawNewProfileScreen() {
		// CREATE A FRAME
		final int frameX = tileSize*3;
		final int frameY = tileSize*2;
		final int frameWidth = tileSize*10;
		final int frameHeight = tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

	    if (newProfileDialog == null) {
	        newProfileDialog = new NewProfileDialog(null, "New Profile", true);
	        newProfileDialog.setSize(300, 150);
	        newProfileDialog.setLocationRelativeTo(null); 
	    }
	    newProfileDialog.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	titleScreenState = 0;
	            newProfileDialog.dispose();
	        }
	    });
	   // newProfileDialog.setUndecorated(true);
	    newProfileDialog.setVisible(true);
	}
	
	/**
	 * Method to draw a subWindow 
	 * @param x 		x-coordinate
	 * @param y			y-coordinate
	 * @param width		the width of the new window
	 * @param height	the height of the new window
	 */
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,220);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255); // white for the border
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	/**
	 * Calculates the correct x-coordinate to use for a centered text
	 * @param text		the message to display
	 * @return			the x-coordinate in pixels
	 */
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gamePanel.screenWidth/2 - length/2;
		return x;
	}
	
	/**
	 * Calculates the correct x-coordinate to use for right aligned text
	 * @param text		the message to display
	 * @return			the x-coordinate of the right border
	 */
	public int getXForAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	
	public void setSelectedImagePath(String imagePath) {
	    selectedImagePath = imagePath;
	}
	
	/**
	 * Method to get the image for the monsters to draw in the title screen from the resource folder
	 * @return		the image of the slime to draw
	 */
	public BufferedImage getMonsterImage() {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/monsters/greenslime1.png"));
			image = uTool.scaleImage(image, tileSize, tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
