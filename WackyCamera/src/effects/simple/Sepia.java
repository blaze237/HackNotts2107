package effects.simple;

import effects.Effect;
import util.Image;

public class Sepia implements Effect
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


			    int red2 = (int) ((red * .393) + (green *.769) + (blue * .189));
			    int green2 = (int) ((red * .349) + (green *.686) + (blue * .168));
			    int blue2 =  (int) ((red * .272) + (green *.534) + (blue * .131));

			    float boost = 1.5f;

			    red2 = (int) Math.min(255, red2*boost);
			    green2 = (int) Math.min(255, green2*boost);
			    blue2 = (int)Math.min(255, blue2*boost);

			    img.pixels[c][r] =  (red2<<16) | (green2<<8) | blue2;
			}
		}

		return img;
	}

}
