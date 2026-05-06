package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class with some useful utility tools
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class UtilityTool {
	
	/**
	 * Method to scale the images to make them well visible on the screen with a standard size
	 * @param original		the original image to scale
	 * @param width			the new width to use for the scaled image
	 * @param height		the new height to use for the scaled image
	 * @return				the scaled image 
	 */
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage =  new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
}
