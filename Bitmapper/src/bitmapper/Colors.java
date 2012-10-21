/*
 */
package bitmapper;

/**
 *
 * @author merten
 */
public class Colors {
	
	public static final int[] COLORS = {
		0x000000,
		0xffffff,
		0x894036,
		0x7abfc7,
		0x8a46ae,
		0x68a941,
		0x3e31a2,
		0xd0dc71,
		0x905f25,
		0x5c4700,
		0xbb776d,
		0x555555,
		0x808080,
		0xacea88,
		0x7c70da,
		0xababab
	};
	
	
	public static short getBestMatch(int color) {
		short result = 0;
		double error = Double.MAX_VALUE;
		for(short i = 0; i < COLORS.length; ++i) {
			int viccolor = COLORS[i];
			
			double error2 = getDistance(color, viccolor);
			if(error2 < error) {
				error = error2;
				result = i;
			}
		}
		
		return result;
	}
	
	
	public static int getRed(int color) {
		return ((color & 0xff0000) >> 16);
	}
	
	public static int getGreen(int color) {
		return ((color & 0x00ff00) >> 8);
	}
	
	public static int getBlue(int color) {
		return (color & 0x0000ff);
	}
	
	public static int[] getResidue(int target, int color) {
		int r1 = getRed(target);
		int g1 = getGreen(target);
		int b1 = getBlue(target);
		
		int r2 = getRed(color);
		int g2 = getGreen(color);
		int b2 = getBlue(color);
		
		return new int[]{r1 - r2, g1 - g2, b1 - b2};
	}
	
	
	public static int applyResidue(int[] residue, int color, double scale) {
		int r = getRed(color);
		int g = getGreen(color);
		int b = getBlue(color);
		
		r += residue[0] * scale;
		g += residue[1] * scale;
		b += residue[2] * scale;
		
		r = Math.min(255, Math.max(0, r));
		g = Math.min(255, Math.max(0, g));
		b = Math.min(255, Math.max(0, b));
		
		return (r << 16) | (g << 8) | b;
	}
	
	
	
	public static double getDistance(int color1, int color2) {
		double r1 = getRed(color1);
		double r2 = getRed(color2);
		double g1 = getGreen(color1);
		double g2 = getGreen(color2);
		double b1 = getBlue(color1);
		double b2 = getBlue(color2);
		
		double y1 = 0.299 * r1 + 0.587 * g1 + 0.114 * b1;
		double u1 = (b1 - y1) * 0.493;
		double v1 = (r1 - y1) * 0.877;
		
		double y2 = 0.299 * r2 + 0.587 * g2 + 0.114 * b2;
		double u2 = (b2 - y2) * 0.493;
		double v2 = (r2 - y2) * 0.877;
		
		double error = 0;
		
		error += (y1 - y2) * (y1 - y2);
		error += (u1 - u2) * (u1 - u2);
		error += (v1 - v2) * (v1 - v2);

		
		return error;
	}
	
}
