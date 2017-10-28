package WarpApp;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import WarpApp.ImageWarper;

import effects.Effect_Blur;
import effects.warps.CircleWarp;
import effects.warps.Effect_Warp;
import effects.warps.WarpModifier;
import effects.Effect_Blur;
import effects.simple.Effect_Blue;
import effects.simple.Effect_Grayscale;
import effects.simple.Effect_Green;
import effects.simple.Effect_Red;
import effects.simple.Effect_Sepia;
import effects.simple.Effect_Step;

public class Main {

	public static void main(String[] args)
	{
		ImageScanner scanner = ImageScanner.getInstance();

		Dimension camSize = WebcamResolution.VGA.getSize();

		ImageWarper warper = new ImageWarper(scanner);


		warper.addEffect(new Effect_Step(64));
		//warper.addEffect(new Effect_Warp(new CircleWarp(camSize.width / 2, camSize.height / 2, Math.min(camSize.width, camSize.height) / 2.1)));
		//warper.addEffect(new Effect_Blur());

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
