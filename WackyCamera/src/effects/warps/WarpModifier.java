package effects.warps;

import util.Point;

public interface WarpModifier {
	//Provide a co-ordinate and get the translated co-ordinate back
	abstract Point translate(double x, double y);
}
