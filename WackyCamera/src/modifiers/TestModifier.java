package modifiers;

import util.Point;

public class TestModifier implements Modifier {
	@Override
	public Point translate(double x, double y) {
		return new Point(x / 2.0, y);
	}

}
