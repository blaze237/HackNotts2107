package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Green extends SimpleEffect
{
	@Override
	protected int applyPerPixel(int x, int y, int color) {
	    return Color.makeColor(0, Color.getColor(color, Color.GREEN), 0);
	}
}
