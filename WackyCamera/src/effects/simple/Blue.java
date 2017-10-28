package effects.simple;

import effects.Effect;
import util.Image;

public class Blue implements Effect
{
	@Override
	public Image apply(Image img)
	{

		for(int r = 0; r < img.height; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				int p = img.pixels[c][r];
			    int blue = p & 0xff;

			    float boost = 1.3f;

			    blue = (int) Math.min(255, blue*boost);

			    img.pixels[c][r] =  0 | 0 | blue;

			}
		}

		return img;

	}
}
