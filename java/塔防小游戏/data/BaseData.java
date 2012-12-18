package data;

import java.util.ArrayList;
import java.util.List;

import model.Bullet;
import model.ElectricTower;
import model.Fighter;
import model.FireTower;
import model.IceTower;
import model.Tower;
import model.WoodTower;
import thread.AddFighterThread;
import thread.BulletThread;

public class BaseData implements Runnable {

	private int gameX;

	private int gameY;

	private int squaresSize;

	private List<Fighter> fighterList;

	private List<Tower> towerList;

	private List<Bullet> bulletList;

	private int life;

	private int nowLife;

	private int money;

	private boolean dead;

	private int round;

	private boolean win;

	private int[][] squares = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	public BaseData() {
		init();
	}

	public void init() {
		fighterList = new ArrayList<Fighter>();
		towerList = new ArrayList<Tower>();
		bulletList = new ArrayList<Bullet>();
		gameX = Data.gameX;
		gameY = Data.gameY;
		life = 100;
		nowLife = 100;
		money = 300;
		round = 1;
		dead = false;
		squaresSize = Data.squaresSize;
		win = false;
		Thread addFighterThread = new Thread(new AddFighterThread(this));
		addFighterThread.start();
		Thread bulletThread = new Thread(new BulletThread(this));
		bulletThread.start();
		Thread lifeThread = new Thread(this);
		lifeThread.start();
	}

	public void run() {
		try {
			while (true) {
				if (nowLife <= 0) {
					dead();
				}
				if (round == 100 && fighterList.size() == 0 && nowLife > 0) {
					win();
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void win() {
		dead();
		win = true;
	}

	private void dead() {
		for (Tower tower : towerList) {
			tower.setLife(false);
		}
		towerList.clear();
		bulletList.clear();
		fighterList.clear();
		dead = true;
	}

	public void restart() {
		nowLife = 100;
		money = 300;
		dead = false;
		win = false;
		round = 1;
	}

	public void addTower(int type, int x, int y, int size) {
		Tower tower = null;
		if (type == 0) {
			tower = new IceTower(x, y, size, this);
		}
		if (type == 1) {
			tower = new FireTower(x, y, size, this);
		}
		if (type == 2) {
			tower = new ElectricTower(x, y, size, this);
		}
		if (type == 3) {
			tower = new WoodTower(x, y, size, this);
		}
		towerList.add(tower);
	}

	public void addBullet(Bullet bullet) {
		bulletList.add(bullet);
	}

	public void getFuturePoint(Fighter f) {
		int x = (f.getX() - gameX) / squaresSize;
		int y = (f.getY() - gameY) / squaresSize;
		int dir = f.getDirection();
		if (dir == 1) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 8 && y < 14) {
				if (squares[x - 1][y] == 1) {
					f.setFutureX((x - 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(1);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 8 && y - 1 < 14) {
				if (squares[x][y - 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y - 1) * squaresSize + gameY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 8 && y + 1 < 14) {
				if (squares[x][y + 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y + 1) * squaresSize + gameY);
					f.setDirection(4);
				}
			}
		} else if (dir == 2) {
			if (x + 1 >= 0 && y >= 0 && x + 1 < 8 && y < 14) {
				if (squares[x + 1][y] == 1) {
					f.setFutureX((x + 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 8 && y - 1 < 14) {
				if (squares[x][y - 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y - 1) * squaresSize + gameY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 8 && y + 1 < 14) {
				if (squares[x][y + 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y + 1) * squaresSize + gameY);
					f.setDirection(4);
				}
			}
		} else if (dir == 3) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 8 && y < 14) {
				if (squares[x - 1][y] == 1) {
					f.setFutureX((x - 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 8 && y < 14) {
				if (squares[x + 1][y] == 1) {
					f.setFutureX((x + 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 8 && y - 1 < 14) {
				if (squares[x][y - 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y - 1) * squaresSize + gameY);
					f.setDirection(3);
				}
			}
		} else if (dir == 4) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 8 && y < 14) {
				if (squares[x - 1][y] == 1) {
					f.setFutureX((x - 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 8 && y < 14) {
				if (squares[x + 1][y] == 1) {
					f.setFutureX((x + 1) * squaresSize + gameX);
					f.setFutureY(y * squaresSize + gameY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 8 && y + 1 < 14) {
				if (squares[x][y + 1] == 1) {
					f.setFutureX(x * squaresSize + gameX);
					f.setFutureY((y + 1) * squaresSize + gameY);
					f.setDirection(4);
				}
			}
		}
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
	 * @return the squares
	 */
	public int[][] getSquares() {
		return squares;
	}

	/**
	 * @param squares
	 *            the squares to set
	 */
	public void setSquares(int[][] squares) {
		this.squares = squares;
	}

	/**
	 * @return the towerList
	 */
	public List<Tower> getTowerList() {
		return towerList;
	}

	/**
	 * @param towerList
	 *            the towerList to set
	 */
	public void setTowerList(List<Tower> towerList) {
		this.towerList = towerList;
	}

	/**
	 * @return the bulletList
	 */
	public List<Bullet> getBulletList() {
		return bulletList;
	}

	/**
	 * @param bulletList
	 *            the bulletList to set
	 */
	public void setBulletList(List<Bullet> bulletList) {
		this.bulletList = bulletList;
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
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * @param dead
	 *            the dead to set
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @param round
	 *            the round to set
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(boolean win) {
		this.win = win;
	}

}
