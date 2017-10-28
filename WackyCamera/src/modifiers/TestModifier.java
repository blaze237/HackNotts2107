package modifiers;

import util.Point;

public class TestModifier implements Modifier {

	@Override
	public Point translate(int x, int y) {
		return new Point(x, y);
	}

}
