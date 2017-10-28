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
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ScreenshotFileHandler;

public class Warpy {

	private JComboBox effects;
	private JButton saveImage;
	private ImagePanel feed;
	
	private Boolean isFeedLoaded = false;
	
	private BufferedImage currentFrame;
	
	private int feedWidth = 800;
	private int feedHeight = 800;

	public Warpy() {}

	public void InitGui() {
		JFrame window = new JFrame("Warpy");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		String[] effectsList = new String[] {"Blur", "Wobble", "Dog"};
		effects = new JComboBox<>(effectsList);


		feed = new ImagePanel(currentFrame, new Dimension(feedWidth, feedHeight));
		currentFrame = feed.getFrame();
		feed.setPreferredSize(new Dimension(feedWidth, feedHeight));
		window.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				Component c = (Component)evt.getSource();
				Dimension size = c.getSize();
				feed = new ImagePanel(currentFrame, window.getSize());
				currentFrame = feed.getFrame();
				//feed.scaleFeed(window.getSize());
				
			}
		});
		
		saveImage = new JButton("Take Screenshot");
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTakeScreenshot();
			}
		});

		toolbar.add(effects);
		toolbar.add(saveImage);
		window.add(toolbar, BorderLayout.NORTH);
		window.add(feed, BorderLayout.CENTER);

		window.pack();
		window.setVisible(true);
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
