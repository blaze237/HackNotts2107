package effects.simple;

import effects.Effect;
import util.Color;
import util.Image;

public class Effect_Threshold extends Effect
{

	private double thresh;

	public Effect_Threshold(double thresh)
	{
		this.thresh = Math.max(Math.min(1, thresh),0);
	}

	@Override
	public Image apply(Image img)
	{
		//Find max value
		int maxRGB[] = new int[Color.COUNT];

		for(int r = 0; r < img.height ; r++)
		{
			for(int c = 0; c < img.width ; c++)
			{
				for(int i = 0; i < Color.COUNT; ++i)
				{
					maxRGB[i] = Math.max(maxRGB[i], Color.getColor(img.pixels[c][r],i));
				}
			}
		}



		//Apply bias
		for(int r = 0; r < img.height ; r++)
		{
			for(int c = 0; c < img.width ; c++)
			{
				for(int i = 0; i < Color.COUNT; ++i)
				{
					boolean b = Color.getColor(img.pixels[c][r], i) < (maxRGB[i] * thresh);
					img.pixels[c][r] = Color.setColor(img.pixels[c][r], i, (b ? 0 : 255));
				}
			}
		}

		return img;


	}




}
