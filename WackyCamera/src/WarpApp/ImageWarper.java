package WarpApp;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

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
		Image image = scanner.getImage(img);

		//No problem if concurrent mod
		try {
			//Apply all effects to the image
			for (Effect e : effects)
			{
				image = e.apply(image);
			}
		} catch (ConcurrentModificationException e) {
			return toBuffImg(image);
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

	public void loadEffects(String fName)
	{
		Effect e = null;

		effects.clear();

		try
		{
			FileInputStream fileIn = new FileInputStream(fName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while(true)
			{
				e = (Effect) in.readObject();
				if(e == null)
				break;
				addEffect(e);
			}

			in.close();
			fileIn.close();
		}
		catch(EOFException error)
		{
			return;
		}
		catch (Exception i)
		{
			i.printStackTrace();
			return;
		}
	}

	public void saveEffects(String fName)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(fName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (Effect e : effects)
				out.writeObject(e);
	        out.close();
	        fileOut.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
