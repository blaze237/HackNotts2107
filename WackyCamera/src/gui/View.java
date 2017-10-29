package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import WarpApp.ImageScanner;
import WarpApp.ImageWarper;
import effects.simple.Effect_Negative;
import effects.warps.Effect_Warp;
import effects.warps.Tunnel_Modifier;
import util.EffectPair;
import util.ScreenshotFileHandler;

public class View {

	private String currentEffect;
	private JButton saveImage;

	private Boolean isFeedLoaded = false;

	private Webcam webcam;
	private BufferedImage currentFrame;

	private int feedWidth = 800;
	private int feedHeight = 800;

	private List<EffectPair> activeEffectsList;

	public View() {}

	public void InitGui() {

		ImageScanner scanner = ImageScanner.getInstance();

		Dimension camSize = WebcamResolution.VGA.getSize();

		ImageWarper warper = new ImageWarper(scanner);

		warper.addEffect(new Effect_Negative());
		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.setImageTransformer(warper);
		webcam.open();

		JFrame window = new JFrame("Warpy");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		JPanel effects = new JPanel();
		effects.setLayout(new BorderLayout());


		activeEffectsList = new ArrayList<EffectPair>();
		JPanel activeEffects = new JPanel();
		activeEffects.setLayout(new BoxLayout(activeEffects, BoxLayout.Y_AXIS));
		JScrollPane activeEffectsContainer = new JScrollPane(activeEffects, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel addEffect = new JPanel(new FlowLayout());

		JComboBox selectEffects = new JComboBox(getEffects());

		JButton addEffectButton = new JButton("+");
		addEffectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = selectEffects.getSelectedItem().toString();
				activeEffectsList.add(new EffectPair(tag, null));
				activeEffects.add(new ActiveEffect(tag, activeEffects, activeEffectsList));
				window.pack();
			}
		});

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setFillArea(true);



		saveImage = new JButton("Take Screenshot");
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTakeScreenshot();
			}
		});

		addEffect.add(selectEffects);
		addEffect.add(addEffectButton);
		toolbar.add(saveImage);
		effects.add(addEffect, BorderLayout.SOUTH);
		effects.add(activeEffects, BorderLayout.CENTER);
		window.add(toolbar, BorderLayout.NORTH);
		window.add(effects, BorderLayout.EAST);
		window.add(panel);

		window.pack();
		window.setVisible(true);
	}

	private String[] getEffects() {
		return new String[] {"Blur", "static", "flip"};
	}

	private void onTakeScreenshot() {

		BufferedImage im = webcam.getImage();

		JDialog dialog = new JDialog();
		dialog.setResizable(false);

		JPanel masterLayout = new JPanel();
		masterLayout.setLayout(new BorderLayout());

		JPanel optionsLayout = new JPanel();
		optionsLayout.setLayout(new FlowLayout());

		JButton saveScreenshot = new JButton("Save");
		saveScreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenshotFileHandler.saveImage(im);
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

		JLabel label = new JLabel( new ImageIcon(im.getScaledInstance((int)(im.getWidth()*0.75), (int)(im.getHeight()*0.75), Image.SCALE_FAST)));

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
