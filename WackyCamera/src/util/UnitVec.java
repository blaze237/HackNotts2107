package util;

public class UnitVec {

	//Data storage
	private double dx, dy;

	public UnitVec(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;

		if (this.length2() > 1.0)
			this.normalize();
	}

	//Getters
	public double dx() { return dx; }
	public double dy() { return dy; }

	//Setters
	public void set(double dx, double dy) { set(dx, dy, false); }
	public void set(double dx, double dy, boolean isNormalized) {
		this.dx = dx;
		this.dy = dy;

		if (!isNormalized)
			normalize();
	}

	//Length getters
	public double length2() { return Math.pow(dx, 2) + Math.pow(dy, 2);}
	public double length() { return Math.sqrt(length2()); }

	public UnitVec rotate(double angle) {
		double cs = Math.cos(angle);
		double sn = Math.sin(angle);
		return new UnitVec(dx * cs - dy * sn, dx * sn + dy * cs);
	}

	//Private normalize
	private void normalize() {
		double dist = length();
		dx /= dist;
		dy /= dist;
	}
}
