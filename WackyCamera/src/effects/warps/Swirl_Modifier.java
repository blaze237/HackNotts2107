package effects.warps;

import util.Point;
import util.UnitVec;

public class Swirl_Modifier extends WarpModifier
{
	/**
	 *
	 */
	private static final long serialVersionUID = -6511306899818649043L;


	private double maxAngle;
	private double radius;
	private Point center;


	public Swirl_Modifier(int x, int y, double radius, double angle)
	{
		this.center = new Point(x,y);
		this.radius = radius;
		this.maxAngle = angle;
	}

	@Override
	public Point translate(double x, double y)
	{
		Point p = new Point (x , y);

		double dist = Point.dist(p, center);

		if(dist > radius)
			return p;


		double angOff = angularOffset(dist);
		UnitVec direction = Point.getVec(p, center);
		direction = direction.rotate(angOff);

		return center.translate(direction,dist);
	}

	private double angularOffset(double dist)
	{
		return (dist/radius) * maxAngle;
	}

}
