package effects;

import util.Color;
import util.Image;



public class Effect_Laplacian implements Effect
{

	private static final int[] LAPLACIAN = {-1, -1, -1,
											-1, 9, -1,
											-1, -1, -1};

	private static final int LAPLACIAN_SIZE = 9;




	@Override
	public Image apply(Image img)
	{
		int pixelCopy[][] = img.pixels;

		for(int r = 1; r < img.height - 1; r++)
		{
			for(int c = 1; c < img.width - 1; c++)
			{
				int count = 0;
				int val = 0;

				for (int dy = -1; dy <= 1; ++dy) {
					for (int dx = -1; dx <= 1; ++dx)
					{
						int colour = ((pixelCopy[c + dx][r + dy]) & 0xff);
						colour = (colour/128)*128;
						val += LAPLACIAN[count]*colour;
						++count;
					}
				}

				val = Math.min(255, val);
				img.pixels[c][r] = Color.makeColor(val, val, val);
			}
		}

		return img;
	}

}
