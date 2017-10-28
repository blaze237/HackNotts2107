package effects;

import util.Color;
import util.Image;

public class Effect_Blur implements Effect
{

	private static final double[] GAUSSIAN = {
			0.020407, 0.020408, 0.020408, 0.020408, 0.020408, 0.020408, 0.020407,
			0.020408, 0.020408, 0.020409, 0.020409, 0.020409, 0.020408, 0.020408,
			0.020408, 0.020409, 0.020409, 0.020409, 0.020409, 0.020409, 0.020408,
			0.020408, 0.020409, 0.020409, 0.020409, 0.020409, 0.020409, 0.020408,
			0.020408, 0.020409, 0.020409, 0.020409, 0.020409, 0.020409, 0.020408,
			0.020408, 0.020408, 0.020409, 0.020409, 0.020409, 0.020408, 0.020408,
			0.020407, 0.020408, 0.020408, 0.020408, 0.020408, 0.020408, 0.020407 };

	private static final int GAUSSIAN_DIAMETER = (int) Math.sqrt(GAUSSIAN.length);
	private static final int GAUSSIAN_RADIUS = (GAUSSIAN_DIAMETER - 1) / 2;

//	private static final double[] GAUSSIAN = {
//		0.111018,	0.111157,	0.111018,
//		0.111157,	0.111296,	0.111157,
//		0.111018,	0.111157,	0.111018};
//
//




	@Override
	public Image apply(Image img)
	{
		//Create temp pixel array
		int window[] = new int[GAUSSIAN.length];
		int[][] newPixels = new int[img.width][img.height];

		//Loop through all pixels in image
		for(int r = GAUSSIAN_RADIUS; r < img.height - GAUSSIAN_RADIUS; r++) {
			for(int c = GAUSSIAN_RADIUS; c < img.width - GAUSSIAN_RADIUS; c++) {

				//Assign window array
				int count = 0;
				for (int dy = -GAUSSIAN_RADIUS; dy <= GAUSSIAN_RADIUS; ++dy)
					for (int dx = -GAUSSIAN_RADIUS; dx <= GAUSSIAN_RADIUS; ++dx)
						window[count++] = img.pixels[c + dx][r + dy];

				//Get averages over gaussian area
				double[] rgb = new double[Color.COUNT];
				for (int p = 0; p < GAUSSIAN.length; ++p)
					for (int colorIndex = 0; colorIndex < Color.COUNT; ++colorIndex)
						rgb[colorIndex] += (GAUSSIAN[p] * Color.getColor(window[p], colorIndex));

				//Assign color
				newPixels[c][r] = Color.makeColor((int)rgb[Color.RED], (int)rgb[Color.GREEN], (int)rgb[Color.BLUE]);
			}
		}
		img.pixels = newPixels;
		return img;
	}



}
