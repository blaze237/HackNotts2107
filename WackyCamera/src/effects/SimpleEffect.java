package effects;

import util.Image;

public abstract class SimpleEffect implements Effect {

	@Override
	public Image apply(Image img) {
		for (int y = 0; y < img.height; ++y)
			for (int x = 0; x < img.width; ++x)
				img.pixels[x][y] = applyPerPixel(x, y, img.pixels[x][y]);
		return img;
	}

	protected abstract int applyPerPixel(int x, int y, int color);

}
