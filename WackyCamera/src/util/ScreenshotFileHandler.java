package util;

import java.awt.image.BufferedImage;
import java.io.EOFException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class ScreenshotFileHandler {
	public static boolean saveImage(BufferedImage screenshot) {
		JFileChooser saveFile = new JFileChooser(System.getProperty("user.dir") + "/screenshots/");
        int ret = saveFile.showSaveDialog(null);
        if (ret != JFileChooser.APPROVE_OPTION)
        	return false;

        String fName = saveFile.getSelectedFile().getPath();

		try
		{
			ImageIO.write(screenshot, "png", saveFile.getSelectedFile());
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
