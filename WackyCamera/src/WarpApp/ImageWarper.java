package WarpApp;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class ImageWarper  implements WebcamImageTransformer {


	ImageScanner scanner;

	public ImageWarper(ImageScanner scanner) {

		this.scanner = scanner;

	}

	@Override
	public BufferedImage transform(BufferedImage img)
	{

		return img;
		//return Sepia(img);
	}







//	private BufferedImage toGrayscale(BufferedImage img)
//	{
//		int width = img.getWidth();
//		int height = img.getHeight();
//		int[][] pixels = scanner.getPixels(img);
//
//		for(int r = 0; r < height; r++)
//		{
//			for(int c = 0; c < width; c++)
//			{
//				int p = pixels[c][r];
//
//			    int red = (p>>16) & 0xff;
//			    int green = (p>>8) & 0xff;
//			    int blue = p & 0xff;
//			    int avg = (red+green+blue)/3;
//
//			    p = (255<<24) | (avg<<16) | (avg<<8) | avg;
//
//			    img.setRGB(c, r, p);
//
//			}
//		}
//
//		return img;
//	}
//
//
//	private BufferedImage Sepia(BufferedImage img)
//	{
//		int width = img.getWidth();
//		int height = img.getHeight();
//		int[][] pixels = scanner.getPixels(img);
//
//		for(int r = 0; r < height; r++)
//		{
//			for(int c = 0; c < width; c++)
//			{
//				int p = pixels[c][r];
//
//			    int red = (p>>16) & 0xff;
//			    int green = (p>>8) & 0xff;
//			    int blue = p & 0xff;
//
//
//			    int red2 = (int) ((red * .393) + (green *.769) + (blue * .189));
//			    int green2 = (int) ((red * .349) + (green *.686) + (blue * .168));
//			    int blue2 =  (int) ((red * .272) + (green *.534) + (blue * .131));
//
//			    float boost = 1.5f;
//
//			    red2 = (int) Math.min(255, red2*boost);
//			    green2 = (int) Math.min(255, green2*boost);
//			    blue2 = (int)Math.min(255, blue2*boost);
//
//			    p =  p & 0xff000000 | (red2<<16) | (green2<<8) | blue2;
//
//			    img.setRGB(c, r, p);
//
//			}
//		}
//
//		return img;
//	}

}
