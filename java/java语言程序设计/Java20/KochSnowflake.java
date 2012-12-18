import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class KochSnowflake extends JApplet
{
	private JTextField jtfOrder = new JTextField("0", 5);
	private KochSnowflakePanel kochSnowflakePanel = new KochSnowflakePanel();

	public KochSnowflake()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter an order"));
		panel.add(jtfOrder);
		jtfOrder.setHorizontalAlignment(SwingConstants.RIGHT);


		// Add a Koch snowflake panel to the applet
		add(kochSnowflakePanel);
		add(panel, BorderLayout.SOUTH);

		// Register a listener
		jtfOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				kochSnowflakePanel.setOrder(Integer.parseInt(jtfOrder.getText()));
			}
		});
	}

	static class KochSnowflakePanel extends JPanel
	{
		private int order = 0;

		public void setOrder(int order)
		{
			this.order = order;
			repaint();
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			Point p1 = new Point(getWidth() / 2, 10);
			Point p2 = new Point(10, getHeight() - 10);
			Point p3 = new Point(getWidth() - 10, getHeight() - 10);

			displayKochSnowflakes(g, order, p1, p2);
			displayKochSnowflakes(g, order, p1, p3);
			displayKochSnowflakes(g, order, p2, p3);
		}

		private static void displayKochSnowflakes(Graphics g, int order, Point p1, Point p2)
		{
			if(order >= 0)
			{
				g.drawLine(p1.x, p1.y, p2.x, p2.y);

				Point pm1 = headThirdPoint(p1, p2);
				Point pm2 = tailThirdPoint(p1, p2);
				Point equilateralPoint = equilateralTrianglePoint(pm1, pm2);

				displayKochSnowflakes(g, order - 1, p1, pm1);
				displayKochSnowflakes(g, order - 1, pm1, equilateralPoint);
				displayKochSnowflakes(g, order - 1, equilateralPoint, pm2);
				displayKochSnowflakes(g, order - 1, pm2, p2);
			}
		}

		private static Point headThirdPoint(Point p1, Point p2)
		{
			if((p2.y - p1.y) * (p2.x - p1.x) > 0)
				return new Point((p1.x + p2.x) / 3, (p1.y + p2.y) / 3);
			else
				return new Point((p1.x + p2.x) /3, 2 * (p1.y + p2.y) / 3);
		}

		private static Point tailThirdPoint(Point p1, Point p2)
		{
			if((p2.y - p1.y) * (p2.x - p1.x) > 0)
				return new Point(2 * (p1.x + p2.x) / 3, 2 * (p1.y + p2.y) / 3);
			else
				return new Point(2 * (p1.x + p2.x) / 3, (p1.y + p2.y) / 3);
		}

		private static Point equilateralTrianglePoint(Point pm1, Point pm2)
		{
			return new Point((int)(pm1.x + Math.sqrt(Math.pow((double)(pm2.x - pm1.x), 2.0) +
				Math.pow((double)(pm2.y - pm1.y), 2.0)) * Math.cos(Math.atan((pm2.y - pm1.y) /
				(pm2.x - pm1.x)) + 60 * Math.PI / 180)),
				(int)(pm1.y + Math.sqrt(Math.pow((double)(pm2.x - pm1.x), 2.0) +
				Math.pow((double)(pm2.y - pm1.y), 2.0)) * Math.sin(Math.atan((pm2.y - pm1.y) /
				(pm2.x - pm1.x)) + 60 * Math.PI / 180)));
		}
	}
}
