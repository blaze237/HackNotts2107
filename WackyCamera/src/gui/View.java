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
		dropdownEffects.add(new DropdownPair("Test", "Test.ser"));
		
		
		
		ImageScanner scanner = ImageScanner.getInstance();

		Dimension camSize = WebcamResolution.VGA.getSize();

		warper = new ImageWarper(scanner);

		//warper.addEffect(new Effect_Warp(new Tunnel_Modifier(camSize.width / 2, camSize.height / 2, Math.min(camSize.width, camSize.height) / 5)));
		//warper.addEffect(new Effect_Abberation(10, Effect_Abberation.ONE_WAY_ABBERATION));

		//warper.addEffect(new Effect_Warp(new CircleWarp(camSize.width / 2, camSize.height / 2, Math.min(camSize.width, camSize.height) / 2.1)));
		//warper.addEffect(new Effect_Blur());

		//warper.addEffect(new Effect_Blur());
		//warper.addEffect(new Effect_Blur());
		//warper.addEffect(new Effect_Blur());


		//warper.saveEffects("test.ser");
	
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
