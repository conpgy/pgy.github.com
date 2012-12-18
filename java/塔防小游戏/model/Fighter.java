package model;

import java.awt.Color;
import java.awt.Graphics;

import data.Data;

public abstract class Fighter implements Cloneable {

	public int x = 0;

	public int y = 0;

	public int futureX;

	public int futureY;

	public int life;

	public int nowLife;

	public int speed;

	public boolean ice;

	public int iceTime;

	private boolean wood;

	private int woodTime;

	private boolean fire;

	private int fireTime;

	private int firePower;

	private boolean boss;

	private int[] woodStatusX = { 10, 15, 20, 25, 30, 35, 40 };

	private int[] woodStatusY = { 40, 30, 40, 30, 40, 30, 40 };

	public int direction = 4; // 上1下2左3右4

	public int changeNum = 0;

	public int fireChangeNum = 0;

	private int price;

	public Fighter(int life, int price) {
		this.x = 7 * Data.squaresSize + Data.gameX;
		this.y = -1 * Data.squaresSize + Data.gameY;
		this.futureX = this.x;
		this.futureY = this.y;
		ice = false;
		iceTime = 0;
		wood = false;
		woodTime = 0;
		fire = false;
		fireTime = 0;
		firePower = 0;
		speed = 1;
		boss = false;
		this.life = life;
		this.nowLife = life;
		this.price = price;
	}

	public abstract void draw(Graphics g);

	public void drawLifeStatus(Graphics g, int x, int y) {
		if (nowLife < 0) {
			nowLife = 0;
		}
		int redLength = (int) ((double) (life - nowLife) / life * 40);
		redLength = redLength > 0 ? redLength : 0;
		g.setColor(Color.green);
		g.fillRect(y + 5, x, Data.squaresSize - 10, 5);
		g.setColor(Color.red);
		g.fillRect(y + Data.squaresSize - redLength - 5, x, redLength, 5);
		if (wood) {
			g.setColor(Color.green);
			g.fillPolygon(getWoodStatusX(), getWoodStatusY(), 7);
		}
		if (fire) {
			g.setColor(Color.red);
			int fireNum = fireChangeNum / 10;
			if (fireNum == 1) {
				g.fillOval(y + 10, x + 10, 10, 10);
			} else {
				g.fillOval(y + 10, x + 10, 12, 12);
			}
		}
	}

	private int[] getWoodStatusX() {
		int[] statusx = new int[7];
		for (int i = 0; i < 7; i++) {
			statusx[i] = woodStatusX[i] + y;
		}
		return statusx;
	}

	private int[] getWoodStatusY() {
		int[] statusy = new int[7];
		for (int i = 0; i < 7; i++) {
			statusy[i] = woodStatusY[i] + x;
		}
		return statusy;
	}

	public void move() {
		if (!wood || boss) {
			changeNum++;
			if (changeNum > 20) {
				changeNum = 0;
			}
		}
		fireChangeNum++;
		if (fireChangeNum > 20) {
			fireChangeNum = 0;
		}
		if (fire) {
			if (fireTime % 20 == 0) {
				nowLife -= firePower / 5;
			}
			fireTime--;
			if (fireTime <= 0) {
				fire = false;
				fireTime = 0;
				firePower = 0;
			}
		}
		if (wood && !boss) {
			woodTime--;
			if (woodTime <= 0) {
				woodTime = 0;
				wood = false;
			}
		} else if (ice) {
			if (iceTime % 2 == 0) {
				if (direction == 1) {
					x -= speed;
				}
				if (direction == 2) {
					x += speed;
				}
				if (direction == 3) {
					y -= speed;
				}
				if (direction == 4) {
					y += speed;
				}
			}
			iceTime--;
			if (iceTime <= 0) {
				iceTime = 0;
				ice = false;
			}
		} else {
			if (direction == 1) {
				x -= speed;
			}
			if (direction == 2) {
				x += speed;
			}
			if (direction == 3) {
				y -= speed;
			}
			if (direction == 4) {
				y += speed;
			}
		}
	}

	public int getDistance(int x, int y) {
		return (int) (Math.sqrt((this.x - y) * (this.x - y) + (this.y - x)
				* (this.y - x)));
	}

	public boolean isNeedMove() {
		if (x != futureX || y != futureY) {
			return true;
		}
		return false;
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
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the nowLife
	 */
	public int getNowLife() {
		return nowLife;
	}

	/**
	 * @param nowLife
	 *            the nowLife to set
	 */
	public void setNowLife(int nowLife) {
		this.nowLife = nowLife;
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
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the futureX
	 */
	public int getFutureX() {
		return futureX;
	}

	/**
	 * @param futureX
	 *            the futureX to set
	 */
	public void setFutureX(int futureX) {
		this.futureX = futureX;
	}

	/**
	 * @return the futureY
	 */
	public int getFutureY() {
		return futureY;
	}

	/**
	 * @param futureY
	 *            the futureY to set
	 */
	public void setFutureY(int futureY) {
		this.futureY = futureY;
	}

	/**
	 * @return the ice
	 */
	public boolean isIce() {
		return ice;
	}

	/**
	 * @param ice
	 *            the ice to set
	 */
	public void setIce(boolean ice) {
		this.ice = ice;
	}

	/**
	 * @return the iceTime
	 */
	public int getIceTime() {
		return iceTime;
	}

	/**
	 * @param iceTime
	 *            the iceTime to set
	 */
	public void setIceTime(int iceTime) {
		this.iceTime = iceTime;
	}

	/**
	 * @return the wood
	 */
	public boolean isWood() {
		return wood;
	}

	/**
	 * @param wood
	 *            the wood to set
	 */
	public void setWood(boolean wood) {
		this.wood = wood;
	}

	/**
	 * @return the woodTime
	 */
	public int getWoodTime() {
		return woodTime;
	}

	/**
	 * @param woodTime
	 *            the woodTime to set
	 */
	public void setWoodTime(int woodTime) {
		this.woodTime = woodTime;
	}

	/**
	 * @return the fire
	 */
	public boolean isFire() {
		return fire;
	}

	/**
	 * @param fire
	 *            the fire to set
	 */
	public void setFire(boolean fire) {
		this.fire = fire;
	}

	/**
	 * @return the fireTime
	 */
	public int getFireTime() {
		return fireTime;
	}

	/**
	 * @param fireTime
	 *            the fireTime to set
	 */
	public void setFireTime(int fireTime) {
		this.fireTime = fireTime;
	}

	/**
	 * @return the firePower
	 */
	public int getFirePower() {
		return firePower;
	}

	/**
	 * @param firePower
	 *            the firePower to set
	 */
	public void setFirePower(int firePower) {
		this.firePower = firePower;
	}

	public Fighter clone() throws CloneNotSupportedException {
		return (Fighter) super.clone();
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
	 * @return the boss
	 */
	public boolean isBoss() {
		return boss;
	}

	/**
	 * @param boss
	 *            the boss to set
	 */
	public void setBoss(boolean boss) {
		this.boss = boss;
		this.speed = 1;
	}

}
