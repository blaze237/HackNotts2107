package effects;

import WarpApp.Image;

public class Effect_Blue implements Effect
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



			    img.pixels[c][r] =  0 | 0 | blue;

			}
		}

		return img;

	}
}
