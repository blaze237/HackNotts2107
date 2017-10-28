package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Warpy {

	private JComboBox effects;
	private JButton saveImage;
	private ImagePanel feed;
	
	private Boolean isFeedLoaded = false;
	
	private BufferedImage currentFrame;
	
	private int feedWidth = 500;
	private int feedHeight = 500;

	public Warpy() {
	}

	public void InitGui() {
		JFrame window = new JFrame("Warpy");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		String[] effectsList = new String[] {"Blur", "Wobble", "Dog"};
		effects = new JComboBox<>(effectsList);

		saveImage = new JButton("Take Screenshot");
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTakeScreenshot();
			}
		});

		feed = new ImagePanel((currentFrame == null) ? getDefaultFeed() : currentFrame);
		feed.setPreferredSize(new Dimension(feedWidth, feedHeight));

		toolbar.add(effects);
		toolbar.add(saveImage);
		window.add(toolbar, BorderLayout.NORTH);
		window.add(feed, BorderLayout.CENTER);

		window.pack();
		window.setVisible(true);
	}

	private void onTakeScreenshot() {
		JDialog dialog = new JDialog();
		JLabel label = new JLabel( new ImageIcon() );
		dialog.add(label);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	public void updateFeed(BufferedImage frame) {
		
	}
	
	private BufferedImage getDefaultFeed() {
		BufferedImage dflt = new BufferedImage(feedWidth, feedHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dflt.createGraphics();
		g.setBackground(Color.GRAY);
		g.clearRect(0,0, feedWidth, feedHeight);
		currentFrame = dflt;
		return dflt;
	}

}
