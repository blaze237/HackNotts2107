package effects.warps;
import effects.Effect;
import util.Color;
import util.Image;
import util.Point;

public class Effect_Warp extends Effect {

	/**
	 *
	 */
	private static final long serialVersionUID = -1509928892012762028L;
	private WarpModifier mod;

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
				for (int colorIndex = 0; colorIndex < Color.COUNT; ++colorIndex) {
					//Get greyscale colours
				    double ltc = Color.getColor(img.pixels[lbx][lby], colorIndex);
				    double rtc = Color.getColor(img.pixels[ubx][lby], colorIndex);
				    double lbc = Color.getColor(img.pixels[lbx][uby], colorIndex);
				    double rbc = Color.getColor(img.pixels[ubx][uby], colorIndex);

				    //Get Co-ord offset
				    double dx = p.x - Math.floor(p.x);
				    double dy = p.y - Math.floor(p.y);

				    //Interp colours
				    double x1 = ltc + (rtc - ltc) * dx;
				    double x2 = lbc + (rbc - lbc) * dx;
				    int color = (int) (x1 + (x2 - x1) * dy);
				    newPixels[x][y] = Color.setColor(newPixels[x][y], colorIndex, color);
				}

			}
		}

		//Override old pixel array with new pixel array
		img.pixels = newPixels;

		//Return modified image
		return img;
	}
}
