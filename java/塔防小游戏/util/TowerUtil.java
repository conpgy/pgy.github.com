package util;

public class TowerUtil {

	public static int getPriceByTowerType(int type) {
		int price = 0;
		if (type == 0) {
			price = 100;
		}
		if (type == 1) {
			price = 140;
		}
		if (type == 2) {
			price = 160;
		}
		if (type == 3) {
			price = 150;
		}
		return price;
	}

}
