/**
  * 需求：编写一个程序，在面板内的任意位置显示一个气球，使用向左向右键确定枪的方向，向上键可以发射子弹，一旦击中
  * 	  气球，就显示气球碎片，然后在新的位置显示一个新的气球。如果没有击中气球，当子弹到达边界的时候，子弹消失，
  * 	  然后可以发射新的子弹。
  * 步骤：1.创建一个随机产生的气球
  *       2.绘制一条线段作为枪，并且当按下向左向右键时，触发按键事件
  *       3.处理事件：当按下向左或向右键时，枪向左想向右移动5度。当按下向上键时，发射一颗子弹，子弹每过一段时间向前
  *       移动一段距离。且当子弹到达边界或击中气球时，才可以发射下一颗子弹。
  *	  4.当子弹遇到气球时，气球破碎。
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ShootBallonGame extends JFrame
{
	private ShootBallonGamePanel shootBallonGamePanel = new ShootBallonGamePanel();

	public ShootBallonGame()
	{
		add(shootBallonGamePanel, BorderLayout.CENTER);
		shootBallonGamePanel.setFocusable(true);
	}

	public static void main(String[] args)
	{
		ShootBallonGame frame = new ShootBallonGame();
		frame.setTitle("Shoot Ballon Game");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class ShootBallonGamePanel extends JPanel
	{
		private int radius = 8;
		private Random random = new Random();
		private int xCenter;
		private int yCenter;
		private int lineLength = 15;
		private int xEnd;
		private int yEnd;
		private int pressNumCount = 0; //-18到18之间
		private boolean isShoot = false;
		private boolean reShoot = false;
		private int xBallCenter;
		private int yBallCenter;
		private int circleLength = 15;
		private int circleCount;
		Timer timer = new Timer(10, new TimerListener());

		public ShootBallonGamePanel()
		{
			xCenter = (int)(10 + random.nextDouble() * (getWidth() - 10));
                 	yCenter = (int)(random.nextDouble() * (int)((getHeight() * 9) / 10));
			xEnd = getWidth() / 2;
			yEnd = getHeight() - lineLength;

			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e)
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_LEFT:
							if(pressNumCount < 18)
							{
								pressNumCount++;
							}
							if(pressNumCount >= 0)
							{
								xEnd = getWidth() / 2 - 
								(int)(lineLength * Math.sin(pressNumCount * (2 * Math.PI / 72)));
								yEnd = getHeight() - 
								(int)(lineLength * Math.cos(pressNumCount * (2 * Math.PI / 72)));
							}
							else
							{
								xEnd = getWidth() / 2 + (int)(lineLength *
								Math.sin(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
								yEnd = getHeight() - (int)(lineLength *
								Math.cos(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
							}
							break;

						case KeyEvent.VK_RIGHT:
							if(pressNumCount > -18)
								pressNumCount--;
							if(pressNumCount >= 0)
							{
								xEnd = getWidth() / 2 - 
								(int)(lineLength * Math.sin(pressNumCount * (2 * Math.PI / 72)));
								yEnd = getHeight() - 
								(int)(lineLength * Math.cos(pressNumCount * (2 * Math.PI / 72)));
							}
							else
							{
								xEnd = getWidth() / 2 + (int)(lineLength *
								Math.sin(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
								yEnd = getHeight() - (int)(lineLength *
								Math.cos(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
							}
							break;

						case KeyEvent.VK_UP:
							circleCount = pressNumCount;
							reShoot = true;
							ShootBall shootBall = new ShootBall();
							timer.start();
							break;
					}
					
					repaint();
				}
			});	
			
		}

		public void createCircle()
		{
				xCenter = (int)(10 + random.nextDouble() * (getWidth() - 10));
                 		yCenter = (int)(random.nextDouble() * (int)((getHeight() * 9) / 10));
				repaint();
		}

		protected void paintComponent(Graphics g)	
		{
			super.paintComponent(g);

			g.drawLine(getWidth() / 2, getHeight(), xEnd, yEnd);
			g.setColor(Color.RED);
			g.fillOval(xCenter, yCenter, 2 * radius, 2 * radius);

			if(isShoot)
			{
				if(yBallCenter >= -5 && xBallCenter >= -5 && xBallCenter < getWidth() + 5)	
				{
					circleLength += 1;
					reShoot = false;

					if(circleCount >= 0)
					{
						xBallCenter = getWidth() / 2 - 
						(int)(circleLength * Math.sin(circleCount * (2 * Math.PI / 72)));
						yBallCenter = getHeight() - 
						(int)(circleLength * Math.cos(circleCount * (2 * Math.PI / 72)));
						
						//如果子弹到达气球范围内，气球破碎，重新生成新的气球
						if(((xBallCenter >= xCenter - radius) && (xBallCenter <= xCenter + radius)) &&
						    ((yBallCenter >= yCenter - radius) && (yBallCenter <= yCenter + radius)))
						{
							g.fillOval(xCenter+4, yCenter+4, radius, radius );
							g.fillOval(xCenter-5, yCenter-5, radius, radius );
							g.fillOval(xCenter-5, yCenter+6, radius, radius );
							g.fillOval(xCenter+4, yCenter-5, radius, radius );
							createCircle();
						}
					}
					else
					{
						xBallCenter = getWidth() / 2 + (int)(circleLength *
						Math.sin(Math.abs(circleCount) * (2 * Math.PI / 72)));
						yBallCenter = getHeight() - (int)(circleLength *
						Math.cos(Math.abs(circleCount) * (2 * Math.PI / 72)));

						if(((xBallCenter >= xCenter - radius) && (xBallCenter <= xCenter + radius)) &&
						    ((yBallCenter >= yCenter - radius) && (yBallCenter <= yCenter + radius)))
						{
							g.fillOval(xCenter+4, yCenter+4, radius, radius);
							g.fillOval(xCenter-5, yCenter-5, radius, radius);
							g.fillOval(xCenter-5, yCenter+6, radius, radius);
							g.fillOval(xCenter+4, yCenter-5, radius, radius);
							createCircle();
						}
					}
				}
				else
				{
					if(reShoot)
					{
						if(pressNumCount >= 0)
						{
							xBallCenter = getWidth() / 2 - 
							(int)(lineLength * Math.sin(pressNumCount * (2 * Math.PI / 72)));
							yBallCenter = getHeight() - 
							(int)(lineLength * Math.cos(pressNumCount * (2 * Math.PI / 72)));
						}
						else
						{
							xBallCenter = getWidth() / 2 + (int)(lineLength *
							Math.sin(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
							yBallCenter = getHeight() - (int)(lineLength *
							Math.cos(Math.abs(pressNumCount) * (2 * Math.PI / 72)));
						}

						circleLength = 15;
					}
				}
				g.setColor(Color.BLACK);
				g.fillOval(xBallCenter - 5, yBallCenter - 5, 10, 10);
			}	
		}

		class TimerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		}

		class ShootBall
		{
			public ShootBall()
			{
				isShoot = true;
				repaint();
			}
		}

	}
}
