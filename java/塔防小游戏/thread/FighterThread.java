package thread;

import java.util.List;

import model.Fighter;
import data.BaseData;
import data.Data;

public class FighterThread implements Runnable {

	private BaseData data;

	private List<Fighter> fighterList;

	public FighterThread(BaseData data) {
		this.data = data;
		fighterList = data.getFighterList();
	}

	public void run() {
		try {
			while (true) {
				for (int i = 0; i < fighterList.size(); i++) {
					Fighter fighter = fighterList.get(i);
					if (fighter == null) {
						break;
					}
					if (fighter.isNeedMove()) {
						fighter.move();
					} else {
						data.getFuturePoint(fighter);
						fighter.move();
					}
					if (fighter.getX() == Data.gameX + Data.squaresSize
							&& fighter.getY() == Data.gameY + Data.squaresSize
									* 14) {
						if (data.getNowLife() > 0) {
							data.setNowLife(data.getNowLife() - 5);
						}
						fighterList.remove(i);
						i--;
					} else if (fighter.nowLife <= 0) {
						data.setMoney(data.getMoney() + fighter.getPrice());
						fighterList.remove(i);
						i--;
					}
				}
				Thread.sleep(20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the data
	 */
	public BaseData getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(BaseData data) {
		this.data = data;
	}

}
