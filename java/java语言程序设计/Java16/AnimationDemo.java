/*
 * 需求：编写一个会移动的字符串
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimationDemo extends JFrame
{
	public AnimationDemo()
	{
		//Create a MovingMessagePanel for displaying a moving message
		add(new MovingMessagePanel("Message moving?"));
	}

	/* Main method */
	public static void main(String[] args)
	{
		AnimationDemo frame = new AnimationDemo();
		frame.setTitle("AnimationDemo");
		frame.setSize(280, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	//Inner class:Displaying a moving message
	static class MovingMessagePanel extends JPanel
	{
		private String message = "Welcom to Java";
		private int xCoordinate = 0;
		private int yCoordinate = 20;

		public MovingMessagePanel(String message)
		{
			this.message = message;

			//Create a timer
			Timer timer = new Timer(200, new TimerListener());
			timer.start();
		}

		/* Paint message */
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			if(xCoordinate > getWidth())
			{
				xCoordinate = -20;
			}
			xCoordinate += 5;
			g.drawString(message, xCoordinate, yCoordinate);
		}

		class TimerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		}
	}
}
