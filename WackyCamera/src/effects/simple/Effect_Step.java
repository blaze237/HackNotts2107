package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Step extends SimpleEffect {

	/**
	 *
	 */
	private static final long serialVersionUID = 4179678440849912008L;
	private int steps;

	public Effect_Step(int steps) {
		this.steps = steps;
	}

	@Override
	protected int applyPerPixel(int x, int y, int color) {
		return Color.apply(color, c -> { return (c / steps) * steps; });
	}

}
