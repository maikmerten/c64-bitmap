/*
 */
package bitmapper;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author merten
 */
public class Scaler {
	
	public static BufferedImage scaleImage(BufferedImage buf, int width, int height, boolean smooth) {
		
		Image scaled = buf.getScaledInstance(width, height, smooth ? Image.SCALE_SMOOTH : Image.SCALE_FAST);
		
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		result.getGraphics().drawImage(scaled, 0, 0, null);
		
		return result;
	}
	
	
}
