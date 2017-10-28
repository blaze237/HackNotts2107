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
		int pixelCopy[][] = new int[img.width][img.height];


		for(int r = 1; r < img.height - 1; r++)
		{
			for(int c = 1; c < img.width - 1; c++)
			{
				int count = 0;

				int[] rgb = new int[Color.COUNT];

				for (int dy = -1; dy <= 1; ++dy)
				{
					for (int dx = -1; dx <= 1; ++dx)
					{
						for(int i = 0; i < Color.COUNT; i++)
							rgb[i] += LAPLACIAN[count]*Color.getColor(img.pixels[c + dx][r + dy], i);
						++count;
					}
				}

				for(int i = 0; i < Color.COUNT; i++)
				{
					rgb[i] = Math.min(255, rgb[i]);

				}

				pixelCopy[c][r] = Color.makeColor(rgb[Color.RED], rgb[Color.GREEN], rgb[Color.BLUE]);
			}
		}

		img.pixels = pixelCopy;
		return img;
	}

}
