package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Blue extends SimpleEffect
{
	/**
	 *
	 */
	private static final long serialVersionUID = -3531509902402499875L;

	@Override
	protected int applyPerPixel(int x, int y, int color) {
	    return Color.makeColor(0, 0, Color.getColor(color, Color.BLUE));
	}
}
