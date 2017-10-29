package effects;

import util.Image;

public abstract class Effect implements java.io.Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 5762333552243334501L;

	public abstract Image apply(Image img);

}
