package effects.simple;
import effects.Effect;
import util.Image;

public class Effect_Negative extends Effect
{

	/**
	 *
	 */
	private static final long serialVersionUID = 7789060934684290315L;

	@Override
	public Image apply(Image img)
	{
		for(int r = 0; r < img.height; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				int p = img.pixels[c][r];

			    int red =  255 - ((p>>16) & 0xff);
			    int green = 255 - ((p>>8) & 0xff);
			    int blue = 255 - (p & 0xff);


			    img.pixels[c][r] = (red<<16) | (green<<8) | blue;

			}
		}

		return img;

	}

}
