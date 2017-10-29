package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import WarpApp.ImageScanner;
import WarpApp.ImageWarper;
import effects.Effect;
import effects.Effect_Laplacian;
import effects.simple.Effect_Abberation;
import effects.simple.Effect_Grayscale;
import effects.simple.Effect_Step;
import effects.warps.CircleWarp;
import effects.warps.Effect_Warp;
import effects.warps.Mirror_Modifier;
import effects.warps.Swirl_Modifier;
import effects.warps.Tunnel_Modifier;
import util.Point;
import util.ScreenshotFileHandler;

public class View {

	private String effectOptions[] = {"Blur", "Wobble", "Dog"};

	private JComboBox effects;
	private String currentEffect;
	private JButton saveImage;
	//private ImagePanel feed;

	private Boolean isFeedLoaded = false;

	private BufferedImage currentFrame;

	private int feedWidth = 800;
	private int feedHeight = 800;




	public View() {}

	public void InitGui() {

		ImageScanner scanner = ImageScanner.getInstance();

		Dimension camSize = WebcamResolution.VGA.getSize();
		int cx = camSize.width / 2;
		int cy = camSize.height / 2;
		int minSize = Math.min(camSize.width, camSize.height);

		ImageWarper warper = new ImageWarper(scanner);



		warper.addEffect(new Effect_Warp( new Swirl_Modifier(cx,cy, minSize / 5, 6)));




		//warper.addEffect(new Effect_Warp(new Mirror_Modifier(camSize.width / 2, Mirror_Modifier.VERTICAL)));
		//warper.addEffect(new Effect_Warp(new Tunnel_Modifier(camSize.width / 2, camSize.height / 2, Math.min(camSize.width, camSize.height) / 5)));

		//warper.addEffect(new Effect_Abberation(5,Effect_Abberation.ONE_WAY_ABBERATION));

		//warper.saveEffects("newSave.ser");

		//warper.loadEffects("newSave.ser");




		//warper.addEffect(new Effect_Warp(new CircleWarp(camSize.width / 2, camSize.height / 2, Math.min(camSize.width, camSize.height) / 2.1)));
		//warper.addEffect(new Effect_Blur());




		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.setImageTransformer(warper);
		webcam.open();



		JFrame window = new JFrame("Warpy");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		String[] effectsList = effectOptions;

		effects = new JComboBox<>(effectsList);
		currentEffect = effectOptions[0];
		effects.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					onEffectSelected((String)effects.getSelectedItem());
				}
			}
		});


		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setFillArea(true);

		window.add(panel);


		saveImage = new JButton("Take Screenshot");
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTakeScreenshot();
			}
		});

		toolbar.add(effects);
		toolbar.add(saveImage);
		window.add(toolbar, BorderLayout.NORTH);
		//window.add(feed, BorderLayout.CENTER);

		window.pack();
		window.setVisible(true);
	}

	protected void onEffectSelected(String newSelection) {
		if (newSelection == currentEffect)
			return;

		for (int i = 0; i < effectOptions.length - 1; ++i) {
			if (effectOptions[i] == newSelection) {
				setEffect();
				break;
			}
		}
	}

	private void setEffect() {

	}

	private void onTakeScreenshot() {
		JDialog dialog = new JDialog();
		dialog.setResizable(false);

		JPanel masterLayout = new JPanel();
		masterLayout.setLayout(new BorderLayout());

		JPanel optionsLayout = new JPanel();
		optionsLayout.setLayout(new FlowLayout());

		JButton saveScreenshot = new JButton("Save");
		saveScreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenshotFileHandler.saveImage(currentFrame);
				dialog.dispose();
			}
		});
		JButton dismiss = new JButton("Close");
		dismiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		optionsLayout.add(saveScreenshot);
		optionsLayout.add(dismiss);

		JLabel label = new JLabel( new ImageIcon(currentFrame.getScaledInstance((int)(feedWidth*0.75), (int)(feedHeight*0.75), Image.SCALE_FAST)));

		masterLayout.add(label, BorderLayout.CENTER);
		masterLayout.add(optionsLayout, BorderLayout.SOUTH);
		dialog.add(masterLayout);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setLocation(dim.width/4, dim.height/4);
		dialog.pack();
		dialog.setVisible(true);
	}

	public void updateFeed(BufferedImage frame) {
		currentFrame = frame;
	}

}
