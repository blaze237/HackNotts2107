package effects;

import util.Image;



public class Effect_Laplacian implements Effect
{

	private static final int[] LAPLACIAN = {0, 1, 0,
											1, -4, 1,
											0, 1, 0};

	private static final int LAPLACIAN_SIZE = 9;




	@Override
	public Image apply(Image img)
	{
		for(int r = 1; r < img.height - 1; r++)
		{
			for(int c = 1; c < img.width - 1; c++)
			{
				int count = 0;
				int val = 0;

				for (int dy = -1; dy <= 1; ++dy) {
					for (int dx = -1; dx <= 1; ++dx)
					{
						val += LAPLACIAN[count]*((img.pixels[c + dx][r + dy]) & 0xff);
						++count;
					}
				}

				val = Math.min(255, val);
				img.pixels[c][r] = (val<<16) | (val<<8) | val;
			}
		}

		return img;
	}

}
