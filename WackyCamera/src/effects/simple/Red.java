package effects.simple;

import effects.Effect;
import util.Image;

public class Red implements Effect
{

	@Override
	public Image apply(Image img)
	{

		for(int r = 0; r < img.height; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				int p = img.pixels[c][r];

			    int red = (p>>16) & 0xff;


			    img.pixels[c][r] =  (red<<16) | 0 | 0;

			}
		}

		return img;

	}
}
