package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.EffectPair;

public class ActiveEffect extends JPanel {
	
	private String tag;
	
	public ActiveEffect(String tag, JPanel parent, List<EffectPair> activeEffects) {
		super();
		this.tag = tag;
		
		setLayout(new FlowLayout());
		
		JLabel effectTag = new JLabel(tag);
		
		JButton remove = new JButton("-");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.remove(ActiveEffect.this);
				for (int i = 0; i < activeEffects.size() - 1; ++i) {
					if (activeEffects.get(i).getLabel() == ActiveEffect.this.tag){
						activeEffects.remove(i);
						
						break;
					}
				}
			}
		});
		
		add(effectTag);
		add(remove);
	}
}
