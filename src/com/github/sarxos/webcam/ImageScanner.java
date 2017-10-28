package com.github.sarxos.webcam;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageScanner
{

	private static ImageScanner instance = null;

	private ImageScanner(){

	}

	public synchronized static ImageScanner getInstance()
	{
		if(instance == null)
			instance = new ImageScanner();
		return instance;
	}


	public BufferedImage readImg(String s)
	{
		File f = null;

		BufferedImage img = null;

		try
		{
			f = new File(s);
			img = ImageIO.read(f);
		}
		catch(IOException e)
		{
			System.out.println("Error" + e);

		}

		return img;
	}

	public int[][] getPixels(BufferedImage img)
	{
		int h = img.getHeight();
		int w = img.getWidth();

		int[][] pixels = new int[w][h];

		Raster rast = img.getData();

		for(int r = 0; r < h; r++)
		{
			for(int c = 0; c < w; c++)
			{
				pixels[c][r] = rast.getSample(c,r,0);
			}
		}

		return pixels;
	}




}
