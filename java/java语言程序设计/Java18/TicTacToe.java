import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame
{
	//Indicate which palyer has a turn; initially it is the X player
	private char whoseTurn = 'X';

	//Create and initialize cells
	private Cell[][] cells = new Cell[3][3];

	//Create and initialize a status label
	private JLabel jlblStatus = new JLabel("X's turn to play");

	/** public TicTacToe */
	public TicTacToe()
	{
		//Panel p to hold cells
		JPanel p = new JPanel(new GridLayout(3, 3, 0, 0));
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				p.add(cells[i][j] = new Cell());

		//Set line borders on the cells panel and the status label
		p.setBorder(new LineBorder(Color.red, 1));
		jlblStatus.setBorder(new LineBorder(Color.yellow, 1));

		//Place the panel and label to the applet
		add(p, BorderLayout.CENTER);
		add(jlblStatus, BorderLayout.SOUTH);
	}

	/** Determine whether the cells are all occupied */
	public boolean isFull()
	{
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				if(cells[i][j].getToken() == ' ')
					return false;
		return true;
	}

	/** Determine whether the player with the specified token wins */
	public boolean isWon(char token)
	{
		for(int i = 0; i < 3; i++)
		{
			if((cells[i][0].getToken() == token) && (cells[i][1].getToken() == token) 
					&& (cells[i][2].getToken() == token))
				return true;
		}

		for(int j = 0; j < 3; j++)
		{
			if((cells[0][j].getToken() == token) && (cells[1][j].getToken() == token) 
					&& (cells[2][j].getToken() == token))
				return true;
		}
		
		if((cells[0][0].getToken() == token) && (cells[1][1].getToken() == token) 
				&& (cells[2][2].getToken() == token))
			return true;

		if((cells[0][2].getToken() == token) && (cells[1][1].getToken() == token)
				&& (cells[2][0].getToken() == token))
			return true;

		return false;
	}

	//An inner class for a cell
	public class Cell extends JPanel
	{
		//Token used for this cell;
		private char token = ' ';

		public Cell()
		{
			setBorder(new LineBorder(Color.black, 1));
			addMouseListener(new MyMouseListener());
		}

		/** Return token */
		public char getToken()
		{
			return token;
		}

		/** Set a new token */
		public void setToken(char c)
		{
			token = c;
			repaint();
		}

		/** Paint the cell */
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			if(token == 'X')
			{
				g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
				g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
			}
			else if(token == 'O')
			{
				g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
			}
		}

		private class MyMouseListener extends MouseAdapter
		{
			/** Handle mouse click on a cell */
			public void mouseClicked(MouseEvent e)
			{
				//If cell is empty and game is not over
				if(token == ' ' && whoseTurn != ' ')
					setToken(whoseTurn);

				//Check game status
				if(isWon(whoseTurn))
				{
					jlblStatus.setText(whoseTurn + " won! The game is over");
					whoseTurn = ' ';
				}
				else if(isFull())
				{
					jlblStatus.setText("Draw! The game is over");
					whoseTurn = ' ';
				}
				else
				{
					//Change the turn
					whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
					//Display Whose turn
					jlblStatus.setText(whoseTurn + "'s turn");
				}
			}
		}
	}

	public static void main(String[] args)
	{
		TicTacToe frame = new TicTacToe();
		frame.setTitle("TicTacToe game");
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
