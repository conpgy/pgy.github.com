import java.awt.*;
import javax.swing.*;

public class EightQueens extends JApplet
{
	public static final int SIZE = 8; // The size of the chessboard
	private int[] queens = new int[SIZE];

	public EightQueens()
	{
		search(0);
		add(new ChessBoard(), BorderLayout.CENTER);
	}

	/** Check if a queen can be placed at row i and column j */
	private boolean isValid(int row, int column)
	{
		for(int i = 1; i <= row; i++)
		{
			if(queens[row - i] == column || queens[row - i] == column - i || queens[row - i] == column + i)
				return false;
		}
		return true;
	}

	/** Search for a solution starting from a specified row */
	private boolean search(int row)
	{
		if(row == SIZE)
			return true;

		for(int column = 0; column < SIZE; column++)
		{
			queens[row] = column;
			if(isValid(row, column) && search(row + 1))
				return true;
		}

		// No solutionfor a queen placed at any column of this row
		return false;
	}

	class ChessBoard extends JPanel
	{
		private Image queenImage = new ImageIcon("../image/queen.jpg").getImage();

		ChessBoard()
		{
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			//Paint the queens
			for(int i = 0; i < SIZE; i++)
			{
				int j = queens[i];
				g.drawImage(queenImage, j * getWidth() / SIZE, i * getHeight() / SIZE, getWidth() / SIZE,
						getHeight() / SIZE, this);
			}

			//Draw the horizontal and vertical lines
			for(int i = 1; i < SIZE; i++)
			{
				g.drawLine(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE);
				g.drawLine(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight());
			}
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		EightQueens applet = new EightQueens();
		frame.add(applet, BorderLayout.CENTER);

		frame.setSize(400, 400);
		frame.setTitle("EightQueens");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
