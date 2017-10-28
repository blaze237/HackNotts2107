package util;

public class Color {
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int COUNT = 3;

	private static final int[] BIT_OFFSET = { 16, 8, 0 };
	private static final int BIT_MASK = 0xFF;

	public static int getColor(int packed, int color) { return (packed >> BIT_OFFSET[color]) & BIT_MASK; }
	public static int setColor(int packed, int color, int value) { return packed | (value << BIT_OFFSET[color]); }
	public static int makeColor(int r, int g, int b) { return ((r & BIT_MASK) << BIT_OFFSET[RED]) | ((g & BIT_MASK) << BIT_OFFSET[GREEN]) | ((b & BIT_MASK) << BIT_OFFSET[BLUE]); }

	public static int apply(int color, ColorApply func) {
		int red = func.applyToColor(getColor(color, Color.RED));
		int green = func.applyToColor(getColor(color, Color.GREEN));
		int blue = func.applyToColor(getColor(color, Color.BLUE));
		return makeColor(red, green, blue);
	}
}
