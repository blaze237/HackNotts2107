package effects.warps;

import util.Point;

public abstract class WarpModifier implements java.io.Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7626624642129634632L;

	//Provide a co-ordinate and get the translated co-ordinate back
	abstract Point translate(double x, double y);
}
