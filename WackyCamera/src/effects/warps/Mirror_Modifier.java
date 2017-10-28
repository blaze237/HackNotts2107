package effects.warps;

import util.Point;

public class Mirror_Modifier  implements WarpModifier
{
	public static final boolean HORIZONTAL = false;
	public static final boolean VERTICAL = true;

	int center;

	boolean orientation;



	public Mirror_Modifier(int c, boolean horizontal)
	{
		this.center = c;
		this.orientation = horizontal;
	}

	@Override
	public Point translate(double x, double y)
	{
		if(orientation == VERTICAL)
		{
			if(x <= center)
				return new Point(x,y);

			int newX = center + (center - (int)x);
			return new Point(newX, y);

		}
		else
		{
			if(y <= center)
				return new Point(x,y);

			int newY = center + (center - (int)y);
			return new Point(x, newY);
		}
	}

}
