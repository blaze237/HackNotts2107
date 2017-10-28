package util;

public class Point {
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	//Translate by a unit vector
	public Point translate(UnitVec v) { return translate(v, 1.0); }
	public Point translate(UnitVec v, double dist) { return new Point(x + v.dx() * dist, y + v.dy() * dist); }

	public static double dist2(Point p1, Point p2) { return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2.0); }
	public static double dist(Point p1, Point p2) { return Math.sqrt(dist2(p1, p2)); }

	public static UnitVec getVec(Point p1, Point p2) { return new UnitVec(p1.x - p2.x, p1.y - p2.y); }

	public double x;
	public double y;
}
