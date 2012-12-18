package model;

import data.Data;

public class Bullet {

	private Fighter fighter;

	private double x;

	private double y;

	private int speed = 6;

	private int type;

	private int power;

	private boolean done;

	private boolean canMove;

	public Bullet(int x, int y, int type, int power, Fighter fighter) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.power = power;
		this.fighter = fighter;
		this.done = false;
		this.canMove = true;
	}

	public void move() {
		if (canMove) {
			try {
				if (type == 2) {
					new Thread() {
						public void run() {
							try {
								fighter
										.setNowLife(fighter.getNowLife()
												- power);
								Thread.sleep(300);
								done = true;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}.start();

				} else {
					int fX = fighter.getY() + Data.squaresSize / 2;
					int fY = fighter.getX() + Data.squaresSize / 2;
					double r = Math.sqrt((fX - x) * (fX - x) + (fY - y)
							* (fY - y));
					double num = (double) speed / r;
					x = x + (fX - x) * num;
					y = y + (fY - y) * num;
					if (Math.abs(fX - x) < 10 && Math.abs(fY - y) < 10) {
						fighter.setNowLife(fighter.getNowLife() - power);
						if (type == 0) {
							fighter.setIce(true);
							fighter.setIceTime(100);
						}
						if (type == 3) {
							if ((int) (Math.random() * 10) > 4) {
								fighter.setWood(true);
								fighter.setWoodTime(100);
							}
						}
						if (type == 1) {
							fighter.setFire(true);
							fighter.setFireTime(100);
							fighter.setFirePower(power / 6);
						}
						done = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * @return the canMove
	 */
	public boolean isCanMove() {
		return canMove;
	}

	/**
	 * @param canMove
	 *            the canMove to set
	 */
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

}
