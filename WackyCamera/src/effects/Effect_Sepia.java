package effects;

import WarpApp.Image;

public class Effect_Sepia implements Effect
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


			    int red2 = Math.min(255, (int) ((red * .393) + (green *.769) + (blue * .189)));
			    int green2 = Math.min(255, (int) ((red * .349) + (green *.686) + (blue * .168)));
			    int blue2 =  Math.min(255, (int) ((red * .272) + (green *.534) + (blue * .131)));



			    img.pixels[c][r] =  (red2<<16) | (green2<<8) | blue2;
			}
		}

		return img;
	}

}
