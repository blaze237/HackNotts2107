package WarpApp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import WarpApp.ImageWarper;
import modifiers.CircleModifier;
import modifiers.Modifier;
import modifiers.TestModifier;

public class Main {



	public static void main(String[] args)
	{
		ImageScanner scanner = ImageScanner.getInstance();

		ImageWarper warper = new ImageWarper(scanner);

		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.setImageTransformer(warper);
		webcam.open();

		JFrame window = new JFrame("Test Transformer");

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setFillArea(true);

		window.add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





//		ImageScanner scanner = ImageScanner.getInstance();
//
//		BufferedImage img = scanner.readImg("test.jpg");
//		int width = img.getWidth();
//		int height = img.getHeight();
//
//		int[][] pixels = scanner.getPixels(img);
//
//
//		for(int r = 0; r < height; r++)
//		{
//			for(int c = 0; c < width; c++)
//			{
//				int p = pixels[c][r];
//
//				//get red
//			    int red = (p>>16) & 0xff;
//
//			    //get green
//			    int green = (p>>8) & 0xff;
//
//			    //get blue
//			    int blue = p & 0xff;
//
//			    //Get greyscale
//			    int avg = (red+green+blue)/3;
//
//			    p = (255<<24) | (avg<<16) | (avg<<8) | avg;
//
//			    img.setRGB(c, r, p);
//
//			}
//		}
//
//		//Apply the modifier
//		Modifier mod = new CircleModifier(width / 2, height / 2, Math.min(width, height) / 5);
//		pixels = Interpolate.interpolate(pixels, width, height, mod);
//
//		//Update the image
//		for (int y = 0; y < height; ++y)
//			for (int x = 0; x < width; ++x)
//				img.setRGB(x, y, pixels[x][y]);
//
//		try {
//		    // retrieve image
//		    File outputfile = new File("saved.png");
//		    ImageIO.write(img, "png", outputfile);
//		} catch (IOException e) {
//
//		}
//
	}

}
