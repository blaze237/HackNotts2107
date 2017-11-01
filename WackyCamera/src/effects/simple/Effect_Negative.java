package effects.simple;
import effects.SimpleEffect;
import util.Color;

public class Effect_Negative  extends SimpleEffect
{

	/**
	 *
	 */
	private static final long serialVersionUID = 7789060934684290315L;


	@Override
	protected int applyPerPixel(int x, int y, int color)
	{
		int red = 255 - Color.getColor(color, Color.RED);
		int green = 255 - Color.getColor(color, Color.GREEN);
		int blue = 255 - Color.getColor(color, Color.BLUE);

		return Color.makeColor(red, green, blue);
	}

}
