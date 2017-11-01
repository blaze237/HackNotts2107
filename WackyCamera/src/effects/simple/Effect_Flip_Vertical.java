package effects.simple;

import effects.Effect;
import util.Image;

public class Effect_Flip_Vertical extends Effect
{
	/**
	 *
	 */
	private static final long serialVersionUID = -909206085958102985L;

	@Override
	public Image apply(Image img)
	{
		for(int r = 1; r < img.height/2; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				int tmp = img.pixels[c][r];

				img.pixels[c][r] = img.pixels[c][img.height - r];
				img.pixels[c][img.height - r] = tmp;
			}
		}

		return img;
	}
}
