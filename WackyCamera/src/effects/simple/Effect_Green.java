package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Green extends SimpleEffect
{
	/**
	 *
	 */
	private static final long serialVersionUID = 6113674623604058803L;

	@Override
	protected int applyPerPixel(int x, int y, int color) {
	    return Color.makeColor(0, Color.getColor(color, Color.GREEN), 0);
	}
}
