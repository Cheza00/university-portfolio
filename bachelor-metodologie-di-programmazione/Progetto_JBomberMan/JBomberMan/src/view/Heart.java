package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Small class just to get the heart image to draw to represent lives
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Heart {
	
	private BufferedImage image;
	
	/**
	 * Class constructor
	 */
	public Heart() {
		image = setup("/others/heart_full.png");
	}

	/**
	 * Method to retrieve the image from the resource folder
	 * @param imageName		the path to the file
	 * @return				the image of the heart icon
	 */
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream(imageName));
			image = uTool.scaleImage(image, 48, 48);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * Gets the image 
	 * @return	a BufferedImage containing its image to draw on the screen
	 */
	public BufferedImage getImage() {
		return image;
	}
}

