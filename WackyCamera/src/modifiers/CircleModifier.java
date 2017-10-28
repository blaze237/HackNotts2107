package modifiers;

import util.Point;

public class CircleModifier implements Modifier {

	public CircleModifier(int cx, int cy, double radius) {
		this.center = new Point(cx, cy);
		this.radius2 = Math.pow(radius, 2.0);
	}

	@Override
	public Point translate(double x, double y) {
		//Get the point
		Point p = new Point(x, y);

		//Get difference of radius and distance to center
		double dist = radius2 - Point.dist2(p,  center);

		//Return normal point if outside the circle radius
		if (dist < 0)
			return new Point(x, y);

		return new Point(0, 0);
	}

	Point center;
	double radius2;

}
