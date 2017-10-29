package effects.warps;

import util.Point;
import util.UnitVec;

public class Swirl_Modifier  implements WarpModifier
{
	private double maxAngle;
	private double radius;
	private Point center;


	public Swirl_Modifier(int x, int y, double rad, double angle)
	{
		this.center = new Point(x,y);
		this.radius = rad;
		maxAngle = angle;
	}

	@Override
	public Point translate(double x, double y)
	{
		Point p = new Point (x , y);

		double dist = Point.dist(p, center);

		//if(dist > radius)
		//	return p;


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
