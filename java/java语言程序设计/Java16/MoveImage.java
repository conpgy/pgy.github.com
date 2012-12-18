/*
 * 需求：使用鼠标在面板上移动消息
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoveImage extends JFrame
{
	public MoveImage()
	{
		//Create a MovableMessagePanel instance for moving a message
		MovableImagePanel p = new MovableImagePanel();

		//Place the message panel in the frame
		setLayout(new BorderLayout());
		add(p);
	}

	/*Main method*/
	public static void main(String[] args)
	{
		JFrame frame = new MoveImage();
		frame.setTitle("MoveMessageDemo");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/* Inner class: MovableMessagePanel draws a message */
	static class MovableImagePanel extends JPanel
	{
		private ImageIcon imageIcon = new ImageIcon("image/china.gif");
		private Image image = imageIcon.getImage();
		private int xCoordinate = 0;
		private int yCoordinate = getHeight();


		/* Construct a panel to draw string s */
		public MovableImagePanel()
		{
			addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent e) {
					  xCoordinate = e.getX();
					  yCoordinate = e.getY();
					  repaint();
					}
				});

			//Create a timer
			Timer timer = new Timer(10, new TimerListener());
			timer.start();
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			if(yCoordinate <= 0)
				yCoordinate = getHeight() + 20;
			yCoordinate -= 1;

			if(image != null)
				g.drawImage(image, xCoordinate, yCoordinate, 140, 75, this);
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
