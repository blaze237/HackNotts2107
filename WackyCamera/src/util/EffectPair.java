package util;

import effects.Effect;

public class EffectPair {

	private String label;
	private Effect instance;
	
	public EffectPair(String label, Effect instance) {
		this.label = label;
		this.instance = instance;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Effect getEffect() {
		return instance;
	}
}
