/*
 * 需求：使用鼠标在面板上移动消息
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoveMessageDemo extends JFrame
{
	public MoveMessageDemo()
	{
		//Create a MovableMessagePanel instance for moving a message
		MovableMessagePanel p = new MovableMessagePanel("Welcome to Java");

		//Place the message panel in the frame
		setLayout(new BorderLayout());
		add(p);
	}

	/*Main method*/
	public static void main(String[] args)
	{
		JFrame frame = new MoveMessageDemo();
		frame.setTitle("MoveMessageDemo");
		frame.setSize(200, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/* Inner class: MovableMessagePanel draws a message */
	static class MovableMessagePanel extends JPanel
	{
		private String message = "Welcome to Java";
		private int x = 20;
		private int y = 20;

		/* Construct a panel to draw string s */
		public MovableMessagePanel(String s)
		{
			message = s;
			addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent e) {
					  x = e.getX();
					  y = e.getY();
					  repaint();
					}
				});
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawString(message, x, y);
		}
	}
}
