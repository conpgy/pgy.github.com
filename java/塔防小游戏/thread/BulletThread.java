package thread;

import java.util.List;

import model.Bullet;
import data.BaseData;

public class BulletThread implements Runnable {

	private List<Bullet> bulletList;

	public BulletThread(BaseData data) {
		bulletList = data.getBulletList();
	}

	public void run() {
		try {
			while (true) {
				for (int i = 0; i < bulletList.size(); i++) {
					Bullet bullet = bulletList.get(i);
					if (bullet != null) {
						if (bullet.isDone()) {
							bulletList.remove(i);
							i--;
							continue;
						}
						bullet.move();
						if (bullet.getType() == 2) {
							bullet.setCanMove(false);
						}
					}
				}
				Thread.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
