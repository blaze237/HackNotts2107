package WarpApp;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import WarpApp.ImageWarper;
import effects.warps.CircleWarp;
import effects.warps.Effect_Warp;
import effects.warps.WarpModifier;

public class Main {

	public static void main(String[] args)
	{
		ImageScanner scanner = ImageScanner.getInstance();

		ImageWarper warper = new ImageWarper(scanner);

		Dimension size = WebcamResolution.VGA.getSize();
		WarpModifier mod = new CircleWarp(size.width / 2, size.height / 2, Math.min(size.width, size.height) / 2.01);
		warper.addEffect(new Effect_Warp(mod));

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
	}

}
