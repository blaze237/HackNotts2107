package effects.simple;

import effects.SimpleEffect;
import util.Color;

public class Effect_Sepia extends SimpleEffect
{
	@Override
	protected int applyPerPixel(int x, int y, int color) {
		int red = Color.getColor(color, Color.RED);
	    int green = Color.getColor(color, Color.GREEN);
	    int blue = Color.getColor(color, Color.BLUE);

	    int red2 = Math.min(255, (int) ((red * .393) + (green *.769) + (blue * .189)));
	    int green2 = Math.min(255, (int) ((red * .349) + (green *.686) + (blue * .168)));
	    int blue2 =  Math.min(255, (int) ((red * .272) + (green *.534) + (blue * .131)));

	    return Color.makeColor(red2, green2, blue2);
	}

}
