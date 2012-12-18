import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ButtonDemo extends JFrame
{
	//Create a panel for displaying message
	protected MessagePanel messagePanel = new MessagePanel("Welcome to Java");

	//Declare two buttons to move the message left and right
	private JButton jbtLeft = new JButton("<=");
	private JButton jbtRight = new JButton("=>");

	public static void main(String[] args)
	{
		JFrame frame = new ButtonDemo();
		frame.setTitle("ButtonDemo");
		frame.setSize(250, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public ButtonDemo()
	{
		//Set the background color of messagePanel
		messagePanel.setBackground(Color.white);

		//Create Panel jpButtons to hold two Buttons "<=" and "=>"
		JPanel jpButtons = new JPanel();
		jpButtons.add(jbtLeft);
		jpButtons.add(jbtRight);

		//Set keyboard mnemonics
		jbtLeft.setMnemonic('L');
		jbtRight.setMnemonic('R');

		//Set icons and remove text
		jbtLeft.setIcon(new ImageIcon("image/left.gif"));
		jbtRight.setIcon(new ImageIcon("image/right.gif"));
		jbtLeft.setText("Left");
		jbtRight.setText("Right");
		
		//Set tool tip text on the buttons
		jbtLeft.setToolTipText("Moving message to left");
		jbtRight.setToolTipText("Moving message to right");

		//Place panels in the frame
		setLayout(new BorderLayout());
		add(messagePanel, BorderLayout.CENTER);
		add(jpButtons, BorderLayout.SOUTH);

		//Register listeners with the buttons
		jbtLeft.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
					messagePanel.moveLeft();
			  	  }
				});
		jbtRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
				  messagePanel.moveRight();
				}
			});
	}
}
