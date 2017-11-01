package util;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class ScreenshotFileHandler {
	public static boolean saveImage(BufferedImage screenshot) {
		JFileChooser saveFile = new JFileChooser(System.getProperty("user.dir") + "/screenshots/");
        int ret = saveFile.showSaveDialog(null);
        if (ret != JFileChooser.APPROVE_OPTION)
        	return false;

        String filepath = saveFile.getSelectedFile().getPath() + ".png";

		try
		{
			ImageIO.write(screenshot, "png", new File(filepath));
			return true;
		}
		catch(EOFException error)
		{
			return false;
		}
		catch (Exception i)
		{
			i.printStackTrace();
			return false;
		}
	}
}
