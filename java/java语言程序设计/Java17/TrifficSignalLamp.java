/**
  * 需求: 编写一个交通信号灯程序，用户从红、黄、绿三种灯中选择一种，当选择一种灯后，相应的灯被打开，
  *       一次只能亮一种灯，程序开始时所有的灯都不亮。
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrifficSignalLamp extends JFrame
{
	private JRadioButton jrbRedLamp = new JRadioButton("Red");
	private JRadioButton jrbYellowLamp = new JRadioButton("Yellow");
	private JRadioButton jrbGreenLamp = new JRadioButton("Green");

	//创建一个绘制信号灯的面板
	private SignalLampPanel signalLampPanel = new SignalLampPanel();

	public TrifficSignalLamp()
	{
		//创建一个面板，将三个单选按钮添加到面板中
		JPanel jpRadioButtons = new JPanel();
		jpRadioButtons.setLayout(new GridLayout(1, 3));
		jpRadioButtons.add(jrbRedLamp);
		jpRadioButtons.add(jrbYellowLamp);
		jpRadioButtons.add(jrbGreenLamp);
		add(jpRadioButtons, BorderLayout.SOUTH);
		
		//将信号灯面板添加到框架中
		add(signalLampPanel, BorderLayout.CENTER);

		//将三个按钮添加到一个组中
		ButtonGroup group = new ButtonGroup();
		group.add(jrbRedLamp);
		group.add(jrbYellowLamp);
		group.add(jrbGreenLamp);

		//设置三个按钮的热键
		jrbRedLamp.setMnemonic('R');
		jrbYellowLamp.setMnemonic('Y');
		jrbGreenLamp.setMnemonic('G');

		//Register listeners for radio buttons
		jrbRedLamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				signalLampPanel.setType(signalLampPanel.REDLAMP);
			}
		});
		jrbYellowLamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				signalLampPanel.setType(signalLampPanel.YELLOWLAMP);
			}
		});
		jrbGreenLamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				signalLampPanel.setType(signalLampPanel.GREENLAMP);
			}
		});

	}

	public static void main(String[] args)
	{
		TrifficSignalLamp frame = new TrifficSignalLamp();
		frame.setTitle("Triffic singal lamp");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}	

	//Inner class SingalLampPanel
	class SignalLampPanel extends JPanel
	{
		private boolean filled = false;
		private int type = 0;

		private int radius = 10;

		public static final int REDLAMP = 1;
		public static final int YELLOWLAMP = 2;
		public static final int GREENLAMP = 3;

		protected void paintComponent(Graphics g)	
		{
			super.paintComponent(g);

			g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
			g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - 3* radius - 3, 2 * radius, 2 * radius);
			g.drawOval(getWidth() / 2 - radius, getHeight() / 2 + radius + 3, 2 * radius, 2 * radius);
			g.drawRect(getWidth() / 2 - 14, getHeight() / 2 - 50, 28, 100);

			switch(type)
			{
				case REDLAMP:
					g.setColor(Color.RED);
					g.fillOval(getWidth() / 2 - radius, getHeight() / 2
							- 3* radius - 3, 2 * radius, 2 * radius);
					break;

				case YELLOWLAMP:
					g.setColor(Color.YELLOW);
					g.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
					break;

				case GREENLAMP:
					g.setColor(Color.GREEN);
					g.fillOval(getWidth() / 2 - radius, getHeight() / 2 + radius + 3, 2 * radius, 2 * radius);
					break;
			}
		}

		public void setType(int type)
		{
			this.type = type;
			repaint();
		}

	}
}
