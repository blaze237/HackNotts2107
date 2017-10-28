import modifiers.Modifier;
import util.Point;

public class Interpolate {
	static int[][] interpolate(int[][] pixels, int width, int height, Modifier mod) {
		//Define new pixel array
		int[][] newPixels = new int[width][height];

		//Loop through and apply the mod to every pixel
		for (int y = 0; y < height; ++y) {
			for (int x = 0 ; x < width; ++x) {
				//Map the point
				Point p = mod.translate(x, y);

				//Get bounds
				int lbx = (int) Math.floor(p.x);
				int lby = (int) Math.floor(p.y);
				int ubx = (int) Math.ceil(p.x);
				int uby = (int) Math.ceil(p.y);

				//Get greyscale colours
			    double ltc = getColor(pixels, lbx, lby);
			    double rtc = getColor(pixels, ubx, lby);
			    double lbc = getColor(pixels, lbx, uby);
			    double rbc = getColor(pixels, ubx, uby);

			    //Get Co-ord offset
			    double dx = p.x - Math.floor(p.x);
			    double dy = p.y - Math.floor(p.y);

			    //Interp colours
			    double x1 = ltc + (rtc - ltc) * dx;
			    double x2 = lbc + (rbc - lbc) * dx;
			    int color = (int) (x1 + (x2 - x1) * dy);
			    newPixels[x][y] = (0xff << 24) | (color << 16) | (color << 8) | color;
			}
		}

		return newPixels;
	}

	private static int getColor(int[][] pixels, int x, int y) { return pixels[x][y] & 0xff; }
}
