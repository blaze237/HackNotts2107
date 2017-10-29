package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import WarpApp.ImageWarper;
import util.EffectPair;

public class ActiveEffect extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5137557233888379187L;
	
	public ActiveEffect(JPanel parent, EffectPair effect, ImageWarper warper) {
		super();
		
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		setLayout(new FlowLayout());
		this.setMaximumSize(new Dimension(999, 40));
		JLabel effectTag = new JLabel(effect.getLabel());
		
		JButton remove = new JButton("-");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				warper.removeEffect(effect.getEffect());
				parent.remove(ActiveEffect.this);
				parent.validate();
				parent.repaint(50L);
			}
		});
		
		add(effectTag);
		add(remove);
		
		warper.addEffect(effect.getEffect());
	}
}
