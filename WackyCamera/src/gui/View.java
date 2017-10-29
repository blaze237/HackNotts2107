package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
import effects.Effect;
import effects.simple.Effect_Abberation;
import util.DropdownPair;
import util.EffectPair;
import util.ScreenshotFileHandler;

public class View {

	private JButton saveImage;

	private Webcam webcam;

	private List<DropdownPair> dropdownEffects;

	private ImageWarper warper;

	public View() {}

	public void InitGui() {

		//Create array
		dropdownEffects = new ArrayList<>();

		//Add add-able effects
		dropdownEffects.add(new DropdownPair("Tunnel", "filters/tunnel.ser"));
		dropdownEffects.add(new DropdownPair("Circle", "filters/circle.ser"));
		dropdownEffects.add(new DropdownPair("Swirl", "filters/swirl.ser"));
		dropdownEffects.add(new DropdownPair("H Mirror", "filters/h_mirror.ser"));
		dropdownEffects.add(new DropdownPair("V Mirror", "filters/v_mirror.ser"));

		dropdownEffects.add(new DropdownPair("Blur", "filters/blur.ser"));
		dropdownEffects.add(new DropdownPair("F Horiz", "filters/flipH.ser"));
		dropdownEffects.add(new DropdownPair("F Vert", "filters/flipV.ser"));
		dropdownEffects.add(new DropdownPair("Abberant L", "filters/abber_l.ser"));
		dropdownEffects.add(new DropdownPair("Abberant H", "filters/abber_h.ser"));
		dropdownEffects.add(new DropdownPair("Red", "filters/red.ser"));
		dropdownEffects.add(new DropdownPair("Green", "filters/green.ser"));
		dropdownEffects.add(new DropdownPair("Blue", "filters/blue.ser"));
		dropdownEffects.add(new DropdownPair("Greyscale", "filters/gray.ser"));
		dropdownEffects.add(new DropdownPair("Sepia", "filters/sepia.ser"));
		dropdownEffects.add(new DropdownPair("Negative", "filters/negative.ser"));
		dropdownEffects.add(new DropdownPair("Step 16", "filters/step16.ser"));
		dropdownEffects.add(new DropdownPair("Step 32", "filters/step32.ser"));
		dropdownEffects.add(new DropdownPair("Step 64", "filters/step64.ser"));
		dropdownEffects.add(new DropdownPair("thresh 1/4", "filters/thresh-1-4.ser"));
		dropdownEffects.add(new DropdownPair("thresh 1/2", "filters/thresh-2-4.ser"));
		dropdownEffects.add(new DropdownPair("thresh 3/4", "filters/thresh-3-4.ser"));
		dropdownEffects.add(new DropdownPair("Laplacian", "filters/laplacian_f.ser"));
		dropdownEffects.add(new DropdownPair("Laplacian (en)", "filters/laplacian_t.ser"));

		ImageScanner scanner = ImageScanner.getInstance();
		warper = new ImageWarper(scanner);

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

		JPanel activeEffects = new JPanel();
		activeEffects.setLayout(new BoxLayout(activeEffects, BoxLayout.PAGE_AXIS));
		JScrollPane scrollPane = new JScrollPane(activeEffects);

		JPanel addEffect = new JPanel(new FlowLayout());

		JComboBox<String> selectEffects = new JComboBox<>(getEffects());
		selectEffects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actually add the effect to the list of active ones
			}
		});

		JButton addEffectButton = new JButton("+");
		addEffectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = selectEffects.getSelectedItem().toString();
				String filepath = "";
				for (DropdownPair p : dropdownEffects) {
					if (tag == p.getLabel()) {
						filepath = p.getFilepath();
						break;
					}
				}
				if (filepath == "")
					return;

				Effect newEffect = loadEffect(filepath);
				activeEffects.add(new ActiveEffect(activeEffects, new EffectPair(tag, newEffect), warper));
				//window.pack();
				window.validate();
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
		effects.add(scrollPane, BorderLayout.CENTER);
		window.add(toolbar, BorderLayout.NORTH);
		window.add(effects, BorderLayout.EAST);
		window.add(panel);

		window.pack();
		window.setVisible(true);
	}

	private String[] getEffects() {
		String[] s = new String[dropdownEffects.size()];
		for (int i = 0; i < dropdownEffects.size(); ++i)
			s[i] = dropdownEffects.get(i).getLabel();

		return s;
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

	private Effect loadEffect(String filepath) {
		Effect e = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Effect) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (Exception i)
		{
			i.printStackTrace();
			return null;
		}
		return e;
	}

}
