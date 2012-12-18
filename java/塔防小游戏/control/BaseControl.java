package control;

import thread.FighterThread;
import ui.BaseFrame;
import data.BaseData;

public class BaseControl {

	public BaseControl() {
		BaseData baseData = new BaseData();
		BaseFrame baseFrame = new BaseFrame(baseData);
		Thread fighterThread = new Thread(new FighterThread(baseData));
		fighterThread.start();
	}

	public static void main(String[] args) {
		new BaseControl();
	}

}
