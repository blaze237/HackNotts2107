package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Grayscale extends SimpleEffect
{
	@Override
	protected int applyPerPixel(int x, int y, int color) {
	    int red = Color.getColor(color, Color.RED);
	    int green = Color.getColor(color, Color.GREEN);
	    int blue = Color.getColor(color, Color.BLUE);
	    int avg = (red+green+blue)/3;

	    return Color.makeColor(avg, avg, avg);
	}

}
