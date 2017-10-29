package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Red extends SimpleEffect
{
	/**
	 *
	 */
	private static final long serialVersionUID = -2981280008894971063L;

	@Override
	protected int applyPerPixel(int x, int y, int color) {
	    return Color.makeColor(Color.getColor(color, Color.RED), 0, 0);
	}
}
