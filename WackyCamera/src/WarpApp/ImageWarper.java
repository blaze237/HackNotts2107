package WarpApp;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.github.sarxos.webcam.WebcamImageTransformer;

import effects.Effect;
import util.Image;

public class ImageWarper  implements WebcamImageTransformer {

	//Store instance of the scanner used to read from the webcam
	ImageScanner scanner;

	//Store a list of effects
	ArrayList<Effect> effects;

	public ImageWarper(ImageScanner scanner) {
		this.scanner = scanner;
		this.effects = new ArrayList<>();
	}

	@Override
	public BufferedImage transform(BufferedImage img)
	{
		//Get image in our format
		Image image = scanner.getPixels(img);

		//Apply all effects to the image
		for (Effect e : effects)
		{
			image = e.apply(image);
		}

		//Return their format
		return toBuffImg(image);
	}

	public void addEffect(Effect e) {
		effects.add(e);
	}

	public void removeEffect(Effect e) {
		effects.remove(e);
	}

	private static BufferedImage toBuffImg(Image img)
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
