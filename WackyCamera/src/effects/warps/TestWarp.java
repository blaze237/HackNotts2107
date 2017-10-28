package effects.warps;

import util.Point;

public class TestWarp implements WarpModifier {
	@Override
	public Point translate(double x, double y) {
		return new Point(x / 2.0, y);
	}

}
