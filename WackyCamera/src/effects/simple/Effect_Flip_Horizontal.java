package effects.simple;

import effects.Effect;
import util.Image;

public class Effect_Flip_Horizontal extends Effect
{

	/**
	 *
	 */
	private static final long serialVersionUID = -4480875971617719821L;

	@Override
	public Image apply(Image img)
	{
		for(int r = 0; r < img.height; r++)
		{
			for(int c = 1; c < img.width / 2; c++)
			{
				int tmp = img.pixels[c][r];

				img.pixels[c][r] = img.pixels[img.width - c][r];
				img.pixels[img.width - c][r] = tmp;
			}
		}

		return img;
	}
}
