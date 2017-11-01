package effects.warps;

import util.Point;

public class TestWarp extends WarpModifier {
	/**
	 *
	 */
	private static final long serialVersionUID = -3812962249568864566L;

	@Override
	public Point translate(double x, double y) {
		return new Point(x / 2.0, y);
	}

}
