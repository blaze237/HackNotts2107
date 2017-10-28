package effects;

import WarpApp.Image;

public class Effect_Grayscale implements Effect
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
			    int green = (p>>8) & 0xff;
			    int blue = p & 0xff;
			    int avg = (red+green+blue)/3;

			    img.pixels[c][r] = (255<<24) | (avg<<16) | (avg<<8) | avg;

			}
		}

		return img;

	}

}
