package effects;

import util.Image;

public class Effect_Blur implements Effect
{

	private static final double[] GAUSSIAN = {

0.036894,	0.039167,	0.039956,	0.039167,	0.036894,
0.039167,	0.041581,	0.042418,	0.041581,	0.039167,
0.039956,	0.042418,	0.043272,	0.042418,	0.039956,
0.039167,	0.041581,	0.042418,	0.041581,	0.039167,
0.036894,	0.039167,	0.039956,	0.039167,	0.036894};

	private static final int GAUSSIAN_DIAMETER = (int) Math.sqrt(GAUSSIAN.length);
	private static final int GAUSSIAN_RADIUS = (GAUSSIAN_DIAMETER - 1) / 2;

//	private static final double[] GAUSSIAN = {
//		0.111018,	0.111157,	0.111018,
//		0.111157,	0.111296,	0.111157,
//		0.111018,	0.111157,	0.111018};
//
//




	@Override
	public Image apply(Image img)
	{


		for(int r = 2; r < img.height - 2; r++)
		{
			for(int c = 2; c < img.width - 2; c++)
			{

				int window[] = new int[GAUSSIAN.length];

				int count = 0;


				for (int dy = -GAUSSIAN_RADIUS; dy <= GAUSSIAN_RADIUS; ++dy) {
					for (int dx = -GAUSSIAN_RADIUS; dx <= GAUSSIAN_RADIUS; ++dx) {
						window[count++] = img.pixels[c + dx][r + dy];
					}
				}




				int rgb[] = new int[3];

				for(int offset = 16; offset >=0; offset -=8)
				{
					for(int p = 0; p < GAUSSIAN.length; p++)
					{
						rgb[(16-offset)/8] += (int)(GAUSSIAN[p]*((window[p] >> offset) & 0xff));
					}
					rgb[(16-offset)/8] = Math.min(255, rgb[(16-offset)/8]);
				}



				img.pixels[c][r] =  (rgb[0]<<16) | (rgb[1]<<8) | rgb[2];

			}
		}

//		for(int r = 1; r < img.height - 1; r++)
//		{
//			for(int c = 1; c < img.width - 1; c++)
//			{
//
//				int window[] = new int[9];
//
//				window[0] = img.pixels[c-1][r-1];
//				window[1] = img.pixels[c][r-1];
//				window[2] = img.pixels[c+1][r-1];
//				window[3] = img.pixels[c-1][r];
//				window[4] = img.pixels[c][r];
//				window[5] = img.pixels[c+1][r];
//				window[6] = img.pixels[c-1][r+1];
//				window[7] = img.pixels[c][r+1];
//				window[8] = img.pixels[c+1][r+1];
//
//
//				int rgb[] = new int[3];
//
//				for(int offset = 16; offset >=0; offset -=8)
//				{
//					for(int p = 0; p < 9; p++)
//					{
//						rgb[(16-offset)/8] += (int)(GAUSSIAN[p]*((window[p] >> offset) & 0xff));
//					}
//					rgb[(16-offset)/8] = Math.min(255, rgb[(16-offset)/8]);
//				}
//
//
//
//				img.pixels[c][r] =  (rgb[0]<<16) | (rgb[1]<<8) | rgb[2];
//
//			}
//		}


		return img;
	}



}
