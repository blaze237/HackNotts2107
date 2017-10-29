package WarpApp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Image;

public class ImageScanner
{

	private static ImageScanner instance = null;

	//Private to ensure singletons only
	private ImageScanner(){

	}

	public synchronized static ImageScanner getInstance()
	{
		if(instance == null)
			instance = new ImageScanner();
		return instance;
	}



	public Image getImage(BufferedImage img)
	{
		int h = img.getHeight();
		int w = img.getWidth();

		int[][] pixels = new int[w][h];

		Raster rast = img.getData();

		for(int r = 0; r < h; r++)
		{
			for(int c = 1; c <w; c++)
			{
				pixels[w-c][r] = (rast.getSample(c,r,0) << 16) | (rast.getSample(c,r,1) << 8) | rast.getSample(c,r,2);
			}
		}
		return new Image(pixels,w,h);
	}

}
