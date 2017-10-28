package effects.warps;
import effects.Effect;
import util.Image;
import util.Point;

public class Effect_Warp implements Effect {

	private WarpModifier mod;

	private static final int COLOR_RED = 0;
	private static final int COLOR_GREEN = 1;
	private static final int COLOR_BLUE = 2;
	private static final int COLOR_COUNT = 3;

	private static final int[] COLOR_OFFSET = { 16, 8, 0 };
	private static final int COLOR_MASK = 0xFF;

	public Effect_Warp(WarpModifier mod) {
		this.mod = mod;
	}

	@Override
	public Image apply(Image img) {
		//Define new pixel array
		int[][] newPixels = new int[img.width][img.height];

		//Loop through and apply the mod to every pixel
		for (int y = 0; y < img.height; ++y) {
			for (int x = 0 ; x < img.width; ++x) {
				//Map the point
				Point p = mod.translate(x, y);

				//Get bounds
				int lbx = (int) Math.floor(p.x);
				int lby = (int) Math.floor(p.y);
				int ubx = (int) Math.ceil(p.x);
				int uby = (int) Math.ceil(p.y);

				//Interpolate for every color
				for (int colorIndex = 0; colorIndex < COLOR_COUNT; ++colorIndex) {
					//Get greyscale colours
				    double ltc = getColor(img.pixels, lbx, lby, colorIndex);
				    double rtc = getColor(img.pixels, ubx, lby, colorIndex);
				    double lbc = getColor(img.pixels, lbx, uby, colorIndex);
				    double rbc = getColor(img.pixels, ubx, uby, colorIndex);

				    //Get Co-ord offset
				    double dx = p.x - Math.floor(p.x);
				    double dy = p.y - Math.floor(p.y);

				    //Interp colours
				    double x1 = ltc + (rtc - ltc) * dx;
				    double x2 = lbc + (rbc - lbc) * dx;
				    int color = (int) (x1 + (x2 - x1) * dy);
				    newPixels[x][y] |= (color & COLOR_MASK) << COLOR_OFFSET[colorIndex];
				}

			}
		}

		//Override old pixel array with new pixel array
		img.pixels = newPixels;

		//Return modified image
		return img;
	}

	private static int getColor(int[][] pixels, int x, int y, int colorIndex) { return (pixels[x][y] >> COLOR_OFFSET[colorIndex]) & COLOR_MASK; }

}
