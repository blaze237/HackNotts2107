package effects.simple;

import effects.Effect;
import util.Color;
import util.Image;

public class Effect_Abberation extends Effect
{
	/**
	 *
	 */
	private static final long serialVersionUID = -962229356025762746L;
	public static final boolean TWO_WAY_ABBERATION = true;
	public static final boolean ONE_WAY_ABBERATION = false;

	private int abberationH;
	private int abberationV;


	public Effect_Abberation(int abberation, boolean twoWay)
	{
		this.abberationH = abberation;

		if(twoWay = TWO_WAY_ABBERATION)
			abberationV = abberation;
		else
			abberationV = 0;

	}

	@Override
	public Image apply(Image img)
	{
		//Loop through image
		//Each pixel equals pixel + colour offsets

		int pixelCopy[][] = new int[img.width][img.height];


		for(int r = abberationV; r < img.height - abberationV - 1; r++)
		{
			for(int c = abberationH; c < img.width - abberationH - 1; c++)
			{

				int[] rgb = new int[Color.COUNT];

				//Shift RGB colour channels
				rgb[0] += Color.getColor(img.pixels[c -abberationH][r - abberationV], Color.RED);
				rgb[1] += Color.getColor(img.pixels[c ][r], Color.GREEN);
				rgb[2] += Color.getColor(img.pixels[c + abberationH][r - abberationV], Color.BLUE);

				pixelCopy[c][r] = Color.makeColor(rgb[Color.RED], rgb[Color.GREEN], rgb[Color.BLUE]);

			}
		}

		img.pixels = pixelCopy;
		return img;


	}



}
