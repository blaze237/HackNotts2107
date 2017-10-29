package util;

import effects.Effect;

public class EffectPair {

	private String label;
	private Effect e;
	
	public EffectPair(String label, Effect e) {
		this.label = label;
		this.e = e;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Effect getEffect() {
		return e;
	}
}
