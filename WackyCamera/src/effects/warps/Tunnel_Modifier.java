package effects.warps;

import util.Point;
import util.UnitVec;

public class Tunnel_Modifier extends WarpModifier
{


	/**
	 *
	 */
	private static final long serialVersionUID = -1017622362792738225L;
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
		//Get dist to centre of circlr
		Point p = new Point(x,y);
		double dist = Point.dist(cent, p);

		if(dist <= rad)
			return p;

		//Get unit vector from point to center
		UnitVec direction = new UnitVec(p.x - cent.x , p.y - cent.y);

		//Spread radial pixels out from the boundary
		return cent.translate(direction,rad);

	}
}
