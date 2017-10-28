package effects;

import util.Color;
import util.Image;

public class Effect_Abberation implements Effect
{

	private int abberation = 15;



	@Override
	public Image apply(Image img)
	{
		//Loop through image
		//Each pixel equals pixel + colour offsets

		int pixelCopy[][] = new int[img.width][img.height];


		for(int r = abberation; r < img.height - abberation - 1; r++)
		{
			for(int c = abberation; c < img.width - abberation - 1; c++)
			{

				int[] rgb = new int[Color.COUNT];


				rgb[0] += Color.getColor(img.pixels[c -abberation][r], Color.RED);
				rgb[1] += Color.getColor(img.pixels[c ][r], Color.GREEN);
				rgb[2] += Color.getColor(img.pixels[c + abberation][r], Color.BLUE);

				pixelCopy[c][r] = Color.makeColor(rgb[Color.RED], rgb[Color.GREEN], rgb[Color.BLUE]);

			}
		}

		img.pixels = pixelCopy;
		return img;


	}



}
