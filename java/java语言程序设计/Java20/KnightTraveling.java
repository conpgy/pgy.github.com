import java.awt.*;
import javax.swing.*;

public class KnightTraveling extends JApplet
{
	public static final int SIZE = 8;
	public static int[][] array = {
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
	};
	public int row;
	public int column;

	public KnightTraveling()
	{
		Point startPoint = new Point((5 * (getWidth() / SIZE)) / 2, (7 * (getHeight() / SIZE)) / 2);
//		traveled(startPoint);
		search(startPoint);
		add(new ChessBoard(), BorderLayout.CENTER);
	}

	private boolean search(Point point)
	{
		if(!isTravelse())
		{
			if(((point.x - 2 * getWidth() / SIZE) > 0) && ((point.y - getHeight() / SIZE) > 0))
			{
				Point point1 = new Point(point.x - 2 * getWidth() / SIZE, point.y - getWidth() / SIZE);
				if(hasTraveled(point1))
					return false;
				traveled(point1);
				search(point1);
			}
			if(((point.x - 2 * getWidth() / SIZE) > 0) && ((point.y + getHeight() / SIZE) < getHeight()))
			{
				Point point2 = new Point(point.x - 2 * getWidth() / SIZE, point.y + getHeight() / SIZE);
				if(hasTraveled(point2))
					return false;
				traveled(point2);
				search(point2);
			}
			if(((point.x - getWidth() / SIZE) > 0) && ((point.y - 2 * getHeight() / SIZE) > 0))
			{
				Point point3 = new Point(point.x - getWidth() / SIZE, point.y - 2 * getHeight() / SIZE);
				if(hasTraveled(point3))
					return false;
				traveled(point3);
				search(point3);
			}
			if(((point.x - getWidth() / SIZE) > 0) && ((point.y + 2 * getHeight() / SIZE) < getHeight()))
			{
				Point point4 = new Point(point.x - getWidth() / SIZE, point.y + 2 * getHeight() / SIZE);
				if(hasTraveled(point4))
					return false;
				traveled(point4);
				search(point4);
			}
			if(((point.x + 2 * getWidth() / SIZE) < getWidth()) && ((point.y - getHeight() / SIZE) > 0)) 
			{
				Point point5 = new Point(point.x + 2 * getWidth() / SIZE, point.y - getHeight() / SIZE);
				if(hasTraveled(point5))
					return false;
				traveled(point5);
				search(point5);
			}
			if(((point.x + 2 * getWidth() / SIZE) < getWidth()) && ((point.y + getHeight() / SIZE) < getHeight())) 
			{
				Point point6 = new Point(point.x + 2 * getWidth() / SIZE, point.y + getHeight() / SIZE);
				if(hasTraveled(point6))
					return false;
				traveled(point6);
				search(point6);
			}
			if(((point.x + getWidth() / SIZE) < getWidth()) && ((point.y - 2 * getHeight() / SIZE) > 0)) 
			{
				Point point7 = new Point(point.x + getWidth() / SIZE, point.y - 2 * getHeight() / SIZE);
				if(hasTraveled(point7))
					return false;
				traveled(point7);
				search(point7);
			}
			if(((point.x + getWidth() / SIZE) < getWidth()) && ((point.y + 2 * getHeight() / SIZE) < getHeight())) 
			{
				Point point8 = new Point(point.x + getWidth() / SIZE, point.y + 2 * getHeight() / SIZE);
				if(hasTraveled(point8))
					return false;
				traveled(point8);
				search(point8);
			}
			
			return false;
		}

		System.out.println("travelse");
		return true;
	}

	private void traveled(Point point)
	{
		row = point.x / (getWidth() / SIZE);
		column = point.y / (getHeight() / SIZE);
		array[row][column] = 1;
	}

	private boolean hasTraveled(Point point)
	{
		row = point.x / (getWidth() / SIZE);
		column = point.y / (getHeight() / SIZE);
		if(array[row][column] == 1)
			return true;
		else
			return false;
	}

	private boolean isTravelse()
	{
		for(int i = 0; i< 8; i++)
			for(int j = 0; j < 8; j++)
				if(array[i][j] != 1) 
					return false;

		return true;
	}

	class ChessBoard extends JPanel
	{
		public ChessBoard()
		{
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			// Draw the horizontal and vertical lines
			for(int i = 1; i < SIZE; i++)
			{
				g.drawLine(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE);
				g.drawLine(i * getWidth()/ SIZE, 0, i * getWidth() / SIZE, getHeight());
			}
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		KnightTraveling applet = new KnightTraveling();
		frame.add(applet, BorderLayout.CENTER);

		frame.setSize(400, 400);
		frame.setTitle("KnightTraveling");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
