package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.WebcamPanel;

public class Warpy {

	private JComboBox effects;
	private JButton saveImage;
	//private WebcamPanel feed;
	private JPanel feed;

	public Warpy() {
		InitGui();
	}

	private void InitGui() {
		JFrame window = new JFrame("Warpy");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		String[] effectsList = new String[] {"Blur", "Wobble", "Dog"};
		effects = new JComboBox<>(effectsList);

		saveImage = new JButton("Take Screenshot");
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTakeScreenshot();
				saveImage.setText("Taken Screenshot");
			}
		});

		feed = new JPanel();
		feed.setPreferredSize(new Dimension(200, 200));

		toolbar.add(effects);
		toolbar.add(saveImage);
		window.add(toolbar, BorderLayout.NORTH);
		window.add(feed, BorderLayout.CENTER);

		window.pack();
		window.setVisible(true);
	}

	private void onTakeScreenshot() {

	}

}
