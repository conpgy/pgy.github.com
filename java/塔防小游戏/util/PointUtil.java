package util;

public class PointUtil {

	public static int[] addToArray(int x, int[] xs) {
		int[] xss = new int[xs.length];
		for (int i = 0; i < xs.length; i++) {
			xss[i] = xs[i] + x;
		}
		return xss;
	}

}
