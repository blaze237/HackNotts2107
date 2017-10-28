package WarpApp;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import effects.Blue;
import effects.Grayscale;
import effects.Red;
import effects.Sepia;

public class ImageWarper  implements WebcamImageTransformer {


	ImageScanner scanner;
	Sepia greyEffect;

	public ImageWarper(ImageScanner scanner) {

		this.scanner = scanner;
		greyEffect = new Sepia();

	}

	@Override
	public BufferedImage transform(BufferedImage img)
	{

		return( toBuffImg(greyEffect.apply(scanner.getPixels(img))));


		//return img;
		//return Sepia(img);
	}





	private BufferedImage toBuffImg(Image img)
	{
		BufferedImage image = new BufferedImage(img.width,img.height, BufferedImage.TYPE_INT_RGB);

		for(int r = 0; r < img.height; r++)
		{
			for(int c = 0; c < img.width; c++)
			{
				image.setRGB(c, r, img.pixels[c][r]);
			}
		}

		return image;

	}











}
