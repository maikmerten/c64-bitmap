/*
 */
package bitmapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author merten
 */
public class Bitmapper {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {


		BufferedImage buf = ImageIO.read(new File("/tmp/test.png"));

		int[][] pixels = new int[buf.getWidth()][buf.getHeight()];

		for (int x = 0; x < buf.getWidth(); ++x) {
			for (int y = 0; y < buf.getHeight(); ++y) {
				pixels[x][y] = buf.getRGB(x, y);
			}
		}
		
		
		MulticolorBitmap bitmap = new MulticolorBitmap();
		bitmap.encodeImage(buf);

		BufferedImage scaled = Scaler.scaleImage(bitmap.bimg, 640, 400, false);
		
		ImageIO.write(scaled, "png", new File("/tmp/c64.png"));
		
	
		FileOutputStream fos = new FileOutputStream(new File("/tmp/c64.koala"));
		bitmap.writeKoala(fos);

	}
}
