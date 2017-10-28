package effects.simple;

import effects.Effect;
import util.Image;

public class Effect_Green implements Effect
{
	@Override
	public Image apply(Image img)
	{

		for(int r = 0; r < img.height; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				int p = img.pixels[c][r];

				int green = (p>>8) & 0xff;

			    img.pixels[c][r] =  0 | green << 8 | 0;

			}
		}

		return img;

	}
}
