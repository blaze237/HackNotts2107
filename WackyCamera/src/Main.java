import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args)
	{
		ImageScanner scanner = ImageScanner.getInstance();

		BufferedImage img = scanner.readImg("test.jpg");
		int width = img.getWidth();
		int height = img.getHeight();

		int[][] pixels = scanner.getPixels(img);


		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				int p = pixels[c][r];

				//get red
			    int red = (p>>16) & 0xff;

			    //get green
			    int green = (p>>8) & 0xff;

			    //get blue
			    int blue = p & 0xff;

			    int avg = (red+green+blue)/3;

			    p = (255<<24) | (avg<<16) | (avg<<8) | avg;


			    img.setRGB(c, r, p);



			}
		}

		try {
		    // retrieve image
		    File outputfile = new File("saved.png");
		    ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {

		}

	}

}
