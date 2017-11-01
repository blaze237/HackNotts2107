package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ImagePanel extends JPanel {

	private BufferedImage image;

	public ImagePanel(BufferedImage image, Dimension size) {
		if (image == null)
			this.image = getDefaultFeed(size);
		else
			this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	/*
	public void scaleFeed(Dimension newSize) {
		image = new BufferedImage((int)newSize.getWidth(), (int)newSize.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.clearRect(0,0, (int)newSize.getWidth(), (int)newSize.getHeight());
		g.drawImage(image, 0, 0, (int)newSize.getWidth(), (int)newSize.getHeight(), null);
	}
	*/

	private BufferedImage getDefaultFeed(Dimension size) {
		BufferedImage dflt = new BufferedImage((int)size.getWidth(), (int)size.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dflt.createGraphics();
		g.setBackground(Color.GRAY);
		g.clearRect(0,0, (int)size.getWidth(), (int)size.getHeight());
		return dflt;
	}

	public BufferedImage getFrame() {
		return image;
	}
}
