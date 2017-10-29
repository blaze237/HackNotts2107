package effects.warps;

import util.Point;
import util.UnitVec;

public class Circle_Modifier extends WarpModifier {

	/**
	 *
	 */
	private static final long serialVersionUID = -66959928199368292L;
	Point center;
	double radius;

	public Circle_Modifier(int cx, int cy, double radius) {
		this.center = new Point(cx, cy);
		this.radius = radius;
	}

	@Override
	public Point translate(double x, double y) {
		//Get the point
		Point p = new Point(x, y);

		//Get difference of radius and distance to center
		double dist = radius - Point.dist(p,  center);

		//Return normal point if outside the circle radius
		if (dist <= 0)
			return new Point(x, y);

		//%'age distance to edge (0.0 - Center, 1.0 - Edge)
		double ratio = dist / radius;

		//Get directional vector
		UnitVec vec = new UnitVec(p.x - center.x, p.y - center.y);

		//Translate p
		p = p.translate(vec, dist * Math.pow(Math.sin(Math.PI * ratio), 2.0));

		return p;
	}

//	//Get the point
//	Point p = new Point(x, y);
//
//	//Get difference of radius and distance to center
//	double dist = radius - Point.dist(p,  center);
//
//	//Return normal point if outside the circle radius
//	if (dist < 0)
//		return new Point(x, y);
//
//	//%'age distance to edge (0.0 - Center, 1.0 - Edge)
//	double ratio = dist / radius;
//
//	//Get directional vector
//	UnitVec vec = new UnitVec(p.x - center.x, p.y - center.y);
//
//	//Translate p
//	p = p.translate(vec, radius * Math.sin(Math.PI * ratio));
//
//	return p;
}
