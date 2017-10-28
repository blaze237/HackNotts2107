package modifiers;

import util.Point;

public interface Modifier {
	//Provide a co-ordinate and get the translated co-ordinate back
	abstract Point translate(double x, double y);
}
