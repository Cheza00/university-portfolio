package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.PowerUp;

public class PowerUpView {

	private static GamePanel gamePanel = GamePanel.getInstance();
	private BufferedImage image;
	private int x;
	private int y;
	private static UtilityTool uTool = new UtilityTool();
	
	public static PowerUpView[] powerUpsView = new PowerUpView[20];
	
	public PowerUpView(String name, int x, int y) {
		this.x = x;
		this.y = y;
		switch(name) {
		case "PowerUpBomb": 
			image = PowerUpView.setup("/power_ups/power_bomb.png");
			break;
		case "PowerUpFireUp":
			image = PowerUpView.setup("/power_ups/power_fireup.png");
			break;
		case "PowerUpSpeed":
			image = PowerUpView.setup("/power_ups/power_speed.png");
			break;
		default:
			break;	
		}
	}
	
	
	// change to use the uTool to scale
	public static void draw(Graphics2D g2, PowerUpView obj) {		
		g2.drawImage(obj.image, obj.x, obj.y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);			
	}
	
	public static BufferedImage setup(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(PowerUp.class.getResourceAsStream(path));
			image = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
