package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ScreenshotFileHandler {
	public static boolean saveImage(BufferedImage screenshot) {
		Random r = new Random(System.currentTimeMillis());
		int fileNum = r.nextInt(100000);
		try {
			File f = new File("Screenshots");
			if (!f.exists())
				f.mkdir();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try{
			File out = new File("Screenshots/Sc_" + fileNum + ".png");
			if (out.exists())
				out = new File("Screenshots/Sc_" + r.nextInt(100000) + ".png");
			ImageIO.write(screenshot, "png", out);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
