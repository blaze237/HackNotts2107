package effects.warps;

import util.Point;
import util.UnitVec;

public class Tunnel_Modifier implements WarpModifier
{

	private Point cent;
	private double rad;

	public Tunnel_Modifier(int x, int y, double rad)
	{
		this.cent = new Point(x,y);
		this.rad = rad;
	}

	@Override
	public Point translate(double x, double y)
	{
		Point p = new Point(x,y);
		double dist = Point.dist(cent, p);

		if(dist <= rad)
			return p;

		UnitVec direction = new UnitVec(p.x - cent.x , p.y - cent.y);

		return cent.translate(direction,rad);

	}
}
