package model;

import java.util.ArrayList;
import java.util.List;

import util.BulletUtil;
import data.BaseData;

public class Tower implements Runnable {

	private int x;

	private int y;

	private int type;

	private int power;

	private int level;

	private int bulletType;

	private int fightNum;

	private int speed;

	private int gunshot = 150;

	private BaseData data;

	private List<Fighter> fighterList;

	private int[] powers;

	private int[] fightNums;

	private int price;

	private boolean enable;

	private int buildTime;

	private boolean life;

	public Tower() {
		enable = false;
		life = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void levelUp() {
		if (level < 6) {
			level++;
			buildTime = 0;
			enable = false;
			setPowerAndFinghtNum();
		}
	}

	public void setPowerAndFinghtNum() {
		this.power = powers[level - 1];
		this.fightNum = fightNums[level - 1];
	}

	private void buildTower() {
		try {
			buildTime = 0;
			while (buildTime < 100) {
				buildTime++;
				Thread.sleep(20);
			}
			enable = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (life) {
				if (enable) {
					Fighter[] fighters = null;
					if (fightNum == 1 && type != 3) {
						fighters = getNearlyFighters();
					} else {
						fighters = getRandomFighters();
					}
					int powers = power;
					if (fighters.length > 0) {
						powers = power / fighters.length;
					}
					for (int i = 0; i < fighters.length; i++) {
						Fighter fighter = fighters[i];
						if (fighter != null) {
							data.addBullet(BulletUtil.getBulletByType(this,
									fighter, powers));
						}
					}
					Thread.sleep(1000);
				} else {
					buildTower();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Fighter[] getRandomFighters() {
		List<Fighter> randomFighter = new ArrayList<Fighter>();
		for (int i = 0; i < fighterList.size(); i++) {
			Fighter fighter = fighterList.get(i);
			if (fighter.getDistance(x, y) < gunshot) {
				randomFighter.add(fighter);
			}
		}
		Fighter[] fighterArr = new Fighter[fightNum > randomFighter.size() ? randomFighter
				.size()
				: fightNum];
		for (int i = 0; i < fightNum; i++) {
			if (randomFighter.size() > 0) {
				int num = (int) (Math.random() * randomFighter.size());
				fighterArr[i] = randomFighter.get(num);
				randomFighter.remove(num);
			}
		}
		return fighterArr;
	}

	public Fighter[] getNearlyFighters() {
		Fighter[] fighters = new Fighter[fighterList.size()];
		for (int i = 0; i < fighterList.size(); i++) {
			fighters[i] = fighterList.get(i);
		}
		for (int i = 0; i < fightNum && i < fighterList.size() - 1; i++) {
			for (int j = i + 1; j < fighterList.size(); j++) {
				if (fighters[i].getDistance(x, y) > fighters[j].getDistance(x,
						y)) {
					Fighter fighter = fighters[i];
					fighters[i] = fighters[j];
					fighters[j] = fighter;
				}
			}
		}
		Fighter[] result = new Fighter[fightNum];
		for (int i = 0; i < result.length && i < fighters.length; i++) {
			if (fighters[i].getDistance(x, y) <= gunshot) {
				result[i] = fighters[i];
			}
		}
		return result;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the bulletType
	 */
	public int getBulletType() {
		return bulletType;
	}

	/**
	 * @param bulletType
	 *            the bulletType to set
	 */
	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	/**
	 * @return the fightNum
	 */
	public int getFightNum() {
		return fightNum;
	}

	/**
	 * @param fightNum
	 *            the fightNum to set
	 */
	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the powers
	 */
	public int[] getPowers() {
		return powers;
	}

	/**
	 * @param powers
	 *            the powers to set
	 */
	public void setPowers(int[] powers) {
		this.powers = powers;
	}

	/**
	 * @return the fightNums
	 */
	public int[] getFightNums() {
		return fightNums;
	}

	/**
	 * @param fightNums
	 *            the fightNums to set
	 */
	public void setFightNums(int[] fightNums) {
		this.fightNums = fightNums;
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

	/**
	 * @return the fighterList
	 */
	public List<Fighter> getFighterList() {
		return fighterList;
	}

	/**
	 * @param fighterList
	 *            the fighterList to set
	 */
	public void setFighterList(List<Fighter> fighterList) {
		this.fighterList = fighterList;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the buildTime
	 */
	public int getBuildTime() {
		return buildTime;
	}

	/**
	 * @param buildTime
	 *            the buildTime to set
	 */
	public void setBuildTime(int buildTime) {
		this.buildTime = buildTime;
	}

	/**
	 * @return the life
	 */
	public boolean isLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(boolean life) {
		this.life = life;
	}

}
