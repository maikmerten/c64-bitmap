/*
 */
package bitmapper;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author merten
 */
public class MulticolorBitmap {

	public short screencolor;
	public short[][] colorram = new short[40][25];
	public short[][] color1 = new short[40][25];
	public short[][] color2 = new short[40][25];
	public short[][] bitmap = new short[160][200];
	public BufferedImage bimg;
	private int[][] pixels;
	private int[] screenhistogram = new int[16];
	private double ditherscale = 0.2;
	private double scatterscale = 0.1;
	private int rounds = 4;

	
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
	
	public void setDitherScale(double ditherscale) {
		this.ditherscale = ditherscale;
	}
	
	public void setScatterScale(double scatterscale) {
		this.scatterscale = scatterscale;
	}
	
	private int[] getBlock(int x, int y) {
		return new int[]{x >> 2, y >> 3};
	}

	private int[] getCoordinates(int blockx, int blocky) {
		return new int[]{blockx << 2, blocky << 3};
	}

	private void determineScreenColor() {

		// do histogram of color usage
		for (int x = 0; x < pixels.length; ++x) {
			for (int y = 0; y < pixels[0].length; ++y) {
				int color = pixels[x][y];
				short viccolor = Colors.getBestMatch(color);
				screenhistogram[viccolor]++;
			}
		}

		// find most used color
		short color = 0;
		int count = 0;
		for (short i = 0; i < screenhistogram.length; ++i) {
			if (screenhistogram[i] > count) {
				color = i;
				count = screenhistogram[i];
			}
		}
		screencolor = color;
	}

	private void determineBlockColors() {
		for (int i = 0; i < colorram.length; ++i) {
			for (int j = 0; j < colorram[0].length; ++j) {
				int[] coordinates = getCoordinates(i, j);
				int x = coordinates[0];
				int y = coordinates[1];

				int[] histogram = new int[16];

				for (int xoff = 0; xoff < 4; ++xoff) {
					for (int yoff = 0; yoff < 8; ++yoff) {
						short viccolor = Colors.getBestMatch(pixels[x + xoff][y + yoff]);
						histogram[viccolor]++;
					}
				}

				short color = 0;
				int count = 0;
				boolean found = false;
				for (short k = 0; k < histogram.length; ++k) {
					if (histogram[k] > count && k != screencolor) {
						color = k;
						count = histogram[k];
						found = true;
					}
				}
				if (!found) {
					color = 3;
				}
				colorram[i][j] = color;

				color = 0;
				count = 0;

				for (short k = 0; k < histogram.length; ++k) {
					if (histogram[k] > count && k != screencolor && k != colorram[i][j]) {
						color = k;
						count = histogram[k];
						found = true;
					}
				}
				if (!found) {
					color = 11; // dark grey
				}
				color1[i][j] = color;

				color = 0;
				count = 0;
				found = false;
				for (short k = 0; k < histogram.length; ++k) {
					if (histogram[k] > count && k != screencolor && k != colorram[i][j] && k != color1[i][j]) {
						color = k;
						count = histogram[k];
						found = true;
					}
				}
				if (!found) {
					color = 5; // green
				}
				color2[i][j] = color;

			}
		}
	}

	private void createBitmap() {

		Random r = new Random(pixels.hashCode());

		short[] blockcolors = new short[4];
		int[] residue = new int[3];

		for (int x = 0; x < bitmap.length; ++x) {

			for (int y = 0; y < bitmap[0].length; ++y) {
				int i = getBlock(x, y)[0];
				int j = getBlock(x, y)[1];

				blockcolors[0] = screencolor;
				blockcolors[1] = color1[i][j];
				blockcolors[2] = color2[i][j];
				blockcolors[3] = colorram[i][j];

				int pixel = pixels[x][y];
				short code = 0;
				double error = Double.MAX_VALUE;
				int viccolor = 0;

				for (short code2 = 0; code2 < 4; ++code2) {
					short color = blockcolors[code2];
					int viccolor2 = Colors.COLORS[color];
					double error2 = Colors.getDistance(pixel, viccolor2);
					if (error2 < error) {
						code = code2;
						error = error2;
						viccolor = viccolor2;
					}
				}

				residue = Colors.getResidue(pixel, viccolor);

				double myditherscale = ditherscale + ((r.nextDouble() - 0.5) * scatterscale);


				if (x > 0 && y > 0) {
					pixels[x - 1][y - 1] = Colors.applyResidue(residue, pixels[x - 1][y - 1], myditherscale * (3.0 / 16.0));
				}
				if (y < pixels[0].length - 1) {
					pixels[x][y + 1] = Colors.applyResidue(residue, pixels[x][y + 1], myditherscale * (5.0 / 16.0));
				}
				if (x < pixels.length - 1 && y < pixels[0].length - 1) {
					pixels[x + 1][y + 1] = Colors.applyResidue(residue, pixels[x + 1][y + 1], myditherscale * (1.0 / 16.0));
				}
				if (x < pixels.length - 1) {
					pixels[x + 1][y] = Colors.applyResidue(residue, pixels[x + 1][y], myditherscale * (7.0 / 16.0));
				}

				bitmap[x][y] = code;


			}
		}
	}

	private void decodeBitmap() {

		short[] blockcolors = new short[4];

		for (int x = 0; x < bitmap.length; ++x) {
			for (int y = 0; y < bitmap[0].length; ++y) {
				int[] block = getBlock(x, y);

				blockcolors[0] = screencolor;
				blockcolors[1] = color1[block[0]][block[1]];
				blockcolors[2] = color2[block[0]][block[1]];
				blockcolors[3] = colorram[block[0]][block[1]];


				short color = blockcolors[bitmap[x][y]];
				//short color = blockcolors[2]; // hack to always show color2
				int pixel = Colors.COLORS[color];

				bimg.setRGB(x, y, pixel);
			}
		}

	}


	public void writeKoala(OutputStream os) throws Exception {
		os.write((byte) 0x00);
		os.write((byte) 0x60);


		// write bitmap
		for (int j = 0; j < 25; ++j) {
			for (int i = 0; i < 40; ++i) {

				int[] coordinates = getCoordinates(i, j);
				int x = coordinates[0];
				int y = coordinates[1];

				for (int yoff = 0; yoff < 8; ++yoff) {
					int output = 0;
					for (int xoff = 0; xoff < 4; ++xoff) {
						output = output << 2;
						int code = bitmap[x + xoff][y + yoff];
						output |= code & 0x3;
					}

					byte b = (byte) (output & 0xFF);
					os.write(b);
					//os.write((byte)(2 << 6 | 2 << 4 | 2 << 2 | 2));
				}
			}
		}
		
		// write character mem
		for (int j = 0; j < color1[0].length; ++j) {
			for (int i = 0; i < color1.length; ++i) {

				int tmp = color1[i][j];
				tmp = (tmp << 4) | color2[i][j];

				byte b = (byte) (tmp & 0xFF);
				os.write(b);
			}
		}
		
		// write color mem
		for (int j = 0; j < colorram[0].length; ++j) {
			for (int i = 0; i < colorram.length; ++i) {
				byte b  = (byte)colorram[i][j];
				os.write(b);
			}
		}
		
		// write bgcolor
		os.write((byte)screencolor);
		
		os.flush();
		os.close();
	}

	public void encodeImage(BufferedImage img) {
		// scale
		bimg = Scaler.scaleImage(img, 160, 200, true);

		// copy to int array
		pixels = new int[bimg.getWidth()][bimg.getHeight()];
		for (int x = 0; x < pixels.length; ++x) {
			for (int y = 0; y < pixels[0].length; ++y) {
				pixels[x][y] = bimg.getRGB(x, y);
			}
		}


		for (int i = 0; i < rounds; ++i) {
			determineScreenColor();
			determineBlockColors();
			createBitmap();
		}

		decodeBitmap();

	}
	
	
	public BufferedImage getEncodedImage() {
		return bimg;
	}
}
